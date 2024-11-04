package br.insper.filmes.filme;

import br.insper.filmes.avaliacao.Avaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping("/filme/{id}/avaliacao")
    public List<Avaliacao> ListarAvaliacoes(@PathVariable String id, @RequestHeader(name = "Authorization") String jwtToken) {
        return filmeService.ListarAvaliacoes(id, jwtToken);
    }


    @PostMapping("/filme")
    public Filme CriarFilme(@RequestBody Filme filme) { return filmeService.CriarFilme(filme);
    }


    @PostMapping("/filme/{id}")
    public Filme EditarFilme(@RequestBody Filme filme, @PathVariable String id){
        return filmeService.EditarFilme(filme, id);
    }

    @DeleteMapping("/filme/{id}")
    public Filme DeletarFilme(@PathVariable String id){
        return filmeService.DeletarFilme(id);
    }

}
