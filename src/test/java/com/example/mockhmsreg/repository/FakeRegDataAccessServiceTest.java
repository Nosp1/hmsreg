package com.example.mockhmsreg.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.Assert;
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

    @Test
    public void skalLagreByggeKort() {
        //arrange
        UUID id = UUID.randomUUID();
        HmsRegDto byggekort = new HmsRegDto(id, "6789");
        //act
        UUID response = fakeRegDataAccessService.insertByggeKort(id, byggekort);
        List<HmsRegDto> dbList = FakeRegDataAccessService.getDB();
        //assert
        assertThat(fakeRegDataAccessService.selectAll().size()).isEqualTo(4);
        assertThat(dbList.size()).isEqualTo(4);
        assertThat(response.equals(id));
        Assert.assertNotNull(byggekort);
    }

    @Test
    public void skalOppdatereByggeKort() {
        //arrange
        UUID id = UUID.randomUUID();
        HmsRegDto byggeKortToUpdate = new HmsRegDto(id, "9999");
        HmsRegDto newByggeKort = new HmsRegDto(id, "8888");
        //act
        UUID response = fakeRegDataAccessService.insertByggeKort(id, byggeKortToUpdate);
        Integer numberToReturn = fakeRegDataAccessService.updateByggeKort(id, newByggeKort);
        //asserts
        assertThat(response.equals(id));
        Assert.assertNotNull(numberToReturn);
        assertThat(fakeRegDataAccessService.selectByggekort(id).getByggeKortNummer()
            .equals(newByggeKort.getByggeKortNummer()));
    }

    @Test
    public void skalSletteByggeKort() {
        //arrange
        UUID id = UUID.randomUUID();
        HmsRegDto byggeKortToDelete = new HmsRegDto(id, "7777");
        fakeRegDataAccessService.insertByggeKort(id, byggeKortToDelete);
        //act
        Integer response = fakeRegDataAccessService.deleteByggeKort(id);
        //assert
        assertThat(response.equals(1));
        Assert.assertNull(fakeRegDataAccessService.selectByggekort(id));
    }
}
