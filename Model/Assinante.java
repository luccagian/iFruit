package Model;

public class Assinante {

    private int idAssinante;
    private String nome;
    private String telefone;

    public Assinante(int idAssinante, String nome, String telefone) {
        this.idAssinante = idAssinante;
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getIdAssinante() {
        return idAssinante;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return nome + " - " + telefone;
    }
}