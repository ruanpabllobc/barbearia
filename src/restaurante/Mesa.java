package src.restaurante;

import src.db.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mesa {

    private int numeroMesa;
    private String idRestaurante;

    public Mesa(int numeroMesa, String idRestaurante) {
        this.numeroMesa = numeroMesa;
        this.idRestaurante = idRestaurante;
    }

    public Mesa() { //Contrutor auxiliar
    }

    public void cadastrarMesa(Banco db) {
        String query = String.format("INSERT INTO Mesa (numero_mesa, restaurante) VALUES ('%d', '%s')", // Formato da tabela mesa no banco
        getNumeroMesa(), getIdRestaurante());
        db.queryUpdate(query); //Metodo da classe banco para inserir ou atualizar dados na tabela
    }

    public void pesquisarMesa(Banco db, String numeroMesa, String cnpj) throws SQLException {
        String query = String.format("SELECT * FROM Mesa WHERE numero_mesa = '%s' and restaurante = '%s'", numeroMesa, cnpj); //Pesquisa uma mesa na tabela de um restaurante específico
        ResultSet rs = db.querySearch(query); //rs recebe o resultado da busca
    
        setNumeroMesa(rs.getInt("numero_mesa")); //Atualizando valor
        setIdRestaurante(rs.getString("restaurante"));
        System.out.println(toString()); //Imprime
    }

    public void listarMesas(Banco db, String cnpj) throws SQLException {
        String query = "SELECT * FROM Mesa WHERE restaurante = '" + cnpj + "'"; //Procura as mesas com aquele CNPJ
        ResultSet rs = db.querySearch(query);
        
        while (rs.next()) { //Percorre cada linha da lista
            Mesa mesa = new Mesa();
            mesa.setNumeroMesa(rs.getInt("numero_mesa"));
            mesa.setIdRestaurante(rs.getString("restaurante"));
            System.out.println(mesa.toString());
        }
    }  

    public void editarMesa(Banco db, String cnpj, int num) {
        String query = String.format("UPDATE Mesa SET numero_mesa = '%d' WHERE numero_mesa = '%d' and restaurante = '%s'", getNumeroMesa(), num, cnpj); //Procura a mesa de acordo com o numero e CNPJ e atualiza
        db.queryUpdate(query); //Atualiza no banco
    }

    public void removerMesa(Banco db, String cnpj) {
        String query = String.format("DELETE FROM Mesa WHERE numero_mesa = '%d'", getNumeroMesa(),cnpj);
        db.queryUpdate(query); //Remova a mesa da lista de acordo com o numero e CNPJ
    }

    //Metodos Get e Set
    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public String getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(String idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    //Converte os valores para String
    @Override
    public String toString() {
        return "Mesa {numeroMesa=" + getNumeroMesa() + "}";
    }
}