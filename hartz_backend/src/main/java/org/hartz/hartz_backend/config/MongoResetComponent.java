package org.hartz.hartz_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoResetComponent implements CommandLineRunner {

    private MongoTemplate mongoTemplate;

    @Autowired
    public MongoResetComponent(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        mongoTemplate.dropCollection("workouts");
    }
}
