package pe.edu.upeu.final_project_gp1.utils;

import java.sql.*;

public class ConexionBD {
    private static Connection connection;

    public static Connection getConnection(){
        try {
            if (connection==null || connection.isClosed()){
                Class.forName("org.sqlite.JDBC");
                String url = "jdbc:sqlite:C:/sqlite/db/reserva_gp1.db";
                connection = DriverManager.getConnection(url);
                connection.setAutoCommit(true);

                try(Statement st = connection.createStatement()){
                    st.execute("PRAGMA foreign_keys = ON;");
                }
                System.out.println("Conexión exitosa a SQLite!");
            }
            return connection;
        } catch (Exception e) {
            throw new RuntimeException("Error de conexión a la base de datos... " + e.getMessage());
        }

    }
}
