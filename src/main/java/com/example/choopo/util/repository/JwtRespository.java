package com.example.choopo.util.repository;

import com.example.choopo.util.model.AuthenticationResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtRespository extends JpaRepository<AuthenticationResponse,Long> {
}
