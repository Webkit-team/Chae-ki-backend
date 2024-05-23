package com.chaekibackend.chellenge.api.response;

import com.chaekibackend.chellenge.domain.entity.ChaekiToday;
import com.chaekibackend.chellenge.domain.entity.ChaekiWeekComment;
import com.chaekibackend.chellenge.domain.entity.ChallengeMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class WeekResponse {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class TodayDetail {
        private Long todayNo;
        private String content;
        private LocalDate createdAt;
        private Integer likeCount;

        public static TodayDetail from (ChaekiToday today) {
            return TodayDetail.builder()
                    .todayNo(today.getNo())
                    .content(today.getContent())
                    .createdAt(today.getCreatedAt())
                    .likeCount(today.getLikeCount())
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class TodayInfo {
        private Long userNo;
        private String nickname;
        private String imageUrl;
        private List<TodayDetail> todays;

        public static TodayInfo from (ChallengeMember member, List<WeekResponse.TodayDetail> todayDetails) {
            return TodayInfo.builder()
                    .userNo(member.getUsers().getNo())
                    .nickname(member.getUsers().getNickname())
                    .imageUrl(member.getUsers().getImageUrl())
                    .todays(todayDetails)
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CommentDetail {
        private Long commentNo;
        private String nickname;
        private String imageUrl;
        private String content;
        private Integer likeCount;
        private Long userNo;

        public static CommentDetail from (ChaekiWeekComment comment) {
            return CommentDetail.builder()
                    .commentNo(comment.getNo())
                    .nickname(comment.getChallengeMember().getUsers().getNickname())
                    .imageUrl(comment.getChallengeMember().getUsers().getImageUrl())
                    .content(comment.getContent())
                    .likeCount(comment.getLikeCount())
                    .userNo(comment.getChallengeMember().getUsers().getNo())
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class WeekInfo {
        private List<TodayInfo> users;
        private List<CommentDetail> comments;
    }
}
