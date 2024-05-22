package com.chaekibackend.book.application;

import com.chaekibackend.book.api.response.BookResponse;
import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.book.domain.service.BookService;
import com.chaekibackend.users.domain.entity.Users;
import com.chaekibackend.users.domain.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookAppService {
    private final BookService bookService;
    private final UsersService usersService;

    public List<BookResponse.Detail> readAllBooks() {
        return bookService.readAllBooks()
                .stream()
                .map(BookResponse.Detail::from)
                .toList();
    }

    // 도서 상세 조회 기능
    public BookResponse.Detail readBook(Long bno, Long uno){
        Book book = bookService.readBook(bno);
        if (book == null) {
            log.error("존재하지 않는 책입니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 책입니다.");
        }
        BookResponse.Detail res =  BookResponse.Detail.from(book);

        usersService.readByNo(uno); // 예외처리 위한 코드

        if(uno != null) {
            Boolean existBookLike = bookService.readBookLike(bno, uno);

            if (existBookLike) {
                res.setCheckLike(true);
            }
        }
        return res;
    }
    
    // 챌린지 등록 시 도서명 & 저자명으로 도서 검색 기능
    public List<BookResponse.Search> searchBook(String word){
        List<Book> searchList = bookService.searchBook(word);
        List<BookResponse.Search> searchBooks = new ArrayList<>();
        for(Book book : searchList) {
            searchBooks.add(BookResponse.Search.from((book)));
        }

        return searchBooks;
    }

    // 도서 찜 등록 기능
    public void createLikeBook(Long bno, Long uno){
        bookService.createLikeBook(bno, uno);
    }

    // 찜 도서 삭제 기능
    public void deleteLikeBook(Long bno, Long uno){
        bookService.deleteLikeBook(bno, uno);
    }

    public List<BookResponse.RankBook> getBookRanking() {
        PageRequest pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "likeCount");
        Page<Book> bookRanking = bookService.getBookRanking(pageable);

        return bookRanking.get()
                .map(BookResponse.RankBook::from)
                .toList();
    }
}
