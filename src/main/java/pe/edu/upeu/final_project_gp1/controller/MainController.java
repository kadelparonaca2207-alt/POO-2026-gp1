package pe.edu.upeu.final_project_gp1.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import pe.edu.upeu.final_project_gp1.modelo.UserSession;

import java.io.IOException;


public class MainController {
    @FXML
    private StackPane contentArea;

    @FXML
    private Label lblUsuario;

    @FXML
    private void handleLogout(){
        UserSession.getInstance().cleanSession();
        System.exit(0);
    }

    @FXML

    public void initialize() {
        if (UserSession.getInstance().getUserLogger() != null) {
            lblUsuario.setText("Bienvenido " +UserSession.getInstance().getUserLogger().getNombre());
        }
        showHome();
    }

    @FXML
    private void showHome() {
        loadView("home-view.fxml");
    }

    private void loadView(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pe/edu/upeu/final_project_gp1/views/" + fxml));
            Parent view = loader.load();
            contentArea.getChildren().setAll(view);

        } catch (IOException e) {
            System.err.println("Error de carga de la vista " + e.getMessage());
        }
    }

    @FXML
    private void showCliente() {
        loadView("cliente-view.fxml");
    }

    @FXML
    private void showHabitaciones() {
        loadView("habitacion-view.fxml");
    }

    @FXML
    private void showReserva() {
        loadView("reserva-view.fxml");
    }

}
