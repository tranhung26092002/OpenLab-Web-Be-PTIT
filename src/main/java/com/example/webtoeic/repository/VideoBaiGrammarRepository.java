package com.example.webtoeic.repository;

import com.example.webtoeic.entity.VideoBaiGrammarEntity;
import com.example.webtoeic.entity.keys.VideoBaiGrammarId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoBaiGrammarRepository extends JpaRepository<VideoBaiGrammarEntity, Integer> {
    List<VideoBaiGrammarEntity> findByBaiGrammar_BaiGrammarId(int baiGrammarId);

    boolean existsById(VideoBaiGrammarId videoBaiGrammarId);
}
