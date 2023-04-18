package dev.model;

import dev.Controller.ChampionEntity;
import dev.Controller.ChampionController;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/champion")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChampionResource {
    @Inject
    private ChampionController championController;

    @GET
    public List<ChampionEntity> read() {
        return ChampionEntity.listAll();
    }

    @POST
    @Transactional
    public Response create(ChampionEntity championEntity) {
        championEntity.persist();
        return Response.ok(championEntity).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, ChampionEntity champ) {
        if (championController.isChampionNameIsNotEmpty(champ))
            return Response.ok("Champion was not found").type(MediaType.APPLICATION_JSON_TYPE).build();

        ChampionEntity championEntity = championController.update(id, champ);
        return Response.ok(championEntity).build();
    }
}