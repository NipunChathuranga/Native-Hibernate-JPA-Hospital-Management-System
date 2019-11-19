package lk.ijse.dep.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.hms.business.BOFactory;
import lk.ijse.dep.hms.business.BOTypes;
import lk.ijse.dep.hms.business.custom.AppoinmentBO;
import lk.ijse.dep.hms.business.custom.DoctorBO;
import lk.ijse.dep.hms.business.custom.PatientBO;
import lk.ijse.dep.hms.db.DBConnection;
import lk.ijse.dep.hms.dto.AppoinmentDTO;
import lk.ijse.dep.hms.dto.AppoinmentInfoDTO;
import lk.ijse.dep.hms.dto.DoctorDTO;
import lk.ijse.dep.hms.dto.PatientDTO;
import lk.ijse.dep.hms.util.AppoinmentTM;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminMakeAppoinmentFormController {


    public TextField txtFieldFee;
    public TextField txtFieldGender;
    DecimalFormat df = new DecimalFormat("0.00");
    @FXML
    private AnchorPane AnchrPaneMakeAppoinment;
    @FXML
    private TableView<AppoinmentTM> tblViewAppoinmentDetails;
    @FXML
    private TextField txtFieldAppoinmentID;
    @FXML
    private TextField txtFieldAppoinmentDate;
    @FXML
    private JFXComboBox<String> cmbPatientID;
    @FXML
    private JFXComboBox<String> cmbDocID;
    @FXML
    private TextField txtFieldPatientName;
    @FXML
    private TextField txtFieldSpecialization;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnNewAppoinment;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TextField txtFieldDocName;

    private DoctorBO doctorBO = BOFactory.getInstance().getBO(BOTypes.DOCTOR);
    private PatientBO patientBO = BOFactory.getInstance().getBO(BOTypes.PATIENT);
    private AppoinmentBO appoinmentBO = BOFactory.getInstance().getBO(BOTypes.APPOINMENT);


    public void initialize() {

        tblViewAppoinmentDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("appoinmentid"));
        tblViewAppoinmentDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("patientid"));
        tblViewAppoinmentDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("patientfname"));
        tblViewAppoinmentDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("doctorid"));
        tblViewAppoinmentDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("doctorname"));
        tblViewAppoinmentDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("specialization"));
        tblViewAppoinmentDetails.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("appoinmentdate"));

        txtFieldDocName.setDisable(true);
        txtFieldPatientName.setDisable(true);
        txtFieldSpecialization.setDisable(true);
        txtFieldAppoinmentID.setDisable(true);
        txtFieldAppoinmentDate.setDisable(true);
        txtFieldFee.setDisable(true);
        txtFieldGender.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
        cmbPatientID.setDisable(true);
        cmbDocID.setDisable(true);


        try {

            List<String> docids = doctorBO.getAllDoctorIDs();
            cmbDocID.setItems(FXCollections.observableArrayList(docids));

            List<String> patientids = patientBO.getAllPatientIDs();
            cmbPatientID.setItems(FXCollections.observableArrayList(patientids));

            ObservableList<AppoinmentTM> appoinmentTMS = tblViewAppoinmentDetails.getItems();
            List<AppoinmentInfoDTO> appoinmentInfoDTOS = appoinmentBO.getAppoinmentsInfo();
            for (AppoinmentInfoDTO appoinmentInfo : appoinmentInfoDTOS) {

                appoinmentTMS.add(new AppoinmentTM(appoinmentInfo.getAppoinmentid(), appoinmentInfo.getPatientid(), appoinmentInfo.getPatientfname(),
                        appoinmentInfo.getDoctorid(), appoinmentInfo.getFirstname(), appoinmentInfo.getSpecialization(), appoinmentInfo.getAppoinmentdate()));
            }
//            List<AppoinmentDTO> appoinmentDTOS = appoinmentBO.findAllAppoinments();
//            for (AppoinmentDTO appoinmentDTO : appoinmentDTOS) {
//                DoctorDTO doctorDTO = doctorBO.findDoctor(appoinmentDTO.getDoctorid());
//                PatientDTO patientDTO = patientBO.findPatient(appoinmentDTO.getPatientid());
//
//                appoinmentTMS.add(new AppoinmentTM(appoinmentDTO.getAppoinmentid(), patientDTO.getPatientid(),patientDTO.getPatientfirstname(),
//                        doctorDTO.getDoctorid(), doctorDTO.getFirstname(), doctorDTO.getSpecialization(), appoinmentDTO.getAppoinmentdate()));
//
//
//            }


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for technical support.").show();
            Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
        }

        cmbDocID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String selectedID = cmbDocID.getSelectionModel().getSelectedItem();
                if (selectedID == null) {
                    btnDelete.setDisable(true);
                    btnSave.setDisable(true);
                    return;
                } else {
                    btnSave.setDisable(false);

                }
                try {
                    DoctorDTO doctorDTO = doctorBO.findDoctor(selectedID);
                    txtFieldDocName.setText(doctorDTO.getFirstname());
                    txtFieldSpecialization.setText(doctorDTO.getSpecialization());
                    txtFieldFee.setText(df.format(doctorDTO.getFee()));

                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for technical support.").show();
                    Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
                }
            }
        });

        cmbPatientID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String selectedPatientID = cmbPatientID.getSelectionModel().getSelectedItem();
                if (selectedPatientID == null) {
                    btnSave.setDisable(true);
                    btnDelete.setDisable(true);
                    return;
                } else {
                    btnSave.setDisable(false);

                }
                try {
                    PatientDTO patientDTO = patientBO.findPatient(selectedPatientID);
                    txtFieldPatientName.setText(patientDTO.getPatientfirstname());
                    System.out.println(patientDTO.getGender());
                    txtFieldGender.setText(patientDTO.getGender());
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for technical support.").show();
                    Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
                }
            }
        });


        tblViewAppoinmentDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AppoinmentTM>() {
            @Override
            public void changed(ObservableValue<? extends AppoinmentTM> observable, AppoinmentTM oldValue, AppoinmentTM newValue) {
                AppoinmentTM appoinmentTM = tblViewAppoinmentDetails.getSelectionModel().getSelectedItem();
                if (appoinmentTM != null) {
                    btnDelete.setDisable(false);
                    txtFieldAppoinmentID.setText(appoinmentTM.getAppoinmentid());
                    txtFieldAppoinmentDate.setText(String.valueOf(appoinmentTM.getAppoinmentdate()));
                    cmbDocID.getSelectionModel().select(appoinmentTM.getDoctorid());
                    cmbPatientID.getSelectionModel().select(appoinmentTM.getPatientid());
                    txtFieldDocName.setText(appoinmentTM.getDoctorname());
                    txtFieldSpecialization.setText(appoinmentTM.getSpecialization());
                    try {

                        PatientDTO patientDTO = patientBO.findPatient(appoinmentTM.getPatientid());
                        DoctorDTO doctorDTO = doctorBO.findDoctor(appoinmentTM.getDoctorid());
                        txtFieldGender.setText(patientDTO.getGender());
                        txtFieldFee.setText(String.valueOf(doctorDTO.getFee()));


                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for technical support.").show();
                        Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
                    }
                }
            }
        });


    }


    @FXML
    void btnDelete_OnAction(ActionEvent event) {
        AppoinmentTM appoinmentTM = tblViewAppoinmentDetails.getSelectionModel().getSelectedItem();
        if (appoinmentTM != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure whether you want to delete this appoinment ?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get() == ButtonType.YES) {


                try {
                    appoinmentBO.deleteAppoinent(appoinmentTM.getAppoinmentid());
                    tblViewAppoinmentDetails.getItems().remove(appoinmentTM);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Delete Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Appoinment deleted successfully.");
                    alert.showAndWait();


                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
                    Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
                }
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select an appoinment");
            alert.showAndWait();

        }
    }

    @FXML
    void btnNewAppoinment_OnAction(ActionEvent event) {

        //cmbPatientID.getSelectionModel().clearSelection();

        cmbPatientID.getSelectionModel().clearSelection();
        txtFieldDocName.clear();
        txtFieldPatientName.clear();
        txtFieldSpecialization.clear();
        txtFieldFee.clear();
        txtFieldGender.clear();
        cmbDocID.getSelectionModel().clearSelection();
        btnDelete.setDisable(true);


        cmbPatientID.setDisable(false);
        cmbDocID.setDisable(false);
        txtFieldAppoinmentDate.setText(LocalDate.now() + "");
        // Generate a new id
        int maxId = 0;

        try {
            String lastAppoinmentId = appoinmentBO.getLastAppoinmentId();


            if (lastAppoinmentId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastAppoinmentId.replace("A", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "A00" + maxId;
            } else if (maxId < 100) {
                id = "A0" + maxId;
            } else {
                id = "A" + maxId;
            }
            txtFieldAppoinmentID.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
            Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
        }


    }

    @FXML
    void btnSave_OnAction(ActionEvent event) {

        if (txtFieldDocName.getText().trim().isEmpty() | txtFieldSpecialization.getText().trim().isEmpty() | txtFieldFee.getText().trim().isEmpty()
                | txtFieldPatientName.getText().trim().isEmpty() | txtFieldGender.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Alert");
            alert.setHeaderText(null);
            alert.setContentText("Empty text fields found. Please fill in required fields.");
            alert.showAndWait();

        } else {
            try {
                AppoinmentDTO appoinmentDTO = new AppoinmentDTO(txtFieldAppoinmentID.getText(), cmbPatientID.getSelectionModel().getSelectedItem(),
                        cmbDocID.getSelectionModel().getSelectedItem(), Date.valueOf(txtFieldAppoinmentDate.getText()));
                appoinmentBO.saveAppoinment(appoinmentDTO);
                ObservableList<AppoinmentTM> appoinments = tblViewAppoinmentDetails.getItems();
                appoinments.add(new AppoinmentTM(txtFieldAppoinmentID.getText(), cmbPatientID.getSelectionModel().getSelectedItem(), txtFieldPatientName.getText(),
                        cmbDocID.getSelectionModel().getSelectedItem(), txtFieldDocName.getText(), txtFieldSpecialization.getText(), Date.valueOf(txtFieldAppoinmentDate.getText())));

                tblViewAppoinmentDetails.refresh();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Appoinment Done !!");
                alert.showAndWait();
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("/lk/ijse/dep/hms/report/MakeAppoinmentJasper.jasper"));
                Map<String, Object> params = new HashMap<>();
                params.put("appid", txtFieldAppoinmentID.getText());
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, DBConnection.getInstance().getConnection());
                JasperViewer.viewReport(jasperPrint, false);
                btnNewAppoinment_OnAction(event);


            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for technical support.").show();
                Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
            }


        }

    }

    public void btnBacktoDashboard_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/AdminDashBoardForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.AnchrPaneMakeAppoinment.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }
}
