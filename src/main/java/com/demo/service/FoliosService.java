package com.demo.service;

import com.demo.model.Folios;
import com.demo.repository.FoliosRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoliosService {

    @Autowired
    private FoliosRepository foliosRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public Folios save(Folios folios) {
        return foliosRepository.save(folios);
    }

    public List<Folios> findAll() {
        return foliosRepository.findAll();
    }

    public Folios findByFolioId(Long foliosId) {
        return foliosRepository.findByFolioId(foliosId);
    }

    public Folios findByNombreFolio(String nombre) {
        return foliosRepository.findByNombreFolio(nombre);
    }
}
