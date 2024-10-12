package src;

import java.sql.Date;
import java.sql.SQLException;
import src.db.Banco;
import src.restaurante.Cliente;
import src.restaurante.Mesa;
import src.restaurante.Reserva;
import src.restaurante.Restaurante;
import java.util.Scanner;

public class Main { 
    public static void main(String[] args) throws SQLException {
        Banco db = new Banco();
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = new Cliente();
        Restaurante restaurante = new Restaurante();
        Mesa mesa = new Mesa();
        Reserva reserva = new Reserva();
        String nome, email, senha, telefone, dataString, metodoPagamento, cnpj, cpf, numeroMesa, endereco;
        int opcao, id, quantidadePessoas, anoAbertura, numero, numeroMesaAtt;
        float valor;
        Date data;

        do{
            System.out.println("-----------------------------");
            System.out.println("SISTEMA DE RESERVA DE MESAS");
            System.out.println("BEM-VINDO!");
            System.out.println("1. LOGIN CLIENTE\n2. LOGIN RESTAURANTE\n3. NOVO CLIENTE\n4. NOVO RESTAURANTE\n0. SAIR");
            System.out.println("-----------------------------");
            System.out.println("Digite uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao){
                case 0:
                    System.out.println("Saindo...");
                    System.exit(0);
                case 1:
                    System.out.println("Digite o seu CPF: ");
                    cpf = scanner.nextLine();
                    cliente.setCpf(cpf);
                    cliente.pesquisarCliente(db, cpf);
                    System.out.println("Digite a sua senha: ");
                    senha = scanner.nextLine();
                    if (cliente.usuarioLogin(senha)){
                        while (true) {
                            menuCliente();
                            System.out.println("Digite uma opção: ");
                            opcao = scanner.nextInt();
                            scanner.nextLine();
                            switch (opcao) {
                                case 0:
                                    break;
                                case 1:
                                    System.out.println("ATUALIZAR DADOS");
                                    System.out.println("Digite o novo nome: ");
                                    nome = scanner.nextLine();
                                    cliente.setNome(nome);
                                    System.out.println("Digite o novo email: ");
                                    email = scanner.nextLine();
                                    cliente.setEmail(email);
                                    System.out.println("Digite a nova senha: ");
                                    senha = scanner.nextLine();
                                    cliente.setSenha(senha);
                                    System.out.println("Digite a nova telefone: ");
                                    telefone = scanner.nextLine();
                                    cliente.setTelefone(telefone);
                                    cliente.editarCliente(db);
                                    System.out.println("Dados atualizados");
                                    break;
                                case 2:
                                    System.out.println("CRIAR NOVA RESERVA");
                                    System.out.println("Informe a data no formato AAAA-MM-DD: ");
                                    dataString = scanner.nextLine();
                                    data = Date.valueOf(dataString);
                                    reserva.setDataReserva(data);
                                    System.out.println("Quantidade de pessoas: ");
                                    quantidadePessoas = scanner.nextInt();
                                    scanner.nextLine();
                                    reserva.setQuantidadePessoas(quantidadePessoas);
                                    valor = quantidadePessoas * 10;
                                    reserva.setValor(valor);
                                    System.out.println("Metodo de pagamento: ");
                                    metodoPagamento = scanner.nextLine();
                                    reserva.setMetodoPagamento(metodoPagamento);
                                    restaurante.listarRestaurantes(db);
                                    System.out.println("Informe o CNPJ do Restaurante escolhido: ");
                                    cnpj = scanner.nextLine();
                                    reserva.setIdRestaurante(cnpj);
                                    mesa.listarMesas(db, cnpj);
                                    System.out.println("Digite o numero da mesa: ");
                                    numeroMesa = scanner.nextLine();
                                    while (Reserva.pesquisarReservaNoDia(db, data, numeroMesa, cnpj)) {
                                        System.out.println("Mesa ocupada para a data especificada. Digite outro número de mesa: ");
                                        numeroMesa = scanner.nextLine();
                                    }
                                    System.out.println("Mesa disponível para a data!");
                                    reserva.setIdCliente(cliente.getCpf());
                                    reserva.setIdMesa(numeroMesa);
                                    reserva.cadastrarReserva(db);
                                    System.out.println("Reserva cadastrada");
                                    break;
                                case 3:
                                    System.out.println("ATUALIZAR RESERVA");
                                    reserva.setIdCliente(cliente.getCpf());
                                    reserva.listarReservas(db);
                                    System.out.println("Digite o ID da reserva a ser atualizada: ");
                                    id = scanner.nextInt();
                                    scanner.nextLine();
                                    reserva.setId(id);
                                    System.out.println("Informe a data no formato AAAA-MM-DD: ");
                                    dataString = scanner.nextLine();
                                    data = Date.valueOf(dataString);
                                    reserva.setDataReserva(data);
                                    System.out.println("Quantidade de pessoas: ");
                                    quantidadePessoas = scanner.nextInt();
                                    scanner.nextLine();
                                    reserva.setQuantidadePessoas(quantidadePessoas);
                                    valor = quantidadePessoas * 10;
                                    reserva.setValor(valor);
                                    System.out.println("Metodo de pagamento: ");
                                    metodoPagamento = scanner.nextLine();
                                    reserva.setMetodoPagamento(metodoPagamento);
                                    restaurante.listarRestaurantes(db);
                                    System.out.println("Informe o CNPJ do Restaurante escolhido: ");
                                    cnpj = scanner.nextLine();
                                    reserva.setIdRestaurante(cnpj);
                                    mesa.listarMesas(db, cnpj);
                                    System.out.println("Digite o numero da mesa: ");
                                    numeroMesa = scanner.nextLine();
                                    while (Reserva.pesquisarReservaNoDia(db, data, numeroMesa, cnpj)) {
                                        System.out.println("Mesa ocupada para a data especificada. Digite outro número de mesa: ");
                                        numeroMesa = scanner.nextLine();
                                    }
                                    System.out.println("Mesa disponível para a data!");
                                    reserva.setIdCliente(cliente.getCpf());
                                    reserva.setIdMesa(numeroMesa);
                                    reserva.editarReserva(db);
                                    System.out.println("Reserva atualizada");
                                    break;
                                case 4:
                                    System.out.println("CANCELAR RESERVA");
                                    reserva.listarReservas(db);
                                    System.out.println("Informe o ID: ");
                                    id = scanner.nextInt();
                                    scanner.nextLine();
                                    reserva.setId(id);
                                    reserva.removerReserva(db);
                                    System.out.println("Reserva cancelada");
                                    break;
                                case 5:
                                    System.out.println("BUSCAR RESERVA");
                                    System.out.println("Informe o ID: ");
                                    id = scanner.nextInt();
                                    scanner.nextLine();
                                    reserva.pesquisarReserva(db, id);
                                    break;
                                case 6:
                                    cliente.removerCliente(db);
                                    System.out.println("APAGANDO DADOS...");
                                    System.exit(0);
                                    break;
                                default:
                                    System.out.println("OPÇÃO INVÁLIDA");
                                    break;
                            }
                            if (opcao == 0) {
                                System.out.println("\nRetornando ao menu principal...");
                                break;
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("Digite o seu CNPJ: ");
                    cnpj = scanner.nextLine();
                    restaurante.setCnpj(cnpj);
                    restaurante.pesquisarRestaurante(db, cnpj);
                    System.out.println("Digite a sua senha:");
                    senha = scanner.nextLine();
                    if (restaurante.usuarioLogin(senha)){
                        while (true) {
                            menuRestaurante();
                            System.out.println("Digite uma opção: ");
                            opcao = scanner.nextInt();
                            scanner.nextLine();
                            switch (opcao) {
                                case 0:
                                    break;
                                case 1:
                                    System.out.println("BUSCAR CLIENTE");
                                    System.out.println("Informe o CPF do cliente: ");
                                    cpf = scanner.nextLine();
                                    cliente.pesquisarCliente(db, cpf);
                                    System.out.println(cliente.toString());
                                    break;
                                case 2:
                                    System.out.println("LISTA DE CLIENTES");
                                    cliente.listarClientes(db);
                                    break;
                                case 3:
                                    System.out.println("CADASTRAR NOVA MESA");
                                    System.out.println("Numero da mesa:");
                                    numero = scanner.nextInt();
                                    scanner.nextLine();
                                    mesa.setNumeroMesa(numero);
                                    mesa.setIdRestaurante(restaurante.getCnpj());
                                    mesa.cadastrarMesa(db);
                                    System.out.println("Mesa cadastrada");
                                    break;
                                case 4:
                                    System.out.println("ATUALIZAR MESA");
                                    System.out.println("Informe o numero da mesa a ser atualizada: ");
                                    numero = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println("Informe o novo número: ");
                                    numeroMesaAtt = scanner.nextInt();
                                    scanner.nextLine();
                                    mesa.setNumeroMesa(numeroMesaAtt);
                                    mesa.editarMesa(db, restaurante.getCnpj(), numero);
                                    System.out.println("Mesa atualizada");
                                    break;
                                case 5:
                                    System.out.println("BUSCAR MESA");
                                    System.out.println("Informe o numero da mesa: ");
                                    numeroMesa = scanner.nextLine();
                                    mesa.pesquisarMesa(db, numeroMesa, mesa.getIdRestaurante());
                                    break;
                                case 6:
                                    System.out.println("LISTA DE MESAS");
                                    mesa.listarMesas(db, cnpj);
                                    break;
                                case 7:
                                    System.out.println("REMOVER MESA");
                                    System.out.println("Informe o numero da mesa a ser removida:");
                                    numero = scanner.nextInt();
                                    scanner.nextLine();
                                    mesa.setNumeroMesa(numero);
                                    mesa.removerMesa(db, restaurante.getCnpj());
                                    System.out.println("Mesa removida");
                                    break;
                                case 8:
                                    System.out.println("ATUALIZAR DADOS DO RESTAURANTE");
                                    System.out.println("Digite o novo nome: ");
                                    nome = scanner.nextLine();
                                    restaurante.setNome(nome);
                                    System.out.println("Digite o endereco");
                                    endereco = scanner.nextLine();
                                    restaurante.setEndereco(endereco);
                                    System.out.println("Digite a nova senha: ");
                                    senha = scanner.nextLine();
                                    restaurante.setSenha(senha);
                                    System.out.println("Digite o email: ");
                                    email = scanner.nextLine();
                                    restaurante.setEmail(email);
                                    restaurante.editarRestaurante(db);
                                    System.out.println("Dados atualizados");
                                    break;
                                case 9:
                                    System.out.println("CADASTRAR NOVA RESERVA");
                                    System.out.println("Informe a data no formato AAAA-MM-DD: ");
                                    dataString = scanner.nextLine();
                                    data = Date.valueOf(dataString);
                                    reserva.setDataReserva(data);
                                    System.out.println("Quantidade de pessoas: ");
                                    quantidadePessoas = scanner.nextInt();
                                    scanner.nextLine();
                                    reserva.setQuantidadePessoas(quantidadePessoas);
                                    valor = quantidadePessoas * 10;
                                    reserva.setValor(valor);
                                    System.out.println("Metodo de pagamento: ");
                                    metodoPagamento = scanner.nextLine();
                                    reserva.setMetodoPagamento(metodoPagamento);
                                    reserva.setIdRestaurante(restaurante.getCnpj());
                                    System.out.println("Informe o CPF do Cliente: ");
                                    cpf = scanner.nextLine();
                                    reserva.setIdCliente(cpf);
                                    mesa.listarMesas(db, cnpj);
                                    System.out.println("Digite o numero da mesa: ");
                                    numeroMesa = scanner.nextLine();
                                    while (Reserva.pesquisarReservaNoDia(db, data, numeroMesa, restaurante.getCnpj())) {
                                        System.out.println("Mesa ocupada para a data especificada. Digite outro número de mesa: ");
                                        numeroMesa = scanner.nextLine();
                                    }
                                    System.out.println("Mesa disponível para a data!");
                                    reserva.setIdMesa(numeroMesa);
                                    reserva.cadastrarReserva(db);  
                                    System.out.println("Reserva cadastrada");                             
                                    break;
                                case 10:
                                    System.out.println("ATUALIZAR RESERVA");
                                    reserva.listarReservas(db);
                                    System.out.println("Digite o ID da reserva a ser atualizada");
                                    id = scanner.nextInt();
                                    scanner.nextLine();
                                    reserva.setId(id);
                                    System.out.println("Informe a data no formato AAAA-MM-DD: ");
                                    dataString = scanner.nextLine();
                                    data = Date.valueOf(dataString);
                                    reserva.setDataReserva(data);
                                    System.out.println("Quantidade de pessoas: ");
                                    quantidadePessoas = scanner.nextInt();
                                    scanner.nextLine();
                                    reserva.setQuantidadePessoas(quantidadePessoas);
                                    valor = quantidadePessoas * 10;
                                    reserva.setValor(valor);
                                    System.out.println("Metodo de pagamento: ");
                                    metodoPagamento = scanner.nextLine();
                                    reserva.setMetodoPagamento(metodoPagamento);
                                    restaurante.listarRestaurantes(db);
                                    System.out.println("Informe o CPF do Cliente: ");
                                    cpf = scanner.nextLine();
                                    cliente.setCpf(cpf);
                                    mesa.listarMesas(db, cnpj);
                                    System.out.println("Digite o numero da mesa: ");
                                    numeroMesa = scanner.nextLine();
                                    while (Reserva.pesquisarReservaNoDia(db, data, numeroMesa, restaurante.getCnpj())) {
                                        System.out.println("Mesa ocupada para a data especificada. Digite outro número de mesa: ");
                                        numeroMesa = scanner.nextLine();
                                        }
                                    System.out.println("Mesa disponível para a data!");
                                    reserva.setIdMesa(numeroMesa);
                                    reserva.editarReserva(db);
                                    System.out.println("Reserva atualizada");
                                    break;
                                case 11:
                                    System.out.println("BUSCAR RESERVA");
                                    System.out.println("Informe o ID da reserva: ");
                                    id = scanner.nextInt();
                                    scanner.nextLine();
                                    reserva.pesquisarReserva(db, id);
                                    break;
                                case 12:
                                    System.out.println("LISTA DE RESERVAS");
                                    reserva.listarReservas(db);
                                    break;
                                case 13:
                                    System.out.println("CANCELAR RESERVA");
                                    reserva.listarReservas(db);
                                    System.out.println("Informe o ID da reserva a ser cancelada: ");
                                    id = scanner.nextInt();
                                    scanner.nextLine();
                                    reserva.setId(id);
                                    reserva.removerReserva(db);
                                    System.out.println("Reserva cancelada");
                                    break;
                                case 14:
                                    restaurante.removerRestaurante(db);
                                    System.out.println("APAGANDO DADOS...");
                                    System.exit(0);
                                    break;
                                default:
                                    System.out.println("Opção inválida");
                                    break;
                            }
                            if (opcao == 0) {
                                System.out.println("\nRetornando ao menu principal...");
                                break;
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("INSIRA OS DADOS PARA CADASTRO");
                    System.out.println("Digite o nome: ");
                    nome = scanner.nextLine();
                    cliente.setNome(nome);
                    System.out.println("Digite o CPF:");
                    cpf = scanner.nextLine();
                    cliente.setCpf(cpf);
                    System.out.println("Digite o email: ");
                    email = scanner.nextLine();
                    cliente.setEmail(email);
                    System.out.println("Digite a senha: ");
                    senha = scanner.nextLine();
                    cliente.setSenha(senha);
                    System.out.println("Digite o telefone: ");
                    telefone = scanner.nextLine();
                    cliente.setTelefone(telefone);
                    cliente.cadastrarCliente(db);
                    System.out.println("Cliente cadastrado");
                    break;
                case 4: 
                    System.out.println("INSIRA OS DADOS PARA CADASTRO");
                    System.out.println("Digite o nome:");
                    nome = scanner.nextLine();
                    restaurante.setNome(nome);
                    System.out.println("Digite o CNPJ:");
                    cnpj = scanner.nextLine();
                    restaurante.setCnpj(cnpj);
                    System.out.println("Informe o ano de abertura:");
                    anoAbertura = scanner.nextInt();
                    scanner.nextLine();
                    restaurante.setAnoAbertura(anoAbertura);
                    System.out.println("Digite o endereco:");
                    endereco = scanner.nextLine();
                    restaurante.setEndereco(endereco);
                    System.out.println("Digite a senha:");
                    senha = scanner.nextLine();
                    restaurante.setSenha(senha);
                    System.out.println("Digite o email:");
                    email = scanner.nextLine();
                    restaurante.setEmail(email);
                    restaurante.cadastrarRestaurante(db);
                    System.out.println("Restaurante cadastrado");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }while (true);
    }

    public static void menuCliente() {
        System.out.println("------------------------");
        System.out.println("1. ATUALIZAR DADOS");
        System.out.println("2. NOVA RESERVA");
        System.out.println("3. ATUALIZAR RESERVA");
        System.out.println("4. CANCELAR RESERVA");
        System.out.println("5. BUSCAR RESERVA");
        System.out.println("6. DELETAR CONTA");
        System.out.println("0. SAIR");
        System.out.println("------------------------");        
    }

    public static void menuRestaurante() {
        System.out.println("------------------------");
        System.out.println("1. BUSCAR CLIENTE");
        System.out.println("2. LISTAR CLIENTES");
        System.out.println("3. CADASTRAR MESA");
        System.out.println("4. ATUALIZAR MESA");
        System.out.println("5. BUSCAR MESA");
        System.out.println("6. LISTAR MESAS");
        System.out.println("7. REMOVER MESA");
        System.out.println("8. ATUALIZAR RESTAURANTE");
        System.out.println("9. CADASTRAR RESERVA");
        System.out.println("10. ATUALIZAR RESERVA");
        System.out.println("11. BUSCAR RESERVA");
        System.out.println("12. LISTAR RESERVA");
        System.out.println("13. CANCELAR RESERVA");
        System.out.println("14. EXCLUIR RESTAURANTE");
        System.out.println("0. SAIR");
        System.out.println("------------------------");        
    }
}