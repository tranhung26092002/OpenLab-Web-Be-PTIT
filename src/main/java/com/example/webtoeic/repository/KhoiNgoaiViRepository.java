package com.example.webtoeic.repository;

import com.example.webtoeic.entity.KhoiDieuKhienEntity;
import com.example.webtoeic.entity.KhoiNgoaiViEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KhoiNgoaiViRepository extends JpaRepository<KhoiNgoaiViEntity, Integer> {
//    Optional<KhoiNgoaiViEntity> findByNgoaiViId(int id);

    @Query("select ngoaiVi FROM KhoiNgoaiViEntity ngoaiVi WHERE ngoaiVi.tenNgoaiVi LIKE CONCAT('%',:search,'%')")
    List<KhoiDieuKhienEntity> searchDieuKhien(@Param("search") String search);
}
