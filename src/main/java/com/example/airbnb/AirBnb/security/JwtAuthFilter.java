package com.example.airbnb.AirBnb.security;

import com.example.airbnb.AirBnb.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        System.out.println("JWT token = " + token);
        String subject = jwtService.extractSubject(token);

        if(!jwtService.isValidToken(token, subject)) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("JWT token VALID");
        Claims claims = jwtService.extractAllClaims(token);

        System.out.println("JWT claims = " + claims);

        Long userId = claims.get("userId", Long.class);
        String role = claims.get("role", String.class);
        String phone = claims.getSubject();

        UserPrincipal userPrincipal = new UserPrincipal(
                userId,
                phone,
                null,
                List.of(new SimpleGrantedAuthority("Role_" + role))
        );

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userPrincipal,
                null,
                userPrincipal.getAuthorities()
        );

        System.out.println("Creating authorities for role: " + role);
        authenticationToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        System.out.println("Authentication SET for userId=" + userId);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
