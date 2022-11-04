
package com.example.obEj456.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    @Value("${app.message}")
    String message;
    
    @GetMapping("/hola")
    public String HolaMundo(){
        System.out.println(message);
        return "Hola Mundo, ejercicios 4,5,6!";
    }
}
