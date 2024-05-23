package com.chaekibackend.chellenge.api.controller;

import com.chaekibackend.chellenge.api.request.CommentRequest;
import com.chaekibackend.chellenge.api.response.CommentResponse;
import com.chaekibackend.chellenge.application.CommentAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentAppService commentAppService;

    // 댓글 작성 API
    @PostMapping("/comments/users/{uno}/challenges/{cno}")
    public CommentResponse.Register registerComment(
            @PathVariable("uno") Long userNo,
            @PathVariable("cno") Long challengeNo,
            @RequestBody CommentRequest.Register request
            ) {
        return commentAppService.registerComment(userNo, challengeNo, request);
    }

    // 댓글 좋아요 API
    @PutMapping("/like/comments/{commentNo}")
    public CommentResponse.Like likeComment(
            @PathVariable("commentNo") Long commentNo,
            @RequestBody CommentRequest.Like request
    ) {
        return commentAppService.likeComment(commentNo, request);
    }
}
