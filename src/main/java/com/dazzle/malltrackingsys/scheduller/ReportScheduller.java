package com.dazzle.malltrackingsys.scheduller;

import com.dazzle.malltrackingsys.service.ReportService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportScheduller {

    private final ReportService reportService;

    public ReportScheduller(ReportService reportService) {
        this.reportService = reportService;
    }

    // Menjalankan setiap jam 23:00 (UTC+7)
    @Scheduled(cron = "0 0 23 * * *", zone = "Asia/Jakarta")
    public void generateDailyReport() {
        System.out.println("[Scheduller] Menjalankan generateDailyReport...");
        reportService.generateDailyReport();
    }

    // Menjalankan setiap Senin pukul 08:00
    @Scheduled(cron = "0 0 8 * * MON", zone = "Asia/Jakarta")
    public void generateWeeklyReport() {
        System.out.println("[Scheduller] Menjalankan generateWeeklyReport...");
        reportService.generateWeeklyReport();
    }
}
