package com.gaming.gamer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface gamesrepository extends MongoRepository<games, String> {
    
}
