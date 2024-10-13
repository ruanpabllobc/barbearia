package src.barbearia;

import java.sql.Date;
import java.sql.SQLException;
import src.db.Banco;
import java.util.Scanner;

public class MenuBarbearia {

    private Scanner scanner;
    private Banco db;
    private Cliente cliente;
    private Barbearia barbearia;
    private Servico servico;
    private Reserva reserva;

    // Construtor para inicializar os objetos
    public MenuBarbearia() {
        this.scanner = new Scanner(System.in);
        this.db = new Banco();
        this.cliente = new Cliente();
        this.barbearia = new Barbearia();
        this.servico = new Servico();
        this.reserva = new Reserva();
    }

    // Método que exibe o menu e trata as opções
    public void exibirMenu() throws SQLException {
        String nome, email, senha, endereco, pagamento, servicos;
        float valor;
        Date dataHora;
        int opcao, id;

        do {
            System.out.println("------------------------");
            System.out.println("1. ATUALIZAR PERFIL");
            System.out.println("2. CADASTRAR RESERVA");
            System.out.println("3. ATUALIZAR RESERVA");
            System.out.println("4. BUSCAR RESERVA");
            System.out.println("5. CANCELAR RESERVA");
            System.out.println("6. LISTAR RESERVA");
            System.out.println("7. CADASTRAR SERVIÇO");
            System.out.println("8. ATUALIZAR SERVIÇO");
            System.out.println("9. LISTAR SERVIÇOS");
            System.out.println("10. REMOVER SERVIÇO");
            System.out.println("11. EXCLUIR PERFIL");
            System.out.println("0. SAIR");
            System.out.println("------------------------");

            System.out.print("Digite a opção desejada: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa a linha

            switch (opcao) {
                case 1:
                    System.out.println("Opção 1 selecionada: Atualizar perfil");
                    System.out.println("Digite o novo nome: ");
                    nome = scanner.nextLine();
                    System.out.println("Digite o novo email: ");
                    email = scanner.nextLine();
                    System.out.println("Digite a nova senha: ");
                    senha = scanner.nextLine();
                    System.out.println("Digite a nova endereço: ");
                    endereco = scanner.nextLine();
                    barbearia.setNome(nome);
                    barbearia.setEmail(email);
                    barbearia.setSenha(senha);
                    barbearia.setEndereco(endereco);
                    barbearia.editarBarbearia(db);
                    System.out.println("Dados atualizados!");
                    break;
                case 2:
                    System.out.println("Opção 2 selecionada: Nova reserva");
                    dataHora = converterData(scanner);
                    while (Reserva.pesquisarReservaNoDia(db, dataHora, barbearia.getCnpj())) {
                        System.out.println("Data ocupada. Tente outra data.");
                        dataHora = converterData(scanner);
                    }
                    System.out.println("Data disponível para a reserva!");
                    System.out.println("Metodo de pagamento: Pix ou Cartão");
                    pagamento = scanner.nextLine();
                    servico.listarServicos(db, barbearia.getCnpj());
                    System.out.println("Digite o nome do serviço: ");
                    servicos = scanner.nextLine();
                    reserva.setIdCliente(cliente.getCpf());
                    reserva.setDataReserva(dataHora);
                    reserva.setMetodoPagamento(pagamento);
                    reserva.setIdBarbearia(barbearia.getCnpj());
                    reserva.setIdServico(servicos);
                    reserva.cadastrarReserva(db);
                    System.out.println("Reserva cadastrada");
                    break;
                case 3:
                    System.out.println("Opção 3 selecionada: Atualizar reserva");
                    dataHora = converterData(scanner);
                    while (Reserva.pesquisarReservaNoDia(db, dataHora, barbearia.getCnpj())) {
                        System.out.println("Data ocupada. Tente outra data.");
                        dataHora = converterData(scanner);
                    }
                    System.out.println("Data disponível para a reserva!");
                    System.out.println("Metodo de pagamento: Pix ou Cartão");
                    pagamento = scanner.nextLine();
                    servico.listarServicos(db, barbearia.getCnpj());
                    System.out.println("Digite o nome do serviço: ");
                    servicos = scanner.nextLine();
                    reserva.setIdCliente(cliente.getCpf());
                    reserva.setDataReserva(dataHora);
                    reserva.setMetodoPagamento(pagamento);
                    reserva.setIdBarbearia(barbearia.getCnpj());
                    reserva.setIdServico(servicos);
                    reserva.cadastrarReserva(db);
                    System.out.println("Reserva cadastrada");
                case 4:
                    System.out.println("Opção 4 selecionada: Buscar reserva");
                    System.out.println("Informe o ID da reserva: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    reserva.pesquisarReserva(db, id);
                    break;
                case 5:
                    System.out.println("Opção 5 selecionada: Cancelar reserva");
                    reserva.listarReservas(db);
                    System.out.println("Informe o ID da reserva a ser cancelada: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    reserva.setId(id);
                    reserva.removerReserva(db);
                    System.out.println("Reserva cancelada");
                    break;
                case 6:
                    System.out.println("Opção 6 selecionada: Listar reservas");
                    reserva.listarReservas(db);
                    break;
                case 7:
                    System.out.println("Opção 7 selecionada: Cadastrar serviços");
                    System.out.println("Informe o nome: ");
                    nome = scanner.nextLine();
                    System.out.println("Informe o valor: ");
                    valor = scanner.nextFloat();
                    scanner.nextLine();
                    servico.setNomeServico(nome);
                    servico.setValorServico(valor);
                    servico.setIdBarbearia(barbearia.getCnpj());
                    servico.cadastrarServico(db);
                    System.out.println("Serviço atualizado");
                    break;
                case 8:
                    System.out.println("Opção 8 selecionada: Atualizar serviços");
                    System.out.println("Informe o nome do serviço: ");
                    servicos = scanner.nextLine();
                    System.out.println("Informe o novo nome do serviço: ");
                    nome = scanner.nextLine();
                    System.out.println("Informe o novo valor: ");
                    valor = scanner.nextFloat();
                    scanner.nextLine();
                    servico.setNomeServico(nome);
                    servico.setValorServico(valor);
                    servico.setIdBarbearia(barbearia.getCnpj());
                    servico.editarServico(db, barbearia.getCnpj(), servicos);
                    System.out.println("Serviço atualizado");
                    break;
                case 9:
                    System.out.println("Opção 9 selecionada: Listar serviços");
                    servico.listarServicos(db, barbearia.getCnpj());
                    break;
                case 10:
                    System.out.println("Opção 10 selecionada: Remover serviço");
                    servico.listarServicos(db, barbearia.getCnpj());
                    System.out.println("Informe o nome do serviço:");
                    nome = scanner.nextLine();
                    servico.setNomeServico(nome);
                    servico.removerServico(db, barbearia.getCnpj());
                    System.out.println("Serviço removido");
                case 11:
                    System.out.println("Opção 11 selecionada: Remover perfil");
                    barbearia.removerBarbearia(db);
                    System.out.println("APAGANDO DADOS...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // Método auxiliar para converter a data
    private static Date converterData(Scanner scanner) {
        System.out.println("Informe a data no formato AAAA-MM-DD: ");
        String dataString = scanner.nextLine();
        return Date.valueOf(dataString);
    }
}