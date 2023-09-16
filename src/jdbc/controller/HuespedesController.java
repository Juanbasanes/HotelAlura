package jdbc.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import jdbc.dao.HuespedesDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Huespedes;

public class HuespedesController {
    private HuespedesDAO huespedDAO;

    public HuespedesController() {
        Connection connection = new ConnectionFactory().recuperarConexion();
        this.huespedDAO = new HuespedesDAO(connection);
    }

    public void guardar(Huespedes huespedes) {
        this.huespedDAO.guardar(huespedes);
    }

    public List<Huespedes> listarHuespedes() {
        return this.huespedDAO.listarHuespedes();
    }

    public List<Huespedes> listarHuespedesId(String id) {
        return this.huespedDAO.buscarId(id);
    }

    public void actualizar(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer idReserva, Integer id) {
        Connection connection = null;
        try {
            connection = new ConnectionFactory().recuperarConexion();
            connection.setAutoCommit(false); // Desactivar la confirmación automática

            String sql = "UPDATE huespedes SET nombre = ?, apellido = ?, fechaNacimiento = ?, nacionalidad = ?, telefono = ?, idReserva = ? WHERE id = ?";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setString(1, nombre);
                stm.setString(2, apellido);
                stm.setDate(3, fechaNacimiento);
                stm.setString(4, nacionalidad);
                stm.setString(5, telefono);
                stm.setInt(6, idReserva);
                stm.setInt(7, id);
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
        this.huespedDAO.eliminar(id);
    }
}
