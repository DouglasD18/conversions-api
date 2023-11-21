package com.conversionsapi.services;

import com.conversionsapi.domain.conversion.Conversion;
import com.conversionsapi.repositories.ConversionRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConversionService {

    @Autowired
    private ConversionRepositorie repositorie;

    @Autowired
    private RequestService requestService;

    public List<Conversion> findConversions() {
        return this.repositorie.findAll();
    }

    private Float request(String realCurr, String convertedCurr) throws Exception {
        return this.requestService.convert(realCurr, convertedCurr);
    }

    public Conversion createConversion(String realCurr, String convertedCurr, BigDecimal realValue) throws Exception {
        Float bid = this.request(realCurr, convertedCurr);

        BigDecimal convertedValue = realValue.multiply(BigDecimal.valueOf(bid));

        Conversion conversion = new Conversion();
        conversion.setRealValue(realValue);
        conversion.setConvertCurr(convertedCurr);
        conversion.setRealCurr(realCurr);
        conversion.setConvertedValue(convertedValue);
        conversion.setCreatedAt(LocalDateTime.now());
        conversion.setUpdateAt(LocalDateTime.now());

        return this.repositorie.save(conversion);
    }

}
