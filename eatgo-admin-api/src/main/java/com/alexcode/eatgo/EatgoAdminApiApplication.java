package com.alexcode.eatgo;

import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.domain.repository.UserRepository;
import com.alexcode.eatgo.domain.status.UserStatus;
import com.alexcode.eatgo.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableEurekaClient
public class EatgoAdminApiApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EatgoAdminApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String email = "admin@eatgo.com";
		String name = "admin";
		String password = passwordEncoder.encode("admin");

		if(userRepository.findByEmail(email).isPresent()) return;

		User admin = User.builder()
				.email(email)
				.name(name)
				.password(password)
				.status(UserStatus.REGISTERED)
				.level(100L)
				.role(ApplicationUserRole.ADMIN)
				.createdAt(LocalDateTime.now())
				.createdBy(ApplicationUserRole.ADMIN.name())
				.build();

		userRepository.save(admin);
	}
}
