package com.chaekibackend;

import com.chaekibackend.book.domain.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChaeKiBackendApplicationTests {
    @Autowired
    private BookService bookService;
    @Test
    void contextLoads() {
        bookService.useWebClient();
    }

}
