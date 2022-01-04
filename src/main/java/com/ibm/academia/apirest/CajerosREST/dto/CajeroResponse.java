package com.ibm.academia.apirest.CajerosREST.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CajeroResponse {
    private String status;
    private List<CajeroDTO> cajeros;
}
