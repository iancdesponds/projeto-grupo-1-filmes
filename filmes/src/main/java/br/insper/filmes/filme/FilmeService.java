package br.insper.filmes.filme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

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


    public Filme EditarFilme(Filme filme, String id){
        Optional<Filme> op = filmeRepository.findById(id);
        if (op.isEmpty()) {
            throw new IllegalArgumentException("Filme não encontrado");
        }
        Filme filmeEditado = op.get();
        filmeEditado.setTitulo(filme.getTitulo());
        filmeEditado.setDescricao(filme.getDescricao());
        filmeEditado.setGenero(filme.getGenero());
        filmeEditado.setAno(filme.getAno());
        filmeEditado.setClassificacao(filme.getClassificacao());
        filmeEditado.setDiretores(filme.getDiretores());
        filmeEditado.setAtores(filme.getAtores());
        return filmeRepository.save(filmeEditado);
    }

    public Filme DeletarFilme(String id){
        Optional<Filme> op = filmeRepository.findById(id);
        if (op.isEmpty()) {
            throw new RuntimeException("Filme não encontrado");
        }
        Filme filmeDeletado = op.get();
        filmeRepository.deleteById(id);
        return filmeDeletado;
    }

    public ArrayList<Filme> ListarFilmes(String genero, Integer ano, String nomeDiretor, String classificacao){


    }

}
