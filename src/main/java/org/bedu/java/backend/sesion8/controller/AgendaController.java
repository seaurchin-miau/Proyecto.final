package org.bedu.java.backend.sesion8.controller;

import org.bedu.java.backend.sesion8.model.Persona;
import org.bedu.java.backend.sesion8.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AgendaController {

    private final AgendaService servicio;

    @Autowired
    public AgendaController(AgendaService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<List<Persona>> getPersonas(){
        return ResponseEntity.ok(servicio.getPersonas());
    }

    @PostMapping
    public ResponseEntity<Persona> guardaPersona(@RequestBody Persona persona) {
        Persona resultado = servicio.guardaPersona(persona);

        if (resultado == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(resultado);
    }

    @GetMapping({"/", "/index"})
    public String paginaInicio(Model model) {
        model.addAttribute("listaPersonas", servicio.getPersonas());
        return "index";
    }

    @PostMapping("/registro")
    public ModelAndView registra(@Valid Persona persona , Errors errors) {

        String vistaResultado = "index";
        if (errors.hasErrors()){
            vistaResultado = "index";
        } else {
            servicio.guardaPersona(persona);

        }


        ModelAndView mav = new ModelAndView(vistaResultado);
        mav.addObject("listaPersonas", servicio.getPersonas());
        return mav;
    }
    @GetMapping("/nuevo")
    public String nuevaPersona(Model modelo) {
        modelo.addAttribute("persona", new Persona());
        return "nuevo";
    }

    @PostMapping("/nuevo")
    public String guardarPersona(@Validated Persona persona, BindingResult bindingResult, RedirectAttributes redirect, Model modelo) {
        if(bindingResult.hasErrors()) {
            modelo.addAttribute("persona", persona);
            return "nuevo";
        }

        servicio.guardaPersona(persona);
        redirect.addFlashAttribute("msgExito", "El persona ha sido agregada con exito");
        return "redirect:/";
    }
    @PostMapping("/{id}/eliminar")
    public String eliminarPersona(@PathVariable Long id,RedirectAttributes redirect) {
        servicio.eliminarPersona(id);
        redirect.addFlashAttribute("msgExito", "La persona ha sido eliminada correctamente");
        return "redirect:/";
    }

    @GetMapping("/{id}/editar")
    public String editarPersona(@PathVariable Long id,Model modelo) {
        Persona persona = servicio.getPersonaById(id);
        modelo.addAttribute("persona", persona);
        return "nuevo";
    }

    @PostMapping("/{id}/editar")
    public String actualizarPersona(@PathVariable Long id,@Validated Persona persona,BindingResult bindingResult,RedirectAttributes redirect,Model modelo) {
        Persona personaAux = servicio.getPersonaById(id);
        if(bindingResult.hasErrors()) {
            modelo.addAttribute("persona", persona);
            return "nuevo";
        }

        personaAux.setNombre(persona.getNombre());
        personaAux.setTelefono(persona.getTelefono());
        personaAux.setEmail(persona.getEmail());
        servicio.guardaPersona(personaAux);

        redirect.addFlashAttribute("msgExito", "La persona fue actualizada correctamente");
        return "redirect:/";
    }
}
