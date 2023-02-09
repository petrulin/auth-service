package com.otus.authservice.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class RequestIdFilter extends OncePerRequestFilter {

    private static final String REQUEST_ID = "requestId";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestId = request.getHeader(REQUEST_ID);
        if (requestId == null && request.getRequestURI().contains("health")) {
            requestId = UUID.randomUUID().toString();
        }

        MDC.put(REQUEST_ID, requestId);

        try {
                filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
