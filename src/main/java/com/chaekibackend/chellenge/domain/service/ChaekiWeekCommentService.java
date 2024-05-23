package com.chaekibackend.chellenge.domain.service;

import com.chaekibackend.chellenge.domain.entity.ChaekiToday;
import com.chaekibackend.chellenge.domain.entity.ChaekiWeekComment;
import com.chaekibackend.chellenge.domain.interfaces.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChaekiWeekCommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public ChaekiWeekComment save(ChaekiWeekComment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    public ChaekiWeekComment findByNo(Long commentNo) {
        Optional<ChaekiWeekComment> opt = commentRepository.findById(commentNo);
        if(opt.isEmpty()) {
            log.error("존재하지 않는 댓글입니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다.");
        }

        return opt.get();
    }

    @Transactional
    public ChaekiWeekComment likeComment(Long commentNo, Boolean liked) {
        Integer offset = liked ? 1 : -1;
        ChaekiWeekComment comment = findByNo(commentNo);
        int newLikeCount = comment.getLikeCount() + offset;
        newLikeCount = Math.max(newLikeCount, 0);
        comment.setLikeCount(newLikeCount);

        return commentRepository.save(comment);
    }
}
