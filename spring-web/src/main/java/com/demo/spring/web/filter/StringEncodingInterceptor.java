package com.demo.spring.web.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class StringEncodingInterceptor extends WebRequestHandlerInterceptorAdapter {

    public StringEncodingInterceptor(WebRequestInterceptor requestInterceptor) {
        super(requestInterceptor);
        log.info(requestInterceptor);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");
//        request.getReader().lines().forEach(log::error);

//        ServletInputStream inputStream = request.getInputStream();
//        String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
//        log.info(body);

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
        //super.postHandle(request, response, handler, modelAndView);
    }
}
