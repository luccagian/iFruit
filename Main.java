import Controller.AssinaturaController;
import repository.AssinaturaRepository;
import repository.PlanoRepository;
import Service.AssinaturaService;
import Service.CestaService;
import Service.ContaService;
import Service.EntregaService;
import Service.PagamentoService;
import View.AppWeb;

public class Main {

    public static void main(String[] args) {
        PlanoRepository planoRepository = new PlanoRepository();
        AssinaturaRepository assinaturaRepository = new AssinaturaRepository();

        ContaService contaService = new ContaService();
        CestaService cestaService = new CestaService();
        PagamentoService pagamentoService = new PagamentoService();
        EntregaService entregaService = new EntregaService();

        AssinaturaService assinaturaService = new AssinaturaService(
                planoRepository,
                assinaturaRepository,
                pagamentoService,
                entregaService
        );

        AssinaturaController controller = new AssinaturaController(
                contaService,
                cestaService,
                assinaturaService,
                entregaService
        );

        AppWeb appWeb = new AppWeb(controller);
        appWeb.iniciar();
    }
}