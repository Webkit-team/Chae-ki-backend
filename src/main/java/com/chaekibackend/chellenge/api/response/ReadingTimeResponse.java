package com.chaekibackend.chellenge.api.response;

import com.chaekibackend.chellenge.domain.entity.ChaekiToday;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// 이게 가능한 구조가 맞나..?
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadingTimeResponse {
    private Long challengeNo;
    private List<ReadingTimeResponse.Detail> readingTimes;

    public static ReadingTimeResponse createReadingTimeResponse(ChaekiToday chaekiToday,
                                                                List<ReadingTimeResponse.Detail> readingTimes){
        List<ReadingTimeResponse.Detail> copyReadingTimes = new ArrayList<>(readingTimes);

        return ReadingTimeResponse.builder()
                .challengeNo(chaekiToday.getChallengeMember().getChallenge().getNo())
                .readingTimes(copyReadingTimes)
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Detail{
        private LocalDate createdAt;
        private Integer readingTime;

        public static ReadingTimeResponse.Detail from(ChaekiToday chaekiToday){
            return Detail.builder()
                    .createdAt(chaekiToday.getCreatedAt())
                    .readingTime(chaekiToday.getReadingTime())
                    .build();
        }
    }

}
