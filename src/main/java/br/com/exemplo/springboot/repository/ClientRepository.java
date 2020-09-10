package br.com.exemplo.springboot.repository;

import br.com.exemplo.springboot.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByName(String name);

    boolean existsById(Long id);
}
