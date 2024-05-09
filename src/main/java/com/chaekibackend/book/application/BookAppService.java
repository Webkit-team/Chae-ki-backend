package com.chaekibackend.book.application;

import com.chaekibackend.book.domain.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookAppService {
    private final BookService bookService;

    // 여기서 Dto로 가공을 해줘야 하는데..?
//    public

}
