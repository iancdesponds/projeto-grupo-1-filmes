package br.insper.filmes.diretor;

import java.util.List;
import br.insper.filmes.filme.Filme;

public class Diretor {
    private Integer id;
    private String nome;
    private String nacionalidade;
    private String biografia;
    private List<Filme> filmes;

    public Diretor(Integer id, String nome, String nacionalidade, String biografia, List<Filme> filmes) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.biografia = biografia;
        this.filmes = filmes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }
}
