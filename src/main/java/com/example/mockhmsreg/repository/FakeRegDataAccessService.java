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
    public int insertByggeKort(UUID id, ByggeKortDto byggekort) {
        DB.add(new ByggeKortDto(id, byggekort.getByggeKortNummer()));
        return 1;
    }

    @Override
    public List<ByggeKortDto> selectAll() {
        return DB;
    }

    @Override
    public Optional<ByggeKortDto> selectByggekort(UUID id) {
        return DB.stream().filter(byggekort -> byggekort.getByggeKortNummer().equals(id)).findFirst();
    }

    @Override
    public int deleteByggeKort(UUID id) {
        Optional<ByggeKortDto> byggekort =  selectByggekort(id);
        if (byggekort.isEmpty()) {

            return 0;
        }
        DB.remove(byggekort.get());
        return 1;
    }

    @Override
    public int updateByggeKort(UUID id, ByggeKortDto byggekort) {
        return selectByggekort(id).map(b -> {
            int indexOfByggeKort = DB.indexOf(b);
            if (indexOfByggeKort >= 0) {
                DB.set(indexOfByggeKort, new ByggeKortDto(id,byggekort.getByggeKortNummer()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }




}
