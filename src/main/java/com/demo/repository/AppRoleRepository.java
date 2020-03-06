package com.demo.repository;

import com.demo.model.AppRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AppRoleRepository extends JpaRepository<AppRole, Long>{
    AppRole findByRoleId(Long roleId);
}
