# 🛒 Projeto E-commerce em Java

Este é um projeto acadêmico de **E-commerce** desenvolvido em **Java**, com foco em aplicar conceitos de **Programação Orientada a Objetos (POO)**.

O sistema simula um **fluxo básico de compra**:
1. O **vendedor** cadastra produtos (roupas).
2. O **cliente** visualiza o catálogo.
3. O cliente pode adicionar produtos ao **carrinho**.
4. O cliente pode **finalizar a compra**, gerando uma **nota fiscal**.

---

## 🚀 Funcionalidades

- **Cadastro de produtos (Vendedor)**
- **Visualização do catálogo**
- **Adicionar itens ao carrinho**
- **Visualizar carrinho**
- **Finalizar compra com nota fiscal**
- **Validação de CPF e idade** (só maiores de 18 podem comprar e só CPFs válidos são aceitos)

---

## 📂 Estrutura de Pastas

src/
├── app/
│ └── Main.java
├── model/
│ ├── Pessoa.java
│ ├── Cliente.java
│ ├── Vendedor.java
│ ├── Produto.java
│ └── Roupa.java
└── service/
├── Catalogo.java
├── Carrinho.java
└── Vendas.java

yaml
Copiar código

---

## 📌 Tecnologias

- **Java 17+**
- **Paradigma Orientado a Objetos**
- **IntelliJ IDEA** para desenvolvimento
- **Mermaid UML** para diagramação

---

## 📖 Fluxo do Sistema

1. Vendedor cadastra um produto no catálogo.
2. Cliente visualiza o catálogo.
3. Cliente escolhe produtos para adicionar ao carrinho.
4. Cliente pode finalizar a compra, gerando uma fatura.

---

## 📊 UML do Projeto

- Arquivo anexado na raiz do repositório.

---

## ▶️ Como executar

No terminal do IntelliJ ou via linha de comando:

```bash
cd src
javac app/Main.java
java app.Main