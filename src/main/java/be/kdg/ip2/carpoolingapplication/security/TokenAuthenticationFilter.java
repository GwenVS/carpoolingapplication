package be.kdg.ip2.carpoolingapplication.security;

import io.jsonwebtoken.Claims;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter class for token filtration
 *
 */

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LogManager.getLogger(TokenAuthenticationFilter.class);

    private TokenHelper tokenHelper;

    private UserDetailsService userDetailsService;

    public TokenAuthenticationFilter(TokenHelper tokenHelper, UserDetailsService userDetailsService) {
        this.tokenHelper = tokenHelper;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        String username;
        String authToken = tokenHelper.getToken(request);

        if (authToken != null) {
            // get username & claims from token
            username = tokenHelper.getUsernameFromToken(authToken);
            Claims claims = tokenHelper.getAllClaimsFromToken(authToken);
            if (username != null) {
                // load the user details
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (tokenHelper.validateToken(authToken, userDetails)) {
                    // create authentication
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                    authentication.setToken(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            request.setAttribute("username", username);
            request.setAttribute("claims", claims);
        }
        chain.doFilter(request, response);
    }

}