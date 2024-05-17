package com.chaekibackend.book.application;

import com.chaekibackend.book.api.response.BookResponse;
import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.book.domain.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public BookResponse.Detail readBook(Long no){
        Book book = bookService.readBook(no);
        return BookResponse.Detail.from(book);
    }

    public List<BookResponse.Search> searchBook(String word){
        List<Book> searchList = bookService.searchBook(word);
        List<BookResponse.Search> searchBooks = new ArrayList<>();
        for(Book book : searchList) {
            searchBooks.add(BookResponse.Search.from((book)));
        }

        return searchBooks;
    }

    public void createLikeBook(Long bno, Long uno){
        bookService.createLikeBook(bno, uno);
    }

}
