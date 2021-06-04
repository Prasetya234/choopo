package com.example.choopo.repository;

import com.example.choopo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select a.* from useres a where a.username= :username", nativeQuery = true)
    User findByUsername(String username);

    @Query(value = "select b.* from useres b where b.username= :username1 and b.password= :password1", nativeQuery = true)
    User login(String username1, String password1);
}
