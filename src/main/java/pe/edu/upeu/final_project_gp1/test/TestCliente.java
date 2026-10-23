package pe.edu.upeu.final_project_gp1.test;

import pe.edu.upeu.final_project_gp1.implementacion.ClienteDaoImpl;
import pe.edu.upeu.final_project_gp1.interfaces.IClienteDao;
import pe.edu.upeu.final_project_gp1.modelo.Cliente;

import java.util.List;

public class TestCliente {
    static IClienteDao cl = new ClienteDaoImpl();

    public void insertarCliente() {
        Cliente c = new Cliente();
        c.setNombre("Pugberto");
        c.setApellido("Buleje");
        c.setDni("12345678");
        c.setEmail("PugbertoBuleje67@gmail.com");
        c.setGenero("M");
        boolean result = cl.insert(c);
        if (result) {
            System.out.println("Se insertó al cliente exitosamente!");
        } else {
            System.out.println("Error al insertar cliente...");
        }
    }

    public void deleteCliente() {
        Cliente c = new Cliente();
        c.setIdcliente(2);
        boolean result = cl.delete(c);
        if (result) {
            System.out.println("Se eliminó al cliente exitosamente!");
        } else {
            System.out.println("Error al eliminar cliente...");
        }
    }

    public void listarCliente() {
        List<Cliente> list = cl.listAll();
        if (list != null && !list.isEmpty()) {
            for (Cliente c : list) {
                System.out.println("ID_CLIENTE: " + c.getIdcliente());
                System.out.println("NOMBRE: " + c.getNombre());
                System.out.println("APELLIDO: " + c.getApellido());
                System.out.println("DNI: " + c.getDni());
                System.out.println("EMAIL: " + c.getEmail());
                System.out.println("GENERO: " + c.getGenero());
            }
        } else {
            System.out.println("No hay registros...");
        }
    }

    public static void main(String[] args) {
        TestCliente t = new TestCliente();
        System.out.println("**********");
        t.listarCliente();
        System.out.println("**********");
        t.insertarCliente();
        System.out.println("**********");
        t.listarCliente();
        System.out.println("**********");
        t.deleteCliente();
        System.out.println("**********");
        t.listarCliente();
        System.out.println("**********");
        t.update();
        t.listarCliente();
    }

    public void searchById() {
        Cliente c = cl.searchById(1);
        if (c != null) {
            System.out.println("ID_CLIENTE: " + c.getIdcliente());
            System.out.println("NOMBRE: " + c.getNombre());
            System.out.println("APELLIDO: " + c.getApellido());
            System.out.println("DNI: " + c.getDni());
            System.out.println("EMAIL: " + c.getEmail());
            System.out.println("GENERO: " + c.getGenero());
        } else {
            System.out.println("No hay registros...");
        }
    }
    public void searchByDni() {
        Cliente c = cl.searchByDni("");
        if (c != null) {
            System.out.println("ID_CLIENTE: " + c.getIdcliente());
            System.out.println("NOMBRE: " + c.getNombre());
            System.out.println("APELLIDO: " + c.getApellido());
            System.out.println("DNI: " + c.getDni());
            System.out.println("EMAIL: " + c.getEmail());
            System.out.println("GENERO: " + c.getGenero());
        } else {
            System.out.println("No hay registros...");
        }
    }
    public void update(){
        Cliente c = new Cliente();
        c.setNombre("Jaimito");
        c.setApellido("Noboa");
        c.setDni("12345678");
        c.setEmail("kweefkfk@hotmail.net");
        c.setGenero("M");
        c.setIdcliente(1);
        boolean result = cl.update(c);
        if (result){
            System.out.println("Actualización exitosa.");
        } else {
            System.out.println("Error al actualizar...");
        }
    }

}
