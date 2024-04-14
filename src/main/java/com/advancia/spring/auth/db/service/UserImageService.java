package com.advancia.spring.auth.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advancia.spring.auth.db.pojo.UserImage;
import com.advancia.spring.auth.db.repo.UserImageRepository;

@Service
public class UserImageService {

    @Autowired
    private UserImageRepository userImageRepository;

    public List<UserImage> findAll() {
        return userImageRepository.findAll();
    }

    public UserImage findById(int id) {
        return userImageRepository.findById(id).orElse(null);
    }

    public void save(UserImage userImage) {
        userImageRepository.save(userImage);
    }

    public void delete(UserImage userImage) {
        userImageRepository.delete(userImage);
    }
}
