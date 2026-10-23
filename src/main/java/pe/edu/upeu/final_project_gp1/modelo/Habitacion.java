package pe.edu.upeu.final_project_gp1.modelo;

import javafx.beans.property.*;

public class Habitacion {
    private final IntegerProperty id_habitacion = new SimpleIntegerProperty();
    private final StringProperty numero = new SimpleStringProperty();
    private final StringProperty numeroPiso = new SimpleStringProperty();
    private final StringProperty numeroHabitacion = new SimpleStringProperty();
    private final StringProperty tipo = new SimpleStringProperty();
    private final DoubleProperty precio = new SimpleDoubleProperty();
    private final StringProperty estado = new SimpleStringProperty();

    public final String getEstado() {
        return estado.get();
    }

    public final void setEstado(String value) {
        estado.set(value);
    }

    public final StringProperty estadoProperty() {
        return estado;
    }


    public final double getPrecio() {
        return precio.get();
    }

    public final void setPrecio(double value) {
        precio.set(value);
    }

    public final DoubleProperty precioProperty() {
        return precio;
    }


    public final String getTipo() {
        return tipo.get();
    }

    public final void setTipo(String value) {
        tipo.set(value);
    }

    public final StringProperty tipoProperty() {
        return tipo;
    }


    public final String getNumeroHabitacion() {
        return numeroHabitacion.get();
    }

    public final void setNumeroHabitacion(String value) {
        numeroHabitacion.set(value);
    }

    public final StringProperty numeroHabitacionProperty() {
        return numeroHabitacion;
    }


    public final String getNumeroPiso() {
        return numeroPiso.get();
    }

    public final void setNumeroPiso(String value) {
        numeroPiso.set(value);
    }

    public final StringProperty numeroPisoProperty() {
        return numeroPiso;
    }


    public final String getNumero() {
        return numero.get();
    }

    public final void setNumero(String value) {
        numero.set(value);
    }

    public final StringProperty numeroProperty() {
        return numero;
    }


    public final int getIdhabitacion() {
        return id_habitacion.get();
    }

    public final void setIdhabitacion(int value) {
        id_habitacion.set(value);
    }

    public final IntegerProperty idhabitacionProperty() {
        return id_habitacion;
    }




}
