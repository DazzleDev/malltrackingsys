package com.dazzle.malltrackingsys.scheduller;

import com.dazzle.malltrackingsys.service.VisitorService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VisitorGenerator {

    private final VisitorService visitorService;

    public VisitorGenerator(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    // Menjalankan setiap 10 menit untuk generate visitor baru
    @Scheduled(fixedRate = 600000) // 10 menit = 600000 ms
    public void generateVisitorData() {
        System.out.println("[Scheduller] Menjalankan generateVisitorData...");
        visitorService.generateRandomVisitor();
    }
}
