package com.demo.spring.security.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Order (0)
//@Configuration
@Log4j2
public class StringEncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        //region Request Wrapper
        RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) request);
        //endregion

        filterChain.doFilter(requestWrapper, response);
    }
}
