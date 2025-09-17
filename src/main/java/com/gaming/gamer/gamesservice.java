package com.gaming.gamer;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class gamesservice {
    private static final Logger log = LoggerFactory.getLogger(gamesservice.class);

    @Autowired
    private gamesrepository repo;

    public games create(games game) {
        log.info("Creating game: {}", game.getName());
        game.setId(null);
        validate(game);
        return repo.save(game);
    }

    public List<games> findAll() {
        log.info("Finding all games");
        return repo.findAll();
    }

    public games findById(String id) {
        log.info("Finding game by id {}", id);
        return repo.findById(id)
                .orElseThrow(() -> {
                    log.error("Game not found: {}", id);
                    return new ResourceNotFoundException("Game not found: " + id);
                });
    }

    public games update(String id, games updated) {
        games old = findById(id);
        log.info("Updating game by id {}", id);
        old.setName(updated.getName());
        old.setPrice(updated.getPrice());
        old.setDescription(updated.getDescription());
        return repo.save(old);
    }

    public boolean delete(String id) {
        games old = findById(id);
        log.info("Deleting game by id {}", id);
        repo.delete(old);
        return true;
    }

    private void validate(games game) {
        if (game.getPrice() < 0) {
            log.error("Price cannot be negative");
            throw new BusinessException("Price cannot be negative");
        }
    }
}
