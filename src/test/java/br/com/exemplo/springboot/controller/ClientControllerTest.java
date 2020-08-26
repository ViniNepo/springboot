package br.com.exemplo.springboot.controller;

import br.com.exemplo.springboot.domain.ClientDto;
import br.com.exemplo.springboot.entities.Client;
import br.com.exemplo.springboot.enums.Gender;
import br.com.exemplo.springboot.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    @Autowired
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<ClientDto> clients;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        clients = new ArrayList<>();
        clients.add(new ClientDto(1L, "vinicius", Gender.MALE));
    }

    @Test
    public void getTest() throws Exception {

        when(clientService.get()).thenReturn(clients);

        mvc.perform(get("/clients")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(clientService).get();
    }

    @Test
    public void get_NotFoundTest() throws Exception {
//
//        when(clientService.get()).thenThrow();
//
//        mvc.perform(get("/clients")
//                .contentType(APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//
//        verify(clientService).get();
    }

//    @Test
//    public void getClubIdUnprocessableEntityTest() throws Exception {
//        when(customerService.getClubId(anyLong())).thenThrow(new UnprocessableEntityException());
//        mvc.perform(get(PROFILES_CLUB_ID)
//                .headers(httpHeaders)
//                .accept(APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(status().isUnprocessableEntity());
//        verify(customerService).getClubId(anyLong());
//    }

    @Test
    public void getByIdTeste() throws Exception {
        long id = anyLong();

        when(clientService.get(id)).thenReturn(clients.get(0));

        mvc.perform(get("/clients/{id}", id)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(clientService).get(id);
    }

    @Test
    public void getByNameTest() throws Exception {
        String name = "vinicius";

        when(clientService.getByName(name)).thenReturn(clients);

        mvc.perform(get("/clients/name?name={name}", name)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(clientService).getByName(name);
    }

    @Test
    public void testSave() throws Exception {
        ClientDto clientDto = new ClientDto(null, "Maria", Gender.FEMALE);

        when(clientService.save(clientDto)).thenReturn(clientDto);

        mvc.perform(post("/clients")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientDto)))
                .andExpect(status().isCreated());

        verify(clientService).save(clientDto);
    }

    @Test
    public void testUpdate() throws Exception {
//        ClientDto clientDto = new ClientDto(1L, "Maria", Gender.FEMALE);
//
//        when(clientService.save(clientDto)).thenReturn(clientDto);
//
//        mvc.perform(put("/clients/1")
//                .contentType(APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(clientDto)))
//                .andExpect(status().isOk());
//
//        verify(clientService).save(clientDto);
    }

    @Test
    public void testDelete() throws Exception {
        mvc.perform(delete("/clients/1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}