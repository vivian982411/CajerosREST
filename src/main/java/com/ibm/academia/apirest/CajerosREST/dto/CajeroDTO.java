package com.ibm.academia.apirest.CajerosREST.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.ibm.academia.apirest.CajerosREST.models.entities.GPS;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CajeroDTO {
    private String numero;
    private String direccion;
    private GPS localizacion;
    private String tipo;
    private Double distancia;

    public CajeroDTO(String numero, String direccion, GPS localizacion, String tipo, Double distancia) {
        this.numero = numero;
        this.direccion = direccion;
        this.localizacion = localizacion;
        this.tipo = tipo;
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "CajeroDTO{" +
                "numero='" + numero + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localizacion=" + localizacion +
                ", tipo='" + tipo + '\'' +
                ", distancia=" + distancia +
                '}';
    }
}
