package com.application.User;

import com.application.User.Entities.Role;
import com.application.User.Entities.User;
import com.application.User.Services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatabasePopulator implements CommandLineRunner {
    UserService userservice;

    public DatabasePopulator(UserService userservice) {
        this.userservice = userservice;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userservice.count() == 0) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword("admin");
            user.setName("admin");
            user.setSurname("admin");
            user.setEmail("admin@gmail.com");
            user.setDNI("12345678A");
            user.setCity("Madrid");
            user.setCountry("España");
            user.setBirthdate(LocalDate.of(1999, 1, 1));
            user.setPhoneNumber(123456789);
            user.addRole(Role.ADMIN);
            userservice.registerUserByAdmin(user);
        }
    }
}
