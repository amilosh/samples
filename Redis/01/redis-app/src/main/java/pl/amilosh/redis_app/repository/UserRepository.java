package pl.amilosh.redis_app.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import pl.amilosh.redis_app.model.User;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    public void createUser(User user) {
        redisTemplate.opsForValue().set("user:" + user.getId(), user);
    }

    public User getUser(Long id) {
        return (User) redisTemplate.opsForValue().get("user:" + id);
    }
}
