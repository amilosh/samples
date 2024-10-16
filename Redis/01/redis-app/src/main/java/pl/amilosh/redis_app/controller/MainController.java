package pl.amilosh.redis_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.amilosh.redis_app.model.User;
import pl.amilosh.redis_app.repository.UserRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("/main")
public class MainController {
    private final UserRepository userRepository;

    @PostMapping
    public void createUser() {
        User user = User.builder().id(1L).name("John").age(25).build();
        userRepository.createUser(user);
    }

    @GetMapping
    public User getUser() {
        return userRepository.getUser(1L);
    }
}
