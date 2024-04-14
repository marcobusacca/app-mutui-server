package com.advancia.spring.auth.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advancia.spring.auth.db.pojo.UserImage;

@Repository
public interface UserImageRepository extends JpaRepository<UserImage, Integer> {
}
