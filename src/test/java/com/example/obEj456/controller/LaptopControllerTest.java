
package com.example.obEj456.controller;

import com.example.obEj456.entities.Laptop;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LaptopControllerTest {
    
    private TestRestTemplate testRestTemplate; //podremos hacer las pruebas de postman(put,delete,create,find) desde aquí
    
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    
    @LocalServerPort
    private int port;
    
    @BeforeEach
    void SetUp(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }
    
    
    
    @Test
    void findAll(){
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class); //aquí devuelvo los valores en un array de Book
        
        assertEquals(HttpStatus.OK, response.getStatusCode()); 
        assertEquals(200, response.getStatusCodeValue());
        
        List<Laptop> laptops = Arrays.asList(response.getBody());//convierto el arreglo de libros del body en una lista
        System.out.println(laptops.size());
    }
    
    @Test
    void findOneById(){
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/1", Laptop.class); 
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); 
        
    }
    
    @Test
    void create(){
        //Para este método hay que enviar JSON, por lo que hay que crear cabeceras HTTP indicando que se envía JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        String json = """
                      {
                          "price": 379000.99,
                          "model": "Apple macbook pro"
                      }   
                      """;
        
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);
        
        Laptop result = response.getBody();
        
        assertEquals(1L, result.getId());
        assertEquals("Apple macbook pro", result.getModel());
        
    }
}
