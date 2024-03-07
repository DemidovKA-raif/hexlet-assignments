package exercise.controller.users;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

// BEGIN

@RestController
@RequestMapping("/api")
public class PostsController {

    @GetMapping("/users/{id}/posts")
    public List<Post> posts(@PathVariable int id) {
        var posts = Data.getPosts();
        List<Post> userPosts = posts.stream()
                .filter(p -> p.getUserId() == id).toList();
            return userPosts;
        }



    @PostMapping("/users/{id}/posts") // Создание страницы
    public ResponseEntity<Object> create(@PathVariable int id, @RequestBody Post data) {
        List<Post> posts = Data.getPosts();
        data.setUserId(id);
        posts.add(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }
}
// END
