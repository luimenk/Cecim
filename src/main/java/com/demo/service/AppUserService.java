package com.demo.service;

import com.demo.model.AppUser;
import com.demo.repository.AppUserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    public List<AppUser> findAllFiltered(Integer a) {
        return appUserRepository.findAllByVisible(a);
    }

    public AppUser findById(Long userId) {
        return appUserRepository.findByUserId(userId);
    }

    public AppUser findByUserName(String userName) {
        return appUserRepository.findByUserName(userName);
    }

    public void delete(Long userId) {
        appUserRepository.deleteById(userId);
    }

    public long contar() {
        return appUserRepository.count();
    }
}
