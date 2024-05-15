package com.chaekibackend.book.application;

import com.chaekibackend.book.api.response.BookResponse;
import com.chaekibackend.book.domain.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookAppService {
    private final BookService bookService;

    public List<BookResponse.Detail> readAllBooks() {
        return bookService.readAllBooks()
                .stream()
                .map(BookResponse.Detail::from)
                .toList();
    }

}
