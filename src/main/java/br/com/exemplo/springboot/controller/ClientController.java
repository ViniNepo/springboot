package br.com.exemplo.springboot.controller;

import br.com.exemplo.springboot.apiException.ApiErrors;
import br.com.exemplo.springboot.domain.ClientDto;

import br.com.exemplo.springboot.service.ClientService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    public ResponseEntity<ClientDto> get(@PathVariable(value = "id") Long id) throws NotFoundException {
            return ResponseEntity.ok(this.service.get(id));
    }

    @GetMapping(path = "/name")
    public ResponseEntity<List<ClientDto>> get(@Param(value = "name") String name) {
        return ResponseEntity.ok(this.service.getByName(name));
    }

    @PostMapping
    public ResponseEntity<ClientDto> save(@RequestBody ClientDto dto) throws ParseException {
        return new ResponseEntity<>(this.service.save(dto), CREATED);
    }

    @PutMapping
    public ResponseEntity<ClientDto> update(@PathVariable(value = "id") Long id, @RequestBody ClientDto dto) throws ParseException {
        return new ResponseEntity<>(this.service.update(id, dto), OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(this.service.delete(id), NO_CONTENT);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleException(NullPointerException ex) {
        return new ApiErrors(ex);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ApiErrors handleException(NotFoundException ex) {
        return new ApiErrors(ex);
    }

}
