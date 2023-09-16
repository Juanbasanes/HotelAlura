package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.modelo.Administradores;

public class AdministradoresDAO {

final private Connection con;
	
	
	public AdministradoresDAO(Connection connection) {
		this.con = connection;
	}
	
	public Administradores autenticar(String usuario, String contrasena) {
        // Crear una consulta SQL para verificar si el nombre de usuario y la contraseña son válidos
        String sql = "SELECT * FROM administradores WHERE nombre = ? AND contrasena = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, usuario);
            statement.setString(2, contrasena);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // El nombre de usuario y la contraseña son válidos
                    Administradores administrador = new Administradores(usuario, contrasena);
                    administrador.setId(resultSet.getInt("id"));
                    return administrador;
                } else {
                    // El nombre de usuario o la contraseña no son válidos
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
	
}
