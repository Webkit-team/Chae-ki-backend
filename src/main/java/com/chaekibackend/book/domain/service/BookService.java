package com.chaekibackend.book.domain.service;

import com.chaekibackend.book.api.response.AladinResponse;
import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.book.domain.interfaces.BookRepository;
import com.chaekibackend.configuration.webClient.WebClientConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final WebClient webClient;
    private final WebClientConfig webClientConfig;

    public List<Book> readAllBooks() {
        return bookRepository.findAll();
    }

    public void useWebClient() {
        WebClient webClient = WebClient.create();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<String> categoryList = new ArrayList<>();
        categoryList.add("사회과학");
        categoryList.add("심리");
        categoryList.add("자기계발");
        categoryList.add("인문학");
        categoryList.add("종교/역학");
        categoryList.add("역사");
        categoryList.add("과학");
        categoryList.add("소설/시/희곡");
        categoryList.add("경제경영");

//        AladinResponse.AladinTotalResponse res = webClient.get()
//                .uri(uriBuilder -> uriBuilder           // uriBuilder를 이용해 동적으로 URI 구성
//                        .queryParam("ttbkey", "ttbchjw9561322001")
//                        .queryParam("QueryType", "Bestseller")
//                        .queryParam("MaxResults", 10)
//                        .queryParam("SearchTarget", "Book")
//                        .queryParam("Output", "JS")
//                        .queryParam("Version", 20131101)
//                        .build())
////            .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(AladinResponse.AladinTotalResponse.class)
//                .block();

        AladinResponse.AladinTotalResponse res = webClient.get()
                .uri("http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbchjw9561322001&QueryType=Bestseller&MaxResults=10&SearchTarget=Book&Output=JS&Version=20131101")
                .retrieve()
                .bodyToMono(AladinResponse.AladinTotalResponse.class)
                .block();

        List<Book> bookList = new ArrayList<>();
        String bookCategory = "";

        for(AladinResponse.BookResponse bookResponse : res.getItem()){
            for (String str: categoryList) {
                if(bookResponse.getCategoryName().contains(str)){
                    bookCategory = str;
                }
            }
            bookList.add(
                Book.builder()
                        .name(bookResponse.getTitle())
                        .category(bookCategory)
                        .writer(bookResponse.getAuthor())
                        .description(bookResponse.getDescription())
                        .likeCount(0)
// todo:                        .pageNumber()  => 직접 넣어주기
                        .publisher(bookResponse.getPublisher())
                        .imageUrl(bookResponse.getCover())
                        .shopUrl(bookResponse.getLink())
                        .price(bookResponse.getPriceStandard())
                        .isbnCode(bookResponse.getIsbn())
                        .publishDate(bookResponse.getPubDate())
                        .build()
            );
        }

        bookRepository.saveAll(bookList);

        return bookList;
    }
}

