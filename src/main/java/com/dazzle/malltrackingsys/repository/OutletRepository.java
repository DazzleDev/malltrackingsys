package com.dazzle.malltrackingsys.repository;

import com.dazzle.malltrackingsys.entity.OutletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OutletRepository extends JpaRepository<OutletEntity, Long> {

    @Query("SELECT COUNT(v) FROM VisitEntity v WHERE v.outlet.id = :outletId")
    Long countVisitsByOutletId(Long outletId);
}
