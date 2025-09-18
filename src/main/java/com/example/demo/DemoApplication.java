package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// cargar variables usando libreria dotenv
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		// cargo una variable de entorno del propio sistema (util en docker)
		//String issuer = System.getenv("JWT_ISSUER_URI");
		String issuer = "";
		System.out.println(String.format("issue: %s", issuer));
		// si no se encuentra la variable en el sistema, se carga con la libreria dotenv
		if (issuer == null) issuer = dotenv.get("JWT_ISSUER_URI");
		System.out.println(String.format("issue: %s", issuer));
		
		// iniciamos la aplicacion
		SpringApplication.run(DemoApplication.class, args);
	}

}
