package dev.Controller;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class ChampionController {
    public ChampionEntity update(Long id, ChampionEntity champion){
        ChampionEntity championEntity = ChampionEntity.findById(id);

        if(championEntity.equals(null))
            throw new WebApplicationException("Champion with id of" + id + "does not exist", Response.Status.NOT_FOUND);

        championEntity.setNome(champion.getNome());
        championEntity.setRegiao(champion.getRegiao());

        return championEntity;
    }

    public boolean isChampionNameIsNotEmpty(ChampionEntity championEntity){
        return championEntity.getNome().isEmpty();
    }
}
