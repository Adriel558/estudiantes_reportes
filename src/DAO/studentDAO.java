package DAO;

import Class.CivilStatus;
import Class.Document;
import Class.Status;
import Class.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class studentDAO {

    private Connection connection;

    public studentDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertEstudiante(Student estudiante) throws SQLException {
        String query = "INSERT INTO estudiantes (nombre, apellido, fecha_nacimiento, estado_id, estado_civil_id, documento_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, estudiante.getName());
            statement.setString(2, estudiante.getLastName());
            statement.setDate(3, new java.sql.Date(estudiante.getBirthDate().getTime()));
            statement.setInt(4, estudiante.getStateId());
            statement.setInt(5, estudiante.getMaritalStatusId());
            statement.setInt(6, estudiante.getDocumentId());
            statement.executeUpdate();
        }
    }

    public void updateEstudiante(Student estudiante) throws SQLException {
        String query = "UPDATE estudiantes SET nombre=?, apellido=?, fecha_nacimiento=?, estado_id=?, estado_civil_id=?, documento_id=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, estudiante.getName());
            statement.setString(2, estudiante.getLastName());
            statement.setDate(3, new java.sql.Date(estudiante.getBirthDate().getTime()));
            statement.setInt(4, estudiante.getStateId());
            statement.setInt(5, estudiante.getMaritalStatusId());
            statement.setInt(6, estudiante.getDocumentId());
            statement.setInt(7, estudiante.getId());
            statement.executeUpdate();
        }
    }

    public Student getEstudianteById(int id) throws SQLException {
        String query = "SELECT * FROM estudiantes WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractEstudianteFromResultSet(resultSet);
            }
        }
        return null;
    }

    public List<Student> getAllEstudiantes() throws SQLException {
        List<Student> estudiantes = new ArrayList<>();
        String query = "SELECT * FROM estudiantes";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student estudiante = extractEstudianteFromResultSet(resultSet);
                estudiantes.add(estudiante);
            }
        }
        return estudiantes;
    }

    public void deleteEstudiante(int id) throws SQLException {
        String query = "DELETE FROM estudiantes WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<Status> getAllEstados() throws SQLException {
        List<Status> estados = new ArrayList<>();
        String query = "SELECT * FROM estados";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                estados.add(new Status(id, nombre));
            }
        }
        return estados;
    }

    public List<CivilStatus> getAllEstadosCiviles() throws SQLException {
        List<CivilStatus> estadosCiviles = new ArrayList<>();
        String query = "SELECT * FROM estado_civil";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("estado");
                estadosCiviles.add(new CivilStatus(id, nombre));
            }
        }
        return estadosCiviles;
    }

    public List<Document> getAllDocumentos() throws SQLException {
        List<Document> documentos = new ArrayList<>();
        String query = "SELECT * FROM documentos";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String numero = resultSet.getString("numero");
                String tipo = resultSet.getString("tipo");
                documentos.add(new Document(id, tipo, numero));
            }
        }
        return documentos;
    }

    public List<Student> getEstudiantesByEstado(String estado) throws SQLException {
        List<Student> estudiantes = new ArrayList<>();
        String query = "SELECT * FROM estudiantes e JOIN estados s ON e.estado_id = s.id WHERE s.nombre = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, estado);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student estudiante = extractEstudianteFromResultSet(resultSet);
                estudiantes.add(estudiante);
            }
        }
        return estudiantes;
    }

    private Student extractEstudianteFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nombre = resultSet.getString("nombre");
        String apellido = resultSet.getString("apellido");
        Date fechaNacimiento = resultSet.getDate("fecha_nacimiento");
        int idEstado = resultSet.getInt("estado_id");
        int idEstadoCivil = resultSet.getInt("estado_civil_id");
        int idDocumento = resultSet.getInt("documento_id");

        Status SelectedStatus = getStatusById(idEstado);
        CivilStatus SelectedCivilStatus = getCivilStatusById(idEstadoCivil);
        Document selectedDocument = getDocumentById(idDocumento);

        return new Student(id, nombre, apellido, (java.sql.Date) fechaNacimiento, SelectedStatus, SelectedCivilStatus, selectedDocument);
    }

    private Status getStatusById(int id) throws SQLException {
        String query = "SELECT * FROM estados WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extratEstatusFronResultSet(resultSet);
            }
        }
        return null;
    }

    private CivilStatus getCivilStatusById(int id) throws SQLException {
        String query = "SELECT * FROM estado_civil WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extratCivilEstatusFronResultSet(resultSet);
            }
        }
        return null;
    }

    private Document getDocumentById(int id) throws SQLException {
        String query = "SELECT * FROM documentos WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extratDocumentFronResultSet(resultSet);
            }
        }
        return null;
    }

    private Status extratEstatusFronResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nombre = resultSet.getString("nombre");
        return new Status(id, nombre);
    }

    private CivilStatus extratCivilEstatusFronResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nombre = resultSet.getString("estado");
        return new CivilStatus(id, nombre);
    }

    private Document extratDocumentFronResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nombre = resultSet.getString("tipo");
        String numero = resultSet.getString("numero");
        return new Document(id, nombre, numero);
    }

}
