package org.bedu.java.backend.sesion8.service;

import org.springframework.stereotype.Service;

@Service
public class ValidadorTel {

    public String limpiaNumero(String telefono){
        return telefono.replaceAll("[^0-9]", "");
    }
}
