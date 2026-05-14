package Model;

import java.util.ArrayList;
import java.util.List;

public class Cesta {

    private int idCesta;
    private int totalItens;
    private List<Item> itens;

    public Cesta(int idCesta) {
        this.idCesta = idCesta;
        this.totalItens = 0;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        if (item != null) {
            itens.add(item);
            calcularTotalItens();
        }
    }

    public void removerItem(Item item) {
        if (item != null) {
            itens.remove(item);
            calcularTotalItens();
        }
    }

    public int calcularTotalItens() {
        int total = 0;

        for (Item item : itens) {
            total += item.getQuantidade();
        }

        this.totalItens = total;
        return total;
    }

    public int getIdCesta() {
        return idCesta;
    }

    public int getTotalItens() {
        return totalItens;
    }

    public List<Item> getItens() {
        return itens;
    }
}