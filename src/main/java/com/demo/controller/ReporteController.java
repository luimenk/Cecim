package com.demo.controller;


import com.demo.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping(path = "/reporteController")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    public ResponseEntity<?> generarReporte(@RequestBody Map<String, String> request, Principal principal) {
        try {
            return reporteService.generarReporte(request);
        } catch (IOException e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
