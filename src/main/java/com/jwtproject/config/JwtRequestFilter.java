package com.jwtproject.config;

import com.jwtproject.auth.model.Vendor;
import com.jwtproject.auth.service.VendorServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter  extends OncePerRequestFilter {

    private VendorServiceImpl vendorService;

    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    public JwtRequestFilter(VendorServiceImpl vendorService, JwtTokenUtils jwtTokenUtils) {
        this.vendorService = vendorService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
//            jwtToken =requestTokenHeader.substring(7);
            jwtToken = requestTokenHeader.replace("Bearer ", "");
            try {
                username = jwtTokenUtils.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e){
                System.out.println("Enable to get JWT token");
            } catch (ExpiredJwtException e){
                System.out.println("Jwt token has expired");
            }
        } else{
            logger.warn("JWT token does not begin with bearer string");
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.vendorService.loadUserByUsername(username);

            if (jwtTokenUtils.validateToken(jwtToken, (Vendor) userDetails)){

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
