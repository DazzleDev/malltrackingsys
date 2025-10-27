package com.dazzle.malltrackingsys.service;

import com.dazzle.malltrackingsys.entity.VisitEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VisitService {
    VisitEntity createVisit(VisitEntity visit);
    VisitEntity completeVisit(Long visitId); // ✅ tambahan
    Long countVisitsByTimeRange(LocalDateTime start, LocalDateTime end);
    List<VisitEntity> getAllVisits();
    List<VisitEntity> getVisitsByOutlet(Long outletId); // ✅ tambahan
    Optional<VisitEntity> getVisitById(Long id);
    void deleteVisit(Long id);

    void startVisit(Long userId, Long outletId);

    void finishVisit(Long userId, Long outletId);
}
