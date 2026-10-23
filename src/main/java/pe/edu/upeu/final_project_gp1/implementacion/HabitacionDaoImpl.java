package pe.edu.upeu.final_project_gp1.implementacion;

import pe.edu.upeu.final_project_gp1.interfaces.IHabitacionDao;
import pe.edu.upeu.final_project_gp1.modelo.Habitacion;
import pe.edu.upeu.final_project_gp1.utils.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDaoImpl implements IHabitacionDao {


    @Override
    public boolean insert(Habitacion h) {
        boolean flag = false;
        String sql = "INSERT INTO habitacion(idhabitacion, numero, numeroPiso, numeroHabitacion, tipo, precio, estado) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, h.getIdhabitacion());
            ps.setString(2, h.getNumero());
            ps.setString(3, h.getNumeroPiso());
            ps.setString(4, h.getNumeroHabitacion());
            ps.setString(5, h.getTipo());
            ps.setDouble(6, h.getPrecio());
            ps.setString(7, h.getEstado());
            ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println("Error al insertar habitación " + e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean update(Habitacion h) {
        boolean flag = false;
        String sql = "UPDATE habitacion SET numero = ?, numeroPiso = ?, numeroHabitacion = ?, tipo = ?, precio = ?, estado = ?" + "WHERE idhabitacion = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, h.getNumero());
            ps.setString(2, h.getNumeroPiso());
            ps.setString(3, h.getNumeroHabitacion());
            ps.setString(4, h.getTipo());
            ps.setDouble(5, h.getPrecio());
            ps.setString(6, h.getEstado());
            ps.setInt(7, h.getIdhabitacion());
            ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos de la habitación" + e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean delete(Habitacion h) {
        boolean flag = false;
        String sql = "DELETE FROM habitacion WHERE idcliente = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar habitación " + e.getMessage());
        }
        return flag;
    }

    @Override
    public Habitacion SearchById(int id, String numero, String numeroPiso, String numeroHab, String tipo, Double precio, String estado) {
        List<Habitacion> lista = new ArrayList<>();
        Habitacion habitacion = null;
        String query = "SELECT * FROM habitacion WHERE idhabitacion = ?;";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.setString(2, numero);
            ps.setString(3, numeroPiso);
            ps.setString(4, numeroHab);
            ps.setString(5, tipo);
            ps.setDouble(6, precio);
            ps.setString(7, estado);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    habitacion = new Habitacion();
                    habitacion.setIdhabitacion(rs.getInt("idhabitacion"));
                    habitacion.setNumero(rs.getString("numero"));
                    habitacion.setNumeroPiso(rs.getString("numeroPiso"));
                    habitacion.setNumeroHabitacion(rs.getString("numeroHabitacion"));
                    habitacion.setTipo(rs.getString("tipo"));
                    habitacion.setPrecio(rs.getDouble("precio"));
                    habitacion.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar " + e.getMessage());
        }
        return habitacion;
    }

    @Override
    public List<Habitacion> listAll() {
        List<Habitacion> lista = new ArrayList<>();
        String sql = " SELECT * FROM habitacion ";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

                while (rs.next()){
                    Habitacion habitacion = new Habitacion();
                    habitacion.setIdhabitacion(rs.getInt("idhabitacion"));
                    habitacion.setNumero(rs.getString("numero"));
                    habitacion.setNumeroPiso(rs.getString("numeroPiso"));
                    habitacion.setNumeroHabitacion(rs.getString("numeroHabitacion"));
                    habitacion.setTipo(rs.getString("tipo"));
                    habitacion.setPrecio(rs.getDouble("precio"));
                    habitacion.setEstado(rs.getString("estado"));
                    lista.add(habitacion);
                }
        } catch (SQLException e) {
            System.out.println("Error al listar " + e.getMessage());
        }
        return lista;
    }
}
