package com.nbkarthi.auth_jwt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbkarthi.auth_jwt.model.FilterError;
import com.nbkarthi.auth_jwt.service.SignatureService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class IssueServerToken extends OncePerRequestFilter {

    @Autowired
    private SignatureService signatureService;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpReq =   servletRequest;

        byte[] body = StreamUtils.copyToByteArray(servletRequest.getInputStream());

        Map<String, Object> jsonRequest = new ObjectMapper().readValue(body, Map.class);


        var key = jsonRequest.get("signature").toString();
        var msg = jsonRequest.get("message").toString();
        System.out.println(key);
        System.out.println(msg);

        try {
            if (key==null){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);//when exception appear in filter, construct error message
            }else{
                if(!signatureService.isValid(msg, key)){
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }else{
                    System.out.println("okkkkkkkkkkkkkkkkkkkkkkk");
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        }catch (ResponseStatusException exception){
            setErrorResponse(exception.getStatus(), servletResponse, exception.getMessage());
        }

        filterChain.doFilter(servletRequest, servletResponse);
        return;
    }

    public void setErrorResponse(HttpStatus httpStatus, ServletResponse response, String message) throws IOException {
        FilterError filterError = new FilterError();
        filterError.setErrorDate(new Date());
        filterError.setMessage(message);
        filterError.setStatusCode(String.valueOf(httpStatus.value()));

        String json = filterError.convertToJson();
        response.getWriter().write(json);
    }
}
