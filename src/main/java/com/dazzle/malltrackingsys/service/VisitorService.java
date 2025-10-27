package com.dazzle.malltrackingsys.service;

import com.dazzle.malltrackingsys.entity.VisitorEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VisitorService {
    VisitorEntity createVisitor(VisitorEntity visitor);
    Long countVisitorsByTimeRange(LocalDateTime start, LocalDateTime end);
    List<VisitorEntity> getAllVisitors();
    void generateRandomVisitor();

    Optional<VisitorEntity> getVisitorById(Long id);

    void deleteVisitor(Long id);
}

