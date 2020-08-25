package com.example.mockhmsreg.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.mockhmsreg.rest.HmsRegDto;

@Repository("fakeDao")
public class FakeRegDataAccessService implements RegDao {
    private static List<HmsRegDto> DB = new ArrayList<>();

    @Override
    public UUID insertByggeKort(UUID id, HmsRegDto byggekort) {
        DB.add(new HmsRegDto(id, byggekort.getByggeKortNummer()));
        return id;
    }

    @Override
    public Optional<HmsRegDto> selectByggekortByStream(UUID id) {
        return DB.stream().filter(byggekort -> byggekort.getId().equals(id)).findFirst();
    }

    @Override
    public List<HmsRegDto> selectAll() {
        return DB;
    }

    @Override
    public HmsRegDto selectByggekort(UUID id){
        HmsRegDto response = null;
        for (HmsRegDto d : DB) {
            if (d.getId().equals(id)) {
                response = d;
            }

        }
        // TODO: 25/08/2020 returne http.status.error??? 
        return response;
    }

    @Override
    public int deleteByggeKort(UUID id) {
        Optional<HmsRegDto> byggekort = selectByggekortByStream(id);
        if (byggekort.isEmpty()) {

            return 0;
        }
        DB.remove(byggekort.get());
        return 1;
    }

    @Override
    public int updateByggeKort(UUID id, HmsRegDto byggekort) {
        return selectByggekortByStream(id).map(b -> {
            int indexOfByggeKort = DB.indexOf(b);
            if (indexOfByggeKort >= 0) {
                DB.set(indexOfByggeKort, new HmsRegDto(id, byggekort.getByggeKortNummer()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }

}
