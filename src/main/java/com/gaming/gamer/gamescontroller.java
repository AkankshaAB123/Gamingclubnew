package com.gaming.gamer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/games")
public class gamescontroller {

    @Autowired
    private gamesservice service;   // ✅ Use service instead of repo

    // Create a new game
    @PostMapping
    public games create(@RequestBody games game) {
        return service.create(game);
    }

    // Get all games
    @GetMapping
    public List<games> findAll() {
        return service.findAll();
    }

    // Get game by ID
    @GetMapping(path="/{id}")
    public games findById(@PathVariable String id) {
        return service.findById(id);   // ✅ exception-safe
    }

    // Update an existing game
    @PutMapping(path="/{id}")
    public games update(@PathVariable String id, @RequestBody games game) {
        return service.update(id, game);
    }

    // Delete game by ID
    @DeleteMapping(path="/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);   // ✅ will throw exception if not found
    }
}
