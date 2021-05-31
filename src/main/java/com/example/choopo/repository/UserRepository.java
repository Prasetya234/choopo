package com.example.choopo.repository;

import com.example.choopo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select a.* from useres a where a.username= :username", nativeQuery = true)
    User findByUsername(String username);

    @Query(value = "select a.* from useres a where a.username= :username", nativeQuery = true)
    User findByUsernameCek(String username);
}
