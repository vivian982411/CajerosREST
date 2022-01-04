package com.ibm.academia.apirest.CajerosREST.dto;

import com.ibm.academia.apirest.CajerosREST.models.entities.GPS;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CajeroRequest {
    private GPS gps;
    private Integer codigoPostal;
    private String estado;
}
