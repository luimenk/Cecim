package com.demo.controller;

//import com.pmisys.admin.api.service.TokenValidator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.model.Method;
import com.demo.service.MethodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/method")
public class MethodController {

    @Autowired
    private MethodService methodService;

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<Method> getAll() {
        System.out.println("Entró");
        return methodService.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{methodId}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public Method get(@PathVariable("methodId") Long methodId) {
        Method method = methodService.findById(methodId);

        if (method == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (method.getCodigoMetodo() == null) {
            method.setCodigoMetodo("");
        }

        return method;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public Method create(@RequestBody Map<String, String> request) throws Exception {
        Method method = new Method();
        method.setCodigoMetodo(request.get("codigoMetodo"));
        method.setCantidadMuestraEnsayo(request.get("cantidadMuestraEnsayo"));
        method.setCantidadMuestraRetencion(request.get("cantidadMuestraRetencion"));
        method.setCantidadMuestraRetencion(request.get("cantidadTotal"));
        method.setDimensionesCorteProbeta(request.get("dimensionesCorteProbeta"));
        method.setNumeroProbetasMuestras(request.get("numeroProbetasMuestras"));
        method.setCondicionesEspecialesAcondicionamiento(request.get("condicionesEspecialesAcondicionamiento"));

        methodService.save(method);

        return method;
    }

    //EliminarElemento
    @RequestMapping(method = RequestMethod.DELETE, value = "/{methodId}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE})
    public void delete(@PathVariable("methodId") Long methodId){
        methodService.delete(methodId);
    }

    @RequestMapping(value = "/generarListaMetodos", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir5() throws Exception {
        System.out.println("Se generó ");
        System.out.println(LocalTime.now());

        return methodService.generarListaMetodos();
    }
}
