package pe.edu.upeu.final_project_gp1.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {
    private final StringProperty nombre = new SimpleStringProperty();

    public final String getNombre() {
        return nombre.get();
    }

    public final void setNombre(String value) {
        nombre.set(value);
    }

    public final StringProperty nombreProperty() {
        return nombre;
    }


    private final StringProperty apellido = new SimpleStringProperty();

    public final String getApellido() {
        return apellido.get();
    }

    public final void setApellido(String value) {
        apellido.set(value);
    }

    public final StringProperty apellidoProperty() {
        return apellido;
    }


    private final StringProperty dni = new SimpleStringProperty();

    public final String getDni() {
        return dni.get();
    }

    public final void setDni(String value) {
        dni.set(value);
    }

    public final StringProperty dniProperty() {
        return dni;
    }

    public Persona() {
    }

    public Persona(String nombre, String apellido, String dni){
        setNombre(nombre);
        setApellido(apellido);
        setDni(dni);
    }
}
