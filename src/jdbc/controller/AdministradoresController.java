package jdbc.controller;

import java.sql.Connection;

import jdbc.dao.AdministradoresDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Administradores;

public class AdministradoresController {

	private ConnectionFactory connectionFactory;
    private AdministradoresDAO administradoresDAO;

    public AdministradoresController() {
        connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexion();
        administradoresDAO = new AdministradoresDAO(connection);
    }

    public Administradores login(String usuario, String contrasena) {
        Administradores administrador = administradoresDAO.autenticar(usuario, contrasena);
        return administrador;
    }
}
