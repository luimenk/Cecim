package com.demo.repository;

import com.demo.model.DocumentOrdenServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DocumentRepositoryOrdenServicio extends JpaRepository<DocumentOrdenServicio,Long> {
    DocumentOrdenServicio findByOrdenServicioId(Long id);
}
