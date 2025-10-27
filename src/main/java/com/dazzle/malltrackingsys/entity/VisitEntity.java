package com.dazzle.malltrackingsys.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visits")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "visitor_id", nullable = false)
    private VisitorEntity visitor;

    @ManyToOne
    @JoinColumn(name = "outlet_id", nullable = false)
    private OutletEntity outlet;

    @Column(nullable = false)
    private LocalDateTime visitTime;

    private LocalDateTime exitTime;

    @Column(nullable = false)
    private boolean hasPaid = false;

    public long getDurationMinutes() {
        if (exitTime != null && visitTime != null) {
            return java.time.Duration.between(visitTime, exitTime).toMinutes();
        }
        return 0;
    }
}
