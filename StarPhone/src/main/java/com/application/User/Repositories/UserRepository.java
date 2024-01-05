package com.application.User.Repositories;

import com.application.User.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

        Optional<User> findByEmail(String email);

        Optional<User> findByUsername(String username);

        Optional<User> findByDNI(String DNI);

        List<User> findByActivateTrue();

        @Query("SELECT u.DNI FROM User u WHERE u.email LIKE %:email%")
        List<String> findDNIByEmailPart(@Param("email") String emailPart);

        @Query("SELECT u.email FROM User u WHERE u.email LIKE %:email%")
        List<String> findFullEmailByEmailPart(@Param("email") String emailPart);

        @Query("SELECT u.username FROM User u WHERE u.username LIKE %:username%")
        List<String> findFullUsernameByUsernamePart(@Param("username") String partusername);
}
