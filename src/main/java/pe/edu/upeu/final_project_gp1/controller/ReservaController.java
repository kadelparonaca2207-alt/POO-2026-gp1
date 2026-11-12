package pe.edu.upeu.final_project_gp1.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pe.edu.upeu.final_project_gp1.implementacion.ReservaDaoImpl;
import pe.edu.upeu.final_project_gp1.interfaces.IReservaDao;
import pe.edu.upeu.final_project_gp1.modelo.Reserva;
import pe.edu.upeu.final_project_gp1.utils.AlertUtils;

import java.time.LocalDate;
import java.util.Optional;

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
                    String estadoText = switch (estadoBD) {
                        case "R" -> "Reservado";
                        case "O" -> "Ocupado";
                        case "T" -> "Terminado";
                        case "C" -> "Cancelado";
                        default -> "Estado Desconocido";
                    };
                    return new SimpleStringProperty(estadoText);
                });
        ColEstado.setCellFactory(
                cl -> new TableCell<Reserva, String>() {
                    @Override
                    protected void updateItem(String estado, boolean empty) {
                        super.updateItem(estado, empty);
                        if (empty || estado == null) {
                            setText(null);
                            setStyle("");
                        } else {
                            setText(estado);
                            switch (estado) {
                                case "Reservado" -> setStyle("-fx-text-fill: #1B5E20; -fx-font-weight: bold; ");
                                case "Ocupado" -> setStyle("-fx-text-fill: #B71C1C; -fx-font-weight: bold; ");
                                case "Terminado" -> setStyle("-fx-text-fill: #0D47A1; -fx-font-weight: bold; ");
                                case "Cancelado" -> setStyle("-fx-text-fill: #d2691e; -fx-font-weight: bold; ");
                                default -> setStyle("");
                            }
                        }
                    }
                }
        );
        cargarDatos();
    }

    private void cargarDatos() {
        listaReserva = FXCollections.observableArrayList(reservaDao.listAll());
        tableReserva.setItems(listaReserva);
    }

    @FXML
    private void handleCheckIn() {
        updateStatus("O", "Confirmar Check-in", "¿Desea marcar la habitación como ocupada?");
    }

    @FXML
    private void handleCkeckOut() {
        updateStatus("T", "Confirmar Check-out", "¿Desea finalizar la reserva?");
    }

    @FXML
    private void handleCancel() {
        updateStatus("C", "Confirmar cancelación", "¿Estás seguro de cancelar la reserva?");
    }

    @FXML
    private void handleCreate() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/pe/edu/upeu/final_project_gp1/resources/views/reserva-form.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Registrar nueva reserva");
            stage.setScene(new Scene(root));

            // Configurar ventana emergente
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);

            // Asociar con la ventana principal
            stage.initOwner(tableReserva.getScene().getWindow());

            // Mostrar ventana
            stage.showAndWait();

            // Recargar información al cerrar
            cargarDatos();

        } catch (Exception e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al abrir formulario");
            alert.setHeaderText("No se pudo cargar reserva-form.fxml");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void updateStatus(String nuevoEstado, String titulo, String mensaje) {
        Reserva select = tableReserva.getSelectionModel().getSelectedItem();
        if (select == null) {
            Alert alert = alertUtils.viewAlerts("Selección requerida", "Seleccione una reserva", "WARNING");
            alert.showAndWait();
            return;
        }
        Alert confirm = alertUtils.viewAlerts(titulo, mensaje, "CONFIRMATION");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            select.setEstado(nuevoEstado);
            if (reservaDao.updateStatus(select)) {
                cargarDatos();
            }
        }
    }

    @FXML
    private void handleNew() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pe/edu/upeu/final_project_gp1/views/reserva-form.fxml"));
            Parent root = loader.load();
            ReservaFormController controller = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Nueva Reserva");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            if (controller.isGuardado()){
                cargarDatos();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = alertUtils.viewAlerts("Error", "No se pudo abrir el formulario.", "ERROR");
            alert.showAndWait();
        }
    }

}