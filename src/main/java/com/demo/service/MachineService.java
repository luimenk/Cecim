package com.demo.service;

import com.demo.model.Machine;
import com.demo.repository.MachineRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public Machine save(Machine machine) {
        return machineRepository.save(machine);
    }

    public List<Machine> findAll() {
        return machineRepository.findAll();
    }

    public Machine findById(Long machineId) {
        return machineRepository.findByMachineId(machineId);
    }

    public void delete(Long machineId) {
        machineRepository.deleteById(machineId);
    }

    public long contar() {
        return machineRepository.count();
    }
}
