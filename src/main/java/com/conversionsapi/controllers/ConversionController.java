package com.conversionsapi.controllers;

import com.conversionsapi.domain.conversion.Conversion;
import com.conversionsapi.services.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/")
public class ConversionController {

    @Autowired
    private ConversionService service;

    @GetMapping
    public ResponseEntity<List<Conversion>> findConversions() {
        List<Conversion> conversions = this.service.findConversions();

        return new ResponseEntity<>(conversions, HttpStatus.OK);
    }

}
