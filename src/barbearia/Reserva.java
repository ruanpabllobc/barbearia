package src.barbearia;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import src.db.Banco;

public class Reserva {

    private int id;
    private Date dataReserva;
    private String metodoPagamento;
    private String idBarbearia;
    private String idCliente;
    private String idServico;

    public Reserva(Date dataReserva, String idBarbearia, String idCliente, String idServico) {
        this.dataReserva = dataReserva;
        this.idBarbearia = idBarbearia;
        this.idCliente = idCliente;
        this.idServico = idServico;
    }
    
    public Reserva(){ //Construtor auxiliar
    }

    public void cadastrarReserva(Banco db) {
        String query = String.format("INSERT INTO Reserva (data_reserva, metodo_pagamento, barbearia, cliente, nome_servico) VALUES ('%tF', '%s', '%s', '%s', '%s')", 
        getDataReserva(), getMetodoPagamento(), getIdBarbearia(), getIdCliente(), getIdServico()); //Preenche cada campo da tabela Reserva atraves do metodo Get
        db.queryUpdate(query); //Realiza inserção na tabela
    }

    public void pesquisarReserva(Banco db, int reservaId) throws SQLException { //Apenas clientes
        String query = String.format("SELECT * FROM Reserva WHERE id_reserva = %d", reservaId); //Busca a reserva na tavbela atraves do seu ID
        ResultSet rs = db.querySearch(query); //O resultado da busca é armazenado em rs (resultado da consulta)
    
        setId(rs.getInt("id_reserva")); //Atualiza cada valor de cada colunana tabela
        String datast = rs.getString("data_reserva");
        Date data = Date.valueOf(datast);
        setDataReserva(data);
        setMetodoPagamento(rs.getString("metodo_pagamento"));
        setIdBarbearia(rs.getString("barbearia"));
        setIdCliente(rs.getString("cliente"));
        setIdServico(rs.getString("nome_servico"));
        System.out.println(toString());
    }

    public static boolean pesquisarReservaNoDia(Banco db, Date data, String barbeariaId) throws SQLException {
        String query = String.format("SELECT * FROM Reserva WHERE data_reserva = '%tF' and barbearia = '%s'", data, barbeariaId); //Realiza uma busca para a mesa na data informada
        ResultSet rs = db.querySearch(query);
       
        if (rs.next()){ //Verifica se existe reserva na mesa para aquela data
            return true; //Existe reserva na data
        }
        return false; //Não existe reserva para a data
    }
    
    
    public void listarReservas(Banco db) throws SQLException {
        String query = "SELECT * FROM Reserva"; //Seleciona os dados da tabela Reserva
        ResultSet rs = db.querySearch(query); //rs obtem o resultado da busca

        while (rs.next()){ //Percore cada linha da lista e atualiza seus dados
            setId(rs.getInt("id_reserva"));
            String datast = rs.getString("data_reserva");
            Date data = Date.valueOf(datast);
            setDataReserva(data);
            setMetodoPagamento(rs.getString("metodo_pagamento"));
            setIdBarbearia(rs.getString("barbearia"));
            setIdServico(rs.getString("nome_servico"));
            setIdCliente(rs.getString("cliente"));
            System.out.println(toString()); //Imprime os dados da linha
            }
    }

    public void editarReserva(Banco db) {
        String query = String.format("UPDATE Reserva SET nome_servico = '%s', data_reserva = '%s', metodo_pagamento = '%s', barbearia = '%s', cliente = '%s' WHERE id_reserva = %d", getIdServico(), getDataReserva(), getMetodoPagamento(), getIdBarbearia(), getIdCliente(), getId()); //Preenche cada campo da tabela com o metodo Get
        db.queryUpdate(query); //Atualiza a tabela
    }

    public void removerReserva(Banco db) {
        String query = String.format("DELETE FROM Reserva WHERE id_reserva = %d", getId());
        db.queryUpdate(query); //Busca uma reserva pelo seu ID e delta da tabela
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public String getIdBarbearia() {
        return idBarbearia;
    }

    public void setIdBarbearia(String idBarbearia) {
        this.idBarbearia = idBarbearia;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdServico() {
        return idServico;
    }

    public void setIdServico(String idServico) {
        this.idServico = idServico;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    @Override
    public String toString() {
        return "Reserva [id=" + id + ", dataReserva=" + dataReserva + ", metodoPagamento=" + metodoPagamento
                + ", idBarbearia=" + idBarbearia + ", idCliente=" + idCliente + ", idServico=" + idServico + "]";
    }
}