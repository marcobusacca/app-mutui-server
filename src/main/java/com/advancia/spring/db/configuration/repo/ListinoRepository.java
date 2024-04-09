package com.advancia.spring.db.configuration.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advancia.spring.db.configuration.pojo.Listino;

@Repository
public interface ListinoRepository extends JpaRepository<Listino, Integer> {

}
