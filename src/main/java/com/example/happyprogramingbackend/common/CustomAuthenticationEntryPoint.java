package com.example.happyprogramingbackend.common;

import com.example.happyprogramingbackend.Exception.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

// Xử lý trong trường hợp chưa đăng nhập mà cố tình truy cập authenticated api
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // Tạo đối tượng
        ErrorMessage error = new ErrorMessage(HttpStatus.UNAUTHORIZED, "You must log in first");

        // Set thuộc tính cho response
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // convert object sang json
        OutputStream responseStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(responseStream, error);
        responseStream.flush();
    }
}
