package com.example.choopo.util.service;

import com.example.choopo.dto.UserDTO;
import com.example.choopo.model.User;
import com.example.choopo.repository.UserRepository;
import com.example.choopo.util.model.AuthenticationRequest;
import com.example.choopo.util.model.AuthenticationResponse;
import com.example.choopo.util.repository.AuthenticationResponseRepository;
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

import java.util.ArrayList;
import java.util.Date;

@Service
public class MyUserDetailsService extends UserDetailsImpl implements UserDetailsService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationResponseRepository authenticationResponseRepository;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw  new UsernameNotFoundException("USER TIDAK DITEMUKAN");
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswordNonEncode(), new ArrayList<>());
        }
    }

    @Override
    public User save(UserDTO userDTO) {
        User userList = userRepository.findByUsernameCek(userDTO.getUsername());
        if (userList == null) {
            com.example.choopo.model.User newUser = new com.example.choopo.model.User();
            newUser.setUserType(userDTO.getUserType());
            newUser.setUsername(userDTO.getUsername());
            newUser.setUserCode(userDTO.getUserCode());
            newUser.setPassword(userDTO.getPassword());
            newUser.setUserStatus(userDTO.getUserStatus());
            newUser.setPasswordNonEncode(passwordEncoder.encode(newUser.getPassword()));
            return userRepository.save(newUser);
        }
        throw new RuntimeException("USER ALREADY EXIST");
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setJwt(jwt);
        authenticationResponse.setExpiredDate(new Date(System.currentTimeMillis() + 900000));
         return authenticationResponseRepository.save(authenticationResponse);
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
