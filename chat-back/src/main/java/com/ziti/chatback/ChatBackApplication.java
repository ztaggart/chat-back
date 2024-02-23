package com.ziti.chatback;

import com.ziti.chatback.entities.Conversation;
import com.ziti.chatback.entities.Message;
import com.ziti.chatback.entities.Role;
import com.ziti.chatback.entities.User;
import com.ziti.chatback.repositories.ConversationRepository;
import com.ziti.chatback.repositories.MessageRepository;
import com.ziti.chatback.repositories.RoleRepository;
import com.ziti.chatback.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EntityScan(basePackages = { "com.ziti.chatback.entities" })
@EnableJpaRepositories(basePackages = { "com.ziti.chatback.repositories" })
public class ChatBackApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChatBackApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, ConversationRepository conversationRepository, MessageRepository messageRepository) {
		return args -> {
			if (roleRepository.findRoleByAuthority("ADMIN").isPresent()) {
				return;
			}
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			Role userRole = roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			User admin = new User("admin", passwordEncoder.encode("password"), roles);
			User saved = userRepository.save(admin);
			System.out.println("saved id");
			System.out.println(saved.getUserId());

			User user = new User("user", passwordEncoder.encode("password"), Set.of(userRole));
			userRepository.save(user);

			Set<User> users = new HashSet<>();
			users.add(admin);
			users.add(user);

			Conversation conversation =  new Conversation(users);
			conversationRepository.save(conversation);

			Message message = new Message(admin, "hello", conversation, Timestamp.from(Instant.now()));
			messageRepository.save(message);
		};
	}

}
