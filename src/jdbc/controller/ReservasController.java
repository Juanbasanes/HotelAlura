package jdbc.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import jdbc.dao.ReservaDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Reserva;

public class ReservasController {
    private ReservaDAO reservaDAO;

    public ReservasController() {
        Connection connection = new ConnectionFactory().recuperarConexion();
        this.reservaDAO = new ReservaDAO(connection);
    }

    public void guardar(Reserva reserva) {
        this.reservaDAO.guardar(reserva);
    }

    public List<Reserva> buscar() {
        return this.reservaDAO.buscar();
    }

    public List<Reserva> buscarId(String id) {
        return this.reservaDAO.buscarId(id);
    }

    public void actualizar(Date fechaEntrada, Date fechaSalida, String valor, String formaPago, Integer id) {
        Connection connection = null;
        try {
            connection = new ConnectionFactory().recuperarConexion();
            connection.setAutoCommit(false); // Desactivar la confirmación automática

            String sql = "UPDATE reservas SET fechaEntrada = ?, fechaSalida = ?, valor = ?, formaPago = ? WHERE id = ?";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setDate(1, fechaEntrada);
                stm.setDate(2, fechaSalida);
                stm.setString(3, valor);
                stm.setString(4, formaPago);
                stm.setInt(5, id);
                stm.executeUpdate();

                // Confirmar la transacción
                connection.commit();
            } catch (SQLException e) {
                // En caso de error, realizar un rollback para deshacer los cambios
                if (connection != null) {
                    connection.rollback();
                }
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Asegurarse de cerrar la conexión
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
            }
        }
    }

    public void eliminar(Integer id) {
        this.reservaDAO.eliminar(id);
    }
}
