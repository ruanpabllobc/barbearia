package src.barbearia;

import java.sql.ResultSet;
import java.sql.SQLException;
import src.db.Banco;

public class Cliente extends Usuario {
    private String telefone;
    private String cpf;

    public Cliente(String nome, String senha, String email, String telefone, String cpf) {
        super(nome, senha, email);
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public Cliente() { // Construtor auxiliar
    }

    public void cadastrarCliente(Banco db) {
        String query = String.format(
                "INSERT INTO Cliente (nome, email, senha, telefone, cpf) VALUES ('%s', '%s', '%s', '%s', '%s')",
                getNome(), getEmail(), getSenha(), getTelefone(), getCpf()); // Insere um Get para cada coluna da tabela
                                                                             // cliente
        db.queryUpdate(query); // Realiza a atualização da tabela
    }

    public void pesquisarCliente(Banco db, String cpf) throws SQLException {
        String query = String.format("SELECT * FROM Cliente WHERE cpf = '%s'", cpf); // Pesquisa por um cliente no banco
                                                                                     // através do seu CPF
        ResultSet rs = db.querySearch(query); // Recebe o resultado da pesquisa

        setNome(rs.getString("nome")); // Atualiza os dados e imprime
        setCpf(rs.getString("cpf"));
        setSenha(rs.getString("senha"));
        setEmail(rs.getString("email"));
        setTelefone(rs.getString("telefone"));
    }

    public void listarClientes(Banco db) throws SQLException {
        String query = "SELECT * FROM Cliente"; // Seleciona os dados da tabela cliente
        ResultSet rs = db.querySearch(query); // Recebe e guarda os dados

        while (rs.next()) { // Avança para a próxima linha da lista
            setNome(rs.getString("nome")); // Atualiza e imprime os valores
            setCpf(rs.getString("cpf"));
            setSenha(rs.getString("senha"));
            setEmail(rs.getString("email"));
            setTelefone(rs.getString("telefone"));
            System.out.println(toString());
        }
    }

    public void editarCliente(Banco db) {
        String query = String.format(
                "UPDATE Cliente SET nome = '%s', email = '%s', senha = '%s', telefone = '%s' WHERE cpf = '%s'",
                getNome(), getEmail(), getSenha(), getTelefone(), getCpf()); // Edita um cliente identificando atraves
                                                                             // do CPF
        db.queryUpdate(query); // Realiza a atualização na tabela
    }

    public void removerCliente(Banco db) {
        String query = String.format("DELETE FROM Cliente WHERE cpf = '%s'", getCpf());
        db.queryUpdate(query); // Remove um cliente buscando pelo CPF na tabela
    }

    // Get e Set
    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // To String
    @Override
    public String toString() {
        return "Cliente{nome=" + getNome() + ", email=" + getEmail() + ", telefone=" + getTelefone() + ", cpf="
                + getCpf() + "}";
    }
}