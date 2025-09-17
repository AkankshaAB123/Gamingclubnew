package com.gaming.gamer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/games")
public class gamescontroller {

    @Autowired
    private gamesrepository repo;

    // Create a new game
    @PostMapping
    public games create(@RequestBody games game) {
        game.setId(null); // let MongoDB generate ObjectId
        return repo.save(game);
    }

    // Get all games
    @GetMapping
    public List<games> findAll() {
        return repo.findAll();
    }

    // Get game by ID
    @GetMapping(path="/{id}")
    public games findById(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }

    // Update an existing game
    @PutMapping(path="/{id}")
    public games update(@PathVariable String id, @RequestBody games game) {
        games oldGame = repo.findById(id).orElse(null);
        if (oldGame == null) {
            return null; // or throw exception
        }
        oldGame.setName(game.getName());
        oldGame.setDescription(game.getDescription());
        oldGame.setPrice(game.getPrice());

        return repo.save(oldGame);
    }

    // Delete game by ID
    @DeleteMapping(path="/{id}")
    public boolean delete(@PathVariable String id) {
        Optional<games> optionalGame = repo.findById(id);
        if (optionalGame.isEmpty()) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
