package com.dazzle.malltrackingsys.service.impl;

import com.dazzle.malltrackingsys.entity.OutletEntity;
import com.dazzle.malltrackingsys.repository.OutletRepository;
import com.dazzle.malltrackingsys.repository.VisitRepository;
import com.dazzle.malltrackingsys.service.OutletService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OutletServiceImpl implements OutletService {

    private final OutletRepository outletRepository;
    private final VisitRepository visitRepository;

    public OutletServiceImpl(OutletRepository outletRepository, VisitRepository visitRepository) {
        this.outletRepository = outletRepository;
        this.visitRepository = visitRepository;
    }

    @Override
    public OutletEntity createOutlet(OutletEntity outlet) {
        return outletRepository.save(outlet);
    }

    @Override
    public List<OutletEntity> getAllOutlets() {
        return outletRepository.findAll();
    }

    @Override
    public Long getVisitCount(Long outletId) {
        return outletRepository.countVisitsByOutletId(outletId);
    }

    @Override
    public Optional<OutletEntity> getOutletById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<OutletEntity> updateOutlet(Long id, OutletEntity outlet) {
        return Optional.empty();
    }

    @Override
    public void deleteOutlet(Long id) {

    }

    @Override
    public List<OutletEntity> getActiveOutlets() {
        return outletRepository.findAll()
                .stream()
                .filter(OutletEntity::isActive) // 🔥 Java Stream filtering
                .collect(Collectors.toList());
    }

    @Override
    public int getQueueCount(Long outletId) {
        return visitRepository.countActiveVisitsByOutlet(outletId);
    }
}

