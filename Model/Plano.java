package Model;

public class Plano {

    private int codPlano;
    private String nome;
    private String descricao;
    private double valor;
    private int limiteTotalItens;

    public Plano(
            int codPlano,
            String nome,
            String descricao,
            double valor,
            int limiteTotalItens
    ) {
        this.codPlano = codPlano;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.limiteTotalItens = limiteTotalItens;
    }

    public int getCodPlano() {
        return codPlano;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public int getLimiteTotalItens() {
        return limiteTotalItens;
    }

    @Override
    public String toString() {
        return nome + " - R$ " + valor + " - limite: " + limiteTotalItens + " itens";
    }
}