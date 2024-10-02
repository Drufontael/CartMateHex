package br.dev.drufontael.CartMateHex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.dev.drufontael.CartMateHex"})
public class CartMateHexApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartMateHexApplication.class, args);
	}

}
