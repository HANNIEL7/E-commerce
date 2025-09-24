package model;

import service.Vendas;

public class Cliente extends Pessoa {
    private String endereco;

    public Cliente(String login, String senha, String cpf, int idade, String endereco) {
        super(login, senha, cpf, idade);
        this.endereco = endereco;
    }

    @Override
    public void visualizarDados() {
        super.visualizarDados();
        System.out.println("Endereço: " + endereco);
    }

    public void concluirVenda(Vendas venda) {
        System.out.println("Venda concluída para o cliente " + login + " no valor de R$ " + String.format("%.2f", venda.getTotal()));
        venda.gerarFatura();
    }
}