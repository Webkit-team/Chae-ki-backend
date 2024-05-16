package com.chaekibackend;

import com.chaekibackend.book.domain.interfaces.BookRepository;
import com.chaekibackend.book.domain.service.BookService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChaeKiBackendApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(ChaeKiBackendApplicationTests.class);
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void contextLoads() {
        bookService.useWebClient();
    }

    @Test
    void testChallengeSearch() {
        bookRepository
                .findByNameOrWriter("김호연")
                .forEach(book -> {
                    log.info("name : " + book.getName());
                    log.info("writer : " + book.getWriter());
                });
    }
}
