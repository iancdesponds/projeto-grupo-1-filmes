package br.insper.filmes.filme;

import br.insper.filmes.ator.AtorRepository;
import br.insper.filmes.diretor.DiretorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private DiretorRepository diretorRepository;

    @Autowired
    private AtorRepository atorRepository;

    public Filme criarFilme(Filme filme) {
        if (filme == null) {
            throw new IllegalArgumentException("Filme não pode ser nulo");
        }

        if (filme.getTitulo() == null || filme.getAtores() == null || filme.getClassificacao() == null || filme.getDescricao() == null || filme.getDiretores() == null || filme.getGenero() == null || filme.getAno() == null) {
            throw new IllegalArgumentException("Campos obrigatórios estão ausentes");
        }

        for( String diretor : filme.getDiretores()){
            if(diretorRepository.findById(diretor).isEmpty()){
                throw new IllegalArgumentException("Diretor não encontrado");
            }
        }

        filme.setId(UUID.randomUUID().toString());
        return filmeRepository.save(filme);
    }

    public Optional<Filme> buscarFilmePorId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return filmeRepository.findById(id);
    }

    public Filme editarFilme(Filme filme, String id){
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

    public Filme deletarFilme(String id){
        Optional<Filme> op = filmeRepository.findById(id);
        if (op.isEmpty()) {
            throw new RuntimeException("Filme não encontrado");
        }
        Filme filmeDeletado = op.get();
        filmeRepository.deleteById(id);
        return filmeDeletado;
    }

    public List<Filme> listarFilmes(String genero, Integer ano, String nomeDiretor, String classificacao) {
        ArrayList<Filme> filmesFiltrados = new ArrayList<>();
        Stream<Filme> filmes = filmesFiltrados.stream();
        if (genero != null) {
            filmes = filmes.filter(filme -> filme.getGenero().equals(genero));
        }
        if (ano != null) {
            filmes = filmes.filter(filme -> filme.getAno().equals(ano));
        }
        if (nomeDiretor != null) {
            filmes = filmes.filter(filme -> filme.getDiretores().stream().anyMatch(diretor -> diretor.equals(nomeDiretor)));
        }
        if (classificacao != null) {
            filmes = filmes.filter(filme -> filme.getClassificacao().equals(classificacao));
        }
        return filmes.toList();
    }
}
