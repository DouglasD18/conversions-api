package com.conversionsapi.repositories;

import com.conversionsapi.domain.conversion.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversionRepositorie extends JpaRepository<Conversion, Long> {
}
