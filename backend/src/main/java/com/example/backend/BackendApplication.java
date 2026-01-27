package com.example.backend;

import com.example.backend.repository.ProductosRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
	}
    //Probar la Conexión
    @Bean
    CommandLineRunner testConnection(ProductosRepository productoRepository) {
        return args -> {
            long count = productoRepository.count();
            System.out.println("Productos en la base de datos: " + count);
        };
    }


}
