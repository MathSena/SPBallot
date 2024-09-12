package com.mathsena.spballot.controller;

import com.mathsena.spballot.model.Candidate;
import com.mathsena.spballot.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/candidates")
@AllArgsConstructor
@CrossOrigin
public class CandidatesController {

    private final CandidateRepository repository;

    @PostMapping("/save")
    public ResponseEntity<Candidate> salvar(@RequestBody Candidate candidate) {
        Candidate newCandidate = repository.save(candidate);
        return ResponseEntity.ok(newCandidate);
    }


    @GetMapping("/consulta")
    public ResponseEntity<Candidate> consulta(@RequestParam String id) {
        Optional<Candidate> opt = repository.findById(id);
        return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/todos")
    public ResponseEntity<List<Candidate>> todos() {
        List<Candidate> list = repository.findAll();
        return ResponseEntity.ok(list);
    }
}
