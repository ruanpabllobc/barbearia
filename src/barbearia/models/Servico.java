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

    public Servico() {
    }

    // Método para cadastrar um novo serviço no banco de dados
    public void cadastrarServico(Banco db) {
        String query = String.format("INSERT INTO Servico (nome_servico, valor_servico, barbearia) VALUES ('%s', '%.2f', '%s')", getNomeServico(), getValorServico(), getIdBarbearia());
        db.queryUpdate(query);
    }

    public void pesquisarServico(Banco db, String nomeServico, String cnpj) throws SQLException {
        String query = String.format("SELECT * FROM Servico WHERE nome_servico = '%s' and barbearia = '%s'", nomeServico, cnpj);
        ResultSet rs = db.querySearch(query);
        // Atualiza os valores do serviço encontrado
        setNomeServico(rs.getString("nome_servico"));
        setValorServico(rs.getFloat("valor_servico"));
        setIdBarbearia(rs.getString("barbearia"));
        System.out.println(toString());
    }

    // Método para listar todos os serviços de uma barbearia específica
    public void listarServicos(Banco db, String cnpj) throws SQLException {
        String query = "SELECT * FROM Servico WHERE barbearia = '" + cnpj + "'";
        ResultSet rs = db.querySearch(query);

        if (!rs.isBeforeFirst()) {
            System.out.println("Nenhum serviço encontrada para o usuário com ID: " + cnpj);
            return;
        }

        while (rs.next()) {
            Servico servico = new Servico();
            servico.setNomeServico(rs.getString("nome_servico"));
            servico.setValorServico(rs.getFloat("valor_servico"));
            servico.setIdBarbearia(rs.getString("barbearia"));
            System.out.println(servico.toString());
        }
    }

    // Método para editar um serviço existente
    public void editarServico(Banco db, String cnpj, String nomeServico) {
        String query = String.format("UPDATE Servico SET nome_servico = '%s', valor_servico = '%.2f' WHERE nome_servico = '%s' and barbearia = '%s'", getNomeServico(), getValorServico(), nomeServico, cnpj);
        db.queryUpdate(query);
    }

    // Método para remover um serviço, verificando se pertence à barbearia
    public void removerServico(Banco db, String cnpj) {
        String query = String.format("DELETE FROM Servico WHERE nome_servico = '%s'", getNomeServico(), cnpj);
        db.queryUpdate(query);
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
        return "Servico [nomeServico=" + nomeServico + ", valorServico=" + valorServico + ", idBarbearia=" + idBarbearia + "]";
    }
}