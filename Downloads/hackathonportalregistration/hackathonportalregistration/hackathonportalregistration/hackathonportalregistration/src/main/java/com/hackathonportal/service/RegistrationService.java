package com.hackathonportal.service;

import com.hackathonportal.model.Hackathon;
import com.hackathonportal.model.Registration;
import com.hackathonportal.model.Registration.RegistrationStatus;
import com.hackathonportal.model.User;
import com.hackathonportal.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {
    
    @Autowired
    private RegistrationRepository registrationRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private HackathonService hackathonService;

    public Registration registerUser(Long userId, Long hackathonId) {
        User user = userService.getUserById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        Hackathon hackathon = hackathonService.getHackathonById(hackathonId)
            .orElseThrow(() -> new RuntimeException("Hackathon not found"));

        // Check if user is already registered
        Optional<Registration> existingRegistration = registrationRepository
            .findByUserUserIdAndHackathonHackathonId(userId, hackathonId);
        
        if (existingRegistration.isPresent()) {
            throw new RuntimeException("User already registered for this hackathon");
        }

        // Check capacity
        long currentParticipants = registrationRepository.countRegisteredParticipants(hackathonId);
        
        Registration registration = new Registration();
        registration.setUser(user);
        registration.setHackathon(hackathon);
        
        if (currentParticipants >= hackathon.getMaxParticipants()) {
            registration.setStatus(RegistrationStatus.WAITLISTED);
        } else {
            registration.setStatus(RegistrationStatus.REGISTERED);
        }
        
        return registrationRepository.save(registration);
    }

    public List<Registration> getRegistrationsByUser(Long userId) {
        return registrationRepository.findByUserUserId(userId);
    }

    public List<Registration> getRegistrationsByHackathon(Long hackathonId) {
        return registrationRepository.findByHackathonHackathonId(hackathonId);
    }

    public Registration cancelRegistration(Long registrationId) {
        Registration registration = registrationRepository.findById(registrationId)
            .orElseThrow(() -> new RuntimeException("Registration not found"));
        
        registration.setStatus(RegistrationStatus.CANCELLED);
        return registrationRepository.save(registration);
    }
}