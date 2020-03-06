package com.demo.service;

import com.demo.model.Client;
import com.demo.repository.ClientRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long clientId) {
        return clientRepository.findByClientId(clientId);
    }

    public void delete(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    public long contar() {
        return clientRepository.count();
    }

    public ResponseEntity<?> actualizarContactos(String contactos, Long id){
        Client client=clientRepository.findByClientId(id);
        client.setContactosDatos(contactos);
        clientRepository.save(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
