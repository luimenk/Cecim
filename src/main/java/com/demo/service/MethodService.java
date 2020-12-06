package com.demo.service;

import com.demo.model.Method;
import com.demo.repository.MethodRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MethodService {

    @Autowired
    private MethodRepository methodRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public Method save(Method method) {
        return methodRepository.save(method);
    }

    public List<Method> findAll() {
        return methodRepository.findAll();
    }

    public Method findById(Long methodId) {
        return methodRepository.findByMethodId(methodId);
    }

    public Method findByCodigo(String codigo){
        return methodRepository.findByCodigoMetodo(codigo);
    }

    public void delete(Long methodId) {
        methodRepository.deleteById(methodId);
    }

    public long contar() {
        return methodRepository.count();
    }
}
