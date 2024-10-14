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
                    nome = obterEntradaValida(scanner, "Digite seu nome: ");
                    cliente.setNome(nome);
                    cpf = obterEntradaValida(scanner, "Digite seu CPF: ");
                    cliente.setCpf(cpf);
                    email = obterEntradaValida(scanner, "Digite seu e-mail: ");
                    cliente.setEmail(email);
                    senha = obterSenhaValida(scanner);
                    cliente.setSenha(senha);
                    telefone = obterEntradaValida(scanner, "Digite seu telefone: ");
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