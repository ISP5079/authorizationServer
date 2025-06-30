package com.isp.authorizationserver.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ClientIdCaptureFilter extends OncePerRequestFilter {

    private static final String CLIENT_ID_SESSION_ATTR = "client_id";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String clientId = request.getParameter(CLIENT_ID_SESSION_ATTR);
        if (clientId != null && !clientId.isEmpty()) {
            request.getSession().setAttribute(CLIENT_ID_SESSION_ATTR, clientId.trim());
        } else {
            clientId = (String) request.getSession().getAttribute(CLIENT_ID_SESSION_ATTR);
            if (clientId != null) {
                ClientIdContextHolder.setClientId(clientId);
            }
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            ClientIdContextHolder.clear(); // limpia al final de la petición
        }
    }

}
