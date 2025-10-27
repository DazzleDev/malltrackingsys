package com.dazzle.malltrackingsys.repository;

import com.dazzle.malltrackingsys.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
    Optional<ReportEntity> findByReportDate(LocalDate date);

    List<ReportEntity> findByTypeAndReportDate(String daily, LocalDate today);

    List<ReportEntity> findByTypeAndReportDateBetween(String weekly, LocalDate start, LocalDate end);
}
