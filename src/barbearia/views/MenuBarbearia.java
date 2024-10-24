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
import java.time.LocalTime;

public class MenuBarbearia {

    private Scanner scanner;
    private Banco db;
    private Cliente cliente;
    private Barbearia barbearia;
    private Servico servico;
    private Reserva reserva;

    public MenuBarbearia(Banco db) {
        this.scanner = new Scanner(System.in);
        this.db = db;
        this.cliente = new Cliente();
        this.barbearia = new Barbearia();
        this.servico = new Servico();
        this.reserva = new Reserva();
    }

    public void exibirMenu() throws SQLException {
        String cnpj, cpf, nome, email, senha, endereco, pagamento, tipoServico, confirma;
        float valor;
        Date data;
        LocalTime hora;
        int opcao, opcaoData, id;
        boolean reservaConfirmada;

        cnpj = Validador.obterEntradaValida(scanner, "Digite seu CNPJ: ");
        barbearia.setCnpj(cnpj);
        barbearia.pesquisarBarbearia(db, cnpj);
        senha = Validador.obterEntradaValida(scanner, "Digite sua senha: ");

        if (barbearia.usuarioLogin(senha)) {
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
                System.out.println("0. VOLTAR");
                System.out.println("------------------------");
                opcao = Validador.obterIntValido(scanner, "Digite a opção desejada: ");

                if (opcao == 0) {
                    System.out.println("Voltando ao menu principal...");
                    return; // Retorna ao menu principal
                }

                switch (opcao) {
                    case 1:
                        System.out.println("Opção 1 selecionada: Atualizar perfil");
                        nome = Validador.obterEntradaValida(scanner, "Digite o novo nome: ");
                        email = Validador.obterEntradaValida(scanner, "Digite o novo email: ");
                        senha = Validador.obterSenhaValida(scanner);
                        endereco = Validador.obterEntradaValida(scanner, "Digite o novo endereço: ");
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
                        cpf = Validador.obterEntradaNumericaValida(scanner, "Digite o CPF do cliente: ");
                        reservaConfirmada = false; // Variável para controlar a confirmação da reserva
                        while (!reservaConfirmada) {
                            data = Validador.converterData(scanner);
                            hora = Validador.converterHora(scanner);

                            opcaoData = 0;

                            while (Reserva.pesquisarReservaNoDia(db, data, barbearia.getCnpj(), hora)) {
                                System.out
                                        .println("Horário indisponível. Tente outro horário ou escolha uma nova data.");
                                System.out
                                        .println("Deseja alterar apenas o horário (1) ou escolher uma nova data (2)?");
                                opcaoData = Validador.obterIntValido(scanner, "Digite a opção: ");
                                if (opcaoData == 2) {
                                    break;
                                } else if (opcaoData == 1) {
                                    hora = Validador.converterHora(scanner);
                                } else {
                                    System.out.println("Opção inválida!");
                                }
                            }

                            if (opcaoData == 2) {
                                continue; // Reinicia o loop principal para pedir nova data
                            }

                            System.out.println("Data e horário disponíveis para a reserva!");
                            pagamento = Validador.obterEntradaValida(scanner,
                                    "Método de pagamento [Pix] ou [Cartão]: ");
                            servico.listarServicos(db, barbearia.getCnpj());
                            tipoServico = Validador.obterEntradaValida(scanner, "Digite o nome do serviço: ");
                            reserva.setIdCliente(cpf);
                            reserva.setDataReserva(data);
                            reserva.setMetodoPagamento(pagamento);
                            reserva.setIdBarbearia(barbearia.getCnpj());
                            reserva.setIdServico(tipoServico);
                            reserva.setHoraInicio(hora);
                            reserva.cadastrarReserva(db);
                            System.out.println("Reserva cadastrada");
                            reservaConfirmada = true; // Reserva foi confirmada
                        }
                        break;
                    case 3:
                        System.out.println("Opção 3 selecionada: Atualizar reserva");
                        cpf = Validador.obterEntradaNumericaValida(scanner, "Digite o CPF do cliente: ");
                        reserva.listarReservas(db, cpf);
                        id = Validador.obterIntValido(scanner, "Digite o ID da reserva: ");
                        reservaConfirmada = false;
                        while (!reservaConfirmada) {
                            data = Validador.converterData(scanner);
                            hora = Validador.converterHora(scanner);

                            opcaoData = 0;

                            while (Reserva.pesquisarReservaNoDia(db, data, barbearia.getCnpj(), hora)) {
                                System.out
                                        .println("Horário indisponível. Tente outro horário ou escolha uma nova data.");
                                System.out
                                        .println("Deseja alterar apenas o horário (1) ou escolher uma nova data (2)?");
                                opcaoData = Validador.obterIntValido(scanner, "Digite a opção: ");
                                if (opcaoData == 2) {
                                    break;
                                } else if (opcaoData == 1) {
                                    hora = Validador.converterHora(scanner);
                                } else {
                                    System.out.println("Opção inválida!");
                                }
                            }

                            if (opcaoData == 2) {
                                continue;
                            }

                            System.out.println("Data e horário disponíveis para a reserva!");
                            pagamento = Validador.obterEntradaValida(scanner,
                                    "Método de pagamento [Pix] ou [Cartão]: ");
                            servico.listarServicos(db, barbearia.getCnpj());
                            tipoServico = Validador.obterEntradaValida(scanner, "Digite o nome do serviço: ");
                            reserva.setIdCliente(cpf);
                            reserva.setDataReserva(data);
                            reserva.setMetodoPagamento(pagamento);
                            reserva.setIdBarbearia(barbearia.getCnpj());
                            reserva.setIdServico(tipoServico);
                            reserva.setHoraInicio(hora);
                            reserva.editarReserva(db);
                            System.out.println("Reserva atualizada");
                            reservaConfirmada = true; // Reserva foi confirmada
                        }
                        break;
                    case 4:
                        System.out.println("Opção 4 selecionada: Buscar reserva");
                        id = Validador.obterIntValido(scanner, "Digite o ID da reserva: ");
                        reserva.pesquisarReserva(db, id, barbearia.getCnpj());
                        break;
                    case 5:
                        System.out.println("Opção 5 selecionada: Cancelar reserva");
                        reserva.listarReservas(db, barbearia.getCnpj());
                        id = Validador.obterIntValido(scanner, "Digite o ID da sua reserva: ");
                        reserva.setId(id);
                        reserva.removerReserva(db, barbearia.getCnpj());
                        break;
                    case 6:
                        System.out.println("Opção 6 selecionada: Listar reservas");
                        reserva.listarReservas(db, barbearia.getCnpj());
                        break;
                    case 7:
                        System.out.println("Opção 7 selecionada: Cadastrar serviços");
                        nome = Validador.obterEntradaValida(scanner, "Digite o nome: ");
                        valor = Validador.obterFloatValido(scanner, "Digite o valor: ");
                        barbearia.setCnpj(barbearia.getCnpj());
                        servico.setNomeServico(nome);
                        servico.setValorServico(valor);
                        servico.setIdBarbearia(barbearia.getCnpj());
                        servico.cadastrarServico(db);
                        System.out.println("Serviço cadastrado");
                        break;
                    case 8:
                        System.out.println("Opção 8 selecionada: Atualizar serviços");
                        servico.listarServicos(db, cnpj);
                        boolean servicoEncontrado = false;
                        tipoServico = null;
                        // Loop até que um serviço válido seja encontrado
                        while (!servicoEncontrado) {
                            tipoServico = Validador.obterEntradaValida(scanner, "Digite o nome do serviço: ");
                            try {
                                servico.pesquisarServico(db, tipoServico, cnpj);
                                servicoEncontrado = true; // Se o serviço foi encontrado, sai do loop
                            } catch (SQLException e) {
                                System.out.println("Erro ao pesquisar o serviço: " + e.getMessage());
                            }
                        }
                        nome = Validador.obterEntradaValida(scanner, "Digite o novo nome do serviço: ");
                        valor = Validador.obterFloatValido(scanner, "Digite o valor: ");
                        servico.setNomeServico(nome);
                        servico.setValorServico(valor);
                        servico.setIdBarbearia(barbearia.getCnpj());
                        servico.editarServico(db, barbearia.getCnpj(), tipoServico);
                        System.out.println("Serviço atualizado");
                        break;
                    case 9:
                        System.out.println("Opção 9 selecionada: Listar serviços");
                        servico.listarServicos(db, barbearia.getCnpj());
                        break;
                    case 10:
                        System.out.println("Opção 10 selecionada: Remover serviço");
                        servico.listarServicos(db, barbearia.getCnpj());
                        nome = Validador.obterEntradaValida(scanner, "Digite o nome do serviço: ");
                        servico.setNomeServico(nome);
                        servico.removerServico(db, barbearia.getCnpj());
                        System.out.println("Serviço removido");
                        break;
                    case 11:
                        System.out.println("Opção 11 selecionada: Remover perfil");
                        System.out.print("Você tem certeza que deseja remover o perfil? (s/n): ");
                        confirma = scanner.nextLine().trim().toLowerCase();
                        if (confirma.equals("s")) {
                            barbearia.removerBarbearia(db);
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