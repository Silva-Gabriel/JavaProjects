package dev.gabriel;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CadastrarProdutoDTO {
    private String nome;
    private BigDecimal valor;
}
