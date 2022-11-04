package com.example.obEj456.controller;

import com.example.obEj456.entities.Laptop;
import com.example.obEj456.repository.LaptopRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LaptopController {

    //atributos
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);
    private LaptopRepository laptopRepository;

    //constructores
    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    /**
     * http://localhost:8081/api/laptops
     *
     * @return
     */
    @GetMapping("/api/laptops")
    public List<Laptop> findAll() {
        // recuperar y devolver los libros de base de datos
        return laptopRepository.findAll();
    }

    @GetMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id) {

        Optional<Laptop> laptopOpt = laptopRepository.findById(id);

        return laptopOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // crear una nueva laptop en base de datos
    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));
        // guardar laptop recibida por parámetro en la base de datos
        if (laptop.getId() != null) { //existe el id y por tanto no es una creación
            log.warn("Trying to create a laptop with id");
            System.out.println("Trying to create a laptop with id");
            return ResponseEntity.badRequest().build();
        }

        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop) {
        if (laptop.getId() == null) { //si no tiene id quiere decir que sí es una creación
            log.warn("Trying to update a non existant laptop");
            return ResponseEntity.badRequest().build();
        }
        if (!laptopRepository.existsById(laptop.getId())) {
            log.warn("Trying to update a non existant laptop");
            return ResponseEntity.notFound().build();
        }
        //Sino hay error en las comprobaciones, actualizo el libro
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    // borrar un libro en base de datos
    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id) {
        if (!laptopRepository.existsById(id)) {
            log.warn("Trying to delete a non existant laptop");
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("api/laptops")
    public ResponseEntity<Laptop> deleteAll() {
        log.info("REST Request for Delete all laptops");

        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
