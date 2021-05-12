import com.demo.spring.cloud.stream.producer.EventMessage;
import com.demo.spring.cloud.stream.producer.Publish;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.cloud.stream.test.binder.TestSupportBinderAutoConfiguration;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EnableBinding(Processor.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Publish.class)
@DirtiesContext
@SpringBootTest(classes = TestSupportBinderAutoConfiguration.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PublishTest {
    @Autowired
    private Processor pipe;
    @Autowired
    private MessageCollector messageCollector;

    @Test
    public void whenSendMessage_thenResponseShouldUpdateText() {
        pipe.input().send(MessageBuilder.withPayload(
                EventMessage.builder().message("This is my message").build())
                .build());
        Object payload = messageCollector.forChannel(pipe.output())
                .poll()
                .getPayload();

        assertEquals("[1]: This is my message", payload.toString());
    }
}