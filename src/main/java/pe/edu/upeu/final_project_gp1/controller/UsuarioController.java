package pe.edu.upeu.final_project_gp1.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pe.edu.upeu.final_project_gp1.HelloApplication;
import pe.edu.upeu.final_project_gp1.implementacion.UsuarioDaoImpl;
import pe.edu.upeu.final_project_gp1.interfaces.IUsuarioDao;
import pe.edu.upeu.final_project_gp1.modelo.UserSession;
import pe.edu.upeu.final_project_gp1.modelo.Usuario;
import pe.edu.upeu.final_project_gp1.utils.AlertUtils;

import javax.imageio.IIOException;
import java.io.IOException;

public class UsuarioController {
    @FXML
    private TextField txUser;
    @FXML
    private PasswordField txPassword;

    private final IUsuarioDao usuarioDao = new UsuarioDaoImpl();

    @FXML
    private void handleLogin() {
        String user = txUser.getText();
        String pass = txPassword.getText();
        if (user.isEmpty() || pass.isEmpty()) {
            Alert alert = AlertUtils.viewAlerts("Campos vacíos", "Ingrese usuario y contraseña", "WARNING");
            alert.showAndWait();
            return;
        }
        Usuario u = usuarioDao.login(user, pass);
        if (u != null){
            Alert alert = AlertUtils.viewAlerts("Acceso autorizado", "Bienvenido/a " + u.getNombre(), "INFORMATION");
            alert.showAndWait();
            UserSession.getInstance().setUserLogger(u);
            navigateToHome();
        } else {
            Alert alert = AlertUtils.viewAlerts("Acceso denegado", "Credenciales inválidas", "ERROR");
            alert.showAndWait();
        }
    }

    private void navigateToHome() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pe/edu/upeu/final_project_gp1/views/main-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) txUser.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setTitle("Inicio sesion");
            stage.show();
        } catch (IOException e){
        System.err.println("Error al cargar home " + e.getMessage());
        e.printStackTrace();
        }
    }
}
