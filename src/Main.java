package src;

import java.sql.SQLException;
import src.barbearia.Barbearia;
import src.barbearia.Cliente;
import src.barbearia.MenuBarbearia;
import src.barbearia.MenuCliente;
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
                    barbearia.setNome(nome);
                    System.out.println("Digite o CNPJ:");
                    cnpj = scanner.nextLine();
                    barbearia.setCnpj(cnpj);
                    System.out.println("Informe o ano de abertura:");
                    anoAbertura = scanner.nextInt();
                    scanner.nextLine();
                    barbearia.setAnoAbertura(anoAbertura);
                    System.out.println("Digite o endereco:");
                    endereco = scanner.nextLine();
                    barbearia.setEndereco(endereco);
                    System.out.println("Digite a senha:");
                    senha = scanner.nextLine();
                    barbearia.setSenha(senha);
                    System.out.println("Digite o email:");
                    email = scanner.nextLine();
                    barbearia.setEmail(email);
                    barbearia.cadastrarBarbearia(db);
                    System.out.println("Restaurante cadastrado");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (true);
    }
}