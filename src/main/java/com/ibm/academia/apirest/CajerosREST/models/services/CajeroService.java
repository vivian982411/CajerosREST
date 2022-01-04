package com.ibm.academia.apirest.CajerosREST.models.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ibm.academia.apirest.CajerosREST.dto.CajeroDTO;
import com.ibm.academia.apirest.CajerosREST.dto.CajeroRequest;
import com.ibm.academia.apirest.CajerosREST.dto.CajeroResponse;

import java.util.List;

public interface CajeroService {
    public List<CajeroDTO> buscarCajeros(CajeroRequest cajeroRequest) throws JsonProcessingException;
}
