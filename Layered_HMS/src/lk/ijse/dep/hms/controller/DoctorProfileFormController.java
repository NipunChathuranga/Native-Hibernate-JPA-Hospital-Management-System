package lk.ijse.dep.hms.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.hms.business.BOFactory;
import lk.ijse.dep.hms.business.BOTypes;
import lk.ijse.dep.hms.business.custom.DoctorBO;
import lk.ijse.dep.hms.business.custom.PrescriptionBO;
import lk.ijse.dep.hms.dto.DoctorDTO;
import lk.ijse.dep.hms.principle.UserLogin;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorProfileFormController {


    @FXML
    private AnchorPane AnchrPaneDoctorProfile;

    @FXML
    private TextField txtFieldDoctorID;

    @FXML
    private TextField txtFieldDoctorFName;

    @FXML
    private TextField txtFieldDoctorLName;

    @FXML
    private TextField txtFieldDoctorSpecialization;

    @FXML
    private TextField txtFieldDoctorEmail;

    @FXML
    private TextField txtFieldDoctorFee;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TextField txtFieldDoctorPassword;

    private DoctorBO doctorBO = BOFactory.getInstance().getBO(BOTypes.DOCTOR);
    private String currentDocID = UserLogin.getUserID();


    public void initialize() {
      DecimalFormat df = new DecimalFormat("0.00");

        try {
            txtFieldDoctorID.setText(currentDocID);
            DoctorDTO doctorDTO = doctorBO.findDoctor(currentDocID);
            txtFieldDoctorFName.setText(doctorDTO.getFirstname());
            txtFieldDoctorLName.setText(doctorDTO.getLastname());
            txtFieldDoctorFee.setText(df.format(doctorDTO.getFee()));
            txtFieldDoctorSpecialization.setText(doctorDTO.getSpecialization());
            txtFieldDoctorEmail.setText(doctorDTO.getEmail());
            txtFieldDoctorPassword.setText(doctorDTO.getPassword());
            txtFieldDoctorID.setDisable(true);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
            Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
        }


    }


    @FXML
    void btnUpdate_OnAction(ActionEvent event) {

       try{
           doctorBO.updateDoctor(new DoctorDTO(txtFieldDoctorID.getText(),txtFieldDoctorFName.getText(),
                   txtFieldDoctorLName.getText(),Double.parseDouble(txtFieldDoctorFee.getText()),txtFieldDoctorSpecialization.getText(),
                   txtFieldDoctorEmail.getText(),txtFieldDoctorPassword.getText()));

           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information");
           alert.setHeaderText(null);
           alert.setContentText("Doctor profile updated successfully !!");
           alert.showAndWait();


       } catch (Exception e) {
           new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
           Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
       }


    }


    public void btnBacktoDashboard_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/DoctorDashBoardForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.AnchrPaneDoctorProfile.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }
}

