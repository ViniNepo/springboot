package br.com.exemplo.springboot.repository;

import br.com.exemplo.springboot.domain.ClientDto;
import br.com.exemplo.springboot.entities.Client;
import br.com.exemplo.springboot.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(
            "select new br.com.exemplo.springboot.domain.ClientDto(c.id, c.name, c.gender)from Client c" +
                    " where c.name = :name"
    )
    List<ClientDto> findByName(String name);

    @Query(
            "select new br.com.exemplo.springboot.domain.ClientDto(c.id, c.name, c.gender)from Client c"
    )
    List<ClientDto> get();

    @Query(
            "select new br.com.exemplo.springboot.domain.ClientDto(c.id, c.name, c.gender)from Client c" +
                    " where c.id = :id"
    )
    ClientDto getById(Long id);
}
