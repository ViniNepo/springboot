package br.com.exemplo.springboot.service;

import br.com.exemplo.springboot.domain.ClientDto;
import br.com.exemplo.springboot.entities.Client;
import br.com.exemplo.springboot.enums.Gender;
import br.com.exemplo.springboot.repository.ClientRepository;
import javassist.NotFoundException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

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
        ReflectionTestUtils.setField(clientService, "modelMapper", new ModelMapper());

        List<Client> clients = new ArrayList<>();
        Client client1 = new Client(1L, "vinicius", Gender.MALE);
        Client client2 = new Client(2L, "Maria", Gender.FEMALE);
        clients.add(client1);
        clients.add(client2);

        when(clientRepository.findAll()).thenReturn(clients);
        List<ClientDto> clientsDto = this.clientService.get();
        verify(clientRepository).findAll();

        assertEquals(clientsDto.get(0).getId(), clients.get(0).getId());
        assertEquals(clientsDto.get(0).getName(), clients.get(0).getName());
        assertEquals(clientsDto.get(0).getGender(), clients.get(0).getGender());

        assertEquals(clientsDto.get(1).getId(), clients.get(1).getId());
        assertEquals(clientsDto.get(1).getName(), clients.get(1).getName());
        assertEquals(clientsDto.get(1).getGender(), clients.get(1).getGender());
    }

    @Test
    public void getByIdTest() throws NotFoundException {
        ReflectionTestUtils.setField(clientService, "modelMapper", new ModelMapper());

        Optional<Client> client = Optional.of(new Client(1L, "teste", Gender.MALE));
        Long id = 1L;

        when(clientRepository.findById(id)).thenReturn(client);
        ClientDto clientDto = this.clientService.get(id);
        verify(clientRepository).findById(id);

        assertNotNull(id);
        assertEquals(clientDto.getId(), client.get().getId());
        assertEquals(clientDto.getName(), client.get().getName());
        assertEquals(clientDto.getGender(), client.get().getGender());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void getByIdTest_NullId() throws NotFoundException {
        ReflectionTestUtils.setField(clientService, "modelMapper", new ModelMapper());

        Optional<Client> client = Optional.of(new Client(1L, "teste", Gender.MALE));
        Long id = null;

        when(clientRepository.findById(id)).thenReturn(client);
        ClientDto clientDto = this.clientService.get(id);
        verify(clientRepository).findById(id);

        assertNull(id);
        assertEquals(clientDto.getId(), client.get().getId());
        assertEquals(clientDto.getName(), client.get().getName());
        assertEquals(clientDto.getGender(), client.get().getGender());
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void getByIdTest_DifferentId() throws NotFoundException {
        ReflectionTestUtils.setField(clientService, "modelMapper", new ModelMapper());

        Optional<Client> client = Optional.of(new Client(1L, "teste", Gender.MALE));
        Long id = 1L;

        when(clientRepository.findById(id)).thenReturn(client);
        ClientDto clientDto = this.clientService.get(5L);
        verify(clientRepository).findById(id);

        assertNull(clientDto);
    }

    @Test
    public void getByNameTest() {
        ReflectionTestUtils.setField(clientService, "modelMapper", new ModelMapper());

        List<Client> clients = new ArrayList<>();
        Client client1 = new Client(1L, "vinicius", Gender.MALE);
        Client client2 = new Client(2L, "Maria", Gender.FEMALE);
        Client client3 = new Client(3L, "Maria", Gender.FEMALE);
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);

        when(clientRepository.findByName("Maria")).thenReturn(clients.stream()
                .filter(x -> x.getName().equals("Maria")).collect(Collectors.toList()));
        List<ClientDto> clientsDto = this.clientService.getByName("Maria");
        verify(clientRepository).findByName("Maria");

        assertEquals(clientsDto.size(), 2);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void getByNameTest_FindNull() {
        ReflectionTestUtils.setField(clientService, "modelMapper", new ModelMapper());

        List<Client> clients = new ArrayList<>();
        Client client1 = new Client(1L, "vinicius", Gender.MALE);
        Client client2 = new Client(2L, "Maria", Gender.FEMALE);
        Client client3 = new Client(3L, "Maria", Gender.FEMALE);
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);

        when(clientRepository.findByName("Bob")).thenReturn(clients.stream()
                .filter(x -> x.getName().equals("Bob")).collect(Collectors.toList()));
        List<ClientDto> clientsDto = this.clientService.getByName(null);
        verify(clientRepository).findByName("Bob");

        assertEquals(clientsDto.size(), 0);
    }

    @Test
    public void saveTest() throws ParseException {
        ReflectionTestUtils.setField(clientService, "modelMapper", new ModelMapper());

        ClientDto clientDto = new ClientDto(null, "vinicius", Gender.MALE);
        Client client = new Client(1L, "vinicius", Gender.MALE);

        when(clientRepository.save(any())).thenReturn(client);
        ClientDto clientsDto2 = this.clientService.save(clientDto);
        verify(clientRepository).save(any());

        clientDto.setId(clientsDto2.getId());
        assertEquals(clientDto,clientsDto2);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void saveTest_NullClient() throws ParseException {
        ReflectionTestUtils.setField(clientService, "modelMapper", new ModelMapper());

        when(clientRepository.save(any())).thenReturn(null);
        ClientDto clientsDto2 = this.clientService.save(null);
        verify(clientRepository).save(any());

        assertEquals(clientsDto2, null);
    }

    @Test
    public void updateTest() throws ParseException {
        ReflectionTestUtils.setField(clientService, "modelMapper", new ModelMapper());

        ClientDto clientDto = new ClientDto(1L, "vinicius", Gender.MALE);
        Client client = new Client(1L, "vinicius", Gender.MALE);

        when(clientRepository.save(any())).thenReturn(client);
        ClientDto clientsDto2 = this.clientService.save(clientDto);
        verify(clientRepository).save(any());

        clientDto.setId(clientsDto2.getId());
        assertEquals(clientDto,clientsDto2);
    }

    @Test
    public void deleteTest() throws NotFoundException {
        ReflectionTestUtils.setField(clientService, "modelMapper", new ModelMapper());

        Optional<Client> client = Optional.of(new Client(1L, "teste", Gender.MALE));
        Long id = 1L;

        when(clientRepository.findById(id)).thenReturn(client);
        ClientDto clientDtoById = this.clientService.get(id);
        verify(clientRepository).findById(id);

        assertNotNull(id);
        assertEquals(clientDtoById.getId(), client.get().getId());
        assertEquals(clientDtoById.getName(), client.get().getName());
        assertEquals(clientDtoById.getGender(), client.get().getGender());

        Client client2 = new Client(1L, "teste", Gender.MALE);

        doNothing().when(clientRepository).delete(client2);
        String result = this.clientService.delete(id);
        verify(clientRepository).delete(client2);

        assertEquals(result,"Cliente deletado!");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void deleteTest_NullId() throws NotFoundException {
        ReflectionTestUtils.setField(clientService, "modelMapper", new ModelMapper());

        Optional<Client> client = Optional.of(new Client(1L, "teste", Gender.MALE));
        Long id = null;

        when(clientRepository.findById(id)).thenReturn(client);
        ClientDto clientDtoById = this.clientService.get(id);
        verify(clientRepository).findById(id);

        assertNull(id);
        assertNull(clientDtoById);

        Client client2 = new Client(1L, "teste", Gender.MALE);

        doNothing().when(clientRepository).delete(null);
        String result = this.clientService.delete(id);
        verify(clientRepository).delete(client2);

        assertEquals(result,"O id esta vazio");
    }
}