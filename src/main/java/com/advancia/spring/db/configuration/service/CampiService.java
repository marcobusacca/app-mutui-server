package com.advancia.spring.db.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advancia.spring.db.configuration.pojo.Campi;
import com.advancia.spring.db.configuration.repo.CampiRepository;

@Service
public class CampiService {

    @Autowired
    private CampiRepository campiRepository;

    public List<Campi> findAll() {
        return campiRepository.findAll();
    }

    public Campi findById(int id) {
        return campiRepository.findById(id).orElse(null);
    }

    public void save(Campi campi) {
        if (campi == null) {
            return;
        }
        campiRepository.save(campi);
    }

    public void delete(Campi campi) {
        if (campi == null) {
            return;
        }
        campiRepository.delete(campi);
    }
}
