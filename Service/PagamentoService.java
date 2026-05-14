package Service;

import Model.Cartao;
import Model.Pagamento;

import java.time.YearMonth;

public class PagamentoService {

    private int proximoIdPagamento = 1;

    public boolean validarCartao(Cartao cartao) {
        if (cartao == null) {
            return false;
        }

        if (cartao.getNomeTitular() == null || cartao.getNomeTitular().trim().isEmpty()) {
            return false;
        }

        if (!validarNumeroCartao(cartao.getNumero())) {
            return false;
        }

        if (!validarValidade(cartao.getValidade())) {
            return false;
        }

        return validarCvv(cartao.getCvv());
    }

    private boolean validarNumeroCartao(String numero) {
        if (numero == null) {
            return false;
        }

        String numeroLimpo = numero.replaceAll("\\s+", "");

        if (!numeroLimpo.matches("\\d+")) {
            return false;
        }

        return numeroLimpo.length() >= 13 && numeroLimpo.length() <= 19;
    }

    private boolean validarCvv(String cvv) {
        if (cvv == null) {
            return false;
        }

        return cvv.matches("\\d{3,4}");
    }

    private boolean validarValidade(String validade) {
        if (validade == null || !validade.matches("\\d{2}/\\d{2}")) {
            return false;
        }

        int mes = Integer.parseInt(validade.substring(0, 2));
        int ano = Integer.parseInt(validade.substring(3, 5)) + 2000;

        if (mes < 1 || mes > 12) {
            return false;
        }

        YearMonth validadeCartao = YearMonth.of(ano, mes);
        YearMonth mesAtual = YearMonth.now();

        return !validadeCartao.isBefore(mesAtual);
    }

    public Pagamento processarPagamento(double valor, Cartao cartao) {
        Pagamento pagamento = new Pagamento(proximoIdPagamento++, valor, cartao);

        if (valor > 0 && validarCartao(cartao)) {
            pagamento.aprovar();
        } else {
            pagamento.recusar();
        }

        return pagamento;
    }
}