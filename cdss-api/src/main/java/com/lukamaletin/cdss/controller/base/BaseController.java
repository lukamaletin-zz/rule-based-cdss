package com.lukamaletin.cdss.controller.base;

import com.lukamaletin.cdss.controller.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public abstract class BaseController<T, ID> {

    protected final JpaRepository<T, ID> repository;

    public BaseController(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid T data) {
        return new ResponseEntity<>(repository.save(data), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity findAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable ID id) {
        return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid T data, @PathVariable ID id) {
        repository.findById(id).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(repository.save(data), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable ID id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
