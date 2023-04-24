package dev.Controller;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class ChampionEntity extends PanacheEntity {
    private String nome;
    private String regiao;
}
