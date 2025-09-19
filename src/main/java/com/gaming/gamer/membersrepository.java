package com.gaming.gamer;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface membersrepository extends MongoRepository<members, String> {
    Optional<members> findByPhone(String phone);
}
