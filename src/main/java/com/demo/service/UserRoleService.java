package com.demo.service;

import com.demo.model.UserRole;
import com.demo.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    public List<UserRole> findBySomething(String algo) {
        return userRoleRepository.findByAppRole_RoleName(algo);
    }

    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    public UserRole findByAppUserUserName(String username) {
        return userRoleRepository.findByAppUser_UserName(username);
    }

    /*public List<Method> findAll() {
        return methodRepository.findAll();
    }

    public Method findById(Long methodId) {
        return methodRepository.findByMethodId(methodId);
    }

    public void delete(Long methodId) {
        methodRepository.deleteById(methodId);
    }

    public long contar() {
        return methodRepository.count();
    }*/
}
