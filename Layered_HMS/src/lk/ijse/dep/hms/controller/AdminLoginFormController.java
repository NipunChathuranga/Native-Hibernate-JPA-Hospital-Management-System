package lk.ijse.dep.hms.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminLoginFormController {

    public AnchorPane AnchrPaneAdmin;
    public Label lblErrorMessage;
    public PasswordField pswrdFieldPassword;
    @FXML
    private TextField txtFieldID;

    @FXML
    private TextField txtFieldPassword;

    @FXML
    private JFXButton btnAdminLogin;

    @FXML
    void btnAdminLogin_OnAction(ActionEvent event) throws IOException {
        if(txtFieldID.getText().equals("admin") && pswrdFieldPassword.getText().equals("admin")){
            URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/AdminDashBoardForm.fxml");
            Parent root = FXMLLoader.load(resource);
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) (this.AnchrPaneAdmin.getScene().getWindow());
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnBacktoDashboard_OnAction(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/DashBoardForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.AnchrPaneAdmin.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }



}
