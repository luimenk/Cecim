package com.demo.controller;

import com.demo.model.DocumentOrdenServicio;
import com.demo.service.DocumentServiceOrdenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/documento")
public class DocumentController {

    @Autowired
    private DocumentServiceOrdenServicio documentServiceOrdenServicio;
    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<DocumentOrdenServicio> getAll() {
        System.out.println("Entró");
        return documentServiceOrdenServicio.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public DocumentOrdenServicio get(@PathVariable("id") Long id) {
        return documentServiceOrdenServicio.findById(id);
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {
        return documentServiceOrdenServicio.save( request);
    }

    //EliminarElemento
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE})
    public void delete(@PathVariable("id") Long id){
        documentServiceOrdenServicio.delete(id);
    }
}
