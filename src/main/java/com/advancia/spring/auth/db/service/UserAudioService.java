package com.advancia.spring.auth.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advancia.spring.auth.db.pojo.UserAudio;
import com.advancia.spring.auth.db.repo.UserAudioRepository;

@Service
public class UserAudioService {

    @Autowired
    private UserAudioRepository userAudioRepository;

    public List<UserAudio> findAll() {
        return userAudioRepository.findAll();
    }

    public UserAudio findById(int id) {
        return userAudioRepository.findById(id).orElse(null);
    }

    public void save(UserAudio userAudio) {
        userAudioRepository.save(userAudio);
    }

    public void delete(UserAudio userAudio) {
        userAudioRepository.delete(userAudio);
    }
}
