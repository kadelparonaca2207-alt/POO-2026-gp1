package pe.edu.upeu.final_project_gp1.utils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected  void onHelloButtonClick() {welcomeText.setText("Welcome to JavaFX Application!");
    }
}
