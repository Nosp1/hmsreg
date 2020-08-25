package com.example.mockhmsreg.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.mockhmsreg.rest.ByggeKortDto;

public interface RegDao {
    UUID insertByggeKort(UUID id, ByggeKortDto byggekort);

    default UUID insertByggeKort(ByggeKortDto byggekort) {
        UUID id = UUID.randomUUID();
        return insertByggeKort(id, byggekort);
    }
    List<ByggeKortDto> selectAll();

    Optional<ByggeKortDto> selectByggekortByStream(UUID id);
    ByggeKortDto selectByggekort(UUID id);
    int deleteByggeKort(UUID id);
    int updateByggeKort(UUID id, ByggeKortDto byggekort);
}
