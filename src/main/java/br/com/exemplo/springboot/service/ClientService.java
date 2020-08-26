package br.com.exemplo.springboot.service;

import br.com.exemplo.springboot.domain.ClientDto;
import br.com.exemplo.springboot.entities.Client;
import br.com.exemplo.springboot.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ClientDto> get() {
        List<Client> clients = repository.findAll();
        return clients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ClientDto get(Long id) {
        if(id == null) {
            throw new NullPointerException("O id está vazio");
        }

        Optional<Client> client = repository.findById(id);
        return convertToDto(client.get());
    }

    public List<ClientDto> getByName(String name) {
        if(name == null) {
            throw new NullPointerException("O nome de pesquisa esta vazio");
        }

        List<Client> clients = repository.findByName(name);
        return clients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ClientDto save(ClientDto dto) throws ParseException {
        if(dto == null) {
            throw new NullPointerException("O DTO está vazio");
        }

        Client client = convertToEntity(dto);
        return convertToDto(repository.save(client));
    }

    public ClientDto update(Long id, ClientDto dto) throws ParseException {
        if(dto == null) {
            throw new NullPointerException("O DTO está vazio");
        }
        if(id == null) {
            throw new NullPointerException("O id está vazio");
        }

        Client client = repository.findById(id).get();
        System.out.println(client.getId());
        client.setName(dto.getName());
        client.setGender(dto.getGender());

        return convertToDto(repository.save(client));
    }

    public String delete(Long id) {
        if(id == null) {
            throw new NullPointerException("O id esta vazio");
        }

        Client client = repository.findById(id).get();
        repository.delete(client);
        return "Cliente deletado!";
    }

    private ClientDto convertToDto(Client client) {

        ClientDto clientDto = modelMapper.map(client, ClientDto.class);
        return clientDto;
    }

    private Client convertToEntity(ClientDto clientDto) throws ParseException {
        Client client = modelMapper.map(clientDto, Client.class);
        return client;
    }
}
