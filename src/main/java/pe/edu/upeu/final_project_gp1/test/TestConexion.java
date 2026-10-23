package pe.edu.upeu.final_project_gp1.test;

import pe.edu.upeu.final_project_gp1.utils.ConexionBD;

import java.sql.Connection;

public class TestConexion {

    public static void testConexion(){
        try {
            Connection con = ConexionBD.getConnection();
            if (con != null && !con.isClosed()){
                System.out.println("Conexión exitosa!");
            } else {
                System.out.println("Error al establecer la conexión...");
            }

        } catch (Exception e) {
            System.out.println("Error de conexión... " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        testConexion();
    }
}
