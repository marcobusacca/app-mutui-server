package com.advancia.spring.db.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advancia.spring.db.configuration.pojo.VincoliProdotti;
import com.advancia.spring.db.configuration.repo.VincoliProdottiRepository;

@Service
public class VincoliProdottiService {

    @Autowired
    private VincoliProdottiRepository vincoliProdottiRepository;

    public List<VincoliProdotti> findAll() {
        return vincoliProdottiRepository.findAll();
    }

    public VincoliProdotti findById(int id) {
        return vincoliProdottiRepository.findById(id).orElse(null);
    }

    public void save(VincoliProdotti vincoliProdotti) {
        if (vincoliProdotti == null) {
            return;
        }
        vincoliProdottiRepository.save(vincoliProdotti);
    }

    public void delete(VincoliProdotti vincoliProdotti) {
        if (vincoliProdotti == null) {
            return;
        }
        vincoliProdottiRepository.delete(vincoliProdotti);
    }
}
