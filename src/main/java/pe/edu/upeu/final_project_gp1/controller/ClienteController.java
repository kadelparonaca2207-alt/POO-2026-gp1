package pe.edu.upeu.final_project_gp1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pe.edu.upeu.final_project_gp1.implementacion.ClienteDaoImpl;
import pe.edu.upeu.final_project_gp1.interfaces.IClienteDao;
import pe.edu.upeu.final_project_gp1.modelo.Cliente;
import pe.edu.upeu.final_project_gp1.utils.AlertUtils;

import java.util.Optional;

public class ClienteController {

    private AlertUtils alertUtils = new AlertUtils();

    @FXML
    private TableView<Cliente> tableCliente;
    @FXML
    private TableColumn<Cliente, Integer> ColId;
    @FXML
    private TableColumn<Cliente, Number> ColNumero;
    @FXML
    private TableColumn<Cliente, String> ColNombre;
    @FXML
    private TableColumn<Cliente, String> ColApellido;
    @FXML
    private TableColumn<Cliente, String> ColDni;
    @FXML
    private TableColumn<Cliente, String> ColEmail;
    @FXML
    private TableColumn<Cliente, String> ColGenero;

    private final IClienteDao clienteDao = new ClienteDaoImpl();
    private ObservableList<Cliente> listaCliente;

    @FXML
    public void initialize() {

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

        //LLENAR LAS COLUMNAS

        ColId.setCellValueFactory(
                cellData -> cellData.getValue().idclienteProperty().asObject());

        ColNombre.setCellValueFactory(
                cellData -> cellData.getValue().nombreProperty());

        ColApellido.setCellValueFactory(
                cellData -> cellData.getValue().apellidoProperty());

        ColDni.setCellValueFactory(
                cellData -> cellData.getValue().dniProperty());

        ColEmail.setCellValueFactory(
                cellData -> cellData.getValue().emailProperty());

        ColGenero.setCellValueFactory(
                cellData -> cellData.getValue().generoProperty());

        //CARGAR DATOS INICIALES
        cargarDatos();
    }

    private void cargarDatos() {
        listaCliente = FXCollections.observableArrayList(clienteDao.listAll());
        tableCliente.setItems(listaCliente);
    }

    @FXML
    private void handleDelete(){
        Cliente selectID = tableCliente.getSelectionModel().getSelectedItem();
        if (selectID != null){
            Alert confirm = alertUtils.viewAlerts("Confirmar eliminación", "Estás seguro de eliminar?" + selectID.getNombre(), "CONFIRMATION");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isPresent() && result.get()==ButtonType.OK){
                if (clienteDao.delete(selectID)){
                    listaCliente.remove(selectID);
                }
            } else {
                Alert confirmWarning = alertUtils.viewAlerts("Selección requerida", null, "WARNING");
                confirmWarning.showAndWait();
            }
        }

    }


}
