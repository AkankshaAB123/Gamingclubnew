package com.gaming.gamer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recharges")
public class rechargescontroller {

    @Autowired
    private rechargesrepository repo;

    @PostMapping
    public recharges create(@RequestBody recharges recharge) {
        recharge.setId(null);
        return repo.save(recharge);
    }

    @GetMapping
    public List<recharges> findAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public recharges findById(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public recharges update(@PathVariable String id, @RequestBody recharges updated) {
        recharges old = repo.findById(id).orElse(null);
        if (old != null) {
            old.setMemberId(updated.getMemberId());
            old.setAmount(updated.getAmount());
            old.setDateTime(updated.getDateTime());
            return repo.save(old);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        Optional<recharges> optional = repo.findById(id);
        if (optional.isEmpty()) return false;
        repo.deleteById(id);
        return true;
    }
}
