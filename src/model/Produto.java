package model;

import utils.Utils;

public class Produto {

    private static int contador = 1;


    private int id;
    private String nome;
    private Double preco;



    //construtor
    public Produto(String nome, Double preco) {
        this.id = contador;
        this.nome = nome;
        this.preco = preco;
        Produto.contador +=1;
    }

    //getters e setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "id: " + this.id + '\n'+
                "nome: " + this.nome + '\n'+
                "preco: " + Utils.doubleToString(getPreco());
    }

}
