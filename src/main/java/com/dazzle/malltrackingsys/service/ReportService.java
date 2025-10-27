package com.dazzle.malltrackingsys.service;

import com.dazzle.malltrackingsys.entity.ReportEntity;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

public interface ReportService {
    ReportEntity saveReport(ReportEntity report);
    Optional<ReportEntity> getReportByDate(LocalDate date);
    List<ReportEntity> getAllReports();
    void generateDailyReport();
    void generateWeeklyReport();

    List<ReportEntity> getDailyReport();

    List<ReportEntity> getWeeklyReport();
}
