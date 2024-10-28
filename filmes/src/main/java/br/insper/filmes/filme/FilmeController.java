package br.insper.filmes.filme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmeController {

    @Autowired
    FilmeService filmeService;

    @PostMapping("/filme")
    public Filme CriarFilme(@RequestBody Filme filme) { return filmeService.CriarFilme(filme);
    }



}
