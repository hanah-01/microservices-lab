package com.example.BookCrud.repository;

import com.example.BookCrud.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(String author);

    List<Book> findByPublisher(String publisher);

    @Query("SELECT b FROM Book b WHERE b.price BETWEEN :min AND :max")
    List<Book> findByPriceRange(@Param("min") double min, @Param("max") double max);
}