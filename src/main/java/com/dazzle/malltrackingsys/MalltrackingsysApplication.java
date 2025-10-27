package com.dazzle.malltrackingsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableScheduling // 🔥 Aktifkan scheduler di seluruh aplikasi
public class MalltrackingsysApplication {

    public static void main(String[] args) {
        SpringApplication.run(MalltrackingsysApplication.class, args);
        System.out.println("✅ Mall Tracking System is running with Scheduler enabled...");
    }

}
