package Model;

public class Produto {

    private int idProduto;
    private String nome;
    private String tipo;

    public Produto(int idProduto, String nome, String tipo) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.tipo = tipo;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return idProduto + " - " + nome + " | " + tipo;
    }
}