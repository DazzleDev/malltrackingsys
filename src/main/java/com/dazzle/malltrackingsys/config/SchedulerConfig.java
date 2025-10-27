package com.dazzle.malltrackingsys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Konfigurasi untuk mengaktifkan scheduler di seluruh aplikasi.
 */
@Configuration
@EnableScheduling
public class SchedulerConfig {
    // Tidak perlu isi apa-apa, cukup annotation-nya saja
}
