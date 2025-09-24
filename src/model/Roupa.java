package model;

public class Roupa extends Produto {
    private String cor;
    private String categoria;
    private String genero;
    private String tamanho;

    public Roupa(String nome, double preco, int idProduto, int estoque, String cor, String categoria, String genero, String tamanho) {
        super(nome, preco, idProduto, estoque);
        this.cor = cor;
        this.categoria = categoria;
        this.genero = genero;
        this.tamanho = tamanho;
    }

    @Override
    public void exibirProduto() {
        System.out.printf("[Roupa] %s | ID: %d | Cat: %s | Cor: %s | Gen: %s | Tam: %s | Estoque: %d | Preço: R$ %.2f%n",
                getNome(), getIdProduto(), categoria, cor, genero, tamanho, getEstoque(), getPreco());
    }

    public String getCategoria() { return categoria; }
}
