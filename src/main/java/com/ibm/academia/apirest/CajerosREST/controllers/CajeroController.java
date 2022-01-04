package com.ibm.academia.apirest.CajerosREST.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ibm.academia.apirest.CajerosREST.dto.CajeroRequest;
import com.ibm.academia.apirest.CajerosREST.dto.CajeroResponse;
import com.ibm.academia.apirest.CajerosREST.models.services.CajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cajero")
public class CajeroController {
    @Autowired
    private CajeroService cajeroService;

    @GetMapping("/lista")
    public ResponseEntity<?> listar(@RequestBody CajeroRequest cajerosRequest){
        System.out.println(cajerosRequest);

        try {
            return new ResponseEntity<>(cajeroService.buscarCajeros(cajerosRequest), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
