package com.example.SampleWebapp.config;

import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class StatusReportingFilter extends OncePerRequestFilter {

    private static String instantiationStatus = "Filter Not Instantiated";

    /* Default constructor must to be present to allow filter to work */
    public StatusReportingFilter() {
    }

    public StatusReportingFilter(String instantiated) {
        StatusReportingFilter.instantiationStatus = instantiated;
        MyHelper.log("Filter Status:  " + instantiationStatus);
    }

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        try {
            chain.doFilter(requestWrapper, responseWrapper);
            logCall(requestWrapper, responseWrapper);
        } catch (Exception e) {
            responseWrapper.setStatus(500);
            logCall(requestWrapper, responseWrapper);
            throw e;
        } finally {
            /* Body must be copied back to response otherwise response sent to client is empty */
            responseWrapper.copyBodyToResponse();
        }
    }

    private void logCall(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper) throws IOException {
        MyHelper.log("Request URL     : " + requestWrapper.getRequestURI());
        MyHelper.log("Request Method  : " + requestWrapper.getMethod());
        MyHelper.log("Request Type    : " + requestWrapper.getContentType());
        MyHelper.log("Request Header  : " + new ServletServerHttpRequest(requestWrapper).getHeaders().toString());
        String requestBody = new String(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding());
        MyHelper.log("Request Body    \n: " + requestBody + "\n");

        MyHelper.log("Response Type   : " + responseWrapper.getContentType());
        MyHelper.log("Response Headers: " + new ServletServerHttpResponse(responseWrapper).getHeaders().toString());
        String responseBody = new String(responseWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding());
        MyHelper.log("Response Body   : \n" + responseBody);
        /* Response status code is sometimes 200 even though internal exception is thrown. Check for this explicitly */
        MyHelper.log("Response Code: " + responseWrapper.getStatusCode());

    }

}