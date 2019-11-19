package lk.ijse.dep.hms.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.hms.business.BOFactory;
import lk.ijse.dep.hms.business.BOTypes;
import lk.ijse.dep.hms.business.custom.DoctorBO;
import lk.ijse.dep.hms.dto.DoctorDTO;
import lk.ijse.dep.hms.principle.UserLogin;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class DoctorLoginFormController {
    public AnchorPane AnchrPaneDoctor;
    public PasswordField pswrdFieldDoctorPassword;
    @FXML
    private TextField txtFieldDoctorID;

    @FXML
    private TextField txtFieldDoctorPassword;

    @FXML
    private JFXButton btnDoctorLogin;

    private DoctorBO doctorBO = BOFactory.getInstance().getBO(BOTypes.DOCTOR);

    @FXML
    void btnBacktoDashboard_OnAction(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/DashBoardForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.AnchrPaneDoctor.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    @FXML
    void btnDoctorLogin_OnAction(ActionEvent event) throws Exception {

        List<DoctorDTO> doctorDTOS = doctorBO.findAllDoctors();
        for (DoctorDTO doctorDTO : doctorDTOS) {

            if (doctorDTO.getDoctorid().equals(txtFieldDoctorID.getText()) &&
                    doctorDTO.getPassword().equals(pswrdFieldDoctorPassword.getText())) {

                UserLogin.setUserID(txtFieldDoctorID.getText());
                URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/DoctorDashBoardForm.fxml");
                Parent root = FXMLLoader.load(resource);
                Scene scene = new Scene(root);
                Stage primaryStage = (Stage) (this.AnchrPaneDoctor.getScene().getWindow());
                primaryStage.setScene(scene);
                primaryStage.centerOnScreen();
                return;

            }

        }


            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error Alert");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password.");
            alert.showAndWait();
            return;

    }

}
