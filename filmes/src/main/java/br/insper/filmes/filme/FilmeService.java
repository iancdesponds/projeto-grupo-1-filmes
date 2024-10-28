package br.insper.filmes.filme;

import br.insper.filmes.diretor.Diretor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public ArrayList<Filme> ListarFilmes(String genero, Integer ano, String nomeDiretor, String classificacao) {
        List<Filme> filmes = filmeRepository.findAll();
        ArrayList<Filme> filmesFiltrados = new ArrayList<>();

        for (Filme filme : filmes) {
            boolean adicionar = true;

            // Verifica o filtro de gênero
            if (genero != null && !filme.getGenero().equalsIgnoreCase(genero)) {
                adicionar = false;
            }

            // Verifica o filtro de ano
            if (ano != null && !filme.getAno().equals(ano)) {
                adicionar = false;
            }

            // Verifica o filtro de diretor
            if (nomeDiretor != null) {
                boolean diretorEncontrado = false;
                for (Diretor diretor : filme.getDiretores()) {
                    if (diretor.getNome().equalsIgnoreCase(nomeDiretor)) {
                        diretorEncontrado = true;
                        break;
                    }
                }
                if (!diretorEncontrado) {
                    adicionar = false;
                }
            }

            // Verifica o filtro de classificação
            if (classificacao != null && !filme.getClassificacao().equalsIgnoreCase(classificacao)) {
                adicionar = false;
            }

            // Adiciona o filme à lista final se todos os filtros foram atendidos
            if (adicionar) {
                filmesFiltrados.add(filme);
            }
        }

        return filmesFiltrados;
    }

}
