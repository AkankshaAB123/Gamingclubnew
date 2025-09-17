package com.gaming.gamer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collections")
public class collectionscontroller {

    @Autowired
    private collectionsrepository repo;

    @PostMapping
    public collections create(@RequestBody collections collection) {
        collection.setId(null);
        return repo.save(collection);
    }

    @GetMapping
    public List<collections> findAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public collections findById(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public collections update(@PathVariable String id, @RequestBody collections updated) {
        collections old = repo.findById(id).orElse(null);
        if (old != null) {
            old.setDate(updated.getDate());
            old.setAmount(updated.getAmount());
            return repo.save(old);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        Optional<collections> optional = repo.findById(id);
        if (optional.isEmpty()) return false;
        repo.deleteById(id);
        return true;
    }
}
