package br.insper.filmes.filme;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
public class Filme {
    @Id
    private String id;
    private String titulo;
    private String descricao;
    private String genero;
    private Integer ano;

    private String classificacao;

    private ArrayList<String> diretores;
    private ArrayList<String> atores;


    public Filme() {
    }

    public Filme(String id, String titulo, String descricao, String genero, Integer ano, String classificacao, ArrayList<String> diretores, ArrayList<String> atores) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.genero = genero;
        this.ano = ano;
        this.classificacao = classificacao;
        this.diretores = diretores;
        this.atores = atores;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public ArrayList<String> getDiretores() {
        return diretores;
    }

    public void setDiretores(ArrayList<String> diretores) {
        this.diretores = diretores;
    }

    public ArrayList<String> getAtores() {
        return atores;
    }

    public void setAtores(ArrayList<String> atores) {
        this.atores = atores;
    }
}
