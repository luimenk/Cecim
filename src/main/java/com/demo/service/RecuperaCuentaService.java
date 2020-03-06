package com.demo.service;

import com.demo.model.RecuperaCuenta;
import com.demo.repository.RecuperaCuentaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecuperaCuentaService {
    @Autowired
    private RecuperaCuentaRepository recuperaCuentaRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public RecuperaCuenta save(RecuperaCuenta recuperaCuenta) {
        return recuperaCuentaRepository.save(recuperaCuenta);
    }

    public RecuperaCuenta findByCorreo(String correo) {
        return recuperaCuentaRepository.findByCorreo(correo);
    }

    public RecuperaCuenta findByCodigo(String codigo) {
        return recuperaCuentaRepository.findByCodigo(codigo);
    }

    public void delete(Long id) {
        recuperaCuentaRepository.deleteById(id);
    }
}
