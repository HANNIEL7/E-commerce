package service;

import model.Roupa;
import model.Cliente;
import java.util.*;

public class Carrinho {
    private static class Item {
        int productId;
        String name;
        double price;
        int qty;
        public Item(int productId, String name, double price, int qty) { this.productId = productId; this.name = name; this.price = price; this.qty = qty; }
    }

    private final Map<Integer, Item> items = new LinkedHashMap<>();

    // Adiciona ao carrinho consultando o catálogo (não decrementa estoque ainda)
    public boolean addToCart(Catalogo catalog, int productId, int qty) {
        if (qty <= 0) return false;
        Roupa p = catalog.getById(productId);
        if (p == null) return false;
        if (p.getEstoque() < qty) return false;
        Item it = items.get(productId);
        if (it == null) items.put(productId, new Item(productId, p.getNome(), p.getPreco(), qty));
        else it.qty += qty;
        return true;
    }

    public boolean removeFromCart(int productId) {
        return items.remove(productId) != null;
    }

    public void viewCart() {
        if (items.isEmpty()) { System.out.println("Carrinho vazio."); return; }
        System.out.println("=== Carrinho ===");
        double total = 0;
        for (Item it : items.values()) {
            System.out.printf("ID:%d - %s x%d = R$ %.2f%n", it.productId, it.name, it.qty, it.price * it.qty);
            total += it.price * it.qty;
        }
        System.out.println("Total: R$ " + String.format("%.2f", total));
    }

    // Finaliza compra: verifica estoque, decrementa e gera Vendas
    public boolean checkout(Catalogo catalog, Cliente cliente) {
        if (items.isEmpty()) { System.out.println("Carrinho vazio. Nada a comprar."); return false; }

        // checar disponibilidade
        for (Item it : items.values()) {
            Roupa p = catalog.getById(it.productId);
            if (p == null) { System.out.println("Produto ID " + it.productId + " não existe."); return false; }
            if (p.getEstoque() < it.qty) { System.out.println("Estoque insuficiente para " + p.getNome()); return false; }
        }

        // decrementar estoque
        for (Item it : items.values()) {
            boolean ok = catalog.decreaseStock(it.productId, it.qty);
            if (!ok) { System.out.println("Falha ao decrementar estoque para id=" + it.productId); return false; }
        }

        // gerar venda
        List<Vendas.LineItem> lines = new ArrayList<>();
        double total = 0;
        for (Item it : items.values()) {
            lines.add(new Vendas.LineItem(it.productId, it.name, it.price, it.qty));
            total += it.price * it.qty;
        }
        Vendas venda = new Vendas("VENDA-" + System.currentTimeMillis(), total, lines);
        cliente.concluirVenda(venda);
        items.clear();
        return true;
    }

    public boolean isEmpty() { return items.isEmpty(); }
}
