package br.insper.filmes.filme;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FilmeRepository extends MongoRepository<Filme, String> {
    List<Filme> findByGenero(String genero);
    List<Filme> findByAno(Integer ano);
    List<Filme> findByClassificacao(String classificacao);
}