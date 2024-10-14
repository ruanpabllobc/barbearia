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
    public MenuCliente(Banco db) {
        this.scanner = new Scanner(System.in);
        this.db = db;
        this.cliente = new Cliente();
        this.barbearia = new Barbearia();
        this.servico = new Servico();
        this.reserva = new Reserva();
    }

    // Método que exibe o menu e trata as opções
    public void exibirMenu() throws SQLException {

        String nome, email, senha, telefone, cnpj, pagamento, servicos, cpf, confirma;
        Date dataHora;
        int opcao, id;

        cpf = obterEntradaValida(scanner, "Digite o seu CPF: ");
        cliente.setCpf(cpf);
        cliente.pesquisarCliente(db, cpf);
        senha = obterEntradaValida(scanner, "Digite a sua senha: ");
        if (cliente.usuarioLogin(senha)) {
            System.out.println("Senha correta!");
            do {
                System.out.println("------------------------");
                System.out.println("1. ATUALIZAR PERFIL");
                System.out.println("2. NOVA RESERVA");
                System.out.println("3. BUSCAR POR RESERVAS");
                System.out.println("4. MINHAS RESERVAS");
                System.out.println("5. CANCELAR RESERVA");
                System.out.println("6. DELETAR PERFIL");
                System.out.println("0. SAIR");
                System.out.println("------------------------");
    
                System.out.print("Digite a opção desejada: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa a linha
    
                switch (opcao) {
                    case 1:
                        System.out.println("Opção 1 selecionada: Atualizar perfil");
                        nome = obterEntradaValida(scanner, "Digite o novo nome: ");
                        senha = obterSenhaValida(scanner);
                        email = obterEntradaValida(scanner, "Digite o novo email: ");
                        telefone = obterEntradaValida(scanner, "Digite a nova telefone: ");
                        cliente.setNome(nome);
                        cliente.setSenha(senha);
                        cliente.setEmail(email);
                        cliente.setTelefone(telefone);
                        cliente.editarCliente(db);
                        System.out.println("Dados atualizados!");
                        break;
                    case 2:
                        System.out.println("Opção 2 selecionada: Nova reserva");
                        barbearia.listarBarbearias(db);
                        cnpj = obterEntradaValida(scanner, "Informe o CNPJ da barbearia: ");
                        dataHora = converterData(scanner);
                        while (Reserva.pesquisarReservaNoDia(db, dataHora, cnpj)) {
                            System.out.println("Data ocupada. Tente outra data.");
                            dataHora = converterData(scanner);
                        }
                        System.out.println("Data disponível para a reserva!");
                        pagamento = obterEntradaValida(scanner, "Método de pagamento [Pix] ou [Cartão]: ");
                        servico.listarServicos(db, cnpj);
                        servicos = obterEntradaValida(scanner, "Digite o nome do serviço: ");
                        reserva.setIdCliente(cliente.getCpf());
                        reserva.setIdBarbearia(cnpj);
                        reserva.setDataReserva(dataHora);
                        reserva.setMetodoPagamento(pagamento);
                        reserva.setIdServico(servicos);
                        reserva.cadastrarReserva(db);
                        System.out.println("Reserva cadastrada");
                        break;
                    case 3:
                        System.out.println("Opção 4 selecionada: Buscar reserva");
                        System.out.println("Informe o ID da sua reserva: ");
                        id = scanner.nextInt(); scanner.nextLine();
                        reserva.pesquisarReserva(db, id, cliente.getCpf());
                        break;
                    case 4:
                        System.out.println("Opção 4 selecionada: Minhas Reservas");
                        reserva.listarReservas(db, cliente.getCpf());
                        break;
                    case 5:
                        System.out.println("Opção 3 selecionada: Cancelar reserva");
                        reserva.listarReservas(db, cliente.getCpf());
                        System.out.println("Informe o ID da sua reserva: ");
                        id = scanner.nextInt(); scanner.nextLine();
                        reserva.setId(id);
                        reserva.removerReserva(db, cliente.getCpf());
                        break;
                    case 6:
                    System.out.println("Opção 11 selecionada: Remover perfil");
                    System.out.print("Você tem certeza que deseja remover o perfil? (s/n): ");
                    confirma = scanner.nextLine().trim().toLowerCase(); // Ler a resposta do usuário
                    // Verifica a resposta do usuário
                    if (confirma.equals("s")) {
                        cliente.removerCliente(db);// Chama o método para remover
                        System.out.println("APAGANDO DADOS...");
                        System.exit(0); // Encerra o programa
                    } else {
                        System.out.println("Ação cancelada. O perfil não foi removido."); // Mensagem de cancelamento
                    }
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 0);
        }

    }

    // Método auxiliar para converter a data
    private static Date converterData(Scanner scanner) {
        System.out.println("Informe a data no formato AAAA-MM-DD: ");
        String dataString = scanner.nextLine();
        return Date.valueOf(dataString);
    }

    private static String obterEntradaValida(Scanner scanner, String mensagem) {
        String entrada;
        do {
            System.out.print(mensagem);
            entrada = scanner.nextLine().trim(); // Remove espaços em branco
            if (entrada.isEmpty()) { // Verifica se a entrada está vazia
                System.out.println("A entrada não pode ser vazia. Por favor, tente novamente.");
            }
        } while (entrada.isEmpty()); // Continua pedindo até receber uma entrada válida
        return entrada; // Retorna a entrada válida
    }

    private static String obterSenhaValida(Scanner scanner) {
        String senha = null;
        String confirmarSenha = null;
        do {
            System.out.print("Digite sua senha: ");
            senha = scanner.nextLine().trim(); // Remove espaços em branco

            // Verifica se a senha não está vazia após trim()
            if (senha.isEmpty()) {
                System.out.println("A senha não pode estar vazia ou conter apenas espaços.");
                continue; // Volta para o início do loop
            }

            System.out.print("Confirme sua senha: ");
            confirmarSenha = scanner.nextLine().trim(); // Remove espaços em branco

            // Verifica se a senha de confirmação não está vazia após trim()
            if (confirmarSenha.isEmpty()) {
                System.out.println("A confirmação da senha não pode estar vazia ou conter apenas espaços.");
                continue; // Volta para o início do loop
            }

            if (!senha.equals(confirmarSenha)) {
                System.out.println("As senhas não correspondem. Tente novamente.");
            }
        } while (!senha.equals(confirmarSenha)); // Continua pedindo até que as senhas coincidam
        return senha; // Retorna a senha válida
    }
}