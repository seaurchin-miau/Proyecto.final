package org.bedu.java.backend.sesion8.service;

import org.bedu.java.backend.sesion8.model.Persona;
import org.bedu.java.backend.sesion8.persistence.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AgendaService {

    private final ValidadorTel validadorTelefono;
    private final PersonaRepository repositorio;

    @Autowired
    public AgendaService(ValidadorTel validadorTelefono, PersonaRepository repositorio) {
        this.validadorTelefono = validadorTelefono;
        this.repositorio = repositorio;
    }

    public Persona guardaPersona(Persona persona) {

        String telefono = validadorTelefono.limpiaNumero(persona.getTelefono());

        persona.setTelefono(telefono);

        return repositorio.save(persona);
    }

    public List<Persona> getPersonas() {
        return repositorio.findAll();
    }

    public void eliminarPersona(Long id) {
        repositorio.deleteById(id);
    }

    public Persona getPersonaById(Long id) {
        return repositorio.findById(id)
                .orElseThrow(
                        ()-> new EntityNotFoundException(String.format("Persona con el id [%d] no fue encontrada!", id)));
    }
}
