package com.example.mockhmsreg.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.mockhmsreg.rest.ByggeKortDto;

@Repository("fakeDao")
public class FakeRegDataAccessService implements RegDao {
    private static List<ByggeKortDto> DB = new ArrayList<>();

    @Override
    public UUID insertByggeKort(UUID id, ByggeKortDto byggekort) {
        DB.add(new ByggeKortDto(id, byggekort.getByggeKortNummer()));
        return id;
    }

    @Override
    public Optional<ByggeKortDto> selectByggekortByStream(UUID id) {
        return DB.stream().filter(byggekort -> byggekort.getId().equals(id)).findFirst();
    }

    @Override
    public List<ByggeKortDto> selectAll() {
        return DB;
    }

    @Override
    public ByggeKortDto selectByggekort(UUID id) {
        for (ByggeKortDto d : DB) {
            if (d.getId().equals(id)) {
                return d;
            }

        }

        return new ByggeKortDto(id, "ingenbyggekort");
    }

    @Override
    public int deleteByggeKort(UUID id) {
        Optional<ByggeKortDto> byggekort = selectByggekortByStream(id);
        if (byggekort.isEmpty()) {

            return 0;
        }
        DB.remove(byggekort.get());
        return 1;
    }

    @Override
    public int updateByggeKort(UUID id, ByggeKortDto byggekort) {
        return selectByggekortByStream(id).map(b -> {
            int indexOfByggeKort = DB.indexOf(b);
            if (indexOfByggeKort >= 0) {
                DB.set(indexOfByggeKort, new ByggeKortDto(id, byggekort.getByggeKortNummer()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }

}
