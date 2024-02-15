package com.api.gestion.demo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FacturaUtils {

    private FacturaUtils() {
    }

    public static ResponseEntity<String> getResponseEntity(String mensaje, HttpStatus httpStatus){
        return new ResponseEntity<String>("Mensaje: " + mensaje, httpStatus);
    }
}
