package com.example.webtoeic.repository;


import com.example.webtoeic.entity.BaiGrammar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaiGrammarRepository extends JpaRepository<BaiGrammar, Integer> {
    Optional<BaiGrammar> findByBaiGrammarId(int id);

    @Query("select grammar FROM BaiGrammar grammar WHERE grammar.tenBaiGrammar LIKE CONCAT('%',:search,'%')")
    List<BaiGrammar> searchGrammar(@Param("search") String search);
}
