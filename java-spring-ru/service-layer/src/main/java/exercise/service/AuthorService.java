package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    // BEGIN

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorMapper authorMapper;
    public List<AuthorDTO> findAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<AuthorDTO> findById(Long id) {
        return authorRepository.findById(id)
                .map(authorMapper::map);
    }

    public AuthorDTO create(AuthorCreateDTO authorCreateDTO) {
        Author author = authorMapper.map(authorCreateDTO);
        author = authorRepository.save(author);
        return authorMapper.map(author);
    }

    public Optional<AuthorDTO> update(Long id, AuthorUpdateDTO authorUpdateDTO) {
        return authorRepository.findById(id).map(author -> {
            authorMapper.update(authorUpdateDTO, author);
            author = authorRepository.save(author);
            return authorMapper.map(author);
        });
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
    // END

