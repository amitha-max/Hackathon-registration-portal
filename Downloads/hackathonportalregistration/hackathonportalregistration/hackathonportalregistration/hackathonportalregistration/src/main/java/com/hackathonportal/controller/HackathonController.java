package com.hackathonportal.controller;

import com.hackathonportal.model.Hackathon;
import com.hackathonportal.service.HackathonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hackathons")
@CrossOrigin(origins = "*")
public class HackathonController {
    
    @Autowired
    private HackathonService hackathonService;

    @PostMapping
    public ResponseEntity<Hackathon> createHackathon(@Valid @RequestBody Hackathon hackathon) {
        Hackathon createdHackathon = hackathonService.createHackathon(hackathon);
        return new ResponseEntity<>(createdHackathon, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Hackathon>> getAllHackathons() {
        List<Hackathon> hackathons = hackathonService.getAllHackathons();
        return new ResponseEntity<>(hackathons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hackathon> getHackathonById(@PathVariable Long id) {
        return hackathonService.getHackathonById(id)
            .map(hackathon -> new ResponseEntity<>(hackathon, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hackathon> updateHackathon(@PathVariable Long id, @Valid @RequestBody Hackathon hackathon) {
        try {
            Hackathon updatedHackathon = hackathonService.updateHackathon(id, hackathon);
            return new ResponseEntity<>(updatedHackathon, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHackathon(@PathVariable Long id) {
        try {
            hackathonService.deleteHackathon(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}