package com.chaekibackend.book.api.controller;

import com.chaekibackend.book.api.response.BookResponse;
import com.chaekibackend.book.application.BookAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     // JSON 형태로 객체 데이터 반환 + 요청 처리 상태 자동 반환
@RequiredArgsConstructor
@Tag(name = "도서 관리")
public class BookController {
    private final BookAppService bookAppService;

    @Operation(summary = "도서 검색", description = "도서 이름과 저자명을 이용해 도서를 검색합니다.")
    @GetMapping(value = "/challenge/books/{word}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<BookResponse.Search> searchBook(@PathVariable("word") String word){
        return bookAppService.searchBook(word);
    }

    // 도서 상세 조회 API
    @GetMapping("/books/{bookNo}")
    public BookResponse.Detail2 getBook(
            @PathVariable("bookNo") Long bookNo,
            @RequestParam(value = "userNo", required = false) Long userNo
    ){
        return bookAppService.getBook(bookNo, userNo);
    }

    // 도서 찜 등록
    @Operation(summary = "도서 찜 등록", description = "도서를 사용자의 도서 찜 목록에 추가합니다.")
    @PostMapping(value = "/books/{bno}/users/{uno}")
    public void createLikeBook(@PathVariable("bno") Long bno,
                               @PathVariable("uno") Long uno){
        bookAppService.createLikeBook(bno, uno);
    }

    // 도서 찜 삭제
    @Operation(summary = "도서 찜 삭제", description = "선택된 도서를 사용자의 도서 찜 목록에서 삭제합니다.")
    @DeleteMapping(value="/books/{bno}/users/{uno}")
    public void deleteLikeBook(@PathVariable("bno") Long bno,
                               @PathVariable("uno") Long uno){
        bookAppService.deleteLikeBook(bno, uno);
    }

    @GetMapping("/bookRank")
    public List<BookResponse.RankBook> getBookRanking () {
        return bookAppService.getBookRanking();
    }
}
