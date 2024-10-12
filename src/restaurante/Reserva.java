package src.restaurante;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import src.db.Banco;

public class Reserva {

    private int id;
    private Date dataReserva;
    private int quantidadePessoas;
    private float valor;
    private String metodoPagamento;
    private String idMesa;
    private String idRestaurante;
    private String idCliente;

    public Reserva(Date dataReserva, int quantidadePessoas, float valor, String metodoPagamento, 
    String idRestaurante, String idCliente, String idMesa) {
        this.dataReserva = dataReserva;
        this.quantidadePessoas = quantidadePessoas;
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.idRestaurante = idRestaurante;
        this.idCliente = idCliente;
        this.idMesa = idMesa;
    }
    
    public Reserva(){ //Construtor auxiliar
    }

    public void cadastrarReserva(Banco db) {
        String query = String.format("INSERT INTO Reserva (data_reserva, quantidade_pessoas, valor, metodo_pagamento, restaurante, cliente, numero_mesa) VALUES ('%tF', '%d', '%.2f', '%s', '%s', '%s', '%s')", 
        getDataReserva(), getQuantidadePessoas(), getValor(), getMetodoPagamento(), getIdRestaurante(), getIdCliente(), getIdMesa()); //Preenche cada campo da tabela Reserva atraves do metodo Get
        db.queryUpdate(query); //Realiza inserção na tabela
    }

    public void pesquisarReserva(Banco db, int reservaId) throws SQLException {
        String query = String.format("SELECT * FROM Reserva WHERE id_reserva = %d", reservaId); //Busca a reserva na tavbela atraves do seu ID
        ResultSet rs = db.querySearch(query); //O resultado da busca é armazenado em rs (resultado da consulta)
    
        setId(rs.getInt("id_reserva")); //Atualiza cada valor de cada colunana tabela
        String datast = rs.getString("data_reserva");
        Date data = Date.valueOf(datast);
        setDataReserva(data);
        setQuantidadePessoas(rs.getInt("quantidade_pessoas"));
        setValor(rs.getFloat("valor"));
        setMetodoPagamento(rs.getString("metodo_pagamento"));
        setIdRestaurante(rs.getString("restaurante"));
        setIdCliente(rs.getString("cliente"));
        setIdMesa(rs.getString("numero_mesa"));
        System.out.println(toString());
    }

    public static boolean pesquisarReservaNoDia(Banco db, Date data, String mesaId, String restauranteId) throws SQLException {
        String query = String.format("SELECT * FROM Reserva WHERE data_reserva = '%tF' and numero_mesa = '%s' and restaurante = '%s'", data, mesaId, restauranteId); //Realiza uma busca para a mesa na data informada
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
            setQuantidadePessoas(rs.getInt("quantidade_pessoas"));
            setValor(rs.getFloat("valor"));
            setMetodoPagamento(rs.getString("metodo_pagamento"));
            setIdRestaurante(rs.getString("restaurante"));
            setIdMesa(rs.getString("numero_mesa"));
            setIdCliente(rs.getString("cliente"));
            System.out.println(toString()); //Imprime os dados da linha
            }
    }

    public void editarReserva(Banco db) {
        String query = String.format("UPDATE Reserva SET numero_mesa = '%s', data_reserva = '%s', quantidade_pessoas = '%d', valor = '%.2f', metodo_pagamento = '%s', restaurante = '%s', cliente = '%s' WHERE id_reserva = %d",getIdMesa(), getDataReserva(), getQuantidadePessoas(), getValor(), getMetodoPagamento(), getIdRestaurante(), getIdCliente(), getId()); //Preenche cada campo da tabela com o metodo Get
        db.queryUpdate(query); //Atualiza a tabela
    }

    public void removerReserva(Banco db) {
        String query = String.format("DELETE FROM Reserva WHERE id_reserva = %d", getId());
        db.queryUpdate(query); //Busca uma reserva pelo seu ID e delta da tabela
    }
    
    //Metodos Get e Set
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }
   
    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getIdMesa(){
        return idMesa;
    }

    public void setIdMesa(String idMesa){
        this.idMesa = idMesa;
    }

    public String getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(String idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    //To String
    @Override
    public String toString() {
        return "Reserva {id=" + getId() + ", dataReserva=" + getDataReserva() + ", quantidadePessoas=" + getQuantidadePessoas() + ", valor=" + getValor() + ", metodoPagamento=" + getMetodoPagamento() + ", mesa=" + getIdMesa() + ", restaurante=" + getIdRestaurante() + ", cliente=" + getIdCliente() + "}";
    }
}