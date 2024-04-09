package com.advancia.spring.db.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advancia.spring.db.configuration.pojo.Listino;
import com.advancia.spring.db.configuration.repo.ListinoRepository;

@Service
public class ListinoService {

    @Autowired
    private ListinoRepository listinoRepository;

    public List<Listino> findAll() {
        return listinoRepository.findAll();
    }

    public Listino findById(int id) {
        return listinoRepository.findById(id).orElse(null);
    }

    public void save(Listino listino) {
        if (listino == null) {
            return;
        }
        listinoRepository.save(listino);
    }

    public void delete(Listino listino) {
        if (listino == null) {
            return;
        }
        listinoRepository.delete(listino);
    }
}
