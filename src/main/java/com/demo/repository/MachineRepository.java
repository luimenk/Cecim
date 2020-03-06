package com.demo.repository;

import com.demo.model.Machine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MachineRepository extends JpaRepository<Machine, Long>{
    Machine findByMachineId(Long idMachine);
}
