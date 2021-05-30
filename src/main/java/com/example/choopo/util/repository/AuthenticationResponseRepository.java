package com.example.choopo.util.repository;

import com.example.choopo.util.model.AuthenticationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationResponseRepository extends JpaRepository<AuthenticationResponse, Long> {
//    @Query(value = "INSERT INTO temporary_token VALUE('1', :jwt)", nativeQuery = true)
//    ResponseEntity<AuthenticationResponse> jwt(String jwt);
}
