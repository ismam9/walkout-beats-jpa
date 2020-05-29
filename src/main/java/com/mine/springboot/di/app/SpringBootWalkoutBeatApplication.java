package com.mine.springboot.di.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mine.springboot.di.app.models.services.IUploadFileService;

@SpringBootApplication
public class SpringBootWalkoutBeatApplication implements CommandLineRunner {

	@Autowired
	IUploadFileService uploadFileService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWalkoutBeatApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		uploadFileService.deleteAll();
		uploadFileService.init();
		
		String password = "1234";
		
		for(int i = 0; i < 1; i++) {
			String bcryptString = passwordEncoder.encode(password);
			System.out.println(bcryptString);
		}
		
	}


}
