package repository;

import Model.Produto;

import java.util.ArrayList;
import java.util.List;

public class CatalogoProdutos {

    private List<Produto> produtos;

    public CatalogoProdutos() {
        this.produtos = new ArrayList<>();

        produtos.add(new Produto(1, "Banana", "Fruta"));
        produtos.add(new Produto(2, "Maca", "Fruta"));
        produtos.add(new Produto(3, "Alface", "Verdura"));
        produtos.add(new Produto(4, "Tomate", "Legume"));
        produtos.add(new Produto(5, "Cenoura", "Legume"));
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Produto buscarProdutoPorId(int idProduto) {
        for (Produto produto : produtos) {
            if (produto.getIdProduto() == idProduto) {
                return produto;
            }
        }

        return null;
    }
}