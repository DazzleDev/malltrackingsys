package com.dazzle.malltrackingsys.service.impl;

import com.dazzle.malltrackingsys.entity.ReportEntity;
import com.dazzle.malltrackingsys.entity.VisitEntity;
import com.dazzle.malltrackingsys.repository.OutletRepository;
import com.dazzle.malltrackingsys.repository.ReportRepository;
import com.dazzle.malltrackingsys.repository.VisitRepository;
import com.dazzle.malltrackingsys.repository.VisitorRepository;
import com.dazzle.malltrackingsys.service.ReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final VisitRepository visitRepository;
    private final OutletRepository outletRepository;
    private final VisitorRepository visitorRepository;


    public ReportServiceImpl(VisitRepository visitRepository,
                             OutletRepository outletRepository,
                             VisitorRepository visitorRepository,
                             ReportRepository reportRepository) {
        this.visitRepository = visitRepository;
        this.outletRepository = outletRepository;
        this.visitorRepository = visitorRepository;
        this.reportRepository = reportRepository;
    }


    @Override
    public ReportEntity saveReport(ReportEntity report) {
        return reportRepository.save(report);
    }

    @Override
    public Optional<ReportEntity> getReportByDate(LocalDate date) {
        return reportRepository.findByReportDate(date);
    }

    @Override
    public List<ReportEntity> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public void generateDailyReport() {
        System.out.println("[ReportService] Generating daily report...");

        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.plusDays(1).atStartOfDay();

        // Ambil semua kunjungan hari ini
        List<VisitEntity> todayVisits = visitRepository.findByVisitTimeBetween(startOfDay, endOfDay);

        // Hitung pengunjung unik
        long uniqueVisitors = todayVisits.stream()
                .map(v -> v.getVisitor().getId())
                .distinct()
                .count();

        // Hitung total outlet aktif
        long totalOutlets = outletRepository.count();

        // Buat laporan
        ReportEntity report = new ReportEntity();
        report.setReportDate(today);
        report.setType("DAILY");
        report.setTotalMallVisitors(uniqueVisitors);
        report.setTotalVisits(todayVisits.size());
        report.setTotalOutlets(totalOutlets);
        report.setDescription("Laporan harian tanggal " + today);

        reportRepository.save(report);

        System.out.println("[ReportService] Laporan harian berhasil dibuat untuk " + today);
    }


    @Override
    public void generateWeeklyReport() {
        System.out.println("[ReportService] Generating weekly report...");

        // Ambil visit dalam 7 hari terakhir
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(6);

        LocalDateTime startOfDay = start.atStartOfDay();
        LocalDateTime endOfDay = end.plusDays(1).atStartOfDay(); // agar mencakup akhir hari terakhir
        List<VisitEntity> weeklyVisits = visitRepository.findByVisitTimeBetween(startOfDay, endOfDay);


        long totalVisits = weeklyVisits.size();
        long uniqueOutlets = weeklyVisits.stream()
                .map(v -> v.getOutlet().getId())
                .distinct()
                .count();

        ReportEntity report = new ReportEntity();
        report.setReportDate(end);
        report.setType("WEEKLY");
        report.setTotalVisits(totalVisits);
        report.setTotalOutlets(uniqueOutlets);
        report.setDescription("Weekly report (" + start + " - " + end + ")");

        reportRepository.save(report);

        System.out.println("[ReportService] Weekly report generated for period: " + start + " - " + end);
    }

    @Override
    public List<ReportEntity> getDailyReport() {
        LocalDate today = LocalDate.now();
        return reportRepository.findByTypeAndReportDate("DAILY", today);
    }

    @Override
    public List<ReportEntity> getWeeklyReport() {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(6);
        return reportRepository.findByTypeAndReportDateBetween("WEEKLY", start, end);
    }

}
