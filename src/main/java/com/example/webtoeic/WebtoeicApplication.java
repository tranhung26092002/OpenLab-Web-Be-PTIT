package com.example.webtoeic;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.crypto.SecretKey;

@SpringBootApplication
public class WebtoeicApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
//		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//		String strKey = Encoders.BASE64.encode(key.getEncoded());
//		System.out.println("Generated Secret Key: " + strKey);
		SpringApplication.run(WebtoeicApplication.class, args);
	}

}
