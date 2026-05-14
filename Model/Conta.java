package Model;

public class Conta {

    private String telefone;
    private String codigoValidacao;
    private Assinante assinante;

    public Conta(String telefone, Assinante assinante) {
        this.telefone = telefone;
        this.assinante = assinante;
        this.codigoValidacao = "1234";
    }

    public void criarContaFone(String telefone) {
        this.telefone = telefone;
        this.codigoValidacao = "1234";
    }

    public boolean buscarContaFone(String telefone) {
        return this.telefone != null && this.telefone.equals(telefone);
    }

    public boolean validarCodigo(String codigo) {
        return this.codigoValidacao != null && this.codigoValidacao.equals(codigo);
    }

    public Assinante getAssinante() {
        return assinante;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCodigoValidacao() {
        return codigoValidacao;
    }
}