package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Author;
import exercise.model.Book;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    // BEGIN

    @Autowired
    private  BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private  BookMapper bookMapper;


    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::map)
                .collect(Collectors.toList());
    }

    public BookDTO findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        BookDTO map = bookMapper.map(book);
        map.setAuthorFirstName(book.getAuthor().getFirstName());
        map.setAuthorLastName(book.getAuthor().getLastName());
        return map;
    }

    public BookDTO create(BookCreateDTO bookCreateDTO) {
        if (!authorRepository.existsById(bookCreateDTO.getAuthorId())) {
            throw new IllegalArgumentException("Author with ID " + bookCreateDTO.getAuthorId() + " does not exist.");
        }
        Book book = bookMapper.map(bookCreateDTO);
        book = bookRepository.save(book);
        return bookMapper.map(book);
    }


    public BookDTO update(@RequestBody @Valid BookUpdateDTO productData, @PathVariable Long id) {
        var product = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bad request"));
        bookMapper.update(productData, product);
        Author request = authorRepository.findById(productData.getAuthorId().get())
                .orElseThrow(() -> new ResourceNotFoundException("Bad request"));
        product.setAuthor(request);
        bookRepository.save(product);
        var updatedProductDTO = bookMapper.map(product);
        return updatedProductDTO;
    }


    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
    // END

