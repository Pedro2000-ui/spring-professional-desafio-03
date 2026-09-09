package com.devsuperior.desafio3.dto;

import com.devsuperior.desafio3.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;

public class ClientDTO {

    private Long      id;

    @NotBlank(message = "Campo requerido")
    private String    name;

    @NotBlank(message = "Campo requerido")
    @Size(min = 11, max = 11, message = "CPF deve ter exatamente 11 caracteres")
    private String    cpf;
    private Double    income;

    @PastOrPresent(message = "Não é possível informar datas futuras")
    private LocalDate birthDate;

    private Integer   children;

    public ClientDTO() {}

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id        = id;
        this.name      = name;
        this.cpf       = cpf;
        this.income    = income;
        this.birthDate = birthDate;
        this.children  = children;
    }

    public ClientDTO(Client entity) {
        this.id        = entity.getId();
        this.name      = entity.getName();
        this.cpf       = entity.getCpf();
        this.income    = entity.getIncome();
        this.birthDate = entity.getBirthDate();
        this.children  = entity.getChildren();
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
