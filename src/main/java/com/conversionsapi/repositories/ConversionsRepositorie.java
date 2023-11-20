package com.conversionsapi.repositories;

import com.conversionsapi.domain.conversion.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversionsRepositorie extends JpaRepository<Conversion, Long> {
    Optional<List<Conversion>> findConversions();
}
