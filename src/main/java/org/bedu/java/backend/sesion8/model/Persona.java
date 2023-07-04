package org.bedu.java.backend.sesion8.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.lang.Comparable;
import java.util.Objects;

@Entity
@Table(name = "PERSONAS")
public class Persona implements Comparable <Persona>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre de la persona es obligatorio.")
    @Column(nullable = false, length = 100)
    private String nombre;

    @Pattern(regexp = "^(\\d{2,4}[- .]?){2}\\d{4}$", message = "El teléfono debe tener un formato de ##-####-####")
    @Column(nullable = false, length = 20)
    private String telefono;
    @NotBlank(message = "El email de la persona es obligatorio.")
    @Email(message = "El email tiene un formato incorrecto.")
    @Email
    private String email;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Persona() {  //Constructor sin parámetros

    }
    public Persona(String nombre, String telefono, String email) {  //Constructor con parámetros
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;

    }


    @Override //Sobreescribir para mostrar los datos necesarios
    public String toString() {
        return "Persona{" +
                "nombre ='" + nombre + '\'' +
                ", numero de telefono ='" + telefono + '\'' +
                ", email ='" + email + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return nombre.equals(persona.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public int compareTo(Persona o) {
        return this.nombre.compareTo(o.nombre);
    }
}
