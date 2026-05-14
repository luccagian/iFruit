package repository;

import Model.Plano;

import java.util.ArrayList;
import java.util.List;

public class PlanoRepository {

    private List<Plano> planos;

    public PlanoRepository() {
        this.planos = new ArrayList<>();

        planos.add(new Plano(
                1,
                "Mensal Basico",
                "Assinatura mensal com cesta pequena",
                79.90,
                7
        ));

        planos.add(new Plano(
                2,
                "Mensal Familia",
                "Assinatura mensal com cesta media",
                129.90,
                13
        ));

        planos.add(new Plano(
                3,
                "Mensal Premium",
                "Assinatura mensal com cesta grande",
                189.90,
                20
        ));
    }

    public Plano buscarPlano(int codPlano) {
        for (Plano plano : planos) {
            if (plano.getCodPlano() == codPlano) {
                return plano;
            }
        }

        return null;
    }

    public List<Plano> listarPlanos() {
        return planos;
    }
}