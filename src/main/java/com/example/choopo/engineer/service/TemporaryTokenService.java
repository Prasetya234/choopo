package com.example.choopo.engineer.service;

import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.User;
import com.example.choopo.web.repository.UserRepository;
import com.example.choopo.web.repository.UserTypeRepository;
import com.example.choopo.engineer.model.TemporaryToken;
import com.example.choopo.engineer.repository.TemporaryTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
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
    private UserTypeRepository userTypeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("USER TIDAK DITEMUKAN");
        } else {
           org.springframework.security.core.userdetails.User.UserBuilder userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPasswordEncoder()).authorities(user.getUserTypeId().getUserTypeName());
            return userDetails.build();
        }
    }

    private Collection<GrantedAuthority> getAutorities(User user) {
        String userGroups = user.getUserTypeId().getUserTypeName();
        Collection<GrantedAuthority> authorities = new ArrayList(Integer.parseInt(userGroups));
        authorities.add(new SimpleGrantedAuthority(Arrays.toString(userGroups.getBytes(StandardCharsets.UTF_8))));
        return authorities;
    }
    @Override
    public User register(User userDTO) throws ResourceNotFoundExceotion{
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null) {
            User newUser = new User();
            newUser.setUsername(userDTO.getUsername());
            newUser.setUserCode(userDTO.getUserCode());
            newUser.setUserStatus(userDTO.getUserStatus());
            newUser.setPassword(userDTO.getPassword());
            newUser.setUserType(String.valueOf(1));
            userTypeRepository.findById(Long.valueOf(newUser.getUserType()))
                    .map(user1 -> {
                        newUser.setUserTypeId(user1);
                        return newUser;
                    }).orElseThrow(() -> new ResourceNotFoundExceotion("USER TYPE ID NOT FOUND"));
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
                throw new Exception("USERNAME OR PASSWORD NOT FOUND");
            }
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails == null) {
                throw new Exception("USERNAME AND PASSWORD NOT FOUND");
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
