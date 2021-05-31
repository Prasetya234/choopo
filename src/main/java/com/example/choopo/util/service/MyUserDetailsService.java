package com.example.choopo.util.service;

import com.example.choopo.dto.UserDTO;
import com.example.choopo.model.User;
import com.example.choopo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswordNonEncode(), new ArrayList<>());
        }
    }


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
}
