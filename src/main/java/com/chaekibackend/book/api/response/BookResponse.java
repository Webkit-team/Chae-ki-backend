package com.chaekibackend.book.api.response;

import com.chaekibackend.book.domain.entity.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;


public class BookResponse {
    @Builder
    @AllArgsConstructor
    @Getter
    @Schema(name = "BookResponse.Detail")
    public static class Detail {
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
        private LocalDate publishDate;

        public static BookResponse.Detail from(Book book) {
            return BookResponse.Detail.builder()
                    .no(book.getNo())
                    .name(book.getName())
                    .category(book.getCategory())
                    .writer(book.getWriter())
                    .description(book.getDescription())
                    .likeCount(book.getLikeCount())
                    .pageNumber(book.getPageNumber())
                    .publisher(book.getPublisher())
                    .imageUrl(book.getImageUrl())
                    .shopUrl(book.getShopUrl())
                    .price(book.getPrice())
                    .isbnCode(book.getIsbnCode())
                    .publishDate(book.getPublishDate())
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @Getter
    @Schema(name = "BookResponse.GetBook")
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
        private LocalDate publishDate;

        public static BookResponse.GetBook from(Book book) {
            return BookResponse.GetBook.builder()
                    .no(book.getNo())
                    .name(book.getName())
                    .category(book.getCategory())
                    .writer(book.getWriter())
                    .description(book.getDescription())
                    .likeCount(book.getLikeCount())
                    .pageNumber(book.getPageNumber())
                    .publisher(book.getPublisher())
                    .imageUrl(book.getImageUrl())
                    .shopUrl(book.getShopUrl())
                    .price(book.getPrice())
                    .isbnCode(book.getIsbnCode())
                    .publishDate(book.getPublishDate())
                    .build();
        }
//    public static class GetList{
//        private List<Book> bookList;
//
//        public static BookResponse.GetList from(){
//            return BookResponse.GetList
//        }
    }
}
