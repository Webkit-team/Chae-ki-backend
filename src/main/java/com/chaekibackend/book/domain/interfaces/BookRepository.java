package com.chaekibackend.book.domain.interfaces;

import com.chaekibackend.book.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
