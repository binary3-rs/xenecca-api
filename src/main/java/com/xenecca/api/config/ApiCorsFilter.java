package com.xenecca.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class ApiCorsFilter extends CorsFilter {

    public ApiCorsFilter(CorsConfigurationSource source) {
        super(source);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Headers",
                "Access-Control-Allow-Origin, Origin, Accept, X-Requested-With, Content-Type, " +
                        "Access-Control-Request-Method, auth-token,token, Access-Control-Request-Headers, Authorization");
        response.setHeader("Access-Control-Allow-Origin", "https://xenecca.com, https://admin.xenecca.com");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, HEAD, DELETE, OPTIONS");
        response.setHeader("Access-Control-Request-Headers", "Authorization, Content-Type");
        response.setHeader("Access-Control-Max-Age", "3600");
        /*
         response.setHeader("Access-Control-Allow-Headers", "token, content-type,
         accept, xsrf-token");
         response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
        if (response.getHeader("Access-Control-Allow-Origin") == null)
            response.addHeader("Access-Control-Allow-Origin", "*");

         */
        filterChain.doFilter(request, response);
    }

}
