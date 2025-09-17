package com.gaming.gamer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin_users")
public class admin_userscontroller {

    @Autowired
    private admin_usersrepository repo;

    @PostMapping
    public admin_users create(@RequestBody admin_users admin) {
        admin.setId(null);
        return repo.save(admin);
    }

    @GetMapping
    public List<admin_users> findAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public admin_users findById(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public admin_users update(@PathVariable String id, @RequestBody admin_users updated) {
        admin_users old = repo.findById(id).orElse(null);
        if (old != null) {
            old.setUsername(updated.getUsername());
            old.setPassword(updated.getPassword());
            return repo.save(old);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        Optional<admin_users> optional = repo.findById(id);
        if (optional.isEmpty()) return false;
        repo.deleteById(id);
        return true;
    }
}
