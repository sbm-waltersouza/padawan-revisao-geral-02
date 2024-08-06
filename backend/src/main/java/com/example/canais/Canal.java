package com.example.canais;

public class Canal {
    private Integer id;
    private String nome;
    private String descricao;
    private Integer quantidadeInscritos;

    // Construtor padrão
    public Canal() {}

    // Construtor com parâmetros
    public Canal(Integer id, String nome, String descricao, Integer quantidadeInscritos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidadeInscritos = quantidadeInscritos;
    }

    // Getter e Setter para id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter e Setter para nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para descricao
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Getter e Setter para quantidadeInscritos
    public Integer getQuantidadeInscritos() {
        return quantidadeInscritos;
    }

    public void setQuantidadeInscritos(Integer quantidadeInscritos) {
        this.quantidadeInscritos = quantidadeInscritos;
    }

    @Override
    public String toString() {
        return "Canal{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidadeInscritos=" + quantidadeInscritos +
                '}';
    }
}
