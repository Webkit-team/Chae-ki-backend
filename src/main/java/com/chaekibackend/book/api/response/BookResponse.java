package com.chaekibackend.book.api.response;

import com.chaekibackend.book.domain.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;


public class BookResponse {
    @Builder
    @AllArgsConstructor
    public static class GetBook{
        private Long no;
        private String name;
        private String category;
        private String writer;
        private String description;
        private Integer likeCount;
        private Integer pageNumber;
        private String publisher;
        private String imageUrl;
        private String shopUrl;
        private Integer price;
        private String isbnCode;
        private LocalDateTime publishDate;

//    public static class GetList{
//        private List<Book> bookList;
//
//        public static BookResponse.GetList from(){
//            return BookResponse.GetList
//        }
    }
}
