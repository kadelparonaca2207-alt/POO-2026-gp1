package pe.edu.upeu.final_project_gp1.implementacion;

import pe.edu.upeu.final_project_gp1.interfaces.IUsuarioDao;
import pe.edu.upeu.final_project_gp1.modelo.Usuario;
import pe.edu.upeu.final_project_gp1.utils.AlertUtils;
import pe.edu.upeu.final_project_gp1.utils.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDaoImpl implements IUsuarioDao {

    private static AlertUtils alertUtils = new AlertUtils();

    @Override
    public Usuario login(String user, String password) {
        Usuario u = null;
        String query = "SELECT * FROM usuario WHERE user = ? AND password = ?;";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {

            ps.setString(1, user);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    u = new Usuario();
                    u.setIdusuario(rs.getInt("idusuario"));
                    u.setUser(rs.getString("user"));
                    u.setTipoUser(rs.getString("tipoUser"));

                    u.setNombre(rs.getString("nombre"));
                    u.setApellido(rs.getString("apellido"));
                    u.setDni(rs.getString("dni"));
                    System.out.println("Login exitoso.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al realizar la operación " + e);
        }

        return u;
    }

    @Override
    public boolean insertUser(Usuario usuario) {

        return false;
    }
}
