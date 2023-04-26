package dev.gabriel.domain.repository;

import dev.gabriel.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

// @ApplicationScoped -> Cria uma instância da classe  dentro do contexto da aplicação, no conteiner de injeção de dependências para poder utilizá-lo em qualquer lugar com escopo de aplicação -> singleton
@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

}
