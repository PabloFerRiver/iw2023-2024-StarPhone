package com.application.user.unit.user;


import com.application.User.Entities.User;
import com.application.User.Entities.Role;
import com.application.Contract.Entities.Contract;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testId() {
        UUID id = UUID.randomUUID();
        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    public void testDNI() {
        String dni = "12345678A";
        user.setDNI(dni);
        assertEquals(dni, user.getDNI());
    }

    @Test
    public void testName() {
        String name = "John";
        user.setName(name);
        assertEquals(name, user.getName());
    }

    @Test
    public void testSurname() {
        String surname = "Doe";
        user.setSurname(surname);
        assertEquals(surname, user.getSurname());
    }

    @Test
    public void testUsername() {
        String username = "johndoe";
        user.setUsername(username);
        assertEquals(username, user.getUsername());
    }

    @Test
    public void testCity() {
        String city = "Madrid";
        user.setCity(city);
        assertEquals(city, user.getCity());
    }

    @Test
    public void testCountry() {
        String country = "Spain";
        user.setCountry(country);
        assertEquals(country, user.getCountry());
    }

    @Test
    public void testBirthdate() {
        LocalDate birthdate = LocalDate.now();
        user.setBirthdate(birthdate);
        assertEquals(birthdate, user.getBirthdate());
    }

    @Test
    public void testPhoneNumber() {
        Integer phoneNumber = 123456789;
        user.setPhoneNumber(phoneNumber);
        assertEquals(phoneNumber, user.getPhoneNumber());
    }

    @Test
    public void testEmail() {
        String email = "johndoe@example.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testPassword() {
        String password = "password";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testActivate() {
        user.setActivate(true);
        assertTrue(user.getActivate());
    }

    @Test
    public void testActivateCode() {
        String activateCode = "code";
        user.setActivateCode(activateCode);
        assertEquals(activateCode, user.getActivateCode());
    }

    @Test
    public void testRegisterDate() {
        LocalDate registerDate = LocalDate.now();
        user.setRegisterDate(registerDate);
        assertEquals(registerDate, user.getRegisterDate());
    }

    @Test
    public void testRoles() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        user.setRoles(roles);
        assertEquals(roles, user.getRoles());
    }

    @Test
    public void testContracts() {
        List<Contract> contracts = new ArrayList<>();
        contracts.add(new Contract());
        user.setContracts(contracts);
        assertEquals(contracts, user.getContracts());
    }
}