package Controller;

import Model.Assinante;
import Model.Assinatura;
import Model.Cartao;
import Model.Cesta;
import Model.Item;
import Model.Plano;
import Service.AssinaturaService;
import Service.CestaService;
import Service.ContaService;
import Service.EntregaService;

import java.util.List;

public class AssinaturaController {

    private ContaService contaService;
    private CestaService cestaService;
    private AssinaturaService assinaturaService;
    private EntregaService entregaService;

    public AssinaturaController(
            ContaService contaService,
            CestaService cestaService,
            AssinaturaService assinaturaService,
            EntregaService entregaService
    ) {
        this.contaService = contaService;
        this.cestaService = cestaService;
        this.assinaturaService = assinaturaService;
        this.entregaService = entregaService;
    }

    public Assinante buscarOuCriarConta(String nome, String telefone) {
        return contaService.buscarOuCriarConta(nome, telefone);
    }

    public boolean validarCodigo(String codigo) {
        return contaService.validarCodigo(codigo);
    }

    public Plano definirPlano(int codPlano) {
        return assinaturaService.buscarPlano(codPlano);
    }

    public Cesta montarCesta(List<Item> itens, Plano plano) {
        return cestaService.criarCesta(itens, plano);
    }

    public boolean definirEntrega(String endereco) {
        return entregaService.validarEndereco(endereco);
    }

    public Assinatura criarAssinatura(
            Assinante assinante,
            Plano plano,
            Cesta cesta,
            String endereco,
            Cartao cartao
    ) {
        return assinaturaService.gerarAssinatura(
                assinante,
                plano,
                cesta,
                endereco,
                cartao
        );
    }

    public boolean alterarPlano(Assinatura assinatura, Plano novoPlano, Cesta novaCesta) {
        return assinaturaService.alterarPlano(assinatura, novoPlano, novaCesta);
    }
}