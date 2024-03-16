package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    public PostRepository postRepository;
    @Autowired
    public CommentRepository commentRepository;


    @GetMapping(path = "")
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post data) {
        return postRepository.save(data);
    }


    @GetMapping(path = "/{id}")
    public Post show(@PathVariable long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    @PutMapping(path = "/{id}")
    public Post update(@PathVariable long id, @RequestBody Post data) {
        var post =  postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        post.setBody(data.getBody());
        post.setTitle(data.getTitle());
        postRepository.save(post);
        return post;
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        postRepository.deleteById(id);
        commentRepository.deleteByPostId(id);
    }

}
// END
