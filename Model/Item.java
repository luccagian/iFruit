package Model;

public class Item {

    private int idItem;
    private Produto produto;
    private int quantidade;

    public Item(int idItem, Produto produto, int quantidade) {
        this.idItem = idItem;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public int getIdItem() {
        return idItem;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getNome() {
        return produto.getNome();
    }

    public String getTipo() {
        return produto.getTipo();
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return produto.getNome()
                + " | "
                + produto.getTipo()
                + " | qtd: "
                + quantidade;
    }
}