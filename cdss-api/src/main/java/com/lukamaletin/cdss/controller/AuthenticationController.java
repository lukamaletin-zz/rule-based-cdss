package com.lukamaletin.cdss.controller;

import com.lukamaletin.cdss.model.User;
import com.lukamaletin.cdss.model.dto.AuthenticationRequest;
import com.lukamaletin.cdss.service.ReasoningService;
import com.lukamaletin.cdss.service.UserService;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

import static com.lukamaletin.cdss.util.Constants.API_AUTH;

@RestController
@RequestMapping(API_AUTH)
public class AuthenticationController {

    private final UserService userService;

    private final KieBase kieBase;

    private final ReasoningService reasoningService;

    @Autowired
    public AuthenticationController(UserService userService, KieBase kieBase, ReasoningService reasoningService) {
        this.userService = userService;
        this.kieBase = kieBase;
        this.reasoningService = reasoningService;
    }

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody @Valid AuthenticationRequest dto) {
        final User user = userService.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());

        final KieSession kieSession = kieBase.newKieSession();

        final Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

        final Authentication authentication = new PreAuthenticatedAuthenticationToken(kieSession, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        reasoningService.fillSession();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/signout")
    public ResponseEntity signout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity(HttpStatus.OK);
    }
}
