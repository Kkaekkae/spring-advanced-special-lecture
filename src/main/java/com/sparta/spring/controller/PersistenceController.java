package com.sparta.spring.controller;

import com.sparta.spring.application.PersistenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersistenceController {
    private final PersistenceService persistenceService;

    public PersistenceController(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    @GetMapping("1")
    public ResponseEntity<Boolean> testPersistence() {
        persistenceService.testPersistence();
        return ResponseEntity.ok(true);
    }

    @GetMapping("2")
    public ResponseEntity<Boolean> testPersistence2() {
        persistenceService.testPersistence2();
        return ResponseEntity.ok(true);
    }

    @GetMapping("3")
    public ResponseEntity<Boolean> testPersistence3() {
        persistenceService.testPersistence3();
        return ResponseEntity.ok(true);
    }

    @GetMapping("4")
    public ResponseEntity<Boolean> testPersistence4() {
        persistenceService.testPersistence4();
        return ResponseEntity.ok(true);
    }
}
