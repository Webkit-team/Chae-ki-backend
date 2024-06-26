package com.chaekibackend.book.domain.interfaces;

import com.chaekibackend.book.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByNo(Long no);
  
    @Query("SELECT b FROM Book b WHERE b.name LIKE CONCAT('%', :keyword, '%') OR b.writer LIKE CONCAT('%', :keyword, '%') ")
    List<Book> findByNameOrWriter(@Param("keyword") String keyword);
}
