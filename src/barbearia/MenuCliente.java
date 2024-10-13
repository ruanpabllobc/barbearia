package src.barbearia;

import java.sql.Date;
import java.sql.SQLException;
import src.db.Banco;
import java.util.Scanner;

public class MenuCliente {

    private Scanner scanner;
    private Banco db;
    private Cliente cliente;
    private Barbearia barbearia;
    private Servico servico;
    private Reserva reserva;

    // Construtor para inicializar os objetos
    public MenuCliente() {
        this.scanner = new Scanner(System.in);
        this.db = new Banco();
        this.cliente = new Cliente();
        this.barbearia = new Barbearia();
        this.servico = new Servico();
        this.reserva = new Reserva();
    }

    // Método que exibe o menu e trata as opções
    public void exibirMenu() throws SQLException {
        String nome, email, senha, telefone, cnpj, pagamento, servicos;
        Date dataHora;
        int opcao, id;

        do {
            System.out.println("------------------------");
            System.out.println("1. ATUALIZAR PERFIL");
            System.out.println("2. NOVA RESERVA");
            System.out.println("3. BUSCAR RESERVA");
            System.out.println("4. CANCELAR RESERVA");
            System.out.println("5. DELETAR PERFIL");
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
                    System.out.println("Digite a nova telefone: ");
                    telefone = scanner.nextLine();
                    cliente.setNome(nome);
                    cliente.setEmail(email);
                    cliente.setSenha(senha);
                    cliente.setTelefone(telefone);
                    cliente.editarCliente(db);
                    System.out.println("Dados atualizados!");
                    break;
                case 2:
                    System.out.println("Opção 2 selecionada: Nova reserva");
                    barbearia.listarBarbearias(db);
                    System.out.println("Informe o CNPJ da barbearia: ");
                    cnpj = scanner.nextLine();
                    dataHora = converterData(scanner);
                    while (Reserva.pesquisarReservaNoDia(db, dataHora, cnpj)) {
                        System.out.println("Data ocupada. Tente outra data.");
                        dataHora = converterData(scanner);
                    }
                    System.out.println("Data disponível para a reserva!");
                    System.out.println("Metodo de pagamento: Pix ou Cartão");
                    pagamento = scanner.nextLine();
                    servico.listarServicos(db, cnpj);
                    System.out.println("Digite o nome do serviço: ");
                    servicos = scanner.nextLine();
                    reserva.setIdCliente(cliente.getCpf());
                    reserva.setDataReserva(dataHora);
                    reserva.setMetodoPagamento(pagamento);
                    reserva.setIdBarbearia(cnpj);
                    reserva.setIdServico(servicos);
                    reserva.cadastrarReserva(db);
                    System.out.println("Reserva cadastrada");
                    break;
                case 3:
                    System.out.println("Opção 4 selecionada: Buscar reserva");
                    System.out.println("Informe o ID da sua reserva: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    reserva.pesquisarReserva(db, id);
                    break;
                case 4:
                    System.out.println("Opção 3 selecionada: Cancelar reserva");
                    reserva.listarReservas(db);
                    System.out.println("Informe o ID da sua reserva: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    reserva.setId(id);
                    reserva.removerReserva(db);
                    System.out.println("Reserva cancelada");
                    break;
                case 5:
                    System.out.println("Opção 5 selecionada: Remover perfil");
                    cliente.removerCliente(db);
                    System.out.println("APAGANDO DADOS...");
                    System.exit(0);
                    break;
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