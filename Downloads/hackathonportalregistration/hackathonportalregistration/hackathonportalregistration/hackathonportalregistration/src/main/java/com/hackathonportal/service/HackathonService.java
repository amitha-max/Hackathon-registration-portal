package com.hackathonportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathonportal.model.Hackathon;
import com.hackathonportal.repository.HackathonRepository;

@Service
public class HackathonService {
    
    @Autowired
    private HackathonRepository hackathonRepository;

    public Hackathon createHackathon(Hackathon hackathon) {
        return hackathonRepository.save(hackathon);
    }

    public List<Hackathon> getAllHackathons() {
        return hackathonRepository.findAll();
    }

    public Optional<Hackathon> getHackathonById(Long id) {
        return hackathonRepository.findById(id);
    }

    public Hackathon updateHackathon(Long id, Hackathon hackathon) {
        if (hackathonRepository.existsById(id)) {
            hackathon.setHackathonId(id);
            return hackathonRepository.save(hackathon);
        } else {
            throw new RuntimeException("Hackathon not found with id: " + id);
        }
    }

    public void deleteHackathon(Long id) {
        if (hackathonRepository.existsById(id)) {
            hackathonRepository.deleteById(id);
        } else {
            throw new RuntimeException("Hackathon not found with id: " + id);
        }
    }
}
