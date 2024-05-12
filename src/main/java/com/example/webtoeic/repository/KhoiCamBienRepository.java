package com.example.webtoeic.repository;

import com.example.webtoeic.entity.KhoiCamBienEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KhoiCamBienRepository extends JpaRepository<KhoiCamBienEntity, Integer> {
//    Optional<KhoiCamBienEntity> findByCamBienId(int id);

    @Query("select camBien FROM KhoiCamBienEntity camBien WHERE camBien.tenCamBien LIKE CONCAT('%',:search,'%')")
    List<KhoiCamBienEntity> searchCamBien(@Param("search") String search);



}
