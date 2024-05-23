package com.chaekibackend.book.api.response;

import com.chaekibackend.book.domain.entity.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class BookResponse {
    @Builder
    @AllArgsConstructor
    @Getter
    @Setter
    @Schema(name = "BookResponse.Detail")
    public static class Detail {
        private Long no;
        private String name;
        private String category;
        private String writer;
        private String translator;
        private String description;
        private Integer likeCount;
        private Integer pageNumber;
        private String publisher;
        private String imageUrl;
        private String shopUrl;
        private Integer price;
        private String isbnCode;
        private LocalDate publishDate;
        // 로그인한 유저의 해당 도서에 대한 찜 등록 여부
        // 찜 등록 기록 있음 -> true / 찜 등록 기록 없음 -> false
        private Boolean checkLike;

        public static BookResponse.Detail from(Book book) {
            // (데이터 가공) 응답Dto(BookResponse.Detail)에서 지은이와 옮긴이를 분리해서 담아줌
            String[] writers = book.getWriter().split("\\(지은이\\)");
            String writer = writers[0];

            String translator = null;

            if (writers.length > 1) {
                String splicedStr = writers[1].substring((writers[1].indexOf(("("))));
                translator = splicedStr.substring(2, splicedStr.length() - 1);
            }

            System.out.println("translator = " + translator);

            return Detail.builder()
                    .no(book.getNo())
                    .name(book.getName())
                    .category(book.getCategory())
                    .writer(writer)
                    .translator(translator)
                    .description(book.getDescription())
                    .likeCount(book.getLikeCount())
                    .pageNumber(book.getPageNumber())
                    .publisher(book.getPublisher())
                    .imageUrl(book.getImageUrl())
                    .shopUrl(book.getShopUrl())
                    .price(book.getPrice())
                    .isbnCode(book.getIsbnCode())
                    .publishDate(book.getPublishDate())
                    .checkLike(false)
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @Getter
    @Schema(name = "BookResponse.Search")
    public static class Search {
        private Long no;
        private String name;
        private String category;
        private String writer;
        private Integer pageNumber;
        private String publisher;
        private String imageUrl;
        private String isbnCode;
        private LocalDate publishDate;

        public static BookResponse.Search from(Book book) {
            return Search.builder()
                    .no(book.getNo())
                    .name(book.getName())
                    .category(book.getCategory())
                    .writer(book.getWriter())
                    .pageNumber(book.getPageNumber())
                    .publisher(book.getPublisher())
                    .imageUrl(book.getImageUrl())
                    .isbnCode(book.getIsbnCode())
                    .publishDate(book.getPublishDate())
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Detail2 {
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
        @Setter
        private Boolean checkLike;

        public static BookResponse.Detail2 from(Book book) {
            // (데이터 가공) 응답Dto(BookResponse.Detail)에서 지은이와 옮긴이를 분리해서 담아줌
            String[] parts = book.getWriter().split("\\(지은이\\)");
            String writer = parts[0];
            writer = writer.trim();

            return Detail2.builder()
                    .no(book.getNo())
                    .name(book.getName())
                    .category(book.getCategory())
                    .writer(writer)
                    .description(book.getDescription())
                    .likeCount(book.getLikeCount())
                    .pageNumber(book.getPageNumber())
                    .publisher(book.getPublisher())
                    .imageUrl(book.getImageUrl())
                    .shopUrl(book.getShopUrl())
                    .price(book.getPrice())
                    .isbnCode(book.getIsbnCode())
                    .publishDate(book.getPublishDate())
                    .checkLike(false)
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class RankBook {
        private Long bookNo;
        private String imageUrl;

        public static RankBook from(Book book) {
            return RankBook.builder()
                    .bookNo(book.getNo())
                    .imageUrl(book.getImageUrl())
                    .build();
        }
    }
}
