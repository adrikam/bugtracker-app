package com.example.bugtracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bugs")
public class BugController {

    @Autowired
    private BugRepository repo;

    @GetMapping
    public List<Bug> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Bug create(@RequestBody Bug bug) {
        bug.setStatus("open");
        return repo.save(bug);
    }

    @PutMapping("/{id}")
    public Bug update(@PathVariable Long id, @RequestBody Bug updatedBug) {
        Bug bug = repo.findById(id).orElseThrow();
        bug.setStatus(updatedBug.getStatus());
        return repo.save(bug);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
