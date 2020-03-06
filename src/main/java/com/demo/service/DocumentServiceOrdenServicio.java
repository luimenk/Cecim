package com.demo.service;

import com.demo.model.DocumentOrdenServicio;
import com.demo.repository.DocumentRepositoryOrdenServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Service
public class DocumentServiceOrdenServicio {
    @Autowired
    DocumentRepositoryOrdenServicio documentRepositoryOrdenServicio;

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public ResponseEntity<?> save(Map<String, String> request) {
        DocumentOrdenServicio documentOrdenServicio=new DocumentOrdenServicio();
        



        documentRepositoryOrdenServicio.save(documentOrdenServicio);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<DocumentOrdenServicio> findAll() {
        return documentRepositoryOrdenServicio.findAll();
    }

    public DocumentOrdenServicio findById(Long id) {
        return documentRepositoryOrdenServicio.findByOrdenServicioId(id);
    }

    public void delete(Long methodId) {
        documentRepositoryOrdenServicio.deleteById(methodId);
    }

    public long contar() {
        return documentRepositoryOrdenServicio.count();
    }
}
