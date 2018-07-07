package com.lukamaletin.cdss.controller;

import com.lukamaletin.cdss.controller.base.BaseController;
import com.lukamaletin.cdss.model.User;
import com.lukamaletin.cdss.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lukamaletin.cdss.util.Constants.API_USERS;

@RestController
@RequestMapping(API_USERS)
public class UserController extends BaseController<User, Long> {

    @Autowired
    public UserController(UserRepository repository) {
        super(repository);
    }
}
