package br.com.exemplo.springboot.repository;

import br.com.exemplo.springboot.entities.Client;
import br.com.exemplo.springboot.enums.Gender;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    ClientRepository clientRepository;

    private final String NAME = "Gersialdo";
    private final Long ID = 2L;
    private final Gender GENDER = Gender.MALE;
    
    @Test
    public void findByName() {
        
    }

    @Test
    void findByName_NotInDB() {

    }

    @Test
    void findById() {

    }


    @Test
    void findById_NotInDB() {

    }
}