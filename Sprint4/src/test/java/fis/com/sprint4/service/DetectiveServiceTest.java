package fis.com.sprint4.service;

import fis.com.sprint4.entity.Detective;
import fis.com.sprint4.entity.Person;
import fis.com.sprint4.repository.DetectiveRepository;
import fis.com.sprint4.util.EmploymentStatus;
import fis.com.sprint4.util.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DetectiveServiceTest {

    @MockBean
    private DetectiveRepository detectiveRepository;
    @Autowired
    private DetectiveService detectiveService;

    @Test
    void addDetective() {
        Detective detective = new Detective();
        detective.setCreateAt(LocalDateTime.now());
        detective.setModifiedAt(LocalDateTime.now());
        detective.setVersion(1);
        detective.setArmed(true);
        detective.setBadgeNumber("9871263");
        detective.setRank(Rank.valueOf("SENIOR"));
        detective.setStatus(EmploymentStatus.valueOf("VACATION"));
        Person person = new Person();
        person.setId(2L);
        detective.setPerson(person);
        Mockito.when(detectiveRepository.save(detective)).thenReturn(detective);
        Assertions.assertThat(this.detectiveService.addDetective(detective)).isEqualTo(detective);
    }

    @Test
    void updateDetective() {
        Detective detective = new Detective();
        detective.setId(15L);
        detective.setCreateAt(LocalDateTime.now());
        detective.setModifiedAt(LocalDateTime.now());
        detective.setVersion(1);
        detective.setArmed(true);
        detective.setBadgeNumber("9871263");
        detective.setRank(Rank.valueOf("SENIOR"));
        detective.setStatus(EmploymentStatus.valueOf("VACATION"));
        Person person = new Person();
        person.setId(2L);
        detective.setPerson(person);
        Mockito.when(this.detectiveRepository.findById(15L)).thenReturn(Optional.of(detective));
        detective.setBadgeNumber("bug1231");
        Mockito.when(this.detectiveRepository.save(detective)).thenReturn(detective);
        Assertions.assertThat(this.detectiveService.updateDetective(detective)).isEqualTo(detective);

    }

    @Test
    void getDetectives() {
        Detective detective1 = new Detective();
        detective1.setId(12L);
        detective1.setCreateAt(LocalDateTime.now());
        detective1.setModifiedAt(LocalDateTime.now());
        detective1.setVersion(12);
        detective1.setArmed(true);
        detective1.setBadgeNumber("9871263");
        detective1.setRank(Rank.valueOf("SENIOR"));
        detective1.setStatus(EmploymentStatus.valueOf("VACATION"));
        Person person1 = new Person();
        person1.setId(1L);
        detective1.setPerson(person1);

        Detective detective2 = new Detective();
        detective2.setId(13L);
        detective2.setCreateAt(LocalDateTime.now());
        detective2.setModifiedAt(LocalDateTime.now());
        detective2.setVersion(1);
        detective2.setArmed(true);
        detective2.setBadgeNumber("9871263");
        detective2.setRank(Rank.valueOf("SENIOR"));
        detective2.setStatus(EmploymentStatus.valueOf("VACATION"));
        Person person2 = new Person();
        person2.setId(2L);
        detective2.setPerson(person2);

        Set<Detective> detectives = new HashSet<>();
        detectives.add(detective1);
        detectives.add(detective2);

        Mockito.when(this.detectiveRepository.findAll()).thenReturn((List<Detective>) detectives);
        Assertions.assertThat(this.detectiveService.getDetectives()).isEqualTo(detectives);

    }

    @Test
    void getDetective() {
        Detective detective = new Detective();
        detective.setId(15L);
        detective.setCreateAt(LocalDateTime.now());
        detective.setModifiedAt(LocalDateTime.now());
        detective.setVersion(1);
        detective.setArmed(true);
        detective.setBadgeNumber("9871263");
        detective.setRank(Rank.valueOf("SENIOR"));
        detective.setStatus(EmploymentStatus.valueOf("VACATION"));
        Person person = new Person();
        person.setId(2L);
        detective.setPerson(person);
        Mockito.when(this.detectiveRepository.findById(15L)).thenReturn(Optional.of(detective));
        Assertions.assertThat(this.detectiveService.getDetective(15L)).isEqualTo(detective);
    }

    @Test
    void deleteDetective() {
        Detective detective = new Detective();
        detective.setId(15L);
        detective.setCreateAt(LocalDateTime.now());
        detective.setModifiedAt(LocalDateTime.now());
        detective.setVersion(1);
        detective.setArmed(true);
        detective.setBadgeNumber("9871263");
        detective.setRank(Rank.valueOf("SENIOR"));
        detective.setStatus(EmploymentStatus.valueOf("VACATION"));
        Person person = new Person();
        person.setId(2L);
        detective.setPerson(person);
        Mockito.when(this.detectiveRepository.findById(15L)).thenReturn(Optional.of(detective));
        Mockito.when(this.detectiveRepository.existsById(detective.getId())).thenReturn(false);
        assertFalse(this.detectiveRepository.existsById(detective.getId()));

    }
}