package br.insper.filmes.diretor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiretorRepository extends MongoRepository<Diretor, String> {
}
