package com.hackathonportal.controller;

import com.hackathonportal.model.Registration;
import com.hackathonportal.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/registrations")
@CrossOrigin(origins = "*")
public class RegistrationController {
    
    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<Registration> registerUser(@RequestBody Map<String, Long> request) {
        try {
            Long userId = request.get("userId");
            Long hackathonId = request.get("hackathonId");
            
            Registration registration = registrationService.registerUser(userId, hackathonId);
            return new ResponseEntity<>(registration, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Registration>> getRegistrationsByUser(@PathVariable Long userId) {
        List<Registration> registrations = registrationService.getRegistrationsByUser(userId);
        return new ResponseEntity<>(registrations, HttpStatus.OK);
    }

    @GetMapping("/hackathon/{hackathonId}")
    public ResponseEntity<List<Registration>> getRegistrationsByHackathon(@PathVariable Long hackathonId) {
        List<Registration> registrations = registrationService.getRegistrationsByHackathon(hackathonId);
        return new ResponseEntity<>(registrations, HttpStatus.OK);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Registration> cancelRegistration(@PathVariable Long id) {
        try {
            Registration registration = registrationService.cancelRegistration(id);
            return new ResponseEntity<>(registration, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}