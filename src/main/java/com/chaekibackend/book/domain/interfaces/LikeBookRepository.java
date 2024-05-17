package com.chaekibackend.book.domain.interfaces;

import com.chaekibackend.book.domain.entity.LikeBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeBookRepository extends JpaRepository<LikeBook, Long> {
}
