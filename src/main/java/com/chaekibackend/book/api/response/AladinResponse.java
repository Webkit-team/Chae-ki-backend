package com.chaekibackend.book.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class AladinResponse {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AladinTotalResponse{
        private List<BookResponse> item;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookResponse{
        private String title;
        private String categoryName;
        private String author;
        private String description;
        private Integer pageNumber;
        private String publisher;
        private String cover;
        private String link;
        private Integer priceStandard;
        private String isbn;
        private LocalDate pubDate;
    }
}