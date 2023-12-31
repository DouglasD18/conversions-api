package com.conversionsapi.domain.conversion;

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
    private String convertedCurr;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

}
