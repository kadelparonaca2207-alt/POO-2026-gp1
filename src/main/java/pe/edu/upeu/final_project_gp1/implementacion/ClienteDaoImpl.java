package pe.edu.upeu.final_project_gp1.implementacion;

import pe.edu.upeu.final_project_gp1.interfaces.IClienteDao;
import pe.edu.upeu.final_project_gp1.modelo.Cliente;
import pe.edu.upeu.final_project_gp1.utils.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImpl implements IClienteDao {

    @Override
    public boolean insert(Cliente c) {
        boolean flag = false;
        String query = "INSERT INTO cliente(nombre, apellido, dni, email, genero) VALUES (?, ?, ?, ?, ?);";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query);
        ) {
            st.setString(1, c.getNombre());
            st.setString(2, c.getApellido());
            st.setString(3, c.getDni());
            st.setString(4, c.getEmail());
            st.setString(5, c.getGenero());
            st.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println("error al insertar cliente" + e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean update(Cliente c) {
        boolean flag = false;
        String query = "UPDATE cliente SET nombre = ?, apellido = ?, dni = ?, email = ?, genero = ? " +
                "WHERE idcliente = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query);
        ) {
            st.setString(1, c.getNombre());
            st.setString(2, c.getApellido());
            st.setString(3, c.getDni());
            st.setString(4, c.getEmail());
            st.setString(5, c.getGenero());
            st.setInt(6, c.getIdcliente());
            st.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            System.out.println("error al actualizar cliente" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Cliente c) {
        boolean flag = false;
        String query = "DELETE FROM cliente WHERE idcliente = ?";
        try (Connection cn = ConexionBD.getConnection()) {
            PreparedStatement st = (cn.prepareStatement(query));
            st.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente " + e.getMessage());
        }
        return flag;
    }

    @Override
    public Cliente searchById(int id) {
        List<Cliente> lista = new ArrayList<>();
        Cliente cl = null;
        String query = "SELECT * FROM cliente WHERE idcliente = ?;";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery();) {
                if (rs.next()) {
                    cl = new Cliente();
                    cl.setIdcliente(rs.getInt("idcliente"));
                    cl.setNombre(rs.getString("nombre"));
                    cl.setApellido(rs.getString("apellido"));
                    cl.setDni(rs.getString("dni"));
                    cl.setEmail((rs.getString("email")));
                    cl.setGenero(rs.getString("genero"));
                }
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL LISTAR " + e.getMessage());
        }
        return cl;
    }

    @Override
    public Cliente searchByDni(String dni) {
        List<Cliente> lista = new ArrayList<>();
        Cliente cl = null;
        String query = "SELECT * FROM cliente WHERE dni = ?;";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1, dni);
            try (ResultSet rs = st.executeQuery();) {
                if (rs.next()) {
                    cl = new Cliente();
                    cl.setIdcliente(rs.getInt("idcliente"));
                    cl.setNombre(rs.getString("nombre"));
                    cl.setApellido(rs.getString("apellido"));
                    cl.setDni(rs.getString("dni"));
                    cl.setEmail((rs.getString("email")));
                    cl.setGenero(rs.getString("genero"));
                }
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL LISTAR " + e.getMessage());
        }
        return cl;
    }

    @Override
    public List<Cliente> listAll() {
        List<Cliente> lista = new ArrayList<>();
        String query = "SELECT * FROM cliente;";                    //PREPARAR LA CONSULTA
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query);     //PREPARA LOS DATOS
             ResultSet rs = st.executeQuery();                      //RECIBE LOS DATOS
        ) {
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setIdcliente(rs.getInt("idcliente"));
                cl.setNombre(rs.getString("nombre"));
                cl.setApellido(rs.getString("apellido"));
                cl.setDni(rs.getString("dni"));
                cl.setEmail((rs.getString("email")));
                cl.setGenero(rs.getString("genero"));
                lista.add(cl);
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL LISTAR " + e.getMessage());
        }

        return lista;
    }
}

