package com.example.mockhmsreg.rest;

import java.util.UUID;

public class ByggeKortDto {
    private String byggeKortNummer;
    private UUID id;

    public ByggeKortDto(UUID id, String byggeKortNummer) {
        this.byggeKortNummer = byggeKortNummer;
        this.id = id;
    }

    public String getByggeKortNummer() {
        return byggeKortNummer;
    }

    public void setByggeKortNummer(String byggeKortNummer) {
        this.byggeKortNummer = byggeKortNummer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ByggeKortDto{" +
            "byggeKortNummer='" + byggeKortNummer + '\'' +
            '}';
    }
}
