package src.barbearia;

import java.sql.ResultSet;
import java.sql.SQLException;
import src.db.Banco;

public class Barbearia extends Usuario {

    private String endereco; //unidade
    private int anoAbertura;
    private String cnpj;
    
    public Barbearia(String nome, String senha, String endereco, String email, int anoAbertura, String cnpj) {
        super(nome, senha,email);
        this.endereco = endereco;
        this.anoAbertura = anoAbertura;
        this.cnpj = cnpj;
    }
    
    public Barbearia(){ //Construtor auxiliar do Main
    }
    
    public void cadastrarBarbearia(Banco db) {
        String query = String.format("INSERT INTO Barbearia (nome, endereco, senha, email, ano_abertura, cnpj) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", getNome(), getEndereco(), getSenha(), getEmail(), getAnoAbertura(),getCnpj()); //Cadastra uma barbearia preenchendo os valores da coluna Barbearia pelo metodo Get
        db.queryUpdate(query); //Atualiza no banco
    }

    public void pesquisarBarbearia(Banco db, String cnpj) throws SQLException {
        String query = String.format("SELECT * FROM Barbearia WHERE cnpj = '%s'", cnpj); //Busca um restaurante pelo CNPJ
        ResultSet rs = db.querySearch(query); //Recebe o resultado da pesquisa
        
        setNome(rs.getString("nome")); //Atualiza os valores e imprime
        setEndereco(rs.getString("endereco"));
        setSenha(rs.getString("senha"));
        setEmail(rs.getString("email"));
        setAnoAbertura(rs.getInt("ano_abertura"));
        setCnpj(rs.getString("cnpj"));
    }

    public void listarBarbearias(Banco db) throws SQLException {
        String query = "SELECT * FROM Barbearia"; //Seleciona todos os itens da tabela Barbearia
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

    public void editarBarbearia(Banco db) {
        String query = String.format("UPDATE Barbearia SET nome = '%s', endereco = '%s', senha = '%s', email = '%s', ano_abertura = '%d' WHERE cnpj = '%s'", getNome(), getEndereco(), getSenha(), getEmail(), getAnoAbertura(), getCnpj()); //Preenche os itens da tabela Barbearia com o metodo Get de cada atributo
        db.queryUpdate(query); //Atualiza
    }

    public void removerBarbearia(Banco db) {
        String query = String.format("DELETE FROM Barbearia WHERE cnpj = '%s'", getCnpj()); //Remove uma Barbearia encontrado na tabela atraves do seu CNPJ
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
        return getNome() + ", CNPJ=" +  getCnpj();
    }
}