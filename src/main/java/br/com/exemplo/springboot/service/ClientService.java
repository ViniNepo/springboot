package br.com.exemplo.springboot.service;

import br.com.exemplo.springboot.domain.ClientDto;
import br.com.exemplo.springboot.entities.Client;
import br.com.exemplo.springboot.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<ClientDto> get() {
        return repository.get();
    }

    public ClientDto get(Long id) {
        return repository.getById(id);
    }

    public List<ClientDto> getByName(String name) {
        return repository.findByName(name);
    }

    public ClientDto save(ClientDto dto) {
        Client client = new Client(dto.getId(), dto.getName(), dto.getGender());
        repository.save(client);
        return dto;
    }

    public void update(ClientDto dto) {
        Client c = new Client();
        c.setId(dto.getId());
        c.setName(dto.getName());
        c.setGender(dto.getGender());

        repository.save(c);
    }

    public void delete(Long id) {
        Client client = repository.findById(id).get();
        repository.delete(client);
    }
}
