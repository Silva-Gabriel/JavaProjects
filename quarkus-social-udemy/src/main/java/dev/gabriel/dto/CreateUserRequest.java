package dev.gabriel.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserRequest {
    @NotBlank(message="The field name is required")
    private String name;
    @NotNull(message="The field age is required")
    private Integer age;
}
