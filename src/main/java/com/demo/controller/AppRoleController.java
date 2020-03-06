package com.demo.controller;

//import com.pmisys.admin.api.service.TokenValidator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.model.AppRole;
import com.demo.service.AppRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/appRole")
public class AppRoleController {

    @Autowired
    private AppRoleService appRoleService;

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<AppRole> getAll() {
        return appRoleService.findAll();
    }
}
