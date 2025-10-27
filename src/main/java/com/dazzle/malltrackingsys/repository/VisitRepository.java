package com.dazzle.malltrackingsys.repository;

import com.dazzle.malltrackingsys.entity.OutletEntity;
import com.dazzle.malltrackingsys.entity.VisitEntity;
import com.dazzle.malltrackingsys.entity.VisitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<VisitEntity, Long> {


    @Query("SELECT COUNT(v) FROM VisitEntity v WHERE v.visitTime >= :start AND v.visitTime < :end")
    Long countVisitsByTimeRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);


    List<VisitEntity> findByOutletId(Long outletId);


    @Query("SELECT COUNT(v) FROM VisitEntity v WHERE v.outlet.id = :outletId AND v.exitTime IS NULL")
    int countActiveVisitsByOutlet(@Param("outletId") Long outletId);


    Optional<VisitEntity> findByVisitorAndOutletAndExitTimeIsNull(VisitorEntity visitor, OutletEntity outlet);


    List<VisitEntity> findByVisitTimeBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
