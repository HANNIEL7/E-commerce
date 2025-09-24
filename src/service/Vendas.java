package service;

import java.util.*;

public class Vendas {
    public static class LineItem {
        public final int productId;
        public final String name;
        public final double price;
        public final int qty;
        public LineItem(int productId, String name, double price, int qty) { this.productId = productId; this.name = name; this.price = price; this.qty = qty; }
    }

    private final String idVenda;
    private final double total;
    private final List<LineItem> items;

    public Vendas(String idVenda, double total, List<LineItem> items) {
        this.idVenda = idVenda;
        this.total = total;
        this.items = new ArrayList<>(items);
    }

    public double getTotal() { return total; }

    public void gerarFatura() {
        System.out.println("--- NOTA FISCAL ---");
        System.out.println("Venda: " + idVenda);
        for (LineItem li : items) {
            System.out.printf("%s (ID:%d) x%d = R$ %.2f%n", li.name, li.productId, li.qty, li.price * li.qty);
        }
        System.out.println("Total: R$ " + String.format("%.2f", total));
        System.out.println("-------------------");
    }
}