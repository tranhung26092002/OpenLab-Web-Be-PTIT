package com.example.webtoeic.repository;


import com.example.webtoeic.entity.KhoiDieuKhienEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KhoiDieuKhienRepository extends JpaRepository<KhoiDieuKhienEntity, Integer> {
//    Optional<KhoiDieuKhienEntity> findByDieukhienId(int id);

    @Query("select dieuKhien FROM KhoiDieuKhienEntity dieuKhien WHERE dieuKhien.tenDieuKhien LIKE CONCAT('%',:search,'%')")
    List<KhoiDieuKhienEntity> searchDieuKhien(@Param("search") String search);
}
