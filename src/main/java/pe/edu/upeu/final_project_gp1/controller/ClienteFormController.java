package pe.edu.upeu.final_project_gp1.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pe.edu.upeu.final_project_gp1.modelo.Cliente;
import pe.edu.upeu.final_project_gp1.utils.AlertUtils;

public class ClienteFormController {
    private static AlertUtils alertsUtils = new AlertUtils();

    @FXML
    private Label lblTitulo;

    @FXML
    private TextField txtNombre, txtApellido, txtDni, txtEmail;

    @FXML
    private ComboBox<String> cbGenero;

    private Cliente cliente;
    private boolean saved = false;

    @FXML
    public void initialize() {
        cbGenero.getItems().addAll("M", "F");
    }

    public void setCliente(Cliente c) {
        this.cliente = c;
        if (c != null) {
            lblTitulo.setText("Editar cliente");
            txtNombre.setText(c.getNombre());
            txtApellido.setText(c.getApellido());
            txtDni.setText(c.getDni());
            txtEmail.setText(c.getEmail());
            cbGenero.setValue(c.getGenero());
        }
    }

    public boolean isSaved() {
        return saved;
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void cerrar() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

    private boolean validated() {
        if (txtNombre.getText().isEmpty() && txtDni.getText().isEmpty()) {
            Alert alert = alertsUtils.viewAlerts("Error", "Nombre y DNI obligatorios", "WARNING");
            alert.show();
            return false;
        }
        return true;
    }

    @FXML
    private void handleCancel() {
        cerrar();
    }

    @FXML
    private void handleSaved() {
        if (validated()) {
            if (cliente == null) cliente = new Cliente();
            cliente.setNombre(txtNombre.getText());
            cliente.setApellido(txtApellido.getText());
            cliente.setDni(txtDni.getText());
            cliente.setEmail(txtEmail.getText());
            cliente.setGenero(cbGenero.getValue());
            saved = true;
            cerrar();
        }
    }
}
