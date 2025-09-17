package com.gaming.gamer;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class admin_usersservice {
    private static final Logger log = LoggerFactory.getLogger(admin_usersservice.class);

    @Autowired
    private admin_usersrepository repo;

    public admin_users create(admin_users admin) {
        log.info("Creating admin user: {}", admin.getUsername());
        admin.setId(null);
        validate(admin);
        return repo.save(admin);
    }

    public List<admin_users> findAll() {
        log.info("Finding all admin users");
        return repo.findAll();
    }

    public admin_users findById(String id) {
        log.info("Finding admin user by id {}", id);
        Optional<admin_users> optional = repo.findById(id);
        if (optional.isEmpty()) {
            log.error("Attempted to find non-existing admin user id: {}", id);
            throw new ResourceNotFoundException("Admin user not found: " + id);
        }
        return optional.get();
    }

    public admin_users update(String id, admin_users admin) {
        Optional<admin_users> optional = repo.findById(id);
        if (optional.isEmpty()) {
            log.error("Attempted to update non-existing admin user id: {}", id);
            throw new ResourceNotFoundException("Admin user not found: " + id);
        }
        log.info("Updating admin user by id {}", id);

        admin_users existing = optional.get();
        existing.setUsername(admin.getUsername());
        existing.setPassword(admin.getPassword());

        return repo.save(existing);
    }

    public boolean delete(String id) {
        Optional<admin_users> optional = repo.findById(id);
        if (optional.isEmpty()) {
            log.error("Attempted to delete non-existing admin user id: {}", id);
            throw new ResourceNotFoundException("Admin user not found: " + id);
        }
        log.info("Deleting admin user by id {}", id);
        repo.deleteById(id);
        return true;
    }

    private void validate(admin_users admin) {
        if (admin.getUsername() == null || admin.getUsername().trim().isEmpty()) {
            log.error("Username cannot be empty");
            throw new BusinessException("Username cannot be empty");
        }
        if (admin.getPassword() == null || admin.getPassword().trim().isEmpty()) {
            log.error("Password cannot be empty");
            throw new BusinessException("Password cannot be empty");
        }
    }
}
