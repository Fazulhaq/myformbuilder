package com.mcit.myformbuilder;

import com.mcit.myformbuilder.entity.UserData;
import com.mcit.myformbuilder.entity.UserType;
import com.mcit.myformbuilder.repository.UserDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@AllArgsConstructor
@SpringBootApplication
public class MyformbuilderApplication implements CommandLineRunner {

	UserDataRepository userDataRepository;

	public static void main(String[] args) {
		SpringApplication.run(MyformbuilderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserData[] userData = new UserData[]{
				new UserData("Ahmad","Rahimi","ahmad@gmail.com","12345", UserType.ADMIN,"finance ministry"),
				new UserData("Akbar","jan","akbar@gmail.com","12345", UserType.USER,"moi"),
				new UserData("Mahmood","akbari","mahmood@gmail.com","12345", UserType.USER,"finance ministry")
		};
		for (int i =0; i< userData.length; i++){
			userDataRepository.save(userData[i]);
		}
	}

}
