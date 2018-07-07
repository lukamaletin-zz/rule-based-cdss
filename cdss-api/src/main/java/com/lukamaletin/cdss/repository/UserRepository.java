package com.lukamaletin.cdss.repository;

import com.lukamaletin.cdss.model.Role;
import com.lukamaletin.cdss.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findAllByRole(Role role);
}
