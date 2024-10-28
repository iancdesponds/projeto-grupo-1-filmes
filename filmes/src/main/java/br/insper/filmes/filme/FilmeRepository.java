package br.insper.filmes.filme;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FilmeRepository extends MongoRepository<Filme, String> {
}
