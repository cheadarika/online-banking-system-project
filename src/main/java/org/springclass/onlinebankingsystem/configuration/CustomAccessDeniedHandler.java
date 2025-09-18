package org.springclass.onlinebankingsystem.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springclass.onlinebankingsystem.exception.CustomException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        System.out.println(">>>>>>>>> customAccessDeniedHandler");

        var exception = new CustomException(401, "UNAUTHORIZED");

        if (request.getAttribute("invalid") != null) {
            exception.setMessage("Unauthorized");
            exception.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);

        exception.setStatus(HttpServletResponse.SC_FORBIDDEN);
        exception.setMessage(accessDeniedException.getLocalizedMessage());

        var out = response.getOutputStream();
        new ObjectMapper().writeValue(out, exception);
        out.flush();
    }
}
