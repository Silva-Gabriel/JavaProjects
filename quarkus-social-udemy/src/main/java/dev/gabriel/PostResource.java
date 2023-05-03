package dev.gabriel;

import dev.gabriel.domain.model.Post;
import dev.gabriel.domain.model.User;
import dev.gabriel.domain.repository.PostRepository;
import dev.gabriel.domain.repository.UserRepository;
import dev.gabriel.dto.CreatePostRequest;
import dev.gabriel.dto.PostResponse;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import javax.persistence.PrePersist;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Inject
    public PostResource(UserRepository userRepository, PostRepository postRepository){
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @POST
    @Transactional
    public Response savePost(@PathParam("userId") Long userId, CreatePostRequest postRequest){
        User user = userRepository.findById(userId);
        if(user == null){
            return Response.status(404).build();
        }

        Post post = new Post();
        post.setText(postRequest.getText());
        post.setUser(user);
        post.setDateTime(LocalDateTime.now());
        postRepository.persist(post);


        return Response.status(201).build();
    }

    @GET
    public Response listPosts(@PathParam("userId") Long userId){
        User user = userRepository.findById(userId);
        if(user == null){
            return Response.status(404).build();
        }
        PanacheQuery<Post> query = postRepository.find("user", Sort.by("datetime", Sort.Direction.Descending),user);
        List list = query.list();
        var postResponseList = list
                .stream()
                .map(post -> PostResponse.fromEntity((Post) post))
                .collect(Collectors.toList());
        return Response.ok(postResponseList).build();
    }

}
