package com.demo.repository;

import com.demo.model.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
    AppUser findByUserId(Long userId);
    AppUser findByUserName(String userName);
}
