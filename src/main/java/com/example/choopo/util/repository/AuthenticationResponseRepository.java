package com.example.choopo.util.repository;

import com.example.choopo.util.model.AuthenticationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationResponseRepository extends JpaRepository<AuthenticationResponse, Long> {
}
