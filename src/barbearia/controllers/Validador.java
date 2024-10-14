package src.barbearia.controllers;

import java.sql.Date;
import java.util.Scanner;

public class Validador {

    // Método para obter uma entrada válida
    public static String obterEntradaValida(Scanner scanner, String mensagem) {
        String entrada;
        do {
            System.out.print(mensagem);
            entrada = scanner.nextLine().trim(); // Remove espaços em branco
            if (entrada.isEmpty()) {
                System.out.println("A entrada não pode ser vazia. Por favor, tente novamente.");
            }
        } while (entrada.isEmpty());
        return entrada;
    }

    // Método para obter uma senha válida
    public static String obterSenhaValida(Scanner scanner) {
        String senha = null;
        String confirmarSenha = null;
        do {
            System.out.print("Digite sua senha: ");
            senha = scanner.nextLine().trim();

            if (senha.isEmpty()) {
                System.out.println("A senha não pode estar vazia ou conter apenas espaços.");
                continue;
            }

            System.out.print("Confirme sua senha: ");
            confirmarSenha = scanner.nextLine().trim();

            if (confirmarSenha.isEmpty()) {
                System.out.println("A confirmação da senha não pode estar vazia ou conter apenas espaços.");
                continue;
            }

            if (!senha.equals(confirmarSenha)) {
                System.out.println("As senhas não correspondem. Tente novamente.");
            }
        } while (!senha.equals(confirmarSenha));
        return senha;
    }

    // Método para validar entrada numérica
    public static String obterEntradaNumericaValida(Scanner scanner, String mensagem) {
        String entrada;
        do {
            System.out.print(mensagem);
            entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("A entrada não pode ser vazia. Por favor, tente novamente.");
                continue;
            }

            if (!entrada.matches("\\d+")) {
                System.out.println("A entrada deve conter apenas números. Por favor, tente novamente.");
            }

        } while (!entrada.matches("\\d+"));
        return entrada;
    }

    // Método para validar entrada inteira
    public static int obterIntValido(Scanner scanner, String mensagem) {
        int numero = 0;
        boolean entradaValida = false;
        do {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();

            try {
                numero = Integer.parseInt(entrada);
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            }
        } while (!entradaValida);
        return numero;
    }

    // Método para validar entrada float
    public static float obterFloatValido(Scanner scanner, String mensagem) {
        float numero = 0;
        boolean entradaValida = false;
        do {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();

            try {
                numero = Float.parseFloat(entrada);
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número decimal.");
            }
        } while (!entradaValida);
        return numero;
    }

        // Método auxiliar para converter a data
    public static Date converterData(Scanner scanner) {
        System.out.println("Informe a data no formato AAAA-MM-DD: ");
        String dataString = scanner.nextLine();
        return Date.valueOf(dataString);
    }
}
