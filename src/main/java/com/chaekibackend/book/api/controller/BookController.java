package com.chaekibackend.book.api.controller;

import com.chaekibackend.book.api.response.BookResponse;
import com.chaekibackend.book.application.BookAppService;
import com.chaekibackend.book.domain.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController     // JSON 형태로 객체 데이터 반환 + 요청 처리 상태 자동 반환
@RequiredArgsConstructor
@Tag(name = "도서 관리")
public class BookController {
    private final BookAppService bookAppService;

//    @GetMapping("/bookList")
//    @ResponseBody
//    public List<Book> getBookList(){
//        return bookService.useWebClient();
//    }

    // 도서 목록 조회가 아니라 챌린지 등록할 때 도서 검색하는 기능으로 변경해야 함.
//    @Operation(summary = "도서 목록 조회", description = "도서 목록을 조회합니다.")
//    @GetMapping(value = "/books", produces = {MediaType.APPLICATION_JSON_VALUE})
//    public List<BookResponse.Detail> readAllBook() {
//        return bookAppService.readAllBooks();
//    }

    @Operation(summary = "도서 검색", description = "도서 이름과 저자명을 이용해 도서를 검색합니다.")
    @GetMapping(value = "/challenge/books/{word}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<BookResponse.Search> searchBook(@PathVariable("word") String word){
        return bookAppService.searchBook(word);
    }

    // 비회원 도서 상세 조회
    @Operation(summary = "도서 상세 조회", description = "도서 정보를 조회합니다.")
    @GetMapping(value = "/books/{bno}")
    public BookResponse.Detail readBookLogout(@PathVariable("bno") Long bno) {
        return bookAppService.readBook(bno, null);
    }

    // 로그인한 회원 도서 상세 조회
    @Operation(summary = "도서 상세 조회", description = "도서 정보를 조회합니다.")
    @GetMapping(value = "/books/{bno}/users/{uno}")
    public BookResponse.Detail readBookLogin(@PathVariable("bno") Long bno,
                                             @PathVariable("uno") Long uno){
        return bookAppService.readBook(bno, uno);
    }

    // 도서 찜 등록
    @Operation(summary = "도서 찜 등록", description = "도서를 사용자의 도서 찜 목록에 추가합니다.")
    @PostMapping(value = "/books/{bno}/users/{uno}")
    public void createLikeBook(@PathVariable("bno") Long bno, @PathVariable("uno") Long uno){
        bookAppService.createLikeBook(bno, uno);
    }
}
