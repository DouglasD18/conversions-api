package com.conversionsapi.controllers;

import com.conversionsapi.domain.conversion.Conversion;
import com.conversionsapi.dtos.ConversionDTO;
import com.conversionsapi.services.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/conversion")
public class ConversionController {

    @Autowired
    private ConversionService service;

    @GetMapping
    public ResponseEntity<List<Conversion>> findConversions() {
        List<Conversion> conversions = this.service.findConversions();

        return new ResponseEntity<>(conversions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createConversion(@RequestBody ConversionDTO conversionDTO) {
        try {
            Conversion conversion = this.service.createConversion(conversionDTO.realCurr(), conversionDTO.convertedCurr(), conversionDTO.realValue());

            return new ResponseEntity<>(conversion, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getMessage().contains("Moeda especificada não existe")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateConversion(@RequestBody ConversionDTO conversionDTO, @PathVariable("id") Long id) {
        try {
            Conversion conversion = this.service.updateConversion(id, conversionDTO);

            return new ResponseEntity<>(conversion, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().contains("Moeda especificada não existe")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteConversion(@PathVariable("id") Long id) {
        try {
            this.service.deleteConversion(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
