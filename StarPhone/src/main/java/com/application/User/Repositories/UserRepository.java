package com.application.User.Repositories;

import com.application.User.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface UserRepository extends JpaRepository<User, UUID> {

        Optional<User> findByEmail(String email);
        //username
        Optional<User> findByUsername(String username);

        List<User> findByActivateTrue();
}
