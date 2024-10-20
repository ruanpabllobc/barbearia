package src.barbearia.controllers;

import java.sql.Date;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validador {

    public static String obterCPFValido(Scanner scanner) {
        String cpf;
        do {
            System.out.print("Digite o CPF (somente números): ");
            cpf = scanner.nextLine().trim();

            // Remove quaisquer caracteres não numéricos
            String cpfNumeros = cpf.replaceAll("\\D", "");

            // Valida a quantidade de dígitos
            if (cpfNumeros.length() != 11) {
                System.out.println("CPF inválido! O CPF deve conter 11 dígitos numéricos.");
                cpf = null; // Define como null para continuar o loop
            }
        } while (cpf == null);

        return cpf;
    }

    public static String obterCNPJValido(Scanner scanner) {
        String cnpj;
        do {
            System.out.print("Digite o CNPJ (somente números): ");
            cnpj = scanner.nextLine().trim();

            // Remove quaisquer caracteres não numéricos
            String cnpjNumeros = cnpj.replaceAll("\\D", "");

            // Valida a quantidade de dígitos
            if (cnpjNumeros.length() != 14) {
                System.out.println("CNPJ inválido! O CNPJ deve conter 14 dígitos numéricos.");
                cnpj = null; // Define como null para continuar o loop
            }
        } while (cnpj == null);

        return cnpj;
    }

    // Obtém uma entrada válida do usuário
    public static String obterEntradaValida(Scanner scanner, String mensagem) {
        String entrada;
        do {
            System.out.print(mensagem);
            entrada = scanner.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("A entrada não pode ser vazia.");
            }
        } while (entrada.isEmpty());
        return entrada;
    }

    // Obtém uma senha válida
    public static String obterSenhaValida(Scanner scanner) {
        String senha = null;
        String confirmarSenha = null;
        do {
            System.out.print("Digite sua senha: ");
            senha = scanner.nextLine().trim();
            if (senha.isEmpty()) {
                System.out.println("A senha não pode estar vazia.");
                continue;
            }

            System.out.print("Confirme sua senha: ");
            confirmarSenha = scanner.nextLine().trim();
            if (confirmarSenha.isEmpty()) {
                System.out.println("A confirmação não pode estar vazia.");
                continue;
            }

            if (!senha.equals(confirmarSenha)) {
                System.out.println("As senhas não correspondem.");
            }
        } while (!senha.equals(confirmarSenha));
        return senha;
    }

    // Valida entrada numérica
    public static String obterEntradaNumericaValida(Scanner scanner, String mensagem) {
        String entrada;
        do {
            System.out.print(mensagem);
            entrada = scanner.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("A entrada não pode ser vazia.");
                continue;
            }
            if (!entrada.matches("\\d+")) {
                System.out.println("A entrada deve conter apenas números.");
            }
        } while (!entrada.matches("\\d+"));
        return entrada;
    }

    // Valida entrada inteira
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
                System.out.println("Entrada inválida.");
            }
        } while (!entradaValida);
        return numero;
    }

    // Valida entrada float
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
                System.out.println("Entrada inválida.");
            }
        } while (!entradaValida);
        return numero;
    }

    // Converte a data
    public static Date converterData(Scanner scanner) {
        Date data = null;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.println("Informe a data no formato AAAA-MM-DD: ");
                String dataString = scanner.nextLine();

                // Usando Date.valueOf() para converter a string em Date
                data = Date.valueOf(dataString);
                valido = true; // Se a conversão for bem-sucedida, sai do loop
            } catch (IllegalArgumentException e) {
                System.out.println("Data inválida. Por favor, use o formato AAAA-MM-DD.");
            }
        }

        return data;
    }

    // Converte a hora
    public static LocalTime converterHora(Scanner scanner) {
        LocalTime hora = null;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.println("Informe a hora no formato HH:MM: ");
                String horaString = scanner.nextLine();

                // Usando LocalTime.parse() para converter a string em LocalTime
                hora = LocalTime.parse(horaString, DateTimeFormatter.ofPattern("HH:mm"));
                valido = true; // Se a conversão for bem-sucedida, sai do loop
            } catch (DateTimeParseException e) {
                System.out.println("Hora inválida. Por favor, use o formato HH:MM.");
            }
        }

        return hora;
    }
}