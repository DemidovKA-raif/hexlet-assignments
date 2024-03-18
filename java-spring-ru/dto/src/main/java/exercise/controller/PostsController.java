package exercise.controller;

import exercise.dto.CommentDTO;
import exercise.dto.PostDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import exercise.model.Post;
import exercise.repository.PostRepository;


// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    public PostRepository postRepository;
    @Autowired
    public CommentRepository commentRepository;


    /**
     * List<Post> posts = postRepository.findAll();: Получает все посты из базы данных.
     * List<PostDTO> postDTOs = new ArrayList<>();: Создает новый список PostDTO для хранения результатов.
     * for (Post post : posts): Перебирает все посты.
     * PostDTO postDTO = new PostDTO();: Создает новый PostDTO для каждого поста.
     * postDTO.setId(post.getId());: Устанавливает ID PostDTO в ID поста.
     * postDTO.setTitle(post.getTitle());: Устанавливает заголовок PostDTO в заголовок поста.
     * postDTO.setBody(post.getBody());: Устанавливает текст PostDTO в текст поста.
     * List<Comment> comments = commentRepository.findByPostId(post.getId());: Получает комментарии для поста.
     * List<CommentDTO> commentDTOs = new ArrayList<>();: Создает новый список CommentDTO для хранения комментариев.
     * for (Comment comment : comments): Перебирает все комментарии.
     * CommentDTO commentDTO = new CommentDTO();: Создает новый CommentDTO для каждого комментария.
     * commentDTO.setId(comment.getId());: Устанавливает ID CommentDTO в ID комментария.
     * commentDTO.setBody(comment.getBody());: Устанавливает текст CommentDTO в текст комментария.
     * commentDTOs.add(commentDTO);: Добавляет CommentDTO в список CommentDTO.
     * postDTO.setComments(commentDTOs);: Устанавливает комментарии для PostDTO.
     * postDTOs.add(postDTO);: Добавляет PostDTO в список PostDTO.
     * return postDTOs;: Возвращает список PostDTO.
     */
    @GetMapping("")
    public List<PostDTO> showAll() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOs = new ArrayList<>();
        for (Post post : posts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());
            postDTO.setBody(post.getBody());
            List<Comment> comments = commentRepository.findByPostId(post.getId());
            List<CommentDTO> commentDTOs = new ArrayList<>();
            for (Comment comment : comments) {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setId(comment.getId());
                commentDTO.setBody(comment.getBody());
                commentDTOs.add(commentDTO);
            }
            postDTO.setComments(commentDTOs);
            postDTOs.add(postDTO);
        }
        return postDTOs;
    }

    /**
     * Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));: Получает пост по его ID. Если пост не найден, генерируется исключение ResourceNotFoundException.
     * List<Comment> comments = commentRepository.findByPostId(id);: Получает комментарии для поста.
     * PostDTO postDTO = new PostDTO();: Создает новый PostDTO для хранения результатов.
     * postDTO.setId(post.getId());: Устанавливает ID PostDTO в ID поста.
     * postDTO.setTitle(post.getTitle());: Устанавливает заголовок PostDTO в заголовок поста.
     * postDTO.setBody(post.getBody());: Устанавливает текст PostDTO в текст поста.
     * List<CommentDTO> commentDTOs = new ArrayList<>();: Создает новый список CommentDTO для хранения комментариев.
     * for (Comment comment : comments): Перебирает все комментарии.
     * CommentDTO commentDTO = new CommentDTO();: Создает новый CommentDTO для каждого комментария.
     * commentDTO.setId(comment.getId());: Устанавливает ID CommentDTO в ID комментария.
     * commentDTO.setBody(comment.getBody());: Устанавливает текст CommentDTO в текст комментария.
     * commentDTOs.add(commentDTO);: Добавляет CommentDTO в список CommentDTO.
     * postDTO.setComments(commentDTOs);: Устанавливает комментарии для PostDTO.
     * return postDTO;: Возвращает PostDTO.
     */
    @GetMapping("/{id}")
    public PostDTO show(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id 100 not found"));
        List<Comment> comments = commentRepository.findByPostId(id);
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setBody(comment.getBody());
            commentDTOs.add(commentDTO);
        }
        postDTO.setComments(commentDTOs);
        return postDTO;
    }}
// END
