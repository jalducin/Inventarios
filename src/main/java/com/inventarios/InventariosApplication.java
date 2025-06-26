package com.inventarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.inventarios.model.Category;
import com.inventarios.repository.CategoryRepository;

import java.util.List;

@SpringBootApplication
public class InventariosApplication {


public static void main(String[] args) {
    SpringApplication.run(InventariosApplication.class, args);
}

@Bean
public CommandLineRunner initCategories(CategoryRepository catRepo) {
    return args -> {
        List<String> names = List.of(
          "electrónica", "línea blanca", "electrodomésticos", "muebles",
          "motocicletas", "llantas", "acumuladores", "bicicletas",
          "teléfonos celulares", "computadoras", "juguetes"
        );
        for (String n : names) {
            if (catRepo.findByName(n).isEmpty()) {
                catRepo.save(new Category(n));
            }
        }
    };
}


}
