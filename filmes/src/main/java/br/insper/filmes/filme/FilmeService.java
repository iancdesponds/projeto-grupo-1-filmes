package br.insper.filmes.filme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FilmeService {

    @Autowired
    FilmeRepository filmeRepository;

    public Filme CriarFilme(Filme filme) {
        if (filme == null) {
            throw new IllegalArgumentException("Filme não pode ser nulo");
        }

        if (filme.getTitulo() == null || filme.getAtores() == null || filme.getClassificacao() == null || filme.getDescricao() == null || filme.getDiretores() == null || filme.getGenero() == null || filme.getAno() == null) {
            throw new IllegalArgumentException("Campos obrigatórios estão ausentes");
        }

        filme.setId(UUID.randomUUID().toString());

        return filmeRepository.save(filme);


    }
}
