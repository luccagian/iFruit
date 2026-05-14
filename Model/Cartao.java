package Model;

public class Cartao {

    private int cartaoId;
    private String nomeTitular;
    private String numero;
    private String validade;
    private String cvv;

    public Cartao(int cartaoId, String nomeTitular, String numero, String validade, String cvv) {
        this.cartaoId = cartaoId;
        this.nomeTitular = nomeTitular;
        this.numero = numero;
        this.validade = validade;
        this.cvv = cvv;
    }

    public int getCartaoId() {
        return cartaoId;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public String getNumero() {
        return numero;
    }

    public String getValidade() {
        return validade;
    }

    public String getCvv() {
        return cvv;
    }

    public String getUltimos4Digitos() {
        if (numero == null || numero.length() < 4) {
            return "";
        }

        return numero.substring(numero.length() - 4);
    }

    @Override
    public String toString() {
        return "Cartao final " + getUltimos4Digitos();
    }
}