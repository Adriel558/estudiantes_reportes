
package DAO;

// Data Access Object (DAO)

import Class.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;
    
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertUsuario(User usuario) throws SQLException {
        String query = "INSERT INTO usuarios (usuario, clave) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usuario.getUsername());
            statement.setString(2, usuario.getPassword());
            statement.executeUpdate();
        }
    }

    // Method to update a user
    public void updateUsuario(User usuario) throws SQLException {
        String query = "UPDATE usuarios SET usuario=?, clave=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usuario.getUsername());
            statement.setString(2, usuario.getPassword());
            statement.setInt(3, usuario.getId());
            statement.executeUpdate();
        }
    }

    // Method to list all users
    public void listUsuarios() throws SQLException {
        String query = "SELECT * FROM usuarios";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                // Here you can print or handle the data as needed
                System.out.println("ID: " + id + ", Username: " + username + ", Password: " + password);
            }
        }
    }

    // Method to delete a user
    public void deleteUsuario(int id) throws SQLException {
        String query = "DELETE FROM usuarios WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
    
    
    // Method to login user
    public boolean validateUser(String username, String password) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM usuarios WHERE usuario=? AND clave=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        }
        return false;
    }
}
