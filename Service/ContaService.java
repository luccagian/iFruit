package Service;

import Model.Assinante;
import Model.Conta;

public class ContaService {

    private Conta contaAtual;
    private Assinante assinanteAtual;
    private int proximoIdAssinante = 1;

    public Assinante buscarOuCriarConta(String nome, String telefone) {
        if (contaAtual != null && contaAtual.buscarContaFone(telefone)) {
            return assinanteAtual;
        }

        assinanteAtual = new Assinante(proximoIdAssinante++, nome, telefone);
        contaAtual = new Conta(telefone, assinanteAtual);

        return assinanteAtual;
    }

    public boolean validarCodigo(String codigo) {
        if (contaAtual == null) {
            return false;
        }

        return contaAtual.validarCodigo(codigo);
    }

    public Conta getContaAtual() {
        return contaAtual;
    }

    public Assinante getAssinanteAtual() {
        return assinanteAtual;
    }
}