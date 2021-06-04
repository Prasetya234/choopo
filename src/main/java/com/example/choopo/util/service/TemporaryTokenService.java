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
    public TemporaryToken login(AunthenticationRequest aunthenticationRequest) throws ResourceNotFoundExceotion {
        try {
            User validateLogin = userRepository.login(aunthenticationRequest.getUsername(), aunthenticationRequest.getPassword());
            authenticate(aunthenticationRequest.getUsername(), aunthenticationRequest.getPassword());
            UserDetails userDetails = userDetailsService.loadUserByUsername(aunthenticationRequest.getUsername());
            if (userDetails == null) {
                throw new ResourceNotFoundExceotion("USER NOT FOUND");
            }
            if (validateLogin == null) {
                throw new ResourceNotFoundExceotion("USER NOT FOUND");
            }
            String token = UUID.randomUUID().toString();
            TemporaryToken temporaryToken = new TemporaryToken();
            temporaryToken.setToken(String.valueOf(token));
            temporaryToken.setExpiredDate(new Date(System.currentTimeMillis() + 900000));
            temporaryToken.setUser(aunthenticationRequest.getUsername());
            return temporaryTokenRepository.save(temporaryToken);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TemporaryToken> getTokenList() {
        return temporaryTokenRepository.findAll();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
