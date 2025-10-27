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


    @Column(nullable = false)
    private LocalDate reportDate;

    @Column(nullable = false)
    private String type;


    @Column(nullable = false)
    private long totalMallVisitors;


    @Column(nullable = false)
    private long totalVisits;


    @Column(nullable = false)
    private long totalOutlets;


    @Lob
    @Column(columnDefinition = "TEXT")
    private String outletSummaryJson;


    @Column(columnDefinition = "TEXT")
    private String description;
}
