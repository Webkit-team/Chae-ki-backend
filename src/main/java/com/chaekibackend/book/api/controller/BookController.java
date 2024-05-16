package com.chaekibackend.book.api.controller;

import com.chaekibackend.book.api.response.BookResponse;
import com.chaekibackend.book.application.BookAppService;
import com.chaekibackend.book.domain.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController     // JSON 형태로 객체 데이터 반환
@RequiredArgsConstructor
@Tag(name = "도서 관리")
public class BookController {
    private final BookAppService bookAppService;
    private final BookService bookService;

//    @GetMapping("/bookList")
//    @ResponseBody
//    public List<Book> getBookList(){
//        return bookService.useWebClient();
//    }

    @Operation(summary = "도서 목록 조회", description = "도서 목록을 조회합니다.")
    @GetMapping(value = "/books", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<BookResponse.GetBook> readAllBook() {
        return bookAppService.readAllBooks();
    }
}
