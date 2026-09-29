package com.example.BookCrud.services;

import com.example.BookCrud.model.Book;
import com.example.BookCrud.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBook(Long id) {
        return bookRepository.findById(id);
    }

    public Book create(Book book) {
        book.setId(null); // always insert a new row
        return bookRepository.save(book);
    }

    public Optional<Book> update(Long id, Book updated) {
        return bookRepository.findById(id).map(existing -> {
            existing.setTitle(updated.getTitle());
            existing.setAuthor(updated.getAuthor());
            existing.setPublisher(updated.getPublisher());
            existing.setPrice(updated.getPrice());
            existing.setPublicationYear(updated.getPublicationYear());
            return bookRepository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (!bookRepository.existsById(id)) return false;
        bookRepository.deleteById(id);
        return true;
    }

    public List<Book> getByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getByPublisher(String publisher) {
        return bookRepository.findByPublisher(publisher);
    }

    public List<Book> getByPriceRange(double min, double max) {
        return bookRepository.findByPriceRange(min, max);
    }
}