package com.dazzle.malltrackingsys.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "visitors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String deviceId; // unik per pengunjung (cookie / BLE tracker / cam ID)

    private LocalDateTime entryTime; // waktu pertama kali masuk mall
    private LocalDateTime exitTime;  // waktu keluar mall

    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisitEntity> visits;

    @ManyToOne
    @JoinColumn(name = "current_outlet_id")
    private OutletEntity currentOutlet; // outlet yang sedang dikunjungi

    @Column(nullable = false)
    private boolean hasPaid = false; // status apakah sudah membayar atau belum
}
