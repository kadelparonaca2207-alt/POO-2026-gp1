package pe.edu.upeu.final_project_gp1.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pe.edu.upeu.final_project_gp1.implementacion.HabitacionDaoImpl;
import pe.edu.upeu.final_project_gp1.interfaces.IHabitacionDao;
import pe.edu.upeu.final_project_gp1.modelo.Cliente;
import pe.edu.upeu.final_project_gp1.modelo.Habitacion;
import pe.edu.upeu.final_project_gp1.utils.AlertUtils;

import java.util.Optional;

public class HabitacionController {

    private AlertUtils alertUtils = new AlertUtils();

    @FXML
    private TableView<Habitacion> tableHabitacion;
    @FXML
    private TableColumn<Habitacion, Integer> ColId;
    @FXML
    private TableColumn<Habitacion, Number> ColNumero;
    @FXML
    private TableColumn<Habitacion, String> ColNumeroPiso;
    @FXML
    private TableColumn<Habitacion, String> ColNumeroHabitacion;
    @FXML
    private TableColumn<Habitacion, String> ColTipo;
    @FXML
    private TableColumn<Habitacion, Double> ColPrecio;
    @FXML
    private TableColumn<Habitacion, String> ColEstado;

    private final IHabitacionDao habitacionDao = new HabitacionDaoImpl();
    private ObservableList<Habitacion> listaHabitacion;

    @FXML
    private void initialize(){
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
                cellData -> cellData.getValue().idhabitacionProperty().asObject());

        ColNumero.setCellValueFactory(
                cellData -> cellData.getValue().numeroProperty().length());

        ColNumeroPiso.setCellValueFactory(
                cellData -> cellData.getValue().numeroPisoProperty());

        ColNumeroHabitacion.setCellValueFactory(
                cellData -> cellData.getValue().numeroHabitacionProperty());

        ColTipo.setCellValueFactory(
                cellData -> cellData.getValue().tipoProperty());
        ColPrecio.setCellValueFactory(
                cellData -> cellData.getValue().precioProperty().asObject());

        ColEstado.setCellValueFactory(cellData -> cellData.getValue().estadoProperty());

        //CARGAR DATOS INICIALES
        cargarDatos();
    }
    private void cargarDatos(){
        listaHabitacion = FXCollections.observableArrayList(habitacionDao.listAll());
        tableHabitacion.setItems(listaHabitacion);
    }
    @FXML
    private void handleDelete(){
        Habitacion selectID = tableHabitacion.getSelectionModel().getSelectedItem();
        if (selectID != null){
            Alert confirm = alertUtils.viewAlerts("Confirmar eliminación", "Estás seguro de eliminar?" + selectID.getNumero(), "CONFIRMATION");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isPresent() && result.get()==ButtonType.OK){
                if (habitacionDao.delete(selectID)){
                    listaHabitacion.remove(selectID);
                }
            } else {
                Alert confirmWarning = alertUtils.viewAlerts("Selección requerida", null, "WARNING");
                confirmWarning.showAndWait();
            }
        }

    }
}
