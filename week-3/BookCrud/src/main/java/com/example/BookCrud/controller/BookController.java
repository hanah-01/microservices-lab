package com.example.BookCrud.controller;

import com.example.BookCrud.model.Book;
import com.example.BookCrud.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return bookService.getBook(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.update(id, book)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        if (bookService.delete(id)) {
            return ResponseEntity.ok("Book deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
    }

    @GetMapping("/author/{author}")
    public List<Book> getByAuthor(@PathVariable String author) {
        return bookService.getByAuthor(author);
    }

    @GetMapping("/publisher/{publisher}")
    public List<Book> getByPublisher(@PathVariable String publisher) {
        return bookService.getByPublisher(publisher);
    }

    @GetMapping("/price-range")
    public List<Book> getByPriceRange(@RequestParam double min, @RequestParam double max) {
        return bookService.getByPriceRange(min, max);
    }
}