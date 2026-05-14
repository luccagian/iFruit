package View;

import Controller.AssinaturaController;
import Model.Assinante;
import Model.Assinatura;
import Model.Cartao;
import Model.Cesta;
import Model.Item;
import Model.Plano;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppWeb {

    private AssinaturaController controller;
    private Scanner scanner;

    public AppWeb(AssinaturaController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("=======================================");
        System.out.println("   SISTEMA DE ASSINATURA DE CESTAS");
        System.out.println("=======================================");

        System.out.println("\n=== Cadastro e Autenticacao ===");

        System.out.println("Exemplo de preenchimento:");
        System.out.println("Nome: Joao Silva");
        System.out.println("Telefone: 11999999999");

        System.out.print("\nDigite seu nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite seu telefone: ");
        String telefone = scanner.nextLine();

        Assinante assinante = controller.buscarOuCriarConta(nome, telefone);

        System.out.println("\nConta localizada/criada com sucesso.");
        System.out.println("Codigo enviado para o telefone informado.");
        System.out.println("Codigo de teste: 1234");

        System.out.print("Digite o codigo recebido: ");
        String codigo = scanner.nextLine();

        boolean codigoValido = controller.validarCodigo(codigo);

        if (!codigoValido) {
            System.out.println("\nCodigo invalido. Acesso negado.");
            return;
        }

        System.out.println("\nAcesso liberado!");

        Plano planoSelecionado = selecionarPlano();

        if (planoSelecionado == null) {
            System.out.println("\nPlano invalido. Operacao cancelada.");
            return;
        }

        exibirLimitePlano(planoSelecionado);

        Cesta cesta = montarCesta(planoSelecionado);

        if (cesta == null || cesta.getItens().isEmpty()) {
            System.out.println("\nA cesta esta vazia ou excedeu o limite total do plano.");
            System.out.println("Operacao cancelada.");
            return;
        }

        String endereco = informarEndereco();

        boolean enderecoValido = controller.definirEntrega(endereco);

        if (!enderecoValido) {
            System.out.println("\nEndereco invalido. Operacao cancelada.");
            return;
        }

        Cartao cartao = informarCartao();

        System.out.println("\nConfirmando assinatura...");

        Assinatura assinatura = controller.criarAssinatura(
                assinante,
                planoSelecionado,
                cesta,
                endereco,
                cartao
        );

        if (assinatura != null) {
            exibirConfirmacao(assinatura);
            perguntarAlteracaoPlano(assinatura);
        } else {
            System.out.println("\nPagamento recusado ou dados invalidos.");
            System.out.println("Assinatura nao foi criada.");
        }
    }

    private Plano selecionarPlano() {
        System.out.println("\n=== Selecao do Plano Mensal ===");
        System.out.println("1 - Mensal Basico  - R$ 79.90  - ate 7 itens");
        System.out.println("2 - Mensal Familia - R$ 129.90 - ate 13 itens");
        System.out.println("3 - Mensal Premium - R$ 189.90 - ate 20 itens");

        System.out.print("Escolha o codigo do plano: ");
        int codPlano = lerInteiro();

        Plano plano = controller.definirPlano(codPlano);

        if (plano != null) {
            System.out.println("\nPlano selecionado:");
            System.out.println("Nome: " + plano.getNome());
            System.out.println("Descricao: " + plano.getDescricao());
            System.out.println("Valor: R$ " + plano.getValor());
            System.out.println("Total permitido na cesta: " + plano.getLimiteTotalItens() + " itens");
        }

        return plano;
    }

    private void exibirLimitePlano(Plano plano) {
        System.out.println("\n=== Limite da Cesta para o Plano " + plano.getNome() + " ===");
        System.out.println("Total de itens permitido: " + plano.getLimiteTotalItens());
        System.out.println("Voce pode distribuir esse total entre frutas, legumes e verduras como quiser.");
    }

    private Cesta montarCesta(Plano plano) {
        System.out.println("\n=== Montagem da Cesta ===");

        List<Item> itens = new ArrayList<>();

        int totalItens = 0;
        int opcao = -1;

        while (opcao != 0) {
            int restante = plano.getLimiteTotalItens() - totalItens;

            System.out.println("\n---------------------------------------");
            System.out.println("Quantidade atual da cesta:");
            System.out.println("Total usado: " + totalItens + "/" + plano.getLimiteTotalItens());
            System.out.println("Restante: " + restante + " item(ns)");
            System.out.println("---------------------------------------");

            System.out.println("\nMenu da cesta:");
            System.out.println("1 - Adicionar Banana  | Fruta");
            System.out.println("2 - Adicionar Maca    | Fruta");
            System.out.println("3 - Adicionar Alface  | Verdura");
            System.out.println("4 - Adicionar Tomate  | Legume");
            System.out.println("5 - Adicionar Cenoura | Legume");
            System.out.println("6 - Visualizar cesta");
            System.out.println("7 - Remover item da cesta");
            System.out.println("0 - Finalizar cesta");

            System.out.print("\nEscolha uma opcao: ");
            opcao = lerInteiro();

            if (opcao == 0) {
                break;
            }

            if (opcao == 6) {
                visualizarItensCesta(itens, totalItens, plano);
                continue;
            }

            if (opcao == 7) {
                totalItens = removerItemDaCesta(itens);
                continue;
            }

            if (opcao < 1 || opcao > 5) {
                System.out.println("Opcao invalida.");
                continue;
            }

            if (restante == 0) {
                System.out.println("\nLimite total da cesta atingido.");
                System.out.println("Remova algum item para adicionar outro.");
                continue;
            }

            System.out.print("Quantidade: ");
            int quantidade = lerInteiro();

            if (quantidade <= 0) {
                System.out.println("Quantidade invalida. Item nao adicionado.");
                continue;
            }

            if (quantidade > restante) {
                System.out.println("\nLimite total da cesta excedido.");
                System.out.println("Voce ainda pode adicionar apenas " + restante + " item(ns).");
                continue;
            }

            Item item = criarItemPorOpcao(opcao, quantidade);

            if (item == null) {
                System.out.println("Opcao invalida. Item nao adicionado.");
                continue;
            }

            itens.add(item);
            totalItens += quantidade;

            System.out.println("Item adicionado: " + item.getNome());
        }

        Cesta cesta = controller.montarCesta(itens, plano);

        if (cesta != null) {
            System.out.println("\nCesta montada com " + cesta.getTotalItens() + " itens no total.");
        }

        return cesta;
    }

    private void visualizarItensCesta(List<Item> itens, int totalItens, Plano plano) {
        System.out.println("\n=== Itens atuais da cesta ===");

        if (itens.isEmpty()) {
            System.out.println("A cesta ainda esta vazia.");
            return;
        }

        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);

            System.out.println((i + 1) + " - " + item.getNome()
                    + " | Tipo: " + item.getTipo()
                    + " | Quantidade: " + item.getQuantidade());
        }

        System.out.println("\nTotal usado: " + totalItens + "/" + plano.getLimiteTotalItens());
        System.out.println("Restante: " + (plano.getLimiteTotalItens() - totalItens) + " item(ns)");
    }

    private int removerItemDaCesta(List<Item> itens) {
        System.out.println("\n=== Remover item da cesta ===");

        if (itens.isEmpty()) {
            System.out.println("A cesta esta vazia. Nao ha itens para remover.");
            return 0;
        }

        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);

            System.out.println((i + 1) + " - " + item.getNome()
                    + " | Tipo: " + item.getTipo()
                    + " | Quantidade: " + item.getQuantidade());
        }

        System.out.print("Digite o numero do item que deseja remover: ");
        int indice = lerInteiro();

        if (indice < 1 || indice > itens.size()) {
            System.out.println("Item invalido. Nenhum item foi removido.");
            return calcularTotalLista(itens);
        }

        Item removido = itens.remove(indice - 1);

        System.out.println("Item removido: " + removido.getNome()
                + " | Quantidade: " + removido.getQuantidade());

        return calcularTotalLista(itens);
    }

    private int calcularTotalLista(List<Item> itens) {
        int total = 0;

        for (Item item : itens) {
            total += item.getQuantidade();
        }

        return total;
    }

    private Item criarItemPorOpcao(int opcao, int quantidade) {
        switch (opcao) {
            case 1:
                return new Item(1, "Banana", "Fruta", quantidade);
            case 2:
                return new Item(2, "Maca", "Fruta", quantidade);
            case 3:
                return new Item(3, "Alface", "Verdura", quantidade);
            case 4:
                return new Item(4, "Tomate", "Legume", quantidade);
            case 5:
                return new Item(5, "Cenoura", "Legume", quantidade);
            default:
                return null;
        }
    }

    private String informarEndereco() {
        System.out.println("\n=== Definicao da Entrega ===");
        System.out.println("Exemplo de preenchimento:");
        System.out.println("Rua das Flores, 123 - Centro - Sao Paulo/SP");

        System.out.print("\nDigite o endereco de entrega: ");
        return scanner.nextLine();
    }

    private Cartao informarCartao() {
        System.out.println("\n=== Cartao de Credito ===");

        System.out.println("Exemplo de preenchimento:");
        System.out.println("Nome do titular: Joao Silva");
        System.out.println("Numero do cartao: 4111111111111111");
        System.out.println("Validade MM/AA: 12/30");
        System.out.println("CVV: 123");

        System.out.print("\nNome do titular: ");
        String nomeTitular = scanner.nextLine();

        System.out.print("Numero do cartao: ");
        String numero = scanner.nextLine();

        System.out.print("Validade MM/AA: ");
        String validade = scanner.nextLine();

        System.out.print("CVV: ");
        String cvv = scanner.nextLine();

        return new Cartao(1, nomeTitular, numero, validade, cvv);
    }

    private void exibirConfirmacao(Assinatura assinatura) {
        System.out.println("\n=======================================");
        System.out.println("     ASSINATURA CONFIRMADA!");
        System.out.println("=======================================");

        System.out.println("Assinante: " + assinatura.getAssinante().getNome());
        System.out.println("Telefone: " + assinatura.getAssinante().getTelefone());

        System.out.println("\nPlano:");
        System.out.println("- " + assinatura.getPlano().getNome());
        System.out.println("- Valor mensal: R$ " + assinatura.getPlano().getValor());
        System.out.println("- Limite total da cesta: " + assinatura.getPlano().getLimiteTotalItens());

        System.out.println("\nItens da Cesta:");

        for (Item item : assinatura.getCesta().getItens()) {
            System.out.println("- " + item.getNome()
                    + " | Tipo: " + item.getTipo()
                    + " | Quantidade: " + item.getQuantidade());
        }

        System.out.println("\nTotal de itens: " + assinatura.getCesta().getTotalItens());

        System.out.println("\nPagamento:");
        System.out.println("- Status: " + assinatura.getPagamentos()
                .get(assinatura.getPagamentos().size() - 1)
                .getStatus());

        System.out.println("- Cartao final: " + assinatura.getPagamentos()
                .get(assinatura.getPagamentos().size() - 1)
                .getCartao()
                .getUltimos4Digitos());

        System.out.println("\nEntrega:");
        System.out.println("- Endereco: " + assinatura.getEntregas()
                .get(assinatura.getEntregas().size() - 1)
                .getEndereco());

        System.out.println("- Data: " + assinatura.getEntregas()
                .get(assinatura.getEntregas().size() - 1)
                .getDataEntrega());

        System.out.println("\nOs dados foram salvos no arquivo assinaturas.txt");
        System.out.println("=======================================");
    }

    private void perguntarAlteracaoPlano(Assinatura assinatura) {
        System.out.println("\nDeseja alterar o plano?");
        System.out.println("1 - Sim");
        System.out.println("2 - Nao");
        System.out.print("Escolha: ");

        int opcao = lerInteiro();

        if (opcao != 1) {
            System.out.println("\nSistema finalizado.");
            return;
        }

        Plano novoPlano = selecionarPlano();

        if (novoPlano == null) {
            System.out.println("\nPlano invalido. Alteracao cancelada.");
            return;
        }

        exibirLimitePlano(novoPlano);

        System.out.println("\nMonte uma nova cesta de acordo com o novo plano.");
        Cesta novaCesta = montarCesta(novoPlano);

        if (novaCesta == null || novaCesta.getItens().isEmpty()) {
            System.out.println("\nNova cesta invalida. Alteracao cancelada.");
            return;
        }

        boolean alterado = controller.alterarPlano(assinatura, novoPlano, novaCesta);

        if (alterado) {
            System.out.println("\nPlano alterado com sucesso!");
            exibirConfirmacao(assinatura);
        } else {
            System.out.println("\nNao foi possivel alterar o plano.");
        }
    }

    private int lerInteiro() {
        while (true) {
            try {
                String texto = scanner.nextLine();
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.print("Valor invalido. Digite um numero: ");
            }
        }
    }
}