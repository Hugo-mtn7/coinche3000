package com.coinche.Coinche3000.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.coinche.Coinche3000.object.Game;

public interface GameRepository extends MongoRepository<Game, String>  {

}
