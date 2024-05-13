package com.chaekibackend.book.api.controller;

import com.chaekibackend.book.api.response.BookResponse;
import com.chaekibackend.book.application.BookAppService;
import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.book.domain.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController     // JSON 형태로 객체 데이터 반환
@RequiredArgsConstructor
public class BookController {
    private final BookAppService bookAppServiece;
    private final BookService bookService;

//    @GetMapping("/bookList")
//    @ResponseBody
//    public List<Book> getBookList(){
//        return bookService.useWebClient();
//    }
}
