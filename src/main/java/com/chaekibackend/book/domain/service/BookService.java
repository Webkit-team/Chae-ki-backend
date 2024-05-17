package com.chaekibackend.book.domain.service;

import com.chaekibackend.book.api.response.AladinResponse;
import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.book.domain.entity.LikeBook;
import com.chaekibackend.book.domain.interfaces.BookRepository;
import com.chaekibackend.book.domain.interfaces.LikeBookRepository;
import com.chaekibackend.configuration.webClient.WebClientConfig;
import com.chaekibackend.users.domain.entity.Users;
import com.chaekibackend.users.domain.interfaces.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final UsersRepository usersRepository;
    private final LikeBookRepository likeBookRepository;

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
        
        // 챌린지 카테고리 해당되지 않으면 DB에 안 들어가게 하기!

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
// TODO:                         .pageNumber()  => 직접 넣어주기
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
    }

    // 도서 상세 정보 조회
    public Book readBook(Long no){
        return bookRepository.findByNo(no);
    }

    public List<Book> searchBook(String word){
        return bookRepository.findByNameOrWriter(word);
    }
    
    // 등록된 도서 찜 내역이 있는지 확인(조회)하는 메서드
    public Boolean readBookLike(Long bno, Long uno){
        LikeBook likeBook = likeBookRepository.findLikeBookByNo(bno, uno);
        if(likeBook != null){
            return true;
        }else
            return false;
    }
    
    public void createLikeBook(Long bno, Long uno){
        Book book = bookRepository.findByNo(bno);
        Users user = usersRepository.findByNo(uno);

        LikeBook likeBook = LikeBook.builder()
                .createdAt(LocalDate.now())
                .users(user)
                .book(book)
                .build();

        likeBookRepository.save(likeBook);
    }
}

