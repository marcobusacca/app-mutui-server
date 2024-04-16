package com.advancia.spring.auth.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advancia.spring.auth.db.pojo.UserAudio;

@Repository
public interface UserAudioRepository extends JpaRepository<UserAudio, Integer> {
}
