package com.lukamaletin.cdss.controller;

import com.lukamaletin.cdss.model.Role;
import com.lukamaletin.cdss.model.dto.MedicRequest;
import com.lukamaletin.cdss.repository.UserRepository;
import com.lukamaletin.cdss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.lukamaletin.cdss.util.Constants.API_MEDICS;

@RestController
@RequestMapping(API_MEDICS)
public class MedicController {

    private final UserRepository userRepository;

    private final UserService userService;

    @Autowired
    public MedicController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid MedicRequest data) {
        return new ResponseEntity<>(userService.create(data), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity findAll() {
        return new ResponseEntity<>(userRepository.findAllByRole(Role.MEDIC), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid MedicRequest data, @PathVariable Long id) {
        return new ResponseEntity<>(userService.update(data, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
