package com.ibm.academia.apirest.CajerosREST.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "cajeros", url = "https://www.banamex.com")
public interface CajeroRest {
    @GetMapping("/localizador/jsonP/json5.json")
    public String lista();

}
