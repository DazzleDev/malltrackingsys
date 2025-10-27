package com.dazzle.malltrackingsys.service.impl;

import com.dazzle.malltrackingsys.entity.VisitorEntity;
import com.dazzle.malltrackingsys.repository.VisitorRepository;
import com.dazzle.malltrackingsys.service.VisitorService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorRepository;

    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @Override
    public VisitorEntity createVisitor(VisitorEntity visitor) {
        visitor.setEntryTime(LocalDateTime.now());
        return visitorRepository.save(visitor);
    }

    @Override
    public Long countVisitorsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return visitorRepository.countVisitorsByTimeRange(start, end);
    }

    @Override
    public List<VisitorEntity> getAllVisitors() {
        return visitorRepository.findAll();
    }

    @Override
    public void generateRandomVisitor() {
        Random rand = new Random();
        int count = rand.nextInt(10) + 1;

        System.out.println("[VisitorService] Generating " + count + " random visitors...");

        for (int i = 0; i < count; i++) {
            VisitorEntity visitor = VisitorEntity.builder()
                    .deviceId("DEV-" + UUID.randomUUID().toString().substring(0, 8))
                    .entryTime(LocalDateTime.now().minusMinutes(rand.nextInt(60)))
                    .exitTime(null)
                    .build();

            visitorRepository.save(visitor);
        }

        System.out.println("[VisitorService] ✅ Successfully generated and saved " + count + " random visitors to DB.");
    }

    @Override
    public Optional<VisitorEntity> getVisitorById(Long id) {
        return visitorRepository.findById(id);
    }

    @Override
    public void deleteVisitor(Long id) {
        visitorRepository.deleteById(id);
    }
}
