package com.example.mockhmsreg.rest;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mockhmsreg.service.HmsRegService;

@RequestMapping("api/v1/byggekort")
@RestController
public class HmsRegController {
    private static final Logger logger = LoggerFactory.getLogger(HmsRegController.class);
    private final HmsRegService byggeKortService;

    @Autowired
    public HmsRegController(HmsRegService byggeKortService) {
        this.byggeKortService = byggeKortService;
    }

    @PostMapping("/lagre")
    public ResponseEntity<UUID> addByggeKort(@NotNull @Valid @RequestBody HmsRegDto byggeKort)
        throws InterruptedException, IOException {
        UUID response = byggeKortService.addByggeKort(byggeKort);
        return new ResponseEntity<UUID>(response, OK);

    }

    @GetMapping
    public List<HmsRegDto> getAllByggeKort() {
        return byggeKortService.getAll();
    }

    @GetMapping(value = "/hent/byggekort/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<HmsRegDto> getByggeKort(@NotNull @PathVariable("id") UUID id) {
        HmsRegDto response;
        response = byggeKortService.getByggeKort(id);
        return (response != null)
            ? new ResponseEntity<HmsRegDto>(response,OK)
            : new ResponseEntity<HmsRegDto>( (HmsRegDto) null,NOT_FOUND);
    }

    @DeleteMapping(path = "{id}")
    public void deleteByggeKort(@NotNull @PathVariable("id") UUID id) {
        byggeKortService.deleteByggeKort(id);
    }

    @PutMapping
    public void updateByggeKort(@NotNull @PathVariable("id") UUID id,
        @NotNull @Valid @RequestBody HmsRegDto byggeKortToUpdate) {
        byggeKortService.updateByggeKort(id, byggeKortToUpdate);
    }

}
