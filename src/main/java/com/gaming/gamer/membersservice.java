package com.gaming.gamer;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class membersservice {
    private static final Logger log = LoggerFactory.getLogger(membersservice.class);

    @Autowired
    private membersrepository repo;

    public members create(members member) {
        log.info("Creating member: {}", member.getName());
        member.setId(null);
        validate(member);
        return repo.save(member);
    }

    public List<members> findAll() {
        log.info("Finding all members");
        return repo.findAll();
    }

    public members findById(String id) {
        log.info("Finding member by id {}", id);
        return repo.findById(id)
                .orElseThrow(() -> {
                    log.error("Member not found: {}", id);
                    return new ResourceNotFoundException("Member not found: " + id);
                });
    }

    public members update(String id, members updated) {
        members old = findById(id);
        log.info("Updating member by id {}", id);
        old.setName(updated.getName());
        old.setPhone(updated.getPhone());
        old.setBalance(updated.getBalance());
        return repo.save(old);
    }

    public boolean delete(String id) {
        members old = findById(id);
        log.info("Deleting member by id {}", id);
        repo.delete(old);
        return true;
    }

    private void validate(members member) {
        if (member.getBalance() != null && member.getBalance() < 0) {
            log.error("Balance cannot be negative");
            throw new BusinessException("Balance cannot be negative");
        }
    }
}
