package com.ibm.academia.apirest.CajerosREST.models.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.academia.apirest.CajerosREST.clients.CajeroRest;
import com.ibm.academia.apirest.CajerosREST.dto.CajeroDTO;
import com.ibm.academia.apirest.CajerosREST.dto.CajeroRequest;
import com.ibm.academia.apirest.CajerosREST.dto.CajeroResponse;
import com.ibm.academia.apirest.CajerosREST.models.entities.GPS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Primary
@Service("serviceFeing")
public class CajeroServiceFeing implements CajeroService{
    @Autowired
    private CajeroRest cajeroRest;

    @Override
    public List<CajeroDTO> buscarCajeros(CajeroRequest cajeroRequest) {

        try {
            String respuesta = cajeroRest.lista();
            respuesta=respuesta.substring(13);
            respuesta=respuesta.substring(0,respuesta.length()-2);
            JsonObject jsonObject = new JsonParser().parse(respuesta).getAsJsonObject();
            JsonObject json= jsonObject.getAsJsonObject("Servicios");
            JsonArray cajerosSucursales= obtenerCajerosSucursales(json);

            List<CajeroDTO> cajeros = obtenerDistanciaCajerosSucursales(cajeroRequest.getGps(),cajerosSucursales);

            return cajeros;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        return null;
    }

    private JsonArray obtenerCajerosSucursales(JsonObject jsonObject){
        Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
        JsonArray cajerosSucursales= new JsonArray();
        for(Map.Entry<String,JsonElement> entry : entrySet){
            if (entry.getValue().isJsonObject()){
                JsonObject jsonObjectNivel2= (JsonObject) entry.getValue();
                Set<Map.Entry<String, JsonElement>> entrySetNivel2 = jsonObjectNivel2.entrySet();
                for(Map.Entry<String,JsonElement> entryNivel2 : entrySetNivel2){
                    if (entryNivel2.getValue().isJsonObject()){
                        JsonObject jsonObjectNivel3= (JsonObject) entryNivel2.getValue();
                        Map<String, Object> attributesNivel3 = new HashMap<String, Object>();
                        Set<Map.Entry<String, JsonElement>> entrySetNivel3 = jsonObjectNivel3.entrySet();
                        for(Map.Entry<String,JsonElement> entryNivel3 : entrySetNivel3){
                            if (entryNivel3.getValue().isJsonArray()){
                                JsonArray jsonArray= (JsonArray) entryNivel3.getValue();
                                if (jsonArray.get(19).toString().equalsIgnoreCase("\"ATM\"") ||  jsonArray.get(19).toString().equalsIgnoreCase("\"Sucursal\"")){
                                    cajerosSucursales.add(jsonArray);
                                }
                            }
                        }
                    }
                }
            }
        }
        return cajerosSucursales;
    }

    public static double distanciaEntreDosPuntos(double latitud1, double longitud1, double latitud2, double longitud2) {
        double radioTierra = 6371;//en kilómetros
        double dLat = Math.toRadians(latitud2 - latitud1);
        double dLng = Math.toRadians(longitud2 - longitud1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(latitud1)) * Math.cos(Math.toRadians(latitud2)) *
                        Math.sin(dLng/2)* Math.sin(dLng/2);
        double c= 2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        double distancia = radioTierra * c;

        return distancia;
    }


    private List<CajeroDTO> obtenerDistanciaCajerosSucursales(GPS ubicacion, JsonArray cajeros){

        List<CajeroDTO> cajerosList= new ArrayList<>();
        for (JsonElement cajero : cajeros) {
            JsonArray arrayCajero = (JsonArray) cajero;
            Double distancia=distanciaEntreDosPuntos(ubicacion.getLatitud(),ubicacion.getLongitud(),arrayCajero.get(15).getAsDouble(),arrayCajero.get(15).getAsDouble());
            cajerosList.add(
                    new CajeroDTO(
                            arrayCajero.get(0).toString().replace("\"",""),
                            arrayCajero.get(3).toString().replace("\"","") + " " + arrayCajero.get(4).toString().replace("\"",""),
                            new GPS(arrayCajero.get(15).getAsDouble(),arrayCajero.get(16).getAsDouble() ),
                            arrayCajero.get(19).toString().replace("\"",""),
                            distancia
                    )
            );
        }
        cajerosList.sort(Comparator.comparingDouble(CajeroDTO::getDistancia));

        return cajerosList;
    }
}
