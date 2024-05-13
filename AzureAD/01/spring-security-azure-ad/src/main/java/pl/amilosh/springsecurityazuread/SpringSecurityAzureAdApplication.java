package pl.amilosh.springsecurityazuread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class SpringSecurityAzureAdApplication {

    @GetMapping("/login")
    public String login(){
        return "login()";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityAzureAdApplication.class, args);
    }
}
