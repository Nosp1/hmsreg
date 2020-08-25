package com.example.mockhmsreg.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.mockhmsreg.rest.HmsRegDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FakeRegDataAccessServiceTest {

    @Autowired
    FakeRegDataAccessService fakeRegDataAccessService;

    @Before
    public void setUp() throws Exception {
        UUID id = UUID.fromString("66d7a408-e6f1-11ea-adc1-0242ac120002");
        fakeRegDataAccessService.insertByggeKort(id, new HmsRegDto(id, "1234"));
    }

    @Test
    public void skalFinneByggeKort() {
        //arrange
        UUID id = UUID.fromString("66d7a408-e6f1-11ea-adc1-0242ac120002");
        //act
        HmsRegDto byggeKort = fakeRegDataAccessService.selectByggekort(id);
        //assert
        assertThat(byggeKort.getByggeKortNummer()).isEqualTo("1234");
        assertThat(byggeKort.getId()).isEqualTo(id);
    }


}
