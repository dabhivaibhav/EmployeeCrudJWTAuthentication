package com.example.employeecrud.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.employeecrud.config.MyUserDetailService;
import com.example.employeecrud.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	final String AUTHORIZATION_HEADER = "Authorization";
	final String AUTHORIZATION_HEADER_VALUE = "Bearer ";
	final int AUTHORIZATION_HEADER_SUBSTRING = 7;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private MyUserDetailService myUserDetailService;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		final String authHeader = request.getHeader(AUTHORIZATION_HEADER);
		final String jwtToken;
		final String userEmail;
		if (authHeader == null || !authHeader.contains(AUTHORIZATION_HEADER_VALUE)) {
			filterChain.doFilter(request, response);
			return;
		}
		jwtToken = authHeader.substring(AUTHORIZATION_HEADER_SUBSTRING);
		userEmail = jwtService.extractUserName(jwtToken);

		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = myUserDetailService.loadUserByUsername(userEmail);
			if (jwtService.isTokenValid(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);

	}

}
