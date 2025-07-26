package com.example.bookstore.controller;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public Page<Book> getBooks(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return bookRepository.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Author author = authorRepository.findById(book.getAuthor().getId())
            .orElseThrow(() -> new RuntimeException("Author not found"));
        book.setAuthor(author);
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found with id " + id));
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found with id " + id));

        book.setTitle(updatedBook.getTitle());
        book.setGenre(updatedBook.getGenre());
        book.setYear(updatedBook.getYear());

        Long authorId = updatedBook.getAuthor() != null ? updatedBook.getAuthor().getId() : null;
        if (authorId != null) {
            Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found with id " + authorId));
            book.setAuthor(author);
        } else {
            book.setAuthor(null);
        }

        return ResponseEntity.ok(bookRepository.save(book));
    }

    @PostMapping("/{id}/author")
    public ResponseEntity<Book> updateBookAuthor(@PathVariable Long id, @RequestBody Author author) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found with id " + id));
        
        Author existingAuthor = authorRepository.findById(author.getId())
            .orElseThrow(() -> new RuntimeException("Author not found with id " + author.getId()));
        
        book.setAuthor(existingAuthor);
        return ResponseEntity.ok(bookRepository.save(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
