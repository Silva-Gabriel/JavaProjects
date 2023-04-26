package dev.gabriel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String nome;
    private Integer idade;
}
