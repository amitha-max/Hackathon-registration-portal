package com.hackathonportal.config;

import com.hackathonportal.model.Hackathon;
import com.hackathonportal.model.User;
import com.hackathonportal.repository.HackathonRepository;
import com.hackathonportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HackathonRepository hackathonRepository;

    @Override
    public void run(String... args) throws Exception {
        // Initialize sample data only if database is empty
        if (userRepository.count() == 0) {
            // Create sample users
            User user1 = new User();
            user1.setName("Alice Johnson");
            user1.setEmail("alice@example.com");
            user1.setContactNo("1234567890");
            userRepository.save(user1);

            User user2 = new User();
            user2.setName("Bob Smith");
            user2.setEmail("bob@example.com");
            user2.setContactNo("0987654321");
            userRepository.save(user2);

            // Create sample hackathons
            Hackathon hackathon1 = new Hackathon();
            hackathon1.setTitle("AI Innovation Challenge");
            hackathon1.setTheme("Artificial Intelligence");
            hackathon1.setDate(LocalDate.of(2024, 3, 15));
            hackathon1.setMaxParticipants(50);
            hackathonRepository.save(hackathon1);

            Hackathon hackathon2 = new Hackathon();
            hackathon2.setTitle("Web Development Sprint");
            hackathon2.setTheme("Full Stack Development");
            hackathon2.setDate(LocalDate.of(2024, 4, 20));
            hackathon2.setMaxParticipants(30);
            hackathonRepository.save(hackathon2);

            System.out.println("Sample data initialized successfully!");
        }
    }
}