package com.register.user.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@EntityScan
@Entity //Indica que esta classe é uma entidade JPA
@Data // Adiciona os metodos getters e setters automatiucamente
@AllArgsConstructor //Adciona um construtor com todos os campos
@NoArgsConstructor //Cria um construtor vazio

public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email; 
}