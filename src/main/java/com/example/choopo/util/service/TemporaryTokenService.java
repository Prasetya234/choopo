package com.example.choopo.util.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.User;
import com.example.choopo.repository.UserRepository;
import com.example.choopo.util.model.AunthenticationRequest;
import com.example.choopo.util.model.TemporaryToken;
import com.example.choopo.util.repository.TemporaryTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class TemporaryTokenService implements UserDetailsService,TemporaryTokenImpl {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TemporaryTokenRepository temporaryTokenRepository;

   @Autowired
   private TemporaryTokenService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("USER TIDAK DITEMUKAN");
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswordEncoder(), new ArrayList<>());
        }
    }

    @Override
    public User register(User userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null) {
            User newUser = new User();
            newUser.setUserType(userDTO.getUserType());
            newUser.setUsername(userDTO.getUsername());
            newUser.setUserCode(userDTO.getUserCode());
            newUser.setUserStatus(userDTO.getUserStatus());
            newUser.setPassword(userDTO.getPassword());
            newUser.setPasswordEncoder(passwordEncoder.encode(newUser.getPassword()));
            return userRepository.save(newUser);
        }
        throw new RuntimeException("USER ALREADY EXIST");
    }

    @Override
    public TemporaryToken login(HttpServletRequest request) throws Exception {
        String autorizationHeaderUsername = request.getHeader("username");
        String autorizationHeaderPassword = request.getHeader("password");

        String username;
        String password;

        if(autorizationHeaderUsername != null && autorizationHeaderPassword != null) {
            username = autorizationHeaderUsername;
            password = autorizationHeaderPassword;

            User validateLogin = userRepository.login(username, password);
            if (validateLogin == null) {
                throw new Exception("USERNAME OR PASSWORD NOT EMPTY");
            }
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails == null) {
                throw new Exception("USERNAME AND PASSWORD NO FOUND");
            }
            String token = UUID.randomUUID().toString();
            TemporaryToken temporaryToken = new TemporaryToken();
            temporaryToken.setToken(String.valueOf(token));
            temporaryToken.setExpiredDate(new Date(System.currentTimeMillis() + 900000));
            temporaryToken.setUser(username);
            TemporaryToken temporaryToken1 = temporaryTokenRepository.cekUser(temporaryToken.getUser());
            if (temporaryToken1 != null) {
                temporaryTokenRepository.deleteById(temporaryToken1.getIdToken());
                return temporaryTokenRepository.save(temporaryToken);
            }
            return temporaryTokenRepository.save(temporaryToken);
        }
        throw new Exception("NOT VALIDATED");
    }

    @Override
    public List<TemporaryToken> getTokenList() {
        return temporaryTokenRepository.findAll();
    }
}
