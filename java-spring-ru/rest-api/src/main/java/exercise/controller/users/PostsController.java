package exercise.controller.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

// BEGIN

@RestController
@RequestMapping("/api")
public class PostsController {

    List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
        public ResponseEntity<Object> posts(@PathVariable int id) {
        List<Post> userPosts = posts.stream()
                .filter(p -> p.getUserId() == id)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userPosts);
    }

    @PostMapping("/users/{id}/posts") // Создание страницы
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Post> create(@PathVariable int id, @RequestBody Post data) {

        data.setUserId(id);
        posts.add(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }
}
// END

