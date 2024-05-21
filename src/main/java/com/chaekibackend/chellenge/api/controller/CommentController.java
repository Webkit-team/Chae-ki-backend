package com.chaekibackend.chellenge.api.controller;

import com.chaekibackend.chellenge.api.request.CommentRequest;
import com.chaekibackend.chellenge.api.response.CommentResponse;
import com.chaekibackend.chellenge.application.CommentAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
