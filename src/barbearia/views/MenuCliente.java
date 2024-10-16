package src.barbearia.views;

import java.sql.Date;
import java.sql.SQLException;
import src.barbearia.controllers.Validador;
import src.barbearia.models.Barbearia;
import src.barbearia.models.Cliente;
import src.barbearia.models.Reserva;
import src.barbearia.models.Servico;
import src.database.Banco;
import java.util.Scanner;

public class MenuCliente {

    private Scanner scanner;
    private Banco db;
    private Cliente cliente;
    private Barbearia barbearia;
    private Servico servico;
    private Reserva reserva;

    public MenuCliente(Banco db) {
        this.scanner = new Scanner(System.in);
        this.db = db;
        this.cliente = new Cliente();
        this.barbearia = new Barbearia();
        this.servico = new Servico();
        this.reserva = new Reserva();
    }

    public void exibirMenu() throws SQLException {

        String nome, email, senha, telefone, cnpj, pagamento, servicos, cpf, confirma;
        Date dataHora;
        int opcao, id;

        cpf = Validador.obterEntradaValida(scanner, "Digite o seu CPF: ");
        cliente.setCpf(cpf);
        cliente.pesquisarCliente(db, cpf);
        senha = Validador.obterEntradaValida(scanner, "Digite a sua senha: ");
        if (cliente.usuarioLogin(senha)) {
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
                opcao = Validador.obterIntValido(scanner, "Digite a opção desejada: ");

                switch (opcao) {
                    case 1:
                        System.out.println("Opção 1 selecionada: Atualizar perfil");
                        nome = Validador.obterEntradaValida(scanner, "Digite o novo nome: ");
                        senha = Validador.obterSenhaValida(scanner);
                        email = Validador.obterEntradaValida(scanner, "Digite o novo email: ");
                        telefone = Validador.obterEntradaNumericaValida(scanner, "Digite o novo telefone: ");
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
                        cnpj = Validador.obterEntradaValida(scanner, "Digite o CNPJ da barbearia: ");
                        dataHora = Validador.converterData(scanner);
                        while (Reserva.pesquisarReservaNoDia(db, dataHora, cnpj)) {
                            System.out.println("Data ocupada. Tente outra data.");
                            dataHora = Validador.converterData(scanner);
                        }
                        System.out.println("Data disponível para a reserva!");
                        pagamento = Validador.obterEntradaValida(scanner, "Método de pagamento [Pix] ou [Cartão]: ");
                        servico.listarServicos(db, cnpj);
                        servicos = Validador.obterEntradaValida(scanner, "Digite o nome do serviço: ");
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
                        id = Validador.obterIntValido(scanner, "Digite o ID da sua reserva: ");
                        reserva.pesquisarReserva(db, id, cliente.getCpf());
                        break;
                    case 4:
                        System.out.println("Opção 4 selecionada: Minhas Reservas");
                        reserva.listarReservas(db, cliente.getCpf());
                        break;
                    case 5:
                        System.out.println("Opção 3 selecionada: Cancelar reserva");
                        reserva.listarReservas(db, cliente.getCpf());
                        id = Validador.obterIntValido(scanner, "Digite o ID da sua reserva: ");
                        reserva.setId(id);
                        reserva.removerReserva(db, cliente.getCpf());
                        break;
                    case 6:
                        System.out.println("Opção 11 selecionada: Remover perfil");
                        System.out.print("Você tem certeza que deseja remover o perfil? (s/n): ");
                        confirma = scanner.nextLine().trim().toLowerCase();
                        if (confirma.equals("s")) {
                            cliente.removerCliente(db);
                            System.out.println("APAGANDO DADOS...");
                            System.exit(0);
                        } else {
                            System.out.println("Ação cancelada. O perfil não foi removido.");
                        }
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 0);
        }
    }
}