package com.easyloan.controller;

import com.easyloan.model.User;
import com.easyloan.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository repo;
    public UserController(UserRepository repo){ this.repo = repo; }

    @GetMapping
    public List<User> all(){ return repo.findAll(); }

    @PostMapping
    public User create(@Valid @RequestBody User user){ return repo.save(user); }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User u){
        return repo.findById(id).map(existing -> {
            existing.setName(u.getName());
            existing.setEmail(u.getEmail());
            existing.setRole(u.getRole());
            repo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return repo.findById(id).map(existing -> {
            repo.delete(existing);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
