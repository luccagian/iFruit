package Model;

import java.time.LocalDate;

public class Pagamento {

    private int idPagamento;
    private double valor;
    private String status;
    private LocalDate dataPagamento;
    private Cartao cartao;

    public Pagamento(int idPagamento, double valor, Cartao cartao) {
        this.idPagamento = idPagamento;
        this.valor = valor;
        this.cartao = cartao;
        this.status = "PENDENTE";
        this.dataPagamento = LocalDate.now();
    }

    public void aprovar() {
        this.status = "APROVADO";
    }

    public void recusar() {
        this.status = "RECUSADO";
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public double getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public Cartao getCartao() {
        return cartao;
    }
}