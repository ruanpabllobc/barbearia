package src.barbearia.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import src.database.Banco;

public class Barbearia extends Usuario {

    private String endereco; 
    private int anoAbertura; 
    private String cnpj;
    
    public Barbearia(String nome, String senha, String endereco, String email, int anoAbertura, String cnpj) {
        super(nome, senha, email);
        this.endereco = endereco;
        this.anoAbertura = anoAbertura;
        this.cnpj = cnpj;
    }
    
    public Barbearia() {
    }
    
    // Método para cadastrar uma nova barbearia no banco de dados
    public void cadastrarBarbearia(Banco db) {
        String query = String.format("INSERT INTO Barbearia (nome, endereco, senha, email, ano_abertura, cnpj) VALUES ('%s', '%s', '%s', '%s', '%d', '%s')", getNome(), getEndereco(), getSenha(), getEmail(), getAnoAbertura(), getCnpj());
        db.queryUpdate(query);
    }

    public void pesquisarBarbearia(Banco db, String cnpj) throws SQLException {
        String query = String.format("SELECT * FROM Barbearia WHERE cnpj = '%s'", cnpj);
        ResultSet rs = db.querySearch(query);
        
        // Atualiza os valores da barbearia encontrada
        setNome(rs.getString("nome"));
        setEndereco(rs.getString("endereco"));
        setSenha(rs.getString("senha"));
        setEmail(rs.getString("email"));
        setAnoAbertura(rs.getInt("ano_abertura"));
        setCnpj(rs.getString("cnpj"));
    }

    public void listarBarbearias(Banco db) throws SQLException {
        String query = "SELECT * FROM Barbearia";
        ResultSet rs = db.querySearch(query);

        while (rs.next()) {
            // Atualiza os valores da barbearia e imprime
            setNome(rs.getString("nome"));
            setEndereco(rs.getString("endereco"));
            setSenha(rs.getString("senha"));
            setEmail(rs.getString("email"));
            setAnoAbertura(rs.getInt("ano_abertura"));
            setCnpj(rs.getString("cnpj"));
            System.out.println(toString());
        }
    }

    // Método para editar os dados de uma barbearia existente
    public void editarBarbearia(Banco db) {
        String query = String.format("UPDATE Barbearia SET nome = '%s', endereco = '%s', senha = '%s', email = '%s', ano_abertura = '%d' WHERE cnpj = '%s'", getNome(), getEndereco(), getSenha(), getEmail(), getAnoAbertura(), getCnpj());
        db.queryUpdate(query);
    }

    // Método para remover uma barbearia pelo CNPJ
    public void removerBarbearia(Banco db) {
        String query = String.format("DELETE FROM Barbearia WHERE cnpj = '%s'", getCnpj());
        db.queryUpdate(query);
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getAnoAbertura() {
        return anoAbertura;
    }

    public void setAnoAbertura(int anoAbertura) {
        this.anoAbertura = anoAbertura;
    }

    // Retorna uma representação em string da barbearia
    @Override
    public String toString() {
        return getNome() + ", CNPJ=" + getCnpj();
    }
}