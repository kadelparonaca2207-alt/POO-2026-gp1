package pe.edu.upeu.final_project_gp1.implementacion;

import pe.edu.upeu.final_project_gp1.interfaces.IReservaDao;
import pe.edu.upeu.final_project_gp1.modelo.Cliente;
import pe.edu.upeu.final_project_gp1.modelo.Habitacion;
import pe.edu.upeu.final_project_gp1.modelo.Reserva;
import pe.edu.upeu.final_project_gp1.modelo.Reserva;
import pe.edu.upeu.final_project_gp1.utils.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReservaDaoImpl implements IReservaDao {
    @Override
    public boolean insert(Reserva r) {
        boolean flag = false;
        String query = "INSERT INTO reserva(clienteId, habitacionId, fecha_reserva, fecha_ingreso, fecha_salida, estado, total) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(query);) {
            ps.setInt(1, r.getCliente().getIdcliente());
            ps.setInt(2, r.getHabitacion().getIdhabitacion());
            ps.setString(3, r.getFechaReserva().toString());
            ps.setString(4, r.getFechaIngreso().toString());
            ps.setString(5, r.getFechaSalida().toString());
            ps.setString(6, r.getEstado());
            ps.setDouble(7, r.getTotal());
            ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println("Error al insertar " + e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean update(Reserva r) {
     return false;
    }

    @Override
    public boolean delete(Reserva r) {
        return false;
    }

    @Override
    public Reserva searchById(int id) {
        return null;
    }

    @Override
    public List<Reserva> listAll() {
        List<Reserva> lista = new ArrayList<>();
        String query = "SELECT r.*, c.nombre ||' '|| c.apellido AS nombre_completo, c.dni, h.numero, h.numeroPiso, " +
                "h.numeroHabitacion, h.precio, h.estado AS estado_hab" +
                " FROM reserva r, cliente c, habitacion h" +
                " WHERE r.clienteId = c.idcliente" +
                " AND r.habitacionId = h.idhabitacion" +
                " AND r.estado = 'O'";                    //PREPARAR LA CONSULTA
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query);     //PREPARA LOS DATOS
             ResultSet rs = st.executeQuery();                      //RECIBE LOS DATOS
        ) {
            while (rs.next()) {
                Reserva r = new Reserva();
                Cliente cl = new Cliente();
                Habitacion h = new Habitacion();
                cl.setIdcliente(rs.getInt("clienteID"));
                cl.setNombre(rs.getString("nombre_completo"));
                cl.setDni(rs.getString("dni"));

                h.setIdhabitacion(rs.getInt("habitacionId"));
                h.setNumero(rs.getString("numero"));
                h.setNumeroPiso(rs.getString("numeroPiso"));
                h.setNumeroHabitacion(rs.getString("numeroHabitacion"));
                h.setPrecio(rs.getDouble("precio"));

                r.setIdreserva(rs.getInt("idreserva"));
                r.setFechaReserva(LocalDate.parse(rs.getString("fecha_reserva")));
                r.setFechaIngreso(LocalDate.parse(rs.getString("fecha_ingreso")));
                r.setFechaSalida(LocalDate.parse(rs.getString("fecha_salida")));
                r.setTotal(rs.getDouble("total"));
                r.setEstado(rs.getString("estado"));

                r.setCliente(cl);
                r.setHabitacion(h);

                lista.add(r);
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL LISTAR " + e.getMessage());
        }

        return lista;
    }

    @Override
    public boolean updateStatus(Reserva r) {
        boolean flag = false;
        String query = "UPDATE reserva SET estado = ? WHERE idreserva = ? ";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query);
        ) {
            st.setString(1, r.getEstado());
            st.setInt(2, r.getIdreserva());
            st.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            System.out.println("error al actualizar reserva estado " + e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean validateRoom(Reserva r) {
        boolean ocupado = false;
        String query = "SELECT count(1) FROM reserva" +
                " WHERE habitacionId = ?" +
                "AND estado IN('R','O') " +
                "AND (? < fecha_salida " +
                "AND ? > fecha_ingreso)";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query);
        ) {
            st.setInt(1, r.getHabitacion().getIdhabitacion());
            st.setString(2, r.getFechaIngreso().toString());
            st.setString(3, r.getFechaSalida().toString());

            try (ResultSet rs = st.executeQuery()){
                if (rs.next()){
                    ocupado = rs.getInt(1)>0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al validar la habitación " + e.getMessage());
        }
        return ocupado;
    }


}
