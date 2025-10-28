package com.hackathonportal.repository;

import com.hackathonportal.model.Registration;
import com.hackathonportal.model.Registration.RegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByUserUserId(Long userId);
    List<Registration> findByHackathonHackathonId(Long hackathonId);
    
    @Query("SELECT COUNT(r) FROM Registration r WHERE r.hackathon.hackathonId = :hackathonId AND r.status = 'REGISTERED'")
    long countRegisteredParticipants(Long hackathonId);
    
    Optional<Registration> findByUserUserIdAndHackathonHackathonId(Long userId, Long hackathonId);
}