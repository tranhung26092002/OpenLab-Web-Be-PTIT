package com.example.webtoeic.repository;

import com.example.webtoeic.entity.BaiGrammar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<BaiGrammar, Integer> {
    Optional<BaiGrammar> findByAnhBaiGrammar(String fileName);
}
