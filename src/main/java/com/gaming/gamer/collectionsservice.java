package com.gaming.gamer;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class collectionsservice {
    private static final Logger log = LoggerFactory.getLogger(collectionsservice.class);

    @Autowired
    private collectionsrepository repo;

    public collections create(collections collection) {
        log.info("Creating collection entry");
        collection.setId(null);
        validate(collection);
        return repo.save(collection);
    }

    public List<collections> findAll() {
        log.info("Finding all collections");
        return repo.findAll();
    }

    public collections findById(String id) {
        log.info("Finding collection by id {}", id);
        return repo.findById(id)
                .orElseThrow(() -> {
                    log.error("Collection not found: {}", id);
                    return new ResourceNotFoundException("Collection not found: " + id);
                });
    }

    public collections update(String id, collections updated) {
        collections old = findById(id);
        log.info("Updating collection by id {}", id);
        old.setDate(updated.getDate());
        old.setAmount(updated.getAmount());
        return repo.save(old);
    }

    public boolean delete(String id) {
        collections old = findById(id);
        log.info("Deleting collection by id {}", id);
        repo.delete(old);
        return true;
    }

    private void validate(collections collection) {
        if (collection.getAmount() < 0) {
            log.error("Amount cannot be negative");
            throw new BusinessException("Amount cannot be negative");
        }
    }
}
