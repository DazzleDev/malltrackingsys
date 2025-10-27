package com.dazzle.malltrackingsys.service;

import com.dazzle.malltrackingsys.entity.OutletEntity;

import java.util.List;
import java.util.Optional;

public interface OutletService {
    OutletEntity createOutlet(OutletEntity outlet);
    List<OutletEntity> getAllOutlets();
    Long getVisitCount(Long outletId);

    Optional<OutletEntity> getOutletById(Long id);

    Optional<OutletEntity> updateOutlet(Long id, OutletEntity outlet);

    void deleteOutlet(Long id);

    List<OutletEntity> getActiveOutlets();

    int getQueueCount(Long id);
}
