package com.example.registration.repository;

import com.example.registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByActivationToken_Value(String activationToken);

//    @Modifying
//    @Query(value = "update Users u set u.is_enabled = true where u.username = ?1", nativeQuery = true)
//    void enableUser(String username);


}
