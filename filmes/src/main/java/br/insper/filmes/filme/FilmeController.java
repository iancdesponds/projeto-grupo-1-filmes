package br.insper.filmes.filme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmeController {

    @Autowired
    private FilmeService filmeService;


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
