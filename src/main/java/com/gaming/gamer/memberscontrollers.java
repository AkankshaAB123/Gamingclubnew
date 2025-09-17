package com.gaming.gamer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class memberscontrollers {

    @Autowired
    private membersrepository repo;

    @PostMapping
    public members create(@RequestBody members member) {
        member.setId(null);
        return repo.save(member);
    }

    @GetMapping
    public List<members> findAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public members findById(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public members update(@PathVariable String id, @RequestBody members updated) {
        members old = repo.findById(id).orElse(null);
        if (old != null) {
            old.setName(updated.getName());
            old.setPhone(updated.getPhone());
            old.setBalance(updated.getBalance());
            return repo.save(old);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        Optional<members> optional = repo.findById(id);
        if (optional.isEmpty()) return false;
        repo.deleteById(id);
        return true;
    }
}
