package src;

import java.sql.SQLException;
import src.barbearia.Barbearia;
import src.barbearia.Cliente;
import src.barbearia.MenuBarbearia;
import src.barbearia.MenuCliente;
import src.barbearia.Validador;
import src.db.Banco;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Banco db = new Banco();
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = new Cliente();
        Barbearia barbearia = new Barbearia();
        MenuCliente menuCliente = new MenuCliente(db);
        MenuBarbearia menuBarbearia = new MenuBarbearia(db);
        String nome, email, senha, telefone, cnpj, cpf, endereco;
        int opcao, anoAbertura;

        do {
            System.out.println("-----------------------------");
            System.out.println("SISTEMA DE RESERVA EM BARBEARIA");
            System.out.println("BEM-VINDO!");
            System.out.println("1. LOGIN CLIENTE\n2. LOGIN BARBEARIA\n3. NOVO CLIENTE\n4. NOVA BARBEARIA\n0. SAIR");
            System.out.println("-----------------------------");
            System.out.println("Digite uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                case 1:
                    menuCliente.exibirMenu();
                    break;
                case 2:
                    menuBarbearia.exibirMenu();

                    break;
                case 3:
                    System.out.println("INSIRA OS DADOS PARA CADASTRO");
                    cpf = Validador.obterEntradaNumericaValida(scanner, "Digite seu CPF: ");
                    nome = Validador.obterEntradaValida(scanner, "Digite seu nome: ");
                    email = Validador.obterEntradaValida(scanner, "Digite seu e-mail: ");
                    senha = Validador.obterSenhaValida(scanner);
                    telefone = Validador.obterEntradaNumericaValida(scanner, "Digite seu telefone: ");
                    cliente.setCpf(cpf);
                    cliente.setNome(nome);
                    cliente.setSenha(senha);
                    cliente.setEmail(email);
                    cliente.setTelefone(telefone);
                    cliente.cadastrarCliente(db);
                    System.out.println("Cliente cadastrado");
                    break;
                case 4:
                    System.out.println("INSIRA OS DADOS PARA CADASTRO");
                    cnpj = Validador.obterEntradaNumericaValida(scanner, "Digite seu CNPJ: ");
                    nome = Validador.obterEntradaValida(scanner, "Digite seu nome: ");
                    senha = Validador.obterSenhaValida(scanner);
                    anoAbertura = Validador.obterIntValido(scanner, "Digite o Digite o ano de abertura:");
                    endereco = Validador.obterEntradaValida(scanner, "Digite seu endereço: ");
                    email = Validador.obterEntradaValida(scanner, "Digite o email:");
                    barbearia.setCnpj(cnpj);
                    barbearia.setNome(nome);
                    barbearia.setSenha(senha);
                    barbearia.setAnoAbertura(anoAbertura);
                    barbearia.setEndereco(endereco);
                    barbearia.setEmail(email);
                    barbearia.cadastrarBarbearia(db);
                    System.out.println("Barbearia cadastrada");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (true);
    }
}