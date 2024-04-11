package com.advancia.spring.auth.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.advancia.spring.auth.db.pojo.User;
import com.advancia.spring.auth.db.repo.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {

        return userRepository.findAll();
    }

    public User findById(int id) {

        return userRepository.findById(id).get();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.findByEmail(email);
    }
}
