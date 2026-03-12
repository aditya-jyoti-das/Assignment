package com.example.ASSIGNMENT_RBAC_BOTMAKERS.jwtfilter;

import com.example.ASSIGNMENT_RBAC_BOTMAKERS.services.CustomUserDetailsService;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService customUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwt);
            } catch (RuntimeException e) {
                log.warn("ERROR OCCURRED DURING USERNAME EXTRACTION "+e.getMessage());
            }
        }

        if (username != null) {
            try {
                UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

                if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    SecurityContext context = SecurityContextHolder.getContext();
                    log.info("TOKEN VERIFIED SUCCESSFULLY");
                }

            } catch (UsernameNotFoundException e) {
                throw new BadCredentialsException(e.getMessage());
            } catch (RuntimeException e) {
                throw new BadCredentialsException("INVALID JWT TOKEN! LOGIN AGAIN" +e.getMessage());
            }
        }
        filterChain.doFilter(request, response);

    }
}
