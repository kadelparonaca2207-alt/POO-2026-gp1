package pe.edu.upeu.final_project_gp1.controller;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pe.edu.upeu.final_project_gp1.implementacion.ReservaDaoImpl;
import pe.edu.upeu.final_project_gp1.interfaces.IReservaDao;
import pe.edu.upeu.final_project_gp1.modelo.Reserva;
import pe.edu.upeu.final_project_gp1.utils.AlertUtils;

import java.time.LocalDate;

public class ReservaController {

    public static AlertUtils alertUtils = new AlertUtils();

    @FXML
    private TableView<Reserva> tableReserva;
    @FXML
    private TableColumn<Reserva, Number> ColNumero;
    @FXML
    private TableColumn<Reserva, Integer> ColId;
    @FXML
    private TableColumn<Reserva, String> ColCliente;
    @FXML
    private TableColumn<Reserva, String> ColHabitacion;
    @FXML
    private TableColumn<Reserva, LocalDate> ColIngreso;
    @FXML
    private TableColumn<Reserva, LocalDate> ColSalida;
    @FXML
    private TableColumn<Reserva, Double> ColTotal;
    @FXML
    private TableColumn<Reserva, String> ColEstado;

    private final IReservaDao reservaDao = new ReservaDaoImpl();
    private ObservableList<Reserva> listaReserva;

    @FXML
    private void initialize() {
        ColNumero.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.valueOf(getIndex() + 1));
                }
            }

        });
        ColId.setCellValueFactory(
                cd -> cd.getValue().idreservaProperty().asObject());
        ColCliente.setCellValueFactory(
                cd -> cd.getValue().getCliente().nombreProperty());
        ColHabitacion.setCellValueFactory(
                cd -> cd.getValue().getHabitacion().numeroHabitacionProperty());
        ColIngreso.setCellValueFactory(
                cd -> cd.getValue().fechaIngresoProperty());
        ColSalida.setCellValueFactory(
                cd -> cd.getValue().fechaSalidaProperty());
        ColTotal.setCellValueFactory(
                cd -> cd.getValue().totalProperty().asObject());
        ColEstado.setCellValueFactory(
                cd -> {
                    String estadoBD = cd.getValue().getEstado();
                    String estadoText = switch (estadoBD){
                        case "R" -> "Reservado";
                        case "O" -> "Ocupado";
                        case "T" -> "Terminado";
                        case "C" -> "Cancelado";
                        default -> "Estado Desconocido";
                    };
                    return new SimpleStringProperty(estadoText);
                });

    }
}