package org.bedu.java.backend.sesion8;

import org.bedu.java.backend.sesion8.model.Persona;
import org.bedu.java.backend.sesion8.persistence.PersonaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Sesion8Application implements CommandLineRunner {

	private final PersonaRepository repositorio;

	public Sesion8Application(PersonaRepository repositorio) {
		this.repositorio = repositorio;
	}

	public static void main(String[] args) {
		SpringApplication.run(Sesion8Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Persona persona1 = new Persona("Anna Luz Gómez","1234567897","annaluzgp@gmail.com");
		Persona persona2 = new Persona("Javier Juarez Villalobos","7987654321","17171146@itculiacan.edu.mx");
		Persona persona3 = new Persona("Jafeth Rosales","6543217895","rosalesjafeth36@gmail.com");
		Persona persona4 = new Persona("Gregorio Abraham Jurado","1597532684","jurado.abraham@gmail.com");

		repositorio.save(persona1);
		repositorio.save(persona2);
		repositorio.save(persona3);
		repositorio.save(persona4);


	}
}
