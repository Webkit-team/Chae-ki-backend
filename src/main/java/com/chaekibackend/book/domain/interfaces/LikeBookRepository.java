package com.chaekibackend.book.domain.interfaces;

import com.chaekibackend.book.domain.entity.LikeBook;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeBookRepository extends JpaRepository<LikeBook, Long> {
    @Query("SELECT lB FROM LikeBook lB WHERE lB.users.no = :uno AND lB.book.no = :bno")
    LikeBook findLikeBookByNo(Long bno, Long uno);

    @Modifying
    @Transactional
    @Query("DELETE FROM LikeBook lB WHERE lB.users.no = :uno AND lB.book.no = :bno")
    void deleteLikeBookByNo(@Param("bno") Long bno, @Param("uno") Long uno);

}
