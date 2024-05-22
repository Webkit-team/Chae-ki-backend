package com.chaekibackend.chellenge.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public class ChaekiTodayRequest {
    @Schema(name = "ChaekiTodayRequest.Creation")
    @Getter
    public static class Creation {
        private String content;
        private Integer readingPage;
        private Integer readingTime;
    }

    @Schema(name = "ChaekiTodayRequest.TimerSave")
    @Getter
    public static class TimerSave {
        private Integer time;
        private Integer page;
    }

    @Getter
    public static class Like {
        private Boolean liked;
    }
}
