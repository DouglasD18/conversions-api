package com.conversionsapi.domain.conversion;

import com.conversionsapi.dtos.ConversionDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="conversions")
@Table(name="conversions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Conversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal realValue;
    private String realCurr;
    private BigDecimal convertedValue;
    private String convertCurr;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public Conversion(ConversionDTO conversion) {
        this.realCurr = conversion.realCurr();
        this.realValue = conversion.realValue();
        this.convertCurr = conversion.convertedCurr();
        this.createdAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

}
