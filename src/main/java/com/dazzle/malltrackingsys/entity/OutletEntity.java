package com.dazzle.malltrackingsys.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "outlets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Nama outlet

    @Column(nullable = false)
    private String category; // Restaurant, Fashion, Electronics, dll

    @Column(nullable = false)
    private String location; // e.g. Lantai 1, Zona A

    @Column(nullable = false)
    private boolean active = true; // untuk filter outlet aktif

    @Column(nullable = false)
    private int currentQueue = 0; // jumlah antrian aktif saat ini

    @OneToMany(mappedBy = "outlet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisitEntity> visits;
}
