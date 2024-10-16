package src.barbearia.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import src.database.Banco;

public class Cliente extends Usuario {
    private String telefone;
    private String cpf; 

    public Cliente(String nome, String senha, String email, String telefone, String cpf) {
        super(nome, senha, email);
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public Cliente() {
    }

    // Método para cadastrar um novo cliente no banco de dados
    public void cadastrarCliente(Banco db) {
        String query = String.format("INSERT INTO Cliente (nome, email, senha, telefone, cpf) VALUES ('%s', '%s', '%s', '%s', '%s')", getNome(), getEmail(), getSenha(), getTelefone(), getCpf());
        db.queryUpdate(query);
    }

    public void pesquisarCliente(Banco db, String cpf) throws SQLException {
        String query = String.format("SELECT * FROM Cliente WHERE cpf = '%s'", cpf);
        ResultSet rs = db.querySearch(query);

        // Atualiza os valores do cliente encontrado
        setNome(rs.getString("nome"));
        setCpf(rs.getString("cpf"));
        setSenha(rs.getString("senha"));
        setEmail(rs.getString("email"));
        setTelefone(rs.getString("telefone"));
    }

    public void listarClientes(Banco db) throws SQLException {
        String query = "SELECT * FROM Cliente"; 
        ResultSet rs = db.querySearch(query); 

        while (rs.next()) {
            // Atualiza os valores do cliente e imprime
            setNome(rs.getString("nome"));
            setCpf(rs.getString("cpf"));
            setSenha(rs.getString("senha"));
            setEmail(rs.getString("email"));
            setTelefone(rs.getString("telefone"));
            System.out.println(toString());
        }
    }

    // Método para editar os dados de um cliente existente
    public void editarCliente(Banco db) {
        String query = String.format("UPDATE Cliente SET nome = '%s', email = '%s', senha = '%s', telefone = '%s' WHERE cpf = '%s'", getNome(), getEmail(), getSenha(), getTelefone(), getCpf());
        db.queryUpdate(query);
    }

    // Método para remover um cliente pelo CPF
    public void removerCliente(Banco db) {
        String query = String.format("DELETE FROM Cliente WHERE cpf = '%s'", getCpf());
        db.queryUpdate(query);
    }

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

    // Retorna uma representação em string do cliente
    @Override
    public String toString() {
        return "Cliente{nome=" + getNome() + ", email=" + getEmail() + ", telefone=" + getTelefone() + ", cpf=" + getCpf() + "}";
    }
}