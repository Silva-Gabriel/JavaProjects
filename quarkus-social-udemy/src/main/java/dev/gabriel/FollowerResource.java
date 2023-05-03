package dev.gabriel;

import dev.gabriel.domain.model.Follower;
import dev.gabriel.domain.repository.FollowerRepository;
import dev.gabriel.domain.repository.UserRepository;
import dev.gabriel.dto.FollowerRequest;
import dev.gabriel.dto.FollowerResponse;
import dev.gabriel.dto.FollowersPerUserResponse;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("users/{userId}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FollowerResource {
    private FollowerRepository followerRepository;
    private UserRepository userRepository;
    @Inject
    public FollowerResource(FollowerRepository followerRepository, UserRepository userRepository){
        this.followerRepository = followerRepository;
        this.userRepository = userRepository;
    }

    @PUT
    @Transactional
    public Response followUser(@PathParam("userId") Long userId, FollowerRequest followerRequest){
        if(userId.equals(followerRequest.getFollowerId())){
            return Response.status(409).entity("You can't follow yourself!").build();
        }

        var user = userRepository.findById(userId);
        if(user == null){
            return Response.status(404).build();
        }
        var follower = userRepository.findById(followerRequest.getFollowerId());

        boolean follows = followerRepository.follows(follower, user);

        if(!follows){
        var entity = new Follower();
        entity.setUser(user);
        entity.setFollower(follower);

        followerRepository.persist(entity);
        }
        return Response.status(204).build();
    }

    @GET
    public Response listFollowers(@PathParam("userId") Long userId){
        var user = userRepository.findById(userId);
        if(user == null){
            return Response.status(404).build();
        }

        var list = followerRepository.findByUser(userId);
        FollowersPerUserResponse responseObject = new FollowersPerUserResponse();
        responseObject.setFollowersCount(list.size());

        List<FollowerResponse> followerList = list.stream().map(FollowerResponse::new).collect(Collectors.toList());
        responseObject.setContent(followerList);

        return Response.ok(responseObject).build();
    }
}
