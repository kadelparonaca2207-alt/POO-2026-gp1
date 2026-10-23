package pe.edu.upeu.final_project_gp1.interfaces;

import pe.edu.upeu.final_project_gp1.modelo.Habitacion;

import java.util.List;

public interface IHabitacionDao {

    public boolean insert(Habitacion h);
    public boolean update(Habitacion h);
    public boolean delete(Habitacion h);
    public Habitacion SearchById(int id, String numero, String numeroPiso, String numeroHab, String tipo, Double precio, String estado);
    public List<Habitacion> listAll();
}
