package br.com.exemplo.springboot.controller;

import br.com.exemplo.springboot.domain.ClientDto;

import br.com.exemplo.springboot.service.ClientService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientDto>> get() {
        return ResponseEntity.ok(this.service.get());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientDto> get(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.ok(this.service.get(id));
        }catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/name")
    public ResponseEntity<List<ClientDto>> get(@Param(value = "name") String name) {
        return ResponseEntity.ok(this.service.getByName(name));
    }

    @PostMapping
    public ResponseEntity<ClientDto> save(@RequestBody ClientDto dto) throws ParseException {
        try {
            return new ResponseEntity<>(this.service.save(dto), CREATED);
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<ClientDto> update(@RequestBody ClientDto dto) throws ParseException {
        return new ResponseEntity<>(this.service.save(dto), OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(this.service.delete(id), NO_CONTENT);
    }
}
