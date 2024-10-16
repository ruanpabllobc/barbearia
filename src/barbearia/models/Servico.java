package src.barbearia.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import src.database.Banco;

public class Servico {

    private String nomeServico;
    private float valorServico;
    private String idBarbearia;

    public Servico(String nomeServico, float valorServico, String idRestaurante) {
        this.nomeServico = nomeServico;
        this.valorServico = valorServico;
        this.idBarbearia = idRestaurante;
    }

    public Servico() { // Contrutor auxiliar
    }

    public void cadastrarServico(Banco db) {
        String query = String.format(
                "INSERT INTO Servico (nome_servico, valor_servico, barbearia) VALUES ('%s', '%.2f', '%s')",
                getNomeServico(), getValorServico(), getIdBarbearia());
        db.queryUpdate(query); // Metodo da classe banco para inserir ou atualizar dados na tabela
    }

    public void pesquisarServico(Banco db, String nomeServico, String cnpj) throws SQLException {
        String query = String.format("SELECT * FROM Servico WHERE nome_servico = '%s' and barbearia = '%s'",
                nomeServico, cnpj); // Pesquisa uma mesa na tabela de um restaurante específico
        ResultSet rs = db.querySearch(query); // rs recebe o resultado da busca

        setNomeServico(rs.getString("nome_servico")); // Atualizando valor
        setValorServico(rs.getFloat("valor_servico"));
        setIdBarbearia(rs.getString("barbearia"));
        System.out.println(toString()); // Imprime
    }

    public void listarServicos(Banco db, String cnpj) throws SQLException {
        String query = "SELECT * FROM Servico WHERE barbearia = '" + cnpj + "'"; // Procura os serviços com aquele CNPJ
        ResultSet rs = db.querySearch(query);

        // Verifica se o ResultSet está vazio
        if (!rs.isBeforeFirst()) { // Se não houver linhas no ResultSet
            System.out.println("Nenhum serviço encontrada para o usuário com ID: " + cnpj);
            return; // Sai do método se não houver reservas
        }

        while (rs.next()) { // Percorre cada linha da lista
            Servico servico = new Servico();
            servico.setNomeServico(rs.getString("nome_servico"));
            servico.setValorServico(rs.getFloat("valor_servico"));
            servico.setIdBarbearia(rs.getString("barbearia"));
            System.out.println(servico.toString());
        }
    }

    public void editarServico(Banco db, String cnpj, String nomeServico) {
        String query = String.format(
                "UPDATE Servico SET nome_servico = '%s', valor_servico = '%.2f' WHERE nome_servico = '%s' and barbearia = '%s'",
                getNomeServico(), getValorServico(), nomeServico, cnpj); // Procura a mesa de acordo com o numero e CNPJ
                                                                         // e atualiza
        db.queryUpdate(query); // Atualiza no banco
    }

    public void removerServico(Banco db, String cnpj) {
        String query = String.format("DELETE FROM Servico WHERE nome_servico = '%s'", getNomeServico(), cnpj);
        db.queryUpdate(query); // Remova a mesa da lista de acordo com o numero e CNPJ
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public float getValorServico() {
        return valorServico;
    }

    public void setValorServico(float valorServico) {
        this.valorServico = valorServico;
    }

    public String getIdBarbearia() {
        return idBarbearia;
    }

    public void setIdBarbearia(String idBarbearia) {
        this.idBarbearia = idBarbearia;
    }

    @Override
    public String toString() {
        return "Servico [nomeServico=" + nomeServico + ", valorServico=" + valorServico + ", idBarbearia=" + idBarbearia
                + "]";
    }
}