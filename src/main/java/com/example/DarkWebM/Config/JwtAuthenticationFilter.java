package com.example.DarkWebM.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private static final String BEARER_PREFIX = "Bearer ";
    private static final int BEARER_PREFIX_LENGTH = BEARER_PREFIX.length();

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (!isValidAuthHeader(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(BEARER_PREFIX_LENGTH);
            final String userEmail = jwtService.extractUsername(jwt);

            if (shouldAuthenticate(userEmail)) {
                authenticateUser(request, jwt, userEmail);
            }
        } catch (Exception ex) {
            logger.error("Authentication error", ex);
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    private boolean isValidAuthHeader(String authHeader) {
        return authHeader != null
                && authHeader.startsWith(BEARER_PREFIX)
                && authHeader.length() > BEARER_PREFIX_LENGTH;
    }

    private boolean shouldAuthenticate(String userEmail) {
        return userEmail != null
                && SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private void authenticateUser(HttpServletRequest request, String jwt, String userEmail) {
        UserDetails userDetails = loadUserDetails(userEmail);

        if (jwtService.isTokenValid(jwt, userDetails)) {
            setSecurityContextAuthentication(request, userDetails);
        }
    }

    private UserDetails loadUserDetails(String username) {
        try {
            return userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException ex) {
            logger.warn("User not found: {}", username);
            throw ex;
        }
    }

    private void setSecurityContextAuthentication(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        logger.debug("Authenticated user: {}", userDetails.getUsername());
    }
}