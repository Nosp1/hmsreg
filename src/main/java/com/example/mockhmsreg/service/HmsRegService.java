package com.example.mockhmsreg.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.mockhmsreg.repository.RegDao;
import com.example.mockhmsreg.rest.HmsRegDto;

@Service
public class HmsRegService {
    private final RegDao byggeKortDao;

    @Autowired
    public HmsRegService(@Qualifier("fakeDao") RegDao byggeKortDao) {this.byggeKortDao = byggeKortDao;}

    public UUID addByggeKort(HmsRegDto byggekort) {
        return byggeKortDao.insertByggeKort(byggekort);
    }

    public List<HmsRegDto> getAll() {
        return byggeKortDao.selectAll();
    }

    public  HmsRegDto getByggeKort(UUID id) {
        return byggeKortDao.selectByggekort(id);
    }

    public void deleteByggeKort(UUID id) {
        byggeKortDao.deleteByggeKort(id);
    }

    public void updateByggeKort(UUID id, HmsRegDto byggekort) {
        byggeKortDao.updateByggeKort(id, byggekort);
    }
}
