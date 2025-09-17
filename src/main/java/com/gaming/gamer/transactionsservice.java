package com.gaming.gamer;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class transactionsservice {
    private static final Logger log = LoggerFactory.getLogger(transactionsservice.class);

    @Autowired
    private transactionsrepository repo;

    public transactions create(transactions txn) {
        log.info("Creating transaction for memberId {} and gameId {}", txn.getMemberId(), txn.getGameId());
        txn.setId(null);
        validate(txn);
        return repo.save(txn);
    }

    public List<transactions> findAll() {
        log.info("Finding all transactions");
        return repo.findAll();
    }

    public transactions findById(String id) {
        log.info("Finding transaction by id {}", id);
        return repo.findById(id)
                .orElseThrow(() -> {
                    log.error("Transaction not found: {}", id);
                    return new ResourceNotFoundException("Transaction not found: " + id);
                });
    }

    public transactions update(String id, transactions updated) {
        transactions old = findById(id);
        log.info("Updating transaction by id {}", id);
        old.setMemberId(updated.getMemberId());
        old.setGameId(updated.getGameId());
        old.setAmount(updated.getAmount());
        old.setDateTime(updated.getDateTime());
        return repo.save(old);
    }

    public boolean delete(String id) {
        transactions old = findById(id);
        log.info("Deleting transaction by id {}", id);
        repo.delete(old);
        return true;
    }

    private void validate(transactions txn) {
        if (txn.getAmount() < 0) {
            log.error("Transaction amount cannot be negative");
            throw new BusinessException("Transaction amount cannot be negative");
        }
    }
}
