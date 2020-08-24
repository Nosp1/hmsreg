package com.example.mockhmsreg.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.mockhmsreg.repository.RegDao;
import com.example.mockhmsreg.rest.ByggeKortDto;

@Service
public class ByggeKortService {
    private final RegDao byggeKortDao;
    @Autowired
    public ByggeKortService(@Qualifier("fakeDao") RegDao byggeKortDao) {this.byggeKortDao = byggeKortDao;}

    public int addByggeKort(ByggeKortDto byggekort) {
        return byggeKortDao.insertByggeKort(byggekort);
    }
    public List<ByggeKortDto> getAll() {
        return byggeKortDao.selectAll();
    }
    public Optional<ByggeKortDto> getByggeKort(UUID id) {
        return byggeKortDao.selectByggekort(id);
    }
    public int deleteByggeKort(UUID id) {
        return byggeKortDao.deleteByggeKort(id);
    }
    public int updateByggeKort(UUID id, ByggeKortDto byggekort) {
        return byggeKortDao.updateByggeKort(id, byggekort);
    }
}
