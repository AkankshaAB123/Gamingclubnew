package com.gaming.gamer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface membersrepository extends MongoRepository<members, String> {
}
