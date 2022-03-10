package com.example.demo.configuration;

import com.example.demo.persistence.IUtilisateursRepo;
import com.example.demo.persistence.UtilisateursRepo;
import io.jsonwebtoken.Jwts;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.EntityManagerFactory;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtUtil;
    private final IUtilisateursRepo repo;

    @Autowired
    public JwtTokenFilter(JwtTokenUtil jwtUtil, EntityManagerFactory factory) {
        this.jwtUtil = jwtUtil;
        this.repo = new UtilisateursRepo(factory.unwrap(SessionFactory.class));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();
        if (!jwtUtil.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        var userDetails = repo.getUtilisateur(token);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.map(UserDetails::getAuthorities).orElse(List.of())
        );

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
