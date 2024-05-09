package com.chaekibackend.book.domain.service;

import com.chaekibackend.book.api.response.BookResponse;
import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.book.domain.interfaces.BookRepository;
import com.chaekibackend.configuration.webClient.WebClientConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

// 포스트맨으로 일단 해보기
@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final WebClient webClient;
    private final WebClientConfig webClientConfig;

//    public Mono<List<Book>> useWebClient() {
    public List<Book> useWebClient() {
        // 이게 뭔지 제대로 이해 못했음 (CountDownLatch)
//        CountDownLatch cdl = new CountDownLatch(2);
//        List<Book> resultList = new ArrayList<>();
        List<Book> resultList = new ArrayList<>();

        Mono<List<Book>> response = webClient.get()
                .uri(uriBuilder -> uriBuilder           // uriBuilder를 이용해 동적으로 URI 구성
                        .queryParam("ttbkey", "ttbchjw9561322001")
                        .queryParam("QueryType", "Bestseller")
                        .queryParam("MaxResults", 10)
                        .queryParam("SearchTarget", "Book")
                        .queryParam("Output", "JS")
                        .queryParam("Version", 20131101)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()     // 결과를 어떻게 추출할 것인지 명시하는 데 사용함.
                .bodyToMono(new ParameterizedTypeReference<List<Book>>(){})
//                .bodyToMono(Book.class)
//                .subscribe(resultList);
//        .doOnTerminate(() -> cdl.countDown())
//                .map(Book::mapResponse)
//                .block();
                .doOnError(err -> {
                    System.err.println("API 요청 중 오류 발생: " + err.getMessage());
                });
//                });                 // 비동기 응답을 받아 처리함

        List<Book> books = response.block();            //Mono를 벗김

        System.out.println("List<Book>: "+ books);

        return books;
//        return response;
    }

    // 참고 사이트
    // https://stackoverflow.com/questions/72708612/get-list-of-json-objects-with-webclient
//    private List<Book> mapResponse(LinkedHashMap resp){
//        ObjectMapper mapper = new ObjectMapper();
//        List list = (ArrayList) resp.get("");
//    }

    // public static으로?
    // 그냥 리스트가 아니고 select문으로 해서 해야함!
//    public List<BookResponse.GetBook> findAll(){
//        List<Book> bookList = bookRepository.findAll();
//        List<BookResponse.GetBook> bookResponseList;
//        for(Book book: bookList){
//            BookResponse.GetBook.builder()
//                    .no()
//        }
    }

