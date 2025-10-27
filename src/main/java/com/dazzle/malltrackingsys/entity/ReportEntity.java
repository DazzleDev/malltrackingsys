package com.dazzle.malltrackingsys.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Tanggal laporan */
    @Column(nullable = false)
    private LocalDate reportDate;

    /** Jenis laporan: "DAILY" atau "WEEKLY" */
    @Column(nullable = false)
    private String type;

    /** Total pengunjung mall unik hari/minggu itu */
    @Column(nullable = false)
    private long totalMallVisitors;

    /** Total kunjungan ke outlet (bisa lebih dari pengunjung) */
    @Column(nullable = false)
    private long totalVisits;

    /** Total outlet aktif saat laporan dibuat */
    @Column(nullable = false)
    private long totalOutlets;

    /** Ringkasan per outlet, disimpan dalam bentuk JSON string */
    @Lob
    @Column(columnDefinition = "TEXT")
    private String outletSummaryJson;

    /** Deskripsi atau catatan tambahan */
    @Column(columnDefinition = "TEXT")
    private String description;
}
