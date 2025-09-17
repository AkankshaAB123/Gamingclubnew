package com.gaming.gamer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface transactionsrepository extends MongoRepository<transactions, String> {
}
