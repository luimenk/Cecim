package com.demo.repository;

import com.demo.model.RecuperaCuenta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RecuperaCuentaRepository extends JpaRepository<RecuperaCuenta, Long>{
    RecuperaCuenta findByCodigo(String codigo);
    RecuperaCuenta findByCorreo(String correo);
}
