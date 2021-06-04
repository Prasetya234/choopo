package com.example.choopo.util.filter;

import com.example.choopo.repository.UserRepository;
import com.example.choopo.util.model.TemporaryToken;
import com.example.choopo.util.repository.TemporaryTokenRepository;
import com.example.choopo.util.service.TemporaryTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class TokenRequestFilter extends OncePerRequestFilter{

    @Autowired
    private TemporaryTokenService temporaryTokenService;

    @Autowired
    private TemporaryTokenRepository temporaryTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String autorizationHeader = request.getHeader("Authorization");

        String username = null;
        String token = null;

        if (autorizationHeader != null) {
            token = autorizationHeader.substring(0);
            TemporaryToken temporaryToken = temporaryTokenRepository.cekToken(token);
            try {
                username = temporaryToken.getUser();
            } catch (Exception e) {
                System.out.println("USER TIDAK DIKETAHUI");
            }
        } else {
            logger.warn("MASUKAN TOKEN JWT TERLEBIH DAHULU");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.temporaryTokenService.loadUserByUsername(username);

            if (temporaryTokenRepository.checkValidateToken(token, new Date()) != null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                TemporaryToken getToken = temporaryTokenRepository.cekToken(token);
                getToken.setExpiredDate(new Date(System.currentTimeMillis() + 900000));
                temporaryTokenRepository.save(getToken);
            }
        }

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
        response.addHeader("Access-Control-Allow-Credentials", "true");

        filterChain.doFilter(request, response);
    }
}
