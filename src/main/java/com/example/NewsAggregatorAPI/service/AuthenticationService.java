package com.example.NewsAggregatorAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.NewsAggregatorAPI.entity.User;
import com.example.NewsAggregatorAPI.entity.UserDTO;
import com.example.NewsAggregatorAPI.repository.UserRepository;
import com.example.NewsAggregatorAPI.util.JwtUtility;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(_passwordEncoder.encode(userDTO.getPassword()));
        user.setEnabled(false);
        user.setRole("ADMIN");
        return _userRepository.save(user);


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = _userRepository.findByUsername(username);

        if (user == null) {
            throw  new UsernameNotFoundException("user not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), java.util.Collections.emptyList());

    }

    public String signinUser(String username, String password) {
        User registeredUser = _userRepository.findByUsername(username);
        if(registeredUser == null) {
            return "user not found";
        }

        if (!registeredUser.isEnabled()){
            return "user is not enabled, please verify your registration";
        }

        boolean isPasswordMatch = _passwordEncoder.matches( password,registeredUser.getPassword());

        if(!isPasswordMatch) {
            return "Invalid credentials";
        }

        return JwtUtility.generateToken(username,registeredUser.getRole());
    }
    
}
