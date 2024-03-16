package exercise;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.beans.factory.annotation.Autowired;

import exercise.model.User;
import exercise.component.UserProperties;

import static java.util.Collections.sort;

@Component
@ConfigurationProperties(prefix = "user")
@SpringBootApplication
@RestController
public class Application {

    // Все пользователи
    private List<User> users = Data.getUsers();

    // BEGIN
    @Autowired
    private UserProperties admins;


    public List<String> getListWithAdmins() {
        List<String> adminsAdmins = admins.getAdmins();
        return adminsAdmins;
    }

    @GetMapping("/admins")
    public List<String> nameAdmins() {
        List<String> listWithAdmins = getListWithAdmins();
        List<String> adminNames = users.stream()
                .filter(user -> listWithAdmins.contains(user.getEmail()))
                .map(User::getName)
                .collect(Collectors.toList());
        sort(adminNames);
        return adminNames;
    }

    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
            .filter(u -> u.getId() == id)
            .findFirst();
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
