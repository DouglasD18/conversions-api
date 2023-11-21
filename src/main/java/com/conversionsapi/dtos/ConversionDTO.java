package com.conversionsapi.dtos;

import java.math.BigDecimal;

public record ConversionDTO(String realCurr, String convertedCurr, BigDecimal realValue) {
}
