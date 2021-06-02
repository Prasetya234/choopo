package com.example.choopo.util.repository;

import com.example.choopo.util.model.AuthenticationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface AuthenticationResponseRepository extends JpaRepository<AuthenticationResponse, Long> {

    @Query(value = "select * from temporary_token where expired_date < :expired" , nativeQuery = true)
    List<AuthenticationResponse> findDateExpired(Date expired);

}
