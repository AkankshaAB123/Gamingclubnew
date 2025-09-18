package com.gaming.gamer;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class rechargesservice {

    private static final Logger log = LoggerFactory.getLogger(rechargesservice.class);

    @Autowired
    private rechargesrepository repo;

    // Create recharge
    public recharges create(recharges recharge) {
        log.info("Creating recharge for memberId {}", recharge.getMemberId());
        recharge.setId(null);
        validate(recharge);
        return repo.save(recharge);
    }

    // Get all recharges
    public List<recharges> findAll() {
        log.info("Finding all recharges");
        return repo.findAll();
    }

    // Get recharge by ID
    public recharges findById(String id) {
        log.info("Finding recharge by id {}", id);
        return repo.findById(id)
                .orElseThrow(() -> {
                    log.error("Recharge not found: {}", id);
                    return new ResourceNotFoundException("Recharge not found: " + id);
                });
    }

    // Update recharge
    public recharges update(String id, recharges updated) {
        recharges old = findById(id);  // ✅ throws if not found
        log.info("Updating recharge by id {}", id);
        old.setAmount(updated.getAmount());
        old.setDateTime(updated.getDateTime());
        old.setMemberId(updated.getMemberId());
        return repo.save(old);
    }

    // Delete recharge
    public void delete(String id) {
        recharges old = findById(id);  // ✅ throws if not found
        log.info("Deleting recharge by id {}", id);
        repo.delete(old);
    }

    // Validation
    private void validate(recharges recharge) {
        if (recharge.getAmount() < 0) {
            log.error("Recharge amount cannot be negative");
            throw new BusinessException("Recharge amount cannot be negative");
        }
    }
}
