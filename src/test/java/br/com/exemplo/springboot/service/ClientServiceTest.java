package br.com.exemplo.springboot.service;

import br.com.exemplo.springboot.domain.ClientDto;
import br.com.exemplo.springboot.enums.Gender;
import br.com.exemplo.springboot.repository.ClientRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.*;

import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeMethod
    public void beforeMethod(){
        initMocks(this);
    }


    @Test
    public void testGet() {
        List<ClientDto> clients = new ArrayList<>();
        ClientDto client1 = new ClientDto(1L, "vinicius", Gender.MALE);
        ClientDto client2 = new ClientDto(2L, "Maria", Gender.FEMALE);
        clients.add(client1);
        clients.add(client2);

        List<ClientDto> clients2 = this.clientService.get();

        when(clientRepository.get()).thenReturn(clients);
        verify(clientRepository).get();

        assertEquals(clients, clients2);
    }

    @Test
    public void testGetNotFound() {

    }

    @Test
    public void getByIdTest() {
//        ClientDto clientDto = new ClientDto(3L, "teste", Gender.MALE);
//        Long id = 2L;
//
//        ClientDto client = this.clientService.get(id);
//
//        when(clientRepository.getById(id)).thenReturn(clientDto);
//        verify(clientRepository).getById(id);
//
//        assertEquals(client, clientDto);
    }

    @Test
    public void testGetByName() {
    }

    @Test
    public void saveTest() {

    }

    @Test
    public void updateTest() {
    }

    @Test
    public void deleteTest() {
    }
}