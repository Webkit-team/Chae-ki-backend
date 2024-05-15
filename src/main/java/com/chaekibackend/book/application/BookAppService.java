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

    // 여기서 Dto로 가공을 해줘야 하는데..?
//    public

    public List<BookResponse.GetBook> readAllBooks() {
        return bookService.readAllBooks()
                .stream()
                .map(BookResponse.GetBook::from)
                .toList();
    }
}
