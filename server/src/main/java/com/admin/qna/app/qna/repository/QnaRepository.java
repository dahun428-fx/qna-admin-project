package com.admin.qna.app.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.qna.models.Qna;
import java.util.Optional;



public interface QnaRepository extends JpaRepository<Qna, Long> {

    
    // Optional<Qna> findByQnaId(Long qnaId);
}
