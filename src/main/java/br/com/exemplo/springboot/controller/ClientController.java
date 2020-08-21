package br.com.exemplo.springboot.controller;

import br.com.exemplo.springboot.domain.ClientDto;

import br.com.exemplo.springboot.entities.Client;
import br.com.exemplo.springboot.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientDto>> get() {
        return ResponseEntity.ok(this.service.get());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClientDto> get(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(this.service.get(id));
    }

    @RequestMapping(path = "/name", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDto>> get(@Param(value = "name") String name) {
        return ResponseEntity.ok(this.service.getByName(name));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ClientDto> save(@RequestBody ClientDto dto) {
        return new ResponseEntity<>(this.service.save(dto), CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ClientDto> update(@RequestBody ClientDto dto) {
        this.service.update(dto);
        return ResponseEntity.ok(dto);
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity<ClientDto> delete(@PathVariable(value = "id") Long id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }
}
