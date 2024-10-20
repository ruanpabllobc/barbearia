package src.barbearia.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import src.database.Banco;
import java.time.LocalTime;

public class Reserva {
    private int id;
    private Date dataReserva;
    private String metodoPagamento;
    private String idBarbearia;
    private String idCliente;
    private String idServico;
    private LocalTime horaInicio;

    public Reserva(Date dataReserva, String idBarbearia, String idCliente, String idServico, LocalTime horaInicio) {
        this.dataReserva = dataReserva;
        this.idBarbearia = idBarbearia;
        this.idCliente = idCliente;
        this.idServico = idServico;
        this.horaInicio = horaInicio;
    }

    public Reserva() {
    }

    // Método para cadastrar uma nova reserva no banco de dados
    public void cadastrarReserva(Banco db) {
        String query = String.format(
                "INSERT INTO Reserva (data_reserva, metodo_pagamento, nome_servico, barbearia, cliente, hora_inicio) VALUES ('%tF', '%s', '%s', '%s', '%s', '%tR')",
                getDataReserva(), getMetodoPagamento(), getIdServico(), getIdBarbearia(), getIdCliente(),
                getHoraInicio());
        db.queryUpdate(query);
    }

    public void pesquisarReserva(Banco db, int reservaId, String usuarioId) throws SQLException {
        String query = String.format(
                "SELECT * FROM Reserva WHERE id_reserva = %d AND (cliente = '%s' OR barbearia = '%s')", reservaId,
                usuarioId, usuarioId);
        ResultSet rs = db.querySearch(query);

        if (rs.next()) { // Verifica se há resultados
            setId(rs.getInt("id_reserva"));
            String datast = rs.getString("data_reserva");
            Date data = Date.valueOf(datast);
            setDataReserva(data);
            setMetodoPagamento(rs.getString("metodo_pagamento"));
            setIdBarbearia(rs.getString("barbearia"));
            setIdCliente(rs.getString("cliente"));
            setIdServico(rs.getString("nome_servico"));
            String horast = rs.getString("hora_inicio");
            LocalTime time = LocalTime.parse(horast); // Usa parse() em vez de valueOf()
            setHoraInicio(time);
            System.out.println(toString());
        } else {
            System.out.println("Nenhuma reserva encontrada com o ID " + reservaId);
        }
    }

    // Método para verificar se há reservas em uma determinada data para uma barbearia específica
    public static boolean pesquisarReservaNoDia(Banco db, Date data, String barbeariaId, LocalTime hora) throws SQLException {
        LocalTime horaInicio = hora.minusHours(1);
        LocalTime horaFim = hora.plusHours(1);

        String query = String.format("SELECT * FROM Reserva WHERE data_reserva = '%tF' AND barbearia = '%s' AND hora_inicio BETWEEN '%tR' AND '%tR'",
                data, barbeariaId, horaInicio, horaFim);
        ResultSet rs = db.querySearch(query);

        return rs.next();
    }

    // Método para listar todas as reservas de um usuário específico
    public void listarReservas(Banco db, String usuarioId) throws SQLException {
        String query = String.format("SELECT * FROM Reserva WHERE (cliente = '%s' OR barbearia = '%s')", usuarioId,
                usuarioId);
        ResultSet rs = db.querySearch(query);

        if (!rs.isBeforeFirst()) { // Verifica se não há resultados
            System.out.println("Nenhuma reserva encontrada para o usuário com ID: " + usuarioId);
            return;
        }

        while (rs.next()) {
            setId(rs.getInt("id_reserva"));
            String datast = rs.getString("data_reserva");
            Date data = Date.valueOf(datast);
            setDataReserva(data);
            setMetodoPagamento(rs.getString("metodo_pagamento"));
            setIdBarbearia(rs.getString("barbearia"));
            setIdServico(rs.getString("nome_servico"));
            setIdCliente(rs.getString("cliente"));
            System.out.println(toString());
        }
    }

    // Método para editar uma reserva existente
    public void editarReserva(Banco db) {
        String query = String.format(
                "UPDATE Reserva SET nome_servico = '%s', data_reserva = '%s', metodo_pagamento = '%s', barbearia = '%s', cliente = '%s' WHERE id_reserva = %d",
                getIdServico(), getDataReserva(), getMetodoPagamento(), getIdBarbearia(), getIdCliente(), getId());
        db.queryUpdate(query);
    }

    // Método para remover uma reserva, verificando se pertence ao cliente ou
    // barbearia
    public void removerReserva(Banco db, String usuarioId) {
        String query = String.format(
                "DELETE FROM Reserva WHERE id_reserva = %d AND (cliente = '%s' OR barbearia = '%s')", getId(),
                usuarioId, usuarioId);
        int rowsAffected = db.queryUpdate(query);

        if (rowsAffected == 0) {
            System.out.println("Reserva não encontrada.");
        } else {
            System.out.println("Reserva removida com sucesso.");
        }
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

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    @Override
    public String toString() {
        return "Reserva [id=" + id + ", dataReserva=" + dataReserva + ", metodoPagamento=" + metodoPagamento
                + ", idBarbearia=" + idBarbearia + ", idCliente=" + idCliente + ", idServico=" + idServico
                + ", horaInicio=" + horaInicio + "]";
    }

}