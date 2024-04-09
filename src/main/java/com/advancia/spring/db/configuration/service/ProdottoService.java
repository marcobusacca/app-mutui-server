package com.advancia.spring.db.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advancia.spring.db.configuration.pojo.Prodotto;
import com.advancia.spring.db.configuration.repo.ProdottoRepository;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    public List<Prodotto> findAll() {
        return prodottoRepository.findAll();
    }

    public Prodotto findById(int id) {
        return prodottoRepository.findById(id).orElse(null);
    }

    public void save(Prodotto prodotto) {
        if (prodotto == null) {
            return;
        }
        prodottoRepository.save(prodotto);
    }

    public void delete(Prodotto prodotto) {
        if (prodotto == null) {
            return;
        }
        prodottoRepository.delete(prodotto);
    }
}
