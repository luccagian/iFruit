package Service;

import Model.Assinante;
import Model.Assinatura;
import Model.Cartao;
import Model.Cesta;
import Model.Entrega;
import Model.Pagamento;
import Model.Plano;
import repository.AssinaturaRepository;
import repository.PlanoRepository;

public class AssinaturaService {

    private PlanoRepository planoRepository;
    private AssinaturaRepository assinaturaRepository;
    private PagamentoService pagamentoService;
    private EntregaService entregaService;

    private int proximoIdAssinatura = 1;

    public AssinaturaService(
            PlanoRepository planoRepository,
            AssinaturaRepository assinaturaRepository,
            PagamentoService pagamentoService,
            EntregaService entregaService
    ) {
        this.planoRepository = planoRepository;
        this.assinaturaRepository = assinaturaRepository;
        this.pagamentoService = pagamentoService;
        this.entregaService = entregaService;
    }

    public Plano buscarPlano(int codPlano) {
        return planoRepository.buscarPlano(codPlano);
    }

    public boolean validarAssinatura(Assinante assinante, Plano plano, Cesta cesta) {
        if (assinante == null) {
            return false;
        }

        if (plano == null) {
            return false;
        }

        if (cesta == null) {
            return false;
        }

        return cesta.getItens() != null && !cesta.getItens().isEmpty();
    }

    public Assinatura gerarAssinatura(
            Assinante assinante,
            Plano plano,
            Cesta cesta,
            String endereco,
            Cartao cartao
    ) {
        if (!validarAssinatura(assinante, plano, cesta)) {
            return null;
        }

        Assinatura assinatura = new Assinatura(proximoIdAssinatura++, assinante);

        assinatura.definirPlano(plano);
        assinatura.definirCesta(cesta);

        Pagamento pagamento = pagamentoService.processarPagamento(plano.getValor(), cartao);

        assinatura.adicionarPagamento(pagamento);

        if (!pagamento.getStatus().equals("APROVADO")) {
            return null;
        }

        Entrega entrega = entregaService.agendarEntrega(endereco);

        assinatura.adicionarEntrega(entrega);
        assinatura.confirmarAssinatura();

        assinaturaRepository.salvar(assinatura);

        return assinatura;
    }

    public boolean alterarPlano(Assinatura assinatura, Plano novoPlano, Cesta novaCesta) {
        if (assinatura == null || novoPlano == null || novaCesta == null) {
            return false;
        }

        assinatura.alterarPlano(novoPlano);
        assinatura.definirCesta(novaCesta);

        assinaturaRepository.salvar(assinatura);

        return true;
    }
}