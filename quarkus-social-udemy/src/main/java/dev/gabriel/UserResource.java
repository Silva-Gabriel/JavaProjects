package dev.gabriel;

import dev.gabriel.domain.model.User;
import dev.gabriel.domain.repository.UserRepository;
import dev.gabriel.dto.CreateUserRequest;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;


import javax.inject.Inject;
import javax.persistence.Table;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/users")
@Table(name="users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserRepository repository;
    private final Validator validator;

    @Inject
    public UserResource(UserRepository repository, Validator validator){
        this.repository = repository;
        this.validator = validator;
    }

    @POST
    @Transactional
    public Response createUser(CreateUserRequest userRequest){
        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(userRequest);
        if(!violations.isEmpty()){
            ConstraintViolation<CreateUserRequest> erro = violations.stream().findAny().get();
            String errorMessage = erro.getMessage();
            return Response.status(400).entity(errorMessage).build();
        }

        User user = new User();
        user.setNome(userRequest.getNome());
        user.setIdade(userRequest.getIdade());
        repository.persist(user);
        return Response.ok(user).build();
    }

    @GET
    public Response listAllUsers(){
        PanacheQuery<User> query = repository.findAll();
        return Response.ok(query.list()).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") Long id){
        User user = repository.findById(id);
        if(user != null){
            repository.delete(user);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userRequest){
        User user = repository.findById(id);

        if(user != null){
            user.setNome(userRequest.getNome());
            user.setIdade(userRequest.getIdade());
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
