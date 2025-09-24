package model;

public class Vendedor extends Pessoa {
    private int id;

    public Vendedor(String login, String senha, String cpf, int idade, int id) {
        super(login, senha, cpf, idade);
        this.id = id;
    }

    public void cadastrarProduto(Produto produto) {
        System.out.println("Vendedor " + login + " cadastrou o produto: " + produto.getNome());
    }
}