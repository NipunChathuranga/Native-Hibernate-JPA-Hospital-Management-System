package lk.ijse.dep.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.hms.business.BOFactory;
import lk.ijse.dep.hms.business.BOTypes;
import lk.ijse.dep.hms.business.custom.PatientBO;
import lk.ijse.dep.hms.business.exception.AlreadyExistsInAppoinmentException;
import lk.ijse.dep.hms.dto.PatientDTO;
import lk.ijse.dep.hms.util.PatientTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminManagePatientFormController {
    @FXML
    private AnchorPane AnchrPaneManagePatient;

    @FXML
    private TextField txtFieldPatientID;

    @FXML
    private TextField txtFieldLastName;

    @FXML
    private TextField txtFieldFirstName;

    @FXML
    private TextField txtFieldCity;

    @FXML
    private TextField txtFieldEmail;


    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXComboBox<String> cmbBoxGender;

    @FXML
    private TableView<PatientTM> tblViewPatientDetails;

    @FXML
    private TableColumn<PatientTM, String> tblViewPatientID;

    @FXML
    private TableColumn<PatientTM, String> tblViewFirstName;

    @FXML
    private TableColumn<PatientTM, String> tblViewLastName;

    @FXML
    private TableColumn<PatientTM, String> tblViewGender;

    @FXML
    private TableColumn<PatientTM, String> tblViewCity;

    @FXML
    private TableColumn<PatientTM, String> tblViewEmail;


    @FXML
    private JFXButton btnNewPatient;

    @FXML
    private JFXButton btnDelete;

    private PatientBO patientBO = BOFactory.getInstance().getBO(BOTypes.PATIENT);

    public void initialize() {

        tblViewPatientDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("patientid"));
        tblViewPatientDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("firstname"));
        tblViewPatientDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lastname"));
        tblViewPatientDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblViewPatientDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("city"));
        tblViewPatientDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("email"));


        ObservableList<String> genders = cmbBoxGender.getItems();
        genders.add("M");
        genders.add("F");

        txtFieldPatientID.setDisable(true);
        txtFieldFirstName.setDisable(true);
        txtFieldLastName.setDisable(true);
        txtFieldCity.setDisable(true);
        txtFieldEmail.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);
        cmbBoxGender.setDisable(true);

        try {

            List<PatientDTO> patientDTOS = patientBO.findAllPatients();
            ObservableList<PatientTM> patientTMObservableList = tblViewPatientDetails.getItems();
            for (PatientDTO patientDTO : patientDTOS) {
                patientTMObservableList.add(new PatientTM(patientDTO.getPatientid(), patientDTO.getPatientfirstname(),patientDTO.getPatientlastname(),
                        patientDTO.getGender(), patientDTO.getCity(),patientDTO.getPatientemail()));

            }


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
            Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
        }

        tblViewPatientDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PatientTM>() {
            @Override
            public void changed(ObservableValue<? extends PatientTM> observable, PatientTM oldValue, PatientTM newValue) {
                PatientTM selectedItem = tblViewPatientDetails.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    btnSave.setText("Save");
                    btnDelete.setDisable(true);
                    return;
                }
                btnSave.setText("Update");
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
                txtFieldPatientID.setText(selectedItem.getPatientid());
                txtFieldFirstName.setText(selectedItem.getFirstname());
                txtFieldLastName.setText(selectedItem.getLastname());
                cmbBoxGender.getSelectionModel().select(selectedItem.getGender());
                txtFieldCity.setText(selectedItem.getCity());
                txtFieldEmail.setText(selectedItem.getEmail());
                txtFieldFirstName.setDisable(false);
                txtFieldLastName.setDisable(false);
                txtFieldEmail.setDisable(false);
                txtFieldCity.setDisable(false);
                cmbBoxGender.setDisable(false);


            }
        });

    }


    @FXML
    void btnDelete_OnAction(ActionEvent event) {
        PatientTM selectedItem = tblViewPatientDetails.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure whether you want to delete this patient ?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get() == ButtonType.YES){


                try{
                    patientBO.deletePatient(selectedItem.getPatientid());
                    tblViewPatientDetails.getItems().remove(selectedItem);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Delete Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Patient deleted successfully.");
                    alert.showAndWait();


                }catch (AlreadyExistsInAppoinmentException e){
                    new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
                    Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
                }


            }

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a patient");
            alert.showAndWait();

        }






    }

    @FXML
    void btnNewPatient_OnAction(ActionEvent event) {
        txtFieldPatientID.setDisable(true);
        txtFieldFirstName.setDisable(false);
        txtFieldLastName.setDisable(false);
        txtFieldCity.setDisable(false);
        txtFieldEmail.setDisable(false);
        btnDelete.setDisable(false);
        btnSave.setDisable(false);
        cmbBoxGender.setDisable(false);
        txtFieldFirstName.clear();
        txtFieldLastName.clear();
        txtFieldEmail.clear();
        cmbBoxGender.getSelectionModel().clearSelection();
        txtFieldCity.clear();


        // Generate a new patient id
        int maxId = 0;

        try {
            String lastPatientId = patientBO.getLastPatientId();


            if (lastPatientId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastPatientId.replace("P", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "P00" + maxId;
            } else if (maxId < 100) {
                id = "P0" + maxId;
            } else {
                id = "P" + maxId;
            }
            txtFieldPatientID.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
            Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
        }


    }

    @FXML
    void btnSave_OnAction(ActionEvent event) {

        String patientid = txtFieldPatientID.getText();
        String firstname = txtFieldFirstName.getText();
        String lastname = txtFieldLastName.getText();
        String gender = cmbBoxGender.getSelectionModel().getSelectedItem();
        String city = txtFieldCity.getText();
        String email = txtFieldEmail.getText();

        if (firstname.matches("[A-Z]+[a-z]+$")) {
            if (lastname.matches("[A-Z]+[a-z]+$")) {
                if (city.matches("[A-Z]+[a-z]+$")) {
                    if (email.matches("[a-z]+[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")) {
                        if (gender != null) {

                            if (btnSave.getText().equals("Save")) {

                                ObservableList<PatientTM> patientTMObservableList = tblViewPatientDetails.getItems();
                                PatientDTO patientDTO = new PatientDTO(patientid, firstname, lastname, gender, city, email);
                                try {
                                    patientBO.savePatient(patientDTO);
                                    patientTMObservableList.add(new PatientTM(patientid, firstname, lastname, gender, city, email));
                                    btnNewPatient_OnAction(event);
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Information");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Patient added successfully !!");
                                    alert.showAndWait();


                                } catch (Exception e) {
                                    new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for technical support.").show();
                                    Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
                                }


                            } else {

                                PatientTM selectedpatient = tblViewPatientDetails.getSelectionModel().getSelectedItem();
                                try {

                                    patientBO.updatePatient(new PatientDTO(selectedpatient.getPatientid(), txtFieldFirstName.getText(),
                                            txtFieldLastName.getText(), cmbBoxGender.getSelectionModel().getSelectedItem(), txtFieldCity.getText(),
                                            txtFieldEmail.getText()));

                                    selectedpatient.setFirstname(txtFieldFirstName.getText());
                                    selectedpatient.setLastname(txtFieldLastName.getText());
                                    selectedpatient.setGender(cmbBoxGender.getSelectionModel().getSelectedItem());
                                    selectedpatient.setCity(txtFieldCity.getText());
                                    selectedpatient.setEmail(txtFieldEmail.getText());
                                    tblViewPatientDetails.refresh();
                                    btnNewPatient_OnAction(event);
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Information");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Patient updated successfully !!");
                                    alert.showAndWait();


                                } catch (Exception e) {
                                    new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for technical support.").show();
                                    Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
                                }


                            }


                        } else {

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning Alert");
                            alert.setHeaderText(null);
                            alert.setContentText("Invalid gender, please try again.");
                            alert.showAndWait();


                        }


                    } else {
                        txtFieldEmail.requestFocus();
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning Alert");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid email, please try again.");
                        alert.showAndWait();


                    }


                } else {
                    txtFieldCity.requestFocus();
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Alert");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid city, please try again.");
                    alert.showAndWait();


                }


            } else {
                txtFieldLastName.requestFocus();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Alert");
                alert.setHeaderText(null);
                alert.setContentText("Invalid last name, please try again.");
                alert.showAndWait();

            }


        } else {
            txtFieldFirstName.requestFocus();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Alert");
            alert.setHeaderText(null);
            alert.setContentText("Invalid first name, please try again.");
            alert.showAndWait();
        }


    }

    public void btnBacktoDashboard_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/AdminDashBoardForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.AnchrPaneManagePatient.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();


    }


}
