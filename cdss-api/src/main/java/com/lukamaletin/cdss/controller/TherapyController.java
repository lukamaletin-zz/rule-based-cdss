package com.lukamaletin.cdss.controller;

import com.lukamaletin.cdss.controller.base.BaseController;
import com.lukamaletin.cdss.model.Therapy;
import com.lukamaletin.cdss.repository.TherapyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lukamaletin.cdss.util.Constants.API_THERAPIES;

@RestController
@RequestMapping(API_THERAPIES)
public class TherapyController extends BaseController<Therapy, Long> {

    @Autowired
    public TherapyController(TherapyRepository repository) {
        super(repository);
    }
}
