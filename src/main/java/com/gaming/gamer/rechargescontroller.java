package com.gaming.gamer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recharges")
public class rechargescontroller {

    @Autowired
    private rechargesservice service;  // ✅ Use service, not repo

    // Create a new recharge
    @PostMapping
    public recharges create(@RequestBody recharges recharge) {
        return service.create(recharge);
    }

    // Get all recharges
    @GetMapping
    public List<recharges> findAll() {
        return service.findAll();
    }

    // Get recharge by ID
    @GetMapping("/{id}")
    public recharges findById(@PathVariable String id) {
        return service.findById(id);  // ✅ exception-safe
    }

    // Update recharge
    @PutMapping("/{id}")
    public recharges update(@PathVariable String id, @RequestBody recharges updated) {
        return service.update(id, updated);  // ✅ exception-safe
    }

    // Delete recharge
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);  // ✅ exception-safe
    }
}
