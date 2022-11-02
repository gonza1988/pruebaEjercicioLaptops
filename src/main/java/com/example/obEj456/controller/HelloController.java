
package com.example.obEj456.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    @GetMapping("/hola")
    public String HolaMundo(){
        return "Hola Mundo, ejercicios 4,5,6!";
    }
}
