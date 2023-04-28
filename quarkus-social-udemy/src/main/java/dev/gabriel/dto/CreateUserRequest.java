package dev.gabriel.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateUserRequest {
    @NotBlank(message="O campo nome é obrigatório")
    private String name;
    @NotNull(message="O campo idade é obrigatório")
    private Integer age;
}
