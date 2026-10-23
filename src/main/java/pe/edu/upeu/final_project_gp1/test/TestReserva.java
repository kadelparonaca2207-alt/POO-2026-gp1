package pe.edu.upeu.final_project_gp1.test;

import pe.edu.upeu.final_project_gp1.implementacion.ReservaDaoImpl;
import pe.edu.upeu.final_project_gp1.interfaces.IReservaDao;
import pe.edu.upeu.final_project_gp1.modelo.Cliente;
import pe.edu.upeu.final_project_gp1.modelo.Habitacion;
import pe.edu.upeu.final_project_gp1.modelo.Reserva;

import java.time.LocalDate;
import java.util.List;

public class TestReserva {

    static IReservaDao Dao = new ReservaDaoImpl();

    public void insert() {
        Reserva r = new Reserva();
        Cliente c = new Cliente();
        Habitacion h = new Habitacion();

        c.setIdcliente(2);
        r.setCliente(c);

        h.setIdhabitacion(1000);
        r.setHabitacion(h);

        r.setFechaReserva(LocalDate.now());
        r.setFechaIngreso(LocalDate.parse("2026-10-23"));
        r.setFechaSalida(LocalDate.parse("2026-10-25"));
        r.setTotal(200);
        r.setEstado("Reservado");
        boolean result = Dao.insert(r);
        if (result) {
            System.out.println("Reserva creada");
        } else {
            System.out.println("Error al reservar...");
        }
    }

    public void list() {
        List<Reserva> lista = Dao.listAll();
        if (lista != null && !lista.isEmpty()) {
            for (Reserva r : lista){
                System.out.println("ID_RESERVA: " + r.getIdreserva());
                System.out.println("FECHA_RESERVA: " + r.getFechaReserva());
                System.out.println("CLIENTE: " + r.getCliente().getNombre());
                System.out.println("FECHA_INGRESO: " + r.getFechaIngreso());
                System.out.println("FECHA_SALIDA: " + r.getFechaSalida());
                System.out.println("NUMERO: " + r.getHabitacion().getNumero());
                System.out.println("NUMERO_HAB: " + r.getHabitacion().getNumeroHabitacion());
                System.out.println("PISO: " + r.getHabitacion().getNumeroPiso());
                System.out.println("PRECIO: " + r.getHabitacion().getPrecio());
                System.out.println("ESTADO: " + r.getEstado());
            }
        } else {
            System.out.println("No hay registros.");
        }
    }

    public void updateStatus(){
        Reserva r = new Reserva();
        r.setEstado("T");
        r.setIdreserva(1);
        boolean result = Dao.updateStatus(r);
        if (result){
            System.out.println("Actualizacion completa.");
        } else {
            System.out.println("Error al actualizar.");
        }
    }

    public void validateHab(){

        Habitacion h = new Habitacion();
        Reserva r = new Reserva();

        h.setIdhabitacion(1);
        r.setHabitacion(h);

        r.setFechaIngreso(LocalDate.parse("2026-10-24"));
        r.setFechaSalida(LocalDate.parse("2026-10-26"));
        boolean ocupado = Dao.validateRoom(r);

        if (ocupado){
            System.out.println("Habitacion ocupada");
        } else {
            System.out.println("Habitacion desocupada");
        }

    }

    public static void main(String[] args) {
        TestReserva t = new TestReserva();
       // t.insert();
      //  t.list();
       // t.updateStatus();
        t.validateHab();
    }


}
