package com.demo.spring.websocket;

import com.demo.spring.websocket.domain.Greeting;
import com.demo.spring.websocket.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GreetingController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(@Payload Greeting message) throws Exception {
        log.info(message);
        Thread.sleep(1000);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getContent()) + "!");
    }

    @MessageMapping("/secured/room/reply")
    @SendToUser("/secured/user/queue/reply")
    public Message reply(@Payload Greeting message, Principal principal,
                         @Header("simpSessionId") String sessionId) throws Exception {
        log.info(message + " - " + principal + " - " + sessionId);
        Thread.sleep(2000);
        return Message.builder()
                .author(message.getContent())
                .message("Hello..")
                .build();
    }

    @MessageMapping("/secured/room/send")
    public void send(@Payload Greeting message,
                     @Header("simpSessionId") String sessionId) {
        try {
            log.info(message + " - sessionId: " + sessionId);
            Thread.sleep(2000);
            messagingTemplate.convertAndSendToUser(
                    sessionId, "/secured/user/queue/sent",
                    Message.builder().author(message.getContent()).message("Send").build(),
                    new MessagePostProcessor() {
                        @Override
                        public org.springframework.messaging.Message<?> postProcessMessage(org.springframework.messaging.Message<?> message) {
                            System.out.println("message = " + message);
                            return message;
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @MessageExceptionHandler
    @SendToUser("/secured/user/queue/error")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
}
