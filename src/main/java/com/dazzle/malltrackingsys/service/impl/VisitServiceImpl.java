package com.dazzle.malltrackingsys.service.impl;

import com.dazzle.malltrackingsys.entity.OutletEntity;
import com.dazzle.malltrackingsys.entity.VisitEntity;
import com.dazzle.malltrackingsys.entity.VisitorEntity;
import com.dazzle.malltrackingsys.repository.OutletRepository;
import com.dazzle.malltrackingsys.repository.VisitRepository;
import com.dazzle.malltrackingsys.repository.VisitorRepository;
import com.dazzle.malltrackingsys.service.VisitService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VisitServiceImpl implements VisitService {

    private final VisitorRepository visitorRepository;
    private final OutletRepository outletRepository;
    private final VisitRepository visitRepository;

    public VisitServiceImpl(VisitorRepository visitorRepository, OutletRepository outletRepository, VisitRepository visitRepository) {
        this.visitorRepository = visitorRepository;
        this.outletRepository = outletRepository;
        this.visitRepository = visitRepository;
    }

    @Override
    public VisitEntity createVisit(VisitEntity visit) {
        visit.setVisitTime(LocalDateTime.now());
        visit.setHasPaid(false);
        return visitRepository.save(visit);
    }

    @Override
    public VisitEntity completeVisit(Long visitId) {
        VisitEntity visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("Visit not found"));

        visit.setExitTime(LocalDateTime.now());
        visit.setHasPaid(true);
        return visitRepository.save(visit);
    }

    @Override
    public Long countVisitsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return visitRepository.countVisitsByTimeRange(start, end);
    }

    @Override
    public List<VisitEntity> getAllVisits() {
        return visitRepository.findAll();
    }

    @Override
    public List<VisitEntity> getVisitsByOutlet(Long outletId) {
        return visitRepository.findByOutletId(outletId);
    }

    @Override
    public Optional<VisitEntity> getVisitById(Long id) {
        return visitRepository.findById(id);
    }

    @Override
    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }

    @Override
    public void startVisit(Long userId, Long outletId) {
        // ✅ Cek apakah user & outlet valid
        Optional<VisitorEntity> visitorOpt = visitorRepository.findById(userId);
        Optional<OutletEntity> outletOpt = outletRepository.findById(outletId);

        if (visitorOpt.isEmpty() || outletOpt.isEmpty()) {
            throw new RuntimeException("Visitor atau Outlet tidak ditemukan!");
        }

        VisitorEntity visitor = visitorOpt.get();
        OutletEntity outlet = outletOpt.get();

        // ✅ Cek apakah user sudah punya visit aktif di outlet ini
        Optional<VisitEntity> existingVisit = visitRepository
                .findByVisitorAndOutletAndExitTimeIsNull(visitor, outlet);

        if (existingVisit.isPresent()) {
            System.out.println("[VisitService] User sudah berada di outlet ini.");
            return;
        }

        // ✅ Buat visit baru
        VisitEntity visit = VisitEntity.builder()
                .visitor(visitor)
                .outlet(outlet)
                .visitTime(LocalDateTime.now())
                .build();

        visitRepository.save(visit);
        System.out.println("[VisitService] User " + userId + " memulai kunjungan ke outlet " + outlet.getName());
    }

    @Override
    public void finishVisit(Long userId, Long outletId) {
        Optional<VisitorEntity> visitorOpt = visitorRepository.findById(userId);
        Optional<OutletEntity> outletOpt = outletRepository.findById(outletId);

        if (visitorOpt.isEmpty() || outletOpt.isEmpty()) {
            throw new RuntimeException("Visitor atau Outlet tidak ditemukan!");
        }

        VisitorEntity visitor = visitorOpt.get();
        OutletEntity outlet = outletOpt.get();

        // ✅ Cari kunjungan aktif (yang belum ada exitTime)
        Optional<VisitEntity> activeVisit = visitRepository
                .findByVisitorAndOutletAndExitTimeIsNull(visitor, outlet);

        if (activeVisit.isEmpty()) {
            System.out.println("[VisitService] Tidak ada kunjungan aktif untuk user ini di outlet tersebut.");
            return;
        }

        VisitEntity visit = activeVisit.get();
        visit.setExitTime(LocalDateTime.now());
        visitRepository.save(visit);

        System.out.println("[VisitService] User " + userId + " telah menyelesaikan kunjungan ke outlet " + outlet.getName());
    }

}
