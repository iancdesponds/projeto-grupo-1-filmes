package br.insper.filmes.ator;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtorRepository extends MongoRepository<Ator, String> {
}