package pe.edu.upeu.final_project_gp1.test;

import pe.edu.upeu.final_project_gp1.implementacion.HabitacionDaoImpl;
import pe.edu.upeu.final_project_gp1.interfaces.IHabitacionDao;
import pe.edu.upeu.final_project_gp1.modelo.Cliente;
import pe.edu.upeu.final_project_gp1.modelo.Habitacion;

import java.util.ArrayList;
import java.util.List;

public class testHabitacion {
    static IHabitacionDao hb = new HabitacionDaoImpl();

    public void insertarHabitacion() {
        Habitacion h = new Habitacion();
        h.setIdhabitacion(1000);
        h.setNumero("100");
        h.setNumeroPiso("1");
        h.setNumeroHabitacion("101");
        h.setTipo("Matrimonial");
        h.setPrecio(75.00);
        h.setEstado("Disponible");
        boolean result = hb.insert(h);
        if (result) {
            System.out.println("Se insertó exitosamente.");
        } else {
            System.out.println("Error al insertar habitación..");
        }
    }

    public void deleteHabitacion() {
        Habitacion h = new Habitacion();
        h.setIdhabitacion(2);
        boolean result = hb.delete(h);
        if (result) {
            System.out.println("Habitación eliminada exitosamente!");
        } else {
            System.out.println("Error al eliminar habitación..");
        }
    }

    public void listarHabitacion() {
        List<Habitacion> list = hb.listAll();
        if (list != null || !list.isEmpty()) {
            for (Habitacion h : list) {
                System.out.println("ID_HABITACION: " + h.getIdhabitacion());
                System.out.println("NUMERO: " + h.getNumero());
                System.out.println("NUMERO_PISO: " + h.getNumeroPiso());
                System.out.println("NUMERO_HABITACION: " + h.getNumeroHabitacion());
                System.out.println("TIPO: " + h.getTipo());
                System.out.println("PRECIO: " + h.getPrecio());
                System.out.println("ESTADO: " + h.getEstado());
            }
        } else {
            System.out.println("No hay registros...");
        }
    }

    public void update(){
        Habitacion h = new Habitacion();
        h.setNumero("10009");
        h.setNumeroPiso("9");
        h.setNumeroHabitacion("999");
        h.setTipo("Individual");
        h.setPrecio(30.0);
        h.setEstado("OCUPADO");
        boolean result = hb.update(h);
        if (result){
            System.out.println("Actualización exitosa.");
        } else {
            System.out.println("Error al actualizar...");
        }
    }

    public static void main(String[] args) {
        testHabitacion t = new testHabitacion();
        System.out.println("**********");
        t.listarHabitacion();
    }
}
