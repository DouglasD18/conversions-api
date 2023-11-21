package com.conversionsapi.services;

import com.conversionsapi.domain.conversion.Conversion;
import com.conversionsapi.repositories.ConversionRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversionService {

    @Autowired
    private ConversionRepositorie repositorie;

    public List<Conversion> findConversions() {
        return this.repositorie.findAll();
    }

}
