package service;

import model.Roupa;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Catalogo {
    private final Map<Integer, Roupa> produtos = new LinkedHashMap<>();
    private final AtomicInteger idGen = new AtomicInteger(1);

    // Adiciona roupa; se id <= 0 será gerado automaticamente
    public synchronized int addProduct(Roupa r) {
        if (r.getIdProduto() <= 0) {
            r.setIdProduto(idGen.getAndIncrement());
        } else {
            // se id já existe, gera novo
            if (produtos.containsKey(r.getIdProduto())) {
                r.setIdProduto(idGen.getAndIncrement());
            }
        }
        produtos.put(r.getIdProduto(), r);
        return r.getIdProduto();
    }

    public synchronized void listProducts() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto no catálogo.");
            return;
        }
        System.out.println("=== Catálogo de Produtos ===");
        produtos.values().forEach(Roupa::exibirProduto);
    }

    public synchronized void listSummary(int limit) {
        System.out.println("=== Catálogo (resumo) ===");
        int count = 0;
        for (Roupa r : produtos.values()) {
            System.out.printf("ID:%d - %s - R$ %.2f (estoque: %d)%n", r.getIdProduto(), r.getNome(), r.getPreco(), r.getEstoque());
            if (++count >= limit) break;
        }
        System.out.println("Total de produtos: " + produtos.size());
    }

    public synchronized Roupa getById(int id) {
        return produtos.get(id);
    }

    public synchronized List<Roupa> searchByName(String q) {
        String ql = q.toLowerCase();
        List<Roupa> res = new ArrayList<>();
        for (Roupa r : produtos.values()) if (r.getNome().toLowerCase().contains(ql)) res.add(r);
        return res;
    }

    public synchronized List<Roupa> filterByCategory(String cat) {
        String cl = cat.toLowerCase();
        List<Roupa> res = new ArrayList<>();
        for (Roupa r : produtos.values()) if (r.getCategoria().toLowerCase().equals(cl)) res.add(r);
        return res;
    }

    public synchronized boolean decreaseStock(int id, int qty) {
        Roupa r = produtos.get(id);
        if (r == null) return false;
        if (r.getEstoque() < qty) return false;
        r.setEstoque(r.getEstoque() - qty);
        return true;
    }

    public synchronized boolean isEmpty() { return produtos.isEmpty(); }
}