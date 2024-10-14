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
    public MenuBarbearia(Banco db) {
        this.scanner = new Scanner(System.in);
        this.db = db;
        this.cliente = new Cliente();
        this.barbearia = new Barbearia();
        this.servico = new Servico();
        this.reserva = new Reserva();
    }

    // Método que exibe o menu e trata as opções
    public void exibirMenu() throws SQLException {
        String cnpj, cpf, nome, email, senha, endereco, pagamento, servicos, confirma;
        float valor;
        Date dataHora;
        int opcao, id;

        cnpj = obterEntradaValida(scanner, "Digite seu CNPJ: ");
        barbearia.setCnpj(cnpj);
        barbearia.pesquisarBarbearia(db, cnpj);
        senha = obterEntradaValida(scanner, "Digite sua senha: ");

        if (barbearia.usuarioLogin(senha)) {
            System.out.println("Senha correta!");
            do {
                System.out.println("------------------------");
                System.out.println("1. ATUALIZAR PERFIL");
                System.out.println("2. CADASTRAR RESERVA");
                System.out.println("3. ATUALIZAR RESERVA");
                System.out.println("4. BUSCAR RESERVA");
                System.out.println("5. CANCELAR RESERVA");
                System.out.println("6. LISTAR RESERVAS");
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
                        nome = obterEntradaValida(scanner, "Digite o novo nome: ");
                        System.out.println("Digite o novo email: ");
                        email = obterEntradaValida(scanner, "Digite o novo email: ");
                        senha = obterSenhaValida(scanner);
                        endereco = obterEntradaValida(scanner, "Digite o novo endereço: ");
                        barbearia.setNome(nome);
                        barbearia.setEmail(email);
                        barbearia.setSenha(senha);
                        barbearia.setEndereco(endereco);
                        barbearia.editarBarbearia(db);
                        System.out.println("Dados atualizados!");
                        break;
                    case 2:
                        System.out.println("Opção 2 selecionada: Nova reserva");
                        cliente.listarClientes(db);
                        cpf = obterEntradaValida(scanner, "Digite o CPF do cliente: ");
                        dataHora = converterData(scanner);
                        while (Reserva.pesquisarReservaNoDia(db, dataHora, barbearia.getCnpj())) {
                            System.out.println("Data ocupada. Tente outra data.");
                            dataHora = converterData(scanner);
                        }
                        System.out.println("Data disponível para a reserva!");
                        pagamento = obterEntradaValida(scanner, "Método de pagamento [Pix] ou [Cartão]: ");
                        servico.listarServicos(db, barbearia.getCnpj());
                        servicos = obterEntradaValida(scanner, "Digite o nome do serviço: ");
                        cliente.setCpf(cpf);
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
                        pagamento = obterEntradaValida(scanner, "Método de pagamento [Pix] ou [Cartão]: ");
                        servico.listarServicos(db, barbearia.getCnpj());
                        servicos = obterEntradaValida(scanner, "Digite o nome do serviço: ");
                        reserva.setIdCliente(cliente.getCpf());
                        reserva.setDataReserva(dataHora);
                        reserva.setMetodoPagamento(pagamento);
                        reserva.setIdBarbearia(barbearia.getCnpj());
                        reserva.setIdServico(servicos);
                        reserva.cadastrarReserva(db);
                        System.out.println("Reserva cadastrada");
                        break; // Adicionado break aqui
                    case 4:
                        System.out.println("Opção 4 selecionada: Buscar reserva");
                        System.out.println("Informe o ID da reserva: ");
                        id = scanner.nextInt();
                        scanner.nextLine();
                        reserva.pesquisarReserva(db, id, barbearia.getCnpj());
                        break;
                    case 5:
                        System.out.println("Opção 5 selecionada: Cancelar reserva");
                        reserva.listarReservas(db, barbearia.getCnpj());
                        System.out.println("Informe o ID da reserva a ser cancelada: ");
                        id = scanner.nextInt();
                        scanner.nextLine();
                        reserva.setId(id);
                        reserva.removerReserva(db, barbearia.getCnpj());
                        System.out.println("Reserva cancelada");
                        break;
                    case 6:
                        System.out.println("Opção 6 selecionada: Listar reservas");
                        reserva.listarReservas(db, barbearia.getCnpj());
                        break;
                    case 7:
                        System.out.println("Opção 7 selecionada: Cadastrar serviços");
                        nome = obterEntradaValida(scanner, "Informe o nome: ");
                        System.out.println("Informe o valor: ");
                        valor = scanner.nextFloat();
                        scanner.nextLine();
                        barbearia.setCnpj(barbearia.getCnpj());
                        servico.setNomeServico(nome);
                        servico.setValorServico(valor);
                        servico.setIdBarbearia(barbearia.getCnpj());
                        servico.cadastrarServico(db);
                        System.out.println("Serviço atualizado");
                        break;
                    case 8:
                        System.out.println("Opção 8 selecionada: Atualizar serviços");
                        servicos = obterEntradaValida(scanner, "Informe o nome do serviço: ");
                        nome = obterEntradaValida(scanner, "Informe o novo nome do serviço: ");
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
                        nome = obterEntradaValida(scanner, "Informe o nome do serviço: ");
                        servico.setNomeServico(nome);
                        servico.removerServico(db, barbearia.getCnpj());
                        System.out.println("Serviço removido");
                        break;
                    case 11:
                        System.out.println("Opção 11 selecionada: Remover perfil");
                        System.out.print("Você tem certeza que deseja remover o perfil? (s/n): ");
                        confirma = scanner.nextLine().trim().toLowerCase(); // Ler a resposta do usuário
                        // Verifica a resposta do usuário
                        if (confirma.equals("s")) {
                            barbearia.removerBarbearia(db); // Chama o método para remover a barbearia
                            System.out.println("APAGANDO DADOS...");
                            System.exit(0); // Encerra o programa
                        } else {
                            System.out.println("Ação cancelada. O perfil não foi removido."); // Mensagem de
                                                                                              // cancelamento
                        }
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 0);
        }
    }

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