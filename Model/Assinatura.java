package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Assinatura {

    private int idAssinatura;
    private String status;
    private LocalDate dataCriacao;

    private Assinante assinante;
    private Plano plano;
    private Cesta cesta;
    private List<Pagamento> pagamentos;
    private List<Entrega> entregas;

    public Assinatura(int idAssinatura, Assinante assinante) {
        this.idAssinatura = idAssinatura;
        this.assinante = assinante;
        this.status = "CRIADA";
        this.dataCriacao = LocalDate.now();
        this.pagamentos = new ArrayList<>();
        this.entregas = new ArrayList<>();
    }

    public void definirPlano(Plano plano) {
        this.plano = plano;
    }

    public void alterarPlano(Plano novoPlano) {
        this.plano = novoPlano;
    }

    public void definirCesta(Cesta cesta) {
        this.cesta = cesta;
    }

    public void adicionarPagamento(Pagamento pagamento) {
        if (pagamento != null) {
            pagamentos.add(pagamento);
        }
    }

    public void adicionarEntrega(Entrega entrega) {
        if (entrega != null) {
            entregas.add(entrega);
        }
    }

    public void confirmarAssinatura() {
        this.status = "CONFIRMADA";
    }

    public int getIdAssinatura() {
        return idAssinatura;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public Assinante getAssinante() {
        return assinante;
    }

    public Plano getPlano() {
        return plano;
    }

    public Cesta getCesta() {
        return cesta;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public List<Entrega> getEntregas() {
        return entregas;
    }
}