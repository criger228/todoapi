package com.minminas.todoapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Todo API",
        version = "v1",
        description = "API para gestionar lista de tareas por German Ruiz"
))
public class TodoapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoapiApplication.class, args);
    }

}
