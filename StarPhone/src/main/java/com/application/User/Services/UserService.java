package com.application.User.Services;

import com.application.User.Entities.Role;
import com.application.User.Entities.User;
import com.application.User.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository uRepository, EmailService eService, PasswordEncoder pEncoder) {

        this.userRepository = uRepository;
        this.emailService = eService;
        this.passwordEncoder = pEncoder;
    }

    public boolean registerUser(User user) {
        // TODO: Crear contraro de usuario y asociar una línea en caso de que el número
        // introducido sea válido, hacer validador de número de teléfono
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivateCode(UUID.randomUUID().toString().substring(0, 8));
        user.setRegisterDate(LocalDate.now());

        try {
            userRepository.save(user);
            emailService.sendActivateEmail(user);
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    public boolean registerUserByAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivateCode("");
        user.setActivate(true);
        user.setRegisterDate(LocalDate.now());
        try {
            userRepository.save(user);
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    public boolean saveUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    @Transactional
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Usuario no presente con nombre: " + username);
        } else {
            return user.get();
        }

    }

    public boolean activateUserCode(String email, String registerCode) {

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent() && user.get().getActivateCode().equals(registerCode)) {
            user.get().setActivate(true);
            user.get().addRole(Role.CUSTOMER);
            user.get().setActivateCode("");
            userRepository.save(user.get());
            return true;

        } else {
            return false;
        }
    }

    public User loadUserByEmail(String email) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            return user.get();
        } else {
            return new User(); // Devolver un usuario vacío, como no encontrado
        }
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public boolean isActivated(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get().getActivate();
        } else {
            return false;
        }
    }

    public List<String> getDNIByEmail(String emailPart) {
        return userRepository.findDNIByEmailPart(emailPart);
    }

    public List<String> getFullEmailByEmailPart(String emailPart) {
        return userRepository.findFullEmailByEmailPart(emailPart);
    }

    public List<String> getFullUsernameByPartUsername(String partusername) {
        return userRepository.findFullUsernameByUsernamePart(partusername);
    }

    public User getUserByDNI(String dni) {
        Optional<User> user = userRepository.findByDNI(dni);
        if (user.isPresent()) {
            return user.get();
        } else {
            return new User();
        }
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            return new User();
        }
    }

    public boolean deleteByDNI(String dni) {
        User u = getUserByDNI(dni);
        System.out.println(u.getId());
        if (u.getId() != null) {
            userRepository.delete(u);
            return true;
        } else {
            return false;
        }
    }

    public void giveRole(String username, Role role) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            user.get().addRole(role);
            userRepository.save(user.get());
        }
    }

    public void removeRole(String username, Role role) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            System.out.println("Eliminando rol " + role + " a usuario " + username);
            user.get().getRoles().remove(role);
            userRepository.save(user.get());
        }
    }

    public int count() {
        return (int) userRepository.count();
    }

}
