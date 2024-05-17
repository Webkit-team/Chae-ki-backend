package com.chaekibackend;

import com.chaekibackend.chellenge.domain.interfaces.ChaekiWeekRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TodayTest {
    @Autowired
    private ChaekiWeekRepository weekRepository;

    @Test
    void test() {

    }
}
