package jdbc.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

    public DataSource dataSource;

    public ConnectionFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotelalura?useTimezone=true&serverTimezone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("12345678");
        comboPooledDataSource.setMaxPoolSize(5); // cantidad de conexiones que se pueden abrir

        this.dataSource = comboPooledDataSource;
    }

    public Connection recuperarConexion() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error al recuperar la conexión: " + e.getMessage(), e);
        }
    }
}
