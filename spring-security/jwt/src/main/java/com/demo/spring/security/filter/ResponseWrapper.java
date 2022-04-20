package com.demo.spring.security.filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.PrintWriter;

public class ResponseWrapper extends HttpServletResponseWrapper {

    private final CharArrayWriter writer;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        writer = new CharArrayWriter();
    }

    @Override
    public PrintWriter getWriter() {
        return new PrintWriter(writer);
    }

    @Override
    public String toString() {
        return writer.toString();
    }
}
