package com.chaekibackend.chellenge.domain.service;

import com.chaekibackend.chellenge.domain.entity.ChaekiWeekComment;
import com.chaekibackend.chellenge.domain.interfaces.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChaekiWeekCommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public ChaekiWeekComment save(ChaekiWeekComment comment) {
        return commentRepository.save(comment);
    }
}
