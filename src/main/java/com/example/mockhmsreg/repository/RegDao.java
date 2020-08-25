package com.example.mockhmsreg.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.mockhmsreg.rest.HmsRegDto;

public interface RegDao {
    UUID insertByggeKort(UUID id, HmsRegDto byggekort);

    default UUID insertByggeKort(HmsRegDto byggekort) {
        UUID id = UUID.randomUUID();
        return insertByggeKort(id, byggekort);
    }
    List<HmsRegDto> selectAll();

    Optional<HmsRegDto> selectByggekortByStream(UUID id);
    HmsRegDto selectByggekort(UUID id);
    int deleteByggeKort(UUID id);
    int updateByggeKort(UUID id, HmsRegDto byggekort);
}
