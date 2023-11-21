package com.conversionsapi.services;

import com.conversionsapi.domain.conversion.Conversion;
import com.conversionsapi.repositories.ConversionsRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversionService {

    @Autowired
    private ConversionsRepositorie repositorie;

    public Optional<List<Conversion>> findConversions() {
        Optional<List<Conversion>> conversions = this.repositorie.findConversions();
        return conversions;
    }

}
