package com.conversionsapi.services;

import com.conversionsapi.domain.conversion.Conversion;
import com.conversionsapi.dtos.ConversionDTO;
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
        String realCurrToUpper = realCurr.toUpperCase();
        String convertedCurrToUpper = convertedCurr.toUpperCase();

        Float bid = this.request(realCurrToUpper, convertedCurrToUpper);

        BigDecimal convertedValue = realValue.multiply(BigDecimal.valueOf(bid));

        Conversion conversion = new Conversion();
        conversion.setRealValue(realValue);
        conversion.setConvertCurr(convertedCurrToUpper);
        conversion.setRealCurr(realCurrToUpper);
        conversion.setConvertedValue(convertedValue);
        conversion.setCreatedAt(LocalDateTime.now());
        conversion.setUpdateAt(LocalDateTime.now());

        return this.repositorie.save(conversion);
    }

    public Conversion findConversionById(Long id) throws Exception {
        return this.repositorie.findControllerById(id).orElseThrow(() -> new Exception("Converção não encontrada."));
    }

    public Conversion updateConversion(Long id, ConversionDTO conversionDTO) throws Exception {
        String realCurrToUpper = conversionDTO.realCurr().toUpperCase();
        String convertedCurrToUpper = conversionDTO.convertedCurr().toUpperCase();

        Conversion conversion = this.findConversionById(id);
        Float bid = this.request(realCurrToUpper, convertedCurrToUpper);

        conversion.setRealCurr(realCurrToUpper);
        conversion.setConvertCurr(convertedCurrToUpper);
        conversion.setRealValue(conversionDTO.realValue());
        conversion.setConvertedValue(conversionDTO.realValue().multiply(BigDecimal.valueOf(bid)));
        conversion.setUpdateAt(LocalDateTime.now());

        return this.repositorie.save(conversion);
    }

    public void deleteConversion(Long id) throws Exception {
        Conversion conversion = this.findConversionById(id);

        this.repositorie.delete(conversion);
    }

}
