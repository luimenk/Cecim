package com.demo.repository;

import com.demo.model.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
    UserRole findByAppUser_UserName(String username);
    List<UserRole> findByAppRole_RoleId(Long id);

    List<UserRole> findByAppRole_RoleName(String algo);
    List<UserRole> findAllByAppUser_UserName(String username);
    List<UserRole> findAllByAppUser_UserId(Long id);
    UserRole findByAppUser_UserId(Long id);
}
