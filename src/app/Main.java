package app;

import java.util.List;
import java.util.Scanner;
import model.*;
import service.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Catalogo catalog = new Catalogo();
        Carrinho carrinho = new Carrinho();

        // criar usuários de teste (use CPFs válidos)
        Vendedor vendedor;
        Cliente cliente;
        try {
            vendedor = new Vendedor("vend1", "senhaV", "52998224725", 30, 1);
            cliente = new Cliente("cli1", "senhaC", "12345678909", 25, "Rua A, 123");
        } catch (IllegalArgumentException ex) {
            System.out.println("Erro ao criar usuários iniciais: " + ex.getMessage());
            sc.close();
            return;
        }

        // popular catálogo com alguns itens iniciais
        catalog.addProduct(new Roupa("Camiseta Preta", 49.90, 0, 10, "Preto", "Casual", "Unissex", "M"));
        catalog.addProduct(new Roupa("Calça Jeans", 129.90, 0, 5, "Azul", "Jeans", "Masculino", "42"));
        catalog.addProduct(new Roupa("Vestido Floral", 89.90, 0, 3, "Roxo", "Vestidos", "Feminino", "P"));

        boolean running = true;
        while (running) {
            System.out.println("\n=== E-COMMERCE (menu) ===");
            // mostrar catálogo resumido sempre no topo para visibilidade
            catalog.listSummary(4);

            System.out.println("1) Vendedor: cadastrar produto");
            System.out.println("2) Listar todos os produtos");
            System.out.println("3) Buscar produto por nome");
            System.out.println("4) Cliente: adicionar produto ao carrinho (por ID)");
            System.out.println("5) Remover produto do carrinho (por ID)");
            System.out.println("6) Visualizar carrinho");
            System.out.println("7) Finalizar compra");
            System.out.println("8) Sair");
            System.out.print("Escolha uma opção: ");

            String op = sc.nextLine().trim();
            switch (op) {
                case "1":
                    try {
                        System.out.print("Nome: "); String nome = sc.nextLine();
                        System.out.print("Preço: "); double preco = Double.parseDouble(sc.nextLine());
                        System.out.print("Estoque: "); int estoque = Integer.parseInt(sc.nextLine());
                        System.out.print("Cor: "); String cor = sc.nextLine();
                        System.out.print("Categoria: "); String cat = sc.nextLine();
                        System.out.print("Gênero: "); String gen = sc.nextLine();
                        System.out.print("Tamanho: "); String tam = sc.nextLine();
                        Roupa r = new Roupa(nome, preco, 0, estoque, cor, cat, gen, tam);
                        int id = catalog.addProduct(r);
                        vendedor.cadastrarProduto(r);
                        System.out.println("Produto cadastrado com ID: " + id);
                    } catch (NumberFormatException ex) {
                        System.out.println("Entrada numérica inválida. Operação cancelada.");
                    }
                    break;
                case "2":
                    catalog.listProducts();
                    break;
                case "3":
                    System.out.print("Termo de busca: ");
                    String termo = sc.nextLine();
                    List<Roupa> encontrados = catalog.searchByName(termo);
                    if (encontrados.isEmpty()) System.out.println("Nenhum produto encontrado.");
                    else encontrados.forEach(Roupa::exibirProduto);
                    break;
                case "4":
                    try {
                        System.out.print("ID do produto: "); int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Quantidade: "); int q = Integer.parseInt(sc.nextLine());
                        boolean ok = carrinho.addToCart(catalog, id, q);
                        if (ok) System.out.println("Produto adicionado ao carrinho.");
                        else System.out.println("Falha ao adicionar (ID inválido, quantidade inválida ou estoque insuficiente).");
                    } catch (NumberFormatException ex) {
                        System.out.println("Entrada inválida.");
                    }
                    break;
                case "5":
                    try {
                        System.out.print("ID do produto a remover do carrinho: "); int idr = Integer.parseInt(sc.nextLine());
                        boolean removed = carrinho.removeFromCart(idr);
                        System.out.println(removed ? "Removido do carrinho." : "Produto não encontrado no carrinho.");
                    } catch (NumberFormatException ex) {
                        System.out.println("Entrada inválida.");
                    }
                    break;
                case "6":
                    carrinho.viewCart();
                    break;
                case "7":
                    try {
                        boolean success = carrinho.checkout(catalog, cliente);
                        if (success) System.out.println("Compra finalizada com sucesso.");
                    } catch (Exception ex) {
                        System.out.println("Erro ao finalizar compra: " + ex.getMessage());
                    }
                    break;
                case "8":
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        sc.close();
        System.out.println("Aplicação finalizada.");
    }
}
