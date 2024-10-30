package br.insper.filmes.filme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmeController {

    @Autowired
    private FilmeService filmeService;


    @PostMapping("/filme")
    public Filme CriarFilme(@RequestBody Filme filme) { return filmeService.CriarFilme(filme);
    }


    @PutMapping("/filme/{id}")
    public Filme EditarFilme(@RequestBody Filme filme, @PathVariable String id){
        return filmeService.EditarFilme(filme, id);
    }

    @DeleteMapping("/filme/{id}")
    public Filme DeletarFilme(@PathVariable String id){
        return filmeService.DeletarFilme(id);
    }

    @GetMapping("/filme")
    public List<Filme> getFilme(@RequestParam(required = false) String genero, @RequestParam(required = false) Integer ano, @RequestParam(required = false) String classificacao, @RequestParam(required = false) String nomdDiretor) {
        return filmeService.ListarFilmes(genero, ano, nomdDiretor, classificacao);
    }
}
