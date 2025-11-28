package com.jeferson.jeclient.dto;

import com.jeferson.jeclient.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ClientDTO {
    private Long id;

    @Size(min = 3, max = 80, message = "nome precisa ter de 3 a 80 caracteres")
    @NotBlank(message = "campo requerido")
    private String name;

    @Size(min = 11, max = 11, message = "o CPF dever ter exatamente 11 dígitos")
    @NotBlank(message = "campo CPF obrigatório!")
    private String cpf;

    @PositiveOrZero(message = "A renda deve ser zero ou um valor positivo.")
    private Double income;

    @Past(message = "A data de nascimento deve ser uma data anterior ao dia de hoje.")
    private LocalDate birthDate;

    @PositiveOrZero(message = "deve ser zero ou um valor positivo.")
    private Integer children;

    public ClientDTO(){}

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client entity){// sobrecarga de construtor para copiar campos de Client, mas para entidades muito grande opta-se pela lib ModelMapper
        id = entity.getId();
        name = entity.getName();
        cpf = entity.getCpf();
        income = entity.getIncome();
        birthDate = entity.getBirthDate();
        children = entity.getChildren();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
