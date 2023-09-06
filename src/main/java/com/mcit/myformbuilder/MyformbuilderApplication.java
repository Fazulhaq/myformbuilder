package com.mcit.myformbuilder;

import com.mcit.myformbuilder.entity.UserData;
import com.mcit.myformbuilder.entity.UserType;
import com.mcit.myformbuilder.repository.UserDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@AllArgsConstructor
@SpringBootApplication
public class MyformbuilderApplication implements CommandLineRunner {

	UserDataRepository userDataRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(MyformbuilderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserData[] userData = new UserData[]{
				new UserData("admin","Khan","khan@gmail.com",bCryptPasswordEncoder.encode("admin"), UserType.ADMIN,"admin department"),
				new UserData("user","Jan","jan@gmail.com",bCryptPasswordEncoder.encode("user"), UserType.USER,"user department")
		};
		for (int i =0; i< userData.length; i++){
			userDataRepository.save(userData[i]);
		}
	}

}
