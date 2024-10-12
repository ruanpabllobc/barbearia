package src.restaurante;

import java.sql.ResultSet;
import java.sql.SQLException;
import src.db.Banco;

public class Restaurante extends Usuario {

    private String endereco;
    private int anoAbertura;
    private String cnpj;
    
    public Restaurante(String nome, String senha, String endereco,String email, int anoAbertura, String cnpj) {
        super(nome, senha,email);
        this.endereco = endereco;
        this.anoAbertura = anoAbertura;
        this.cnpj = cnpj;
    }
    
    public Restaurante(){ //Construtor auxiliar do Main
    }
    
    public void cadastrarRestaurante(Banco db) {
        String query = String.format("INSERT INTO Restaurante (nome, endereco, senha, email, ano_abertura, cnpj) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", getNome(), getEndereco(), getSenha(), getEmail(), getAnoAbertura(),getCnpj()); //Cadastra um restaurante preenchendo os valores da coluna Restaurante pelo metodo Get
        db.queryUpdate(query); //Atualiza no banco
    }

    public void pesquisarRestaurante(Banco db, String cnpj) throws SQLException {
        String query = String.format("SELECT * FROM Restaurante WHERE cnpj = '%s'", cnpj); //Busca um restaurante pelo CNPJ
        ResultSet rs = db.querySearch(query); //Recebe o resultado da pesquisa
        
        setNome(rs.getString("nome")); //Atualiza os valores e imprime
        setEndereco(rs.getString("endereco"));
        setSenha(rs.getString("senha"));
        setEmail(rs.getString("email"));
        setAnoAbertura(rs.getInt("ano_abertura"));
        setCnpj(rs.getString("cnpj"));
    }

    public void listarRestaurantes(Banco db) throws SQLException {
        String query = "SELECT * FROM Restaurante"; //Seleciona todos os itens da tabela Restaurante
        ResultSet rs = db.querySearch(query); //Recebe o resultado

        while (rs.next()) { //Próxima linha
            setNome(rs.getString("nome")); //Atualiza e imprime
            setEndereco(rs.getString("endereco"));
            setSenha(rs.getString("senha"));
            setEmail(rs.getString("email"));
            setAnoAbertura(rs.getInt("ano_abertura"));
            setCnpj(rs.getString("cnpj"));
            System.out.println(toString());
        }
    }

    public void editarRestaurante(Banco db) {
        String query = String.format("UPDATE Restaurante SET nome = '%s', endereco = '%s', senha = '%s', email = '%s', ano_abertura = '%s' WHERE cnpj = '%s'", getNome(), getEndereco(), getSenha(), getEmail(), getAnoAbertura(), getCnpj()); //Preenche os itens da tabela Restaurante com o metodo Get de cada atributo
        db.queryUpdate(query); //Atualiza
    }

    public void removerRestaurante(Banco db) {
        String query = String.format("DELETE FROM Restaurante WHERE cnpj = '%s'", getCnpj()); //Remove um restaurante encontrado na tabela atraves do seu CNPJ
        db.queryUpdate(query);
    }

    //Metodos Get e Set
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnpj(){
        return cnpj;
    }

    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }

    public int getAnoAbertura() {
        return anoAbertura;
    }

    public void setAnoAbertura(int anoAbertura) {
        this.anoAbertura = anoAbertura;
    }

    //To String
    @Override
    public String toString() {
        return "Restaurante {nome=" + getNome() + ", endereco=" + getEndereco() + ", email=" + getEmail() + ", anoAbertura=" + getAnoAbertura() + ", Cnpj=" +  getCnpj() + "}";
    }
}