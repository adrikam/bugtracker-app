package com.example.bugtracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://heroic-yeot-5cbd71.netlify.app")
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
