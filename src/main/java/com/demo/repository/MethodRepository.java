package com.demo.repository;

import com.demo.model.Method;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MethodRepository extends JpaRepository<Method, Long>{
    Method findByMethodId(Long idMethod);
    Method findByCodigoMetodo(String codigoMetodo);
}
