package model;

public class Produto {
    private String nome;
    private double preco;
    private int idProduto;
    private int estoque;

    public Produto(String nome, double preco, int idProduto, int estoque) {
        this.nome = nome;
        this.preco = preco;
        this.idProduto = idProduto;
        this.estoque = estoque;
    }

    // getters / setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public int getIdProduto() { return idProduto; }
    public void setIdProduto(int idProduto) { this.idProduto = idProduto; }
    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) { this.estoque = estoque; }

    public void exibirProduto() {
        System.out.printf("Produto: %s | ID: %d | Estoque: %d | Preço: R$ %.2f%n", nome, idProduto, estoque, preco);
    }
}