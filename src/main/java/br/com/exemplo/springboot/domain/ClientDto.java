package br.com.exemplo.springboot.domain;

import br.com.exemplo.springboot.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ClientDto {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private Gender gender;

    public ClientDto() {
    }

    public ClientDto(Long id, String name, Gender gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDto clientDto = (ClientDto) o;
        return Objects.equals(id, clientDto.id) &&
                Objects.equals(name, clientDto.name) &&
                gender == clientDto.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
