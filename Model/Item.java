package Model;

public class Item {

    private int idItem;
    private String nome;
    private String tipo;
    private int quantidade;

    public Item(int idItem, String nome, String tipo, int quantidade) {
        this.idItem = idItem;
        this.nome = nome;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public int getIdItem() {
        return idItem;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return nome + " | " + tipo + " | qtd: " + quantidade;
    }
}