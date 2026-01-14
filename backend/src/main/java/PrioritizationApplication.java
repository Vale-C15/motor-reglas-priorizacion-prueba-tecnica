

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicación Spring Boot.
 * 
 * Motor de Reglas de Priorización - Prueba Técnica
 * 
 * Arquitectura: Clean Architecture + DDD Light
 * Stack: Java 17+ / Spring Boot 3.x
 */
@SpringBootApplication(scanBasePackages = {
    "adapters",
    "configuration", 
    "domain",
    "entrypoints",
    "helpers",
    "providers"
})
public class PrioritizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrioritizationApplication.class, args);
    }
}

