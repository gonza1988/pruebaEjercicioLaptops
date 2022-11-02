
package com.example.obEj456.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Configuración Swagger para la generación de documentación de la API REST
 * 
 * HTML: http://localhost:8081/swagger-ui/
 * JSON: http://localhost:8081/v2/api-docs
 * @author GIGABYTE
 */
@Configuration
public class SwaggerConfig {
    
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any()) //para que enganche todas las rutas y documente en todas las clases
                .paths(PathSelectors.any())
                .build(); //va concatenando llamadas de eventos para al final descontracturar cada uno de ellos
    }
    
    private ApiInfo apiDetails(){
        
        return new ApiInfo("Spring Boot Ordenadores API REST",
                "Laptops Api rest docs",
                "1.0",
                "http://www.google.com",
                new Contact("Gonzalo Cozzo", "http://www.google.com", "gonza@example.com"),
                "MIT license",
                "http://www.google.com",
                Collections.emptyList());
    }
}
