package Model;

import java.time.LocalDate;

public class Entrega {

    private int idEntrega;
    private String endereco;
    private String statusEntrega;
    private LocalDate dataEntrega;

    public Entrega(int idEntrega, String endereco) {
        this.idEntrega = idEntrega;
        this.endereco = endereco;
        this.statusEntrega = "AGENDADA";
        this.dataEntrega = LocalDate.now().plusDays(2);
    }

    public void configurarEntrega(String endereco) {
        this.endereco = endereco;
        this.statusEntrega = "AGENDADA";
        this.dataEntrega = LocalDate.now().plusDays(2);
    }

    public void confirmarEntrega() {
        this.statusEntrega = "CONFIRMADA";
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getStatusEntrega() {
        return statusEntrega;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }
}