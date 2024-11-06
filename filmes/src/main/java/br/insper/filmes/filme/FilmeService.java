package br.insper.filmes.filme;

import br.insper.filmes.ator.AtorRepository;
import br.insper.filmes.diretor.DiretorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.insper.filmes.avaliacao.Avaliacao;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.SpringBootExceptionReporter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public List<Avaliacao> ListarAvaliacoes(String id, String jwtToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Avaliacao[]> response = restTemplate.exchange("http://54.81.167.84:8080/avaliacao?filme=" +
                id, HttpMethod.GET, entity, Avaliacao[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return List.of(response.getBody());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliações não encontradas");
        }
    }

    public Filme criarFilme(Filme filme) {
        if (filme == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Filme não pode ser nulo");
        }

        if (filme.getTitulo() == null || filme.getAtores() == null || filme.getClassificacao() == null ||
                filme.getDescricao() == null || filme.getDiretores() == null || filme.getGenero() == null ||
                filme.getAno() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campos obrigatórios não preenchidos");
        }

        for( String diretor : filme.getDiretores()){
            if(diretorRepository.findById(diretor).isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Diretor não encontrado");
            }
        }

        for( String ator : filme.getAtores()){
            if(atorRepository.findById(ator).isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ator não encontrado");
            }
        }

        filme.setId(UUID.randomUUID().toString());
        return filmeRepository.save(filme);
    }

    public Filme buscarFilmePorId(String id) {
        if (id == null || id.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id inválido");
        }
        Optional<Filme> filme = filmeRepository.findById(id);
        if (filme.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o filme.");
        }
        return filme.get();
    }

    public Filme editarFilme(Filme filme, String id){
        Optional<Filme> op = filmeRepository.findById(id);
        if (op.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado");
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado");
        }
        Filme filmeDeletado = op.get();
        filmeRepository.deleteById(id);
        return filmeDeletado;
    }

    public List<Filme> listarFilmes(String genero, Integer ano, String nomeDiretor, String classificacao) {
        List<Filme> filmesFiltrados = filmeRepository.findAll();
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
