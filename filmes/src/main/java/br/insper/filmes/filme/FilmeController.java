package br.insper.filmes.filme;

import br.insper.filmes.avaliacao.Avaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping("/{id}/avaliacao")
    public List<Avaliacao> ListarAvaliacoes(@PathVariable String id, @RequestHeader(name = "Authorization") String jwtToken) {
        return filmeService.ListarAvaliacoes(id, jwtToken);
    }

    @PostMapping
    public Filme CriarFilme(@RequestBody Filme filme) { return filmeService.criarFilme(filme); }

    @PutMapping("/{id}")
    public Filme editarFilme(@RequestBody Filme filme, @PathVariable String id) {
        return filmeService.editarFilme(filme, id);
    }

    @DeleteMapping("/{id}")
    public Filme deletarFilme(@PathVariable String id) {
        return filmeService.deletarFilme(id);
    }

    @GetMapping
    public List<Filme> listarFilmes(
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) String classificacao,
            @RequestParam(required = false) String nomeDiretor) {
        return filmeService.listarFilmes(genero, ano, nomeDiretor, classificacao);
    }

    @GetMapping("/{id}")
    public Filme buscarFilmePorId(@PathVariable String id) {
        return filmeService.buscarFilmePorId(id);
    }
}
