package Service;

import Model.Entrega;

public class EntregaService {

    private int proximoIdEntrega = 1;

    public boolean validarEndereco(String endereco) {
        return endereco != null && endereco.trim().length() >= 5;
    }

    public Entrega agendarEntrega(String endereco) {
        Entrega entrega = new Entrega(proximoIdEntrega++, endereco);
        entrega.confirmarEntrega();

        return entrega;
    }
}