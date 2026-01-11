package com.devremulk.web.audit;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuditUserFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuditUserFilter.class);
    private static final String USER_HEADER = "X-User";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String user = request.getHeader(USER_HEADER);
            if (user == null || user.isBlank()) {
                user = "system";
            }
            AuditContext.setCurrentUser(user);
            filterChain.doFilter(request, response);
        } finally {
            AuditContext.clear();
        }
    }
}
