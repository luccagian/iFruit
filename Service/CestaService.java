package Service;

import Model.Cesta;
import Model.Item;
import Model.Plano;

import java.util.List;

public class CestaService {

    private int proximoIdCesta = 1;

    public Cesta criarCesta(List<Item> itens, Plano plano) {
        Cesta cesta = new Cesta(proximoIdCesta++);

        if (itens != null) {
            for (Item item : itens) {
                cesta.adicionarItem(item);
            }
        }

        if (!validarLimiteTotalPlano(cesta, plano)) {
            return null;
        }

        return cesta;
    }

    public boolean validarLimiteTotalPlano(Cesta cesta, Plano plano) {
        if (cesta == null || plano == null) {
            return false;
        }

        return cesta.getTotalItens() <= plano.getLimiteTotalItens();
    }
}