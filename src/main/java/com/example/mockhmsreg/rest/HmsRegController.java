package com.example.mockhmsreg.rest;


import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mockhmsreg.service.ByggeKortService;

@RequestMapping("api/v1/byggekort")
@RestController
public class HmsRegController {
    private static final Logger logger = LoggerFactory.getLogger(HmsRegController.class);
    private final ByggeKortService byggeKortService;
    @Autowired
    public HmsRegController(ByggeKortService byggeKortService) {
        this.byggeKortService = byggeKortService;
    }
    @PostMapping
    public void addByggeKort(@RequestBody ByggeKortDto byggekort) {
        byggeKortService.addByggeKort(byggekort);

    }

    @GetMapping
    public List<ByggeKortDto> getAllByggeKort() {
        return byggeKortService.getAll();
    }
    @GetMapping(path = "{id}")
    public ByggeKortDto getByggeKort(@PathVariable("id") UUID id) {
        return byggeKortService.getByggeKort(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteByggeKort(@PathVariable("id") UUID id) {
        return byggeKortService.deleteByggeKort(id);
    }

}
