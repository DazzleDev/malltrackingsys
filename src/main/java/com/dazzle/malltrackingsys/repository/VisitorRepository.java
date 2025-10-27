package com.dazzle.malltrackingsys.repository;

import com.dazzle.malltrackingsys.entity.VisitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface VisitorRepository extends JpaRepository<VisitorEntity, Long> {

    @Query("SELECT COUNT(v) FROM VisitorEntity v WHERE v.entryTime >= :start AND v.entryTime < :end")
    Long countVisitorsByTimeRange(LocalDateTime start, LocalDateTime end);
}
