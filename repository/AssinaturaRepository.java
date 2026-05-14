package repository;

import Model.Assinatura;
import Model.Entrega;
import Model.Item;
import Model.Pagamento;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AssinaturaRepository {

    private static final String CAMINHO_ARQUIVO = "assinaturas.txt";

    public void salvar(Assinatura assinatura) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO, true))) {

            writer.write("====================================");
            writer.newLine();

            writer.write("ID Assinatura: " + assinatura.getIdAssinatura());
            writer.newLine();

            writer.write("Status: " + assinatura.getStatus());
            writer.newLine();

            writer.write("Data Criacao: " + assinatura.getDataCriacao());
            writer.newLine();

            writer.write("Assinante: " + assinatura.getAssinante().getNome());
            writer.newLine();

            writer.write("Telefone: " + assinatura.getAssinante().getTelefone());
            writer.newLine();

            writer.write("Plano: " + assinatura.getPlano().getNome());
            writer.newLine();

            writer.write("Valor Plano: R$ " + assinatura.getPlano().getValor());
            writer.newLine();

            writer.write("Limite Total da Cesta: " + assinatura.getPlano().getLimiteTotalItens());
            writer.newLine();

            writer.write("Total de Itens Escolhidos: " + assinatura.getCesta().getTotalItens());
            writer.newLine();

            writer.write("Itens da Cesta:");
            writer.newLine();

            for (Item item : assinatura.getCesta().getItens()) {
                writer.write("- " + item.getNome()
                        + " | Tipo: " + item.getTipo()
                        + " | Quantidade: " + item.getQuantidade());
                writer.newLine();
            }

            writer.write("Pagamentos:");
            writer.newLine();

            for (Pagamento pagamento : assinatura.getPagamentos()) {
                writer.write("- Cartao final: " + pagamento.getCartao().getUltimos4Digitos()
                        + " | Valor: R$ " + pagamento.getValor()
                        + " | Status: " + pagamento.getStatus()
                        + " | Data: " + pagamento.getDataPagamento());
                writer.newLine();
            }

            writer.write("Entregas:");
            writer.newLine();

            for (Entrega entrega : assinatura.getEntregas()) {
                writer.write("- Endereco: " + entrega.getEndereco()
                        + " | Data: " + entrega.getDataEntrega()
                        + " | Status: " + entrega.getStatusEntrega());
                writer.newLine();
            }

            writer.write("====================================");
            writer.newLine();
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Erro ao salvar assinatura: " + e.getMessage());
        }
    }
}