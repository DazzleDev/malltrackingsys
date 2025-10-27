package com.dazzle.malltrackingsys.repository;

import com.dazzle.malltrackingsys.entity.OutletEntity;
import com.dazzle.malltrackingsys.entity.VisitEntity;
import com.dazzle.malltrackingsys.entity.VisitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<VisitEntity, Long> {

    /**
     * Hitung jumlah total kunjungan dalam rentang waktu.
     */
    @Query("SELECT COUNT(v) FROM VisitEntity v WHERE v.visitTime >= :start AND v.visitTime < :end")
    Long countVisitsByTimeRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    /**
     * Ambil semua kunjungan berdasarkan outlet.
     */
    List<VisitEntity> findByOutletId(Long outletId);

    /**
     * Hitung jumlah kunjungan aktif (belum keluar) di outlet tertentu.
     */
    @Query("SELECT COUNT(v) FROM VisitEntity v WHERE v.outlet.id = :outletId AND v.exitTime IS NULL")
    int countActiveVisitsByOutlet(@Param("outletId") Long outletId);

    /**
     * Cari kunjungan aktif berdasarkan visitor dan outlet.
     */
    Optional<VisitEntity> findByVisitorAndOutletAndExitTimeIsNull(VisitorEntity visitor, OutletEntity outlet);

    /**
     * Ambil semua kunjungan dalam rentang waktu tertentu.
     * (Gunakan ini untuk laporan harian/mingguan)
     */
    List<VisitEntity> findByVisitTimeBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
