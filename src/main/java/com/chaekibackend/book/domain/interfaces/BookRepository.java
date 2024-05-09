package com.chaekibackend.book.domain.interfaces;

import com.chaekibackend.book.api.response.BookResponse;
import com.chaekibackend.book.domain.entity.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
