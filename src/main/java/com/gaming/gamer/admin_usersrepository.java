package com.gaming.gamer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface admin_usersrepository extends MongoRepository<admin_users, String> {
}
