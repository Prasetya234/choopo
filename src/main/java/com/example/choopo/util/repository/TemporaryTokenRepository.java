package com.example.choopo.util.repository;

import com.example.choopo.util.model.TemporaryToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TemporaryTokenRepository extends JpaRepository<TemporaryToken, Long> {

    @Query(value = "select * from temporary_token where token= :tkn and expired_date > :expired", nativeQuery = true)
    TemporaryToken checkValidateToken(String tkn, Date expired);

    @Query(value = "select * from temporary_token where token= :token1", nativeQuery = true)
    TemporaryToken cekToken(String token1);

    @Query(value = "select * from temporary_token where expired_date < :expired" , nativeQuery = true)
    List<TemporaryToken> findDateExpired(Date expired);

    @Modifying
    @Query(value = "UPDATE temporary_token SET expired_date= :expired234 WHERE token= :token234", nativeQuery = true)
    void updateToken(Date expired234, String token234);
}
