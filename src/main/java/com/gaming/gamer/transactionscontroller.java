package com.gaming.gamer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class transactionscontroller {

    @Autowired
    private transactionsrepository repo;

    @PostMapping
    public transactions create(@RequestBody transactions transaction) {
        transaction.setId(null);
        return repo.save(transaction);
    }

    @GetMapping
    public List<transactions> findAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public transactions findById(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public transactions update(@PathVariable String id, @RequestBody transactions updated) {
        transactions old = repo.findById(id).orElse(null);
        if (old != null) {
            old.setMemberId(updated.getMemberId());
            old.setGameId(updated.getGameId());
            old.setAmount(updated.getAmount());
            old.setDateTime(updated.getDateTime());
            return repo.save(old);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        Optional<transactions> optional = repo.findById(id);
        if (optional.isEmpty()) return false;
        repo.deleteById(id);
        return true;
    }
}
