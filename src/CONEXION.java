import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;



public class CONEXION {
    String bd="project1";
    String url="jdbc:mysql://localhost:3306/";
    String user="root";
    String password="";
    String driver="com.mysql.cj.jdbc.Driver";
    
    Connection cx; 
    
    

    public CONEXION() {
    }
    
    public Connection conectar(){
        try {
            Class.forName(driver);
            cx=DriverManager.getConnection(url+bd, user, password);
            System .out.println("Se conecto a BD"+ bd);
            
           
            
            
        } catch (ClassNotFoundException |SQLException ex) {
            System .out.println("No se conecto a BD"+ bd);
            Logger.getLogger(CONEXION.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return cx;
    }
    public void desconectar(){
        try {
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(CONEXION.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public static void main(String[] args) {
        CONEXION conexion=new CONEXION();
        conexion.conectar();
    
    
    }

    
     private static void insertarDatos(Connection connection, String nombre, String email) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, email) VALUES (camilo, camilo@gmail.com)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.setString(2, email);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Datos insertados correctamente.");
            }
        }
    }

    private static void consultarDatos(Connection connection) throws SQLException {
        String sql = "SELECT * FROM usuarios";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Email: " + email);
            }
        }
    }

    private static void actualizarDatos(Connection connection, int id, String nombre, String email) throws SQLException {
        String sql = "UPDATE usuarios SET nombre = ?, email = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.setString(2, email);
            statement.setInt(3, id);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Datos actualizados correctamente.");
            }
        }
    }

    private static void eliminarDatos(Connection connection, int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Datos eliminados correctamente.");
            }
        }
    }
    
    
    



}
