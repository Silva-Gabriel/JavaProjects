package dev.gabriel.domain.repository;

import dev.gabriel.domain.model.Follower;
import dev.gabriel.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower> {
    public boolean follows(User follower, User user){
//        Map<String, Object> params = new HashMap<>();
//        params.put("follower",follower);
//        params.put("user",user);
        //O de baixo retorna o mesmo resultado do que está acima
        var params = Parameters.with("follower",follower).and("user",user).map();
        PanacheQuery<Follower> query = find("follower = :follower and user = :user", params);
        Optional<Follower> result = query.firstResultOptional();

        return result.isPresent();
    }
}
