/*
package com.springBoot.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springBoot.response.ApiResponse;
import com.springBoot.service.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


*/
/**
 * Created by Anita on 24-09-2017.
 *//*

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthorizationFIlter implements Filter{

    private static  boolean CONDITION = true;

    @Autowired
    TokenProvider tokenProvider;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String jwtToken =  httpServletRequest.getHeader("Authorization");
            CONDITION = tokenProvider.validateToken(jwtToken);
            if(CONDITION==true)
                chain.doFilter(request,response);
            else{
                ((HttpServletResponse)response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setErrorMessage("Unauthorised Access !");
                apiResponse.setErrorCode(HttpStatus.UNAUTHORIZED);
                String responseString = convertObjectToJson(apiResponse);
                System.out.println("===========================");
                System.out.println("*****************************");
                System.out.println("*****************************");
                System.out.println("*****************************");
                System.out.println(responseString);
                ((HttpServletResponse)response).setContentType("application/json");
                PrintWriter out = ((HttpServletResponse)response).getWriter();
                out.print(responseString);
                out.flush();
            }

        }catch(Exception e){
            ((HttpServletResponse)response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public void destroy() {

    }

    public  String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}*/
