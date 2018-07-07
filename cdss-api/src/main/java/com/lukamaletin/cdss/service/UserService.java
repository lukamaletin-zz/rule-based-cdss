package com.lukamaletin.cdss.service;

import com.lukamaletin.cdss.controller.exception.NotFoundException;
import com.lukamaletin.cdss.model.User;
import com.lukamaletin.cdss.model.dto.MedicRequest;
import com.lukamaletin.cdss.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUsernameAndPassword(String username, String password) {
        final Optional<User> user = userRepository.findByUsername(username);

        if (!user.isPresent() || !passwordEncoder.matches(password, user.get().getPassword())) {
            throw new NotFoundException("Wrong username or password!");
        }

        return user.get();
    }

    public User create(MedicRequest data) {
        final User user = data.newUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(MedicRequest data, Long id) {
        final User persisted = userRepository.findById(id).orElseThrow(NotFoundException::new);
        final User user = data.updatedUser(persisted);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
