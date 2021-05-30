package com.example.choopo.util.service;

import com.example.choopo.dto.UserDTO;
import com.example.choopo.model.User;
import com.example.choopo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MyUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw  new UsernameNotFoundException("USER TIDAK DITEMUKAN");
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
        }
    }

    public com.example.choopo.model.User save(UserDTO userDTO) {

        com.example.choopo.model.User newUser = new com.example.choopo.model.User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());
        userRepository.save(newUser);

        User aa = new User();
        aa.setUsername(newUser.getUsername());
        aa.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return aa;
    }
}
