package com.dazzle.malltrackingsys.controller;

import com.dazzle.malltrackingsys.entity.*;
import com.dazzle.malltrackingsys.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MallController {

    private final OutletService outletService;
    private final UserService userService;
    private final VisitorService visitorService;
    private final VisitService visitService;
    private final ReportService reportService;

    // ========================= OUTLET SECTION =========================
    @GetMapping("/outlets")
    public List<OutletEntity> getAllOutlets() {
        return outletService.getAllOutlets();
    }

    @GetMapping("/outlets/{id}")
    public ResponseEntity<OutletEntity> getOutletById(@PathVariable Long id) {
        return ResponseEntity.of(outletService.getOutletById(id));
    }

    @PostMapping("/outlets")
    public OutletEntity createOutlet(@RequestBody OutletEntity outlet) {
        return outletService.createOutlet(outlet);
    }

    @PutMapping("/outlets/{id}")
    public ResponseEntity<OutletEntity> updateOutlet(@PathVariable Long id, @RequestBody OutletEntity outlet) {
        return ResponseEntity.of(outletService.updateOutlet(id, outlet));
    }

    @DeleteMapping("/outlets/{id}")
    public void deleteOutlet(@PathVariable Long id) {
        outletService.deleteOutlet(id);
    }

    @GetMapping("/outlets/active")
    public ResponseEntity<List<OutletEntity>> getActiveOutlets() {
        return ResponseEntity.ok(outletService.getActiveOutlets());
    }

    @GetMapping("/outlets/{id}/queue")
    public ResponseEntity<Integer> getOutletQueueCount(@PathVariable Long id) {
        int queueCount = outletService.getQueueCount(id);
        return ResponseEntity.ok(queueCount);
    }

    // ========================= USER SECTION =========================
    @GetMapping("/users")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        return ResponseEntity.of(userService.getUserById(id));
    }

    @PostMapping("/users")
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.createUser(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        return ResponseEntity.of(userService.updateUser(id, user));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // ========================= VISITOR SECTION =========================
    @GetMapping("/visitors")
    public List<VisitorEntity> getAllVisitors() {
        return visitorService.getAllVisitors();
    }

    @GetMapping("/visitors/{id}")
    public ResponseEntity<VisitorEntity> getVisitorById(@PathVariable Long id) {
        return ResponseEntity.of(visitorService.getVisitorById(id));
    }

    @PostMapping("/visitors")
    public VisitorEntity createVisitor(@RequestBody VisitorEntity visitor) {
        return visitorService.createVisitor(visitor);
    }

    @DeleteMapping("/visitors/{id}")
    public void deleteVisitor(@PathVariable Long id) {
        visitorService.deleteVisitor(id);
    }

    @GetMapping("/visitors/generate-random")
    public ResponseEntity<String> generateRandomVisitors() {
        visitorService.generateRandomVisitor();
        return ResponseEntity.ok("Random visitors generated successfully.");
    }


    // ========================= VISIT SECTION =========================
    @GetMapping("/visits")
    public List<VisitEntity> getAllVisits() {
        return visitService.getAllVisits();
    }

    @GetMapping("/visits/{id}")
    public ResponseEntity<VisitEntity> getVisitById(@PathVariable Long id) {
        return ResponseEntity.of(visitService.getVisitById(id));
    }

    // === [1] USER KLIK VISIT → MASUK ANTRIAN ===
    @PostMapping("/visits/start")
    public ResponseEntity<String> startVisit(@RequestBody VisitEntity visit) {
        visitService.startVisit(
                visit.getVisitor().getId(),
                visit.getOutlet().getId()
        );
        return ResponseEntity.ok(
                "✅ Visitor " + visit.getVisitor().getId() + " added to queue for outlet " + visit.getOutlet().getId()
        );
    }

    // === [2] USER SELESAI BAYAR → KELUAR ANTRIAN ===
    @PostMapping("/visits/finish")
    public ResponseEntity<String> finishVisit(@RequestBody VisitEntity visit) {
        visitService.finishVisit(
                visit.getVisitor().getId(),
                visit.getOutlet().getId()
        );
        return ResponseEntity.ok(
                "💰 Visitor " + visit.getVisitor().getId() + " finished visit at outlet " + visit.getOutlet().getId()
        );
    }


    @DeleteMapping("/visits/{id}")
    public void deleteVisit(@PathVariable Long id) {
        visitService.deleteVisit(id);
    }

    // ========================= REPORT SECTION =========================
    @GetMapping("/reports")
    public List<ReportEntity> getAllReports() {
        return reportService.getAllReports();
    }

    // === [3] ADMIN MELIHAT REPORT PER HARI ===
    @GetMapping("/reports/daily")
    public List<ReportEntity> getDailyReport() {
        return reportService.getDailyReport();
    }

    @GetMapping("/reports/weekly")
    public List<ReportEntity> getWeeklyReport() {
        return reportService.getWeeklyReport();
    }
}
