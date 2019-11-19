package lk.ijse.dep.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import lk.ijse.dep.hms.business.custom.MedicineBO;
import lk.ijse.dep.hms.business.custom.PrescriptionBO;
import lk.ijse.dep.hms.db.DBConnection;
import lk.ijse.dep.hms.dto.AppoinmentDTO;
import lk.ijse.dep.hms.dto.MedicineDTO;
import lk.ijse.dep.hms.dto.PrescriptionDTO;
import lk.ijse.dep.hms.dto.PrescriptionDetailDTO;
import lk.ijse.dep.hms.principle.UserLogin;
import lk.ijse.dep.hms.util.AppoinmentTM;
import lk.ijse.dep.hms.util.PrescripDetailTM;
import lk.ijse.dep.hms.util.PrescriptionHistoryTM;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IssuePrescriptionFormController {


    public JFXComboBox<String> cmbAppoinmentID;
    public TextField txtFieldMedName;
    public TextField txtFieldBrandName;
    public TextField txtFieldDrugType;
    public JFXButton btnAdd;
    public JFXButton btnNewPrescription;
    public JFXButton btnIssuePrescription;
    public TextField txtFieldPatientID;
    public JFXComboBox<String> cmbMedicineID;
    public TextField txtFieldDocID;
    public JFXButton btnDelete;
    public AnchorPane AnchrPaneIssuePres;
    public TableView<PrescripDetailTM> tblViewMedicineDetails;
    public TextField txtFieldPrescriptionID;
    public TextField txtFieldDate;

    private PrescriptionBO prescriptionBO = BOFactory.getInstance().getBO(BOTypes.PRESCRIPTION);
    private AppoinmentBO appoinmentBO = BOFactory.getInstance().getBO(BOTypes.APPOINMENT);
    private MedicineBO medicineBO = BOFactory.getInstance().getBO(BOTypes.MEDICINE);


    public void initialize() {


        tblViewMedicineDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("medicineid"));
        tblViewMedicineDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("drugname"));
        tblViewMedicineDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("brandname"));
        tblViewMedicineDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("drugtype"));

        txtFieldPrescriptionID.setDisable(false);
        txtFieldDate.setDisable(true);
        txtFieldPatientID.setDisable(true);
        cmbAppoinmentID.setDisable(true);
        cmbMedicineID.setDisable(true);
        txtFieldMedName.setDisable(true);
        txtFieldDrugType.setDisable(true);
        txtFieldBrandName.setDisable(true);
        txtFieldDocID.setDisable(true);
        btnAdd.setDisable(true);
        btnDelete.setDisable(true);
        btnIssuePrescription.setDisable(true);


        try {

            ObservableList<String> idlist = cmbAppoinmentID.getItems();
            List<AppoinmentDTO> appoinments = appoinmentBO.findAllAppoinments();

//            for(PrescriptionDTO prescriptionDTO:history){
//                for(AppoinmentDTO appoinmentDTO:appoinments){
//                    if(!prescriptionDTO.getAppoinmentid().equals(appoinmentDTO.getAppoinmentid())){
//                        System.out.println(appoinmentDTO.getAppoinmentid());
//
//                    }
//                    return;
//                }
//            }


//            for(PrescriptionDTO preshistory : history){
//                System.out.println(preshistory.getAppoinmentid());
//                if(appoinmentBO.findAppoinment(preshistory.getAppoinmentid())!=null) {
//                   System.out.println("Prescription Already issued");
//               }else{
//                    System.out.println(".........................");
//                   idlist.add(preshistory.getAppoinmentid());
//               }
//            }
            //List<String> appoinmentids = appoinmentBO.getAllAppoinmentIDs();

//            for (PrescriptionHistoryDTO preshistory : history) {
//                System.out.println("Appoinment ID : "+ id);
//                System.out.println("History : "+ preshistory.getAppoinmentid());
//
            List<String> medids = medicineBO.getAllMedicineIDs();
            cmbMedicineID.setItems(FXCollections.observableArrayList(medids));

            ObservableList<String> appids = cmbAppoinmentID.getItems();
            List<AppoinmentDTO> docappoinments = appoinmentBO.findAppoinmentsByDoctorID(UserLogin.getUserID());
            for (AppoinmentDTO appoinmentDTO : docappoinments) {
                appids.add(appoinmentDTO.getAppoinmentid());
            }


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for technical support.").show();
            Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
        }

        tblViewMedicineDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PrescripDetailTM>() {
            @Override
            public void changed(ObservableValue<? extends PrescripDetailTM> observable, PrescripDetailTM oldValue, PrescripDetailTM newValue) {
                PrescripDetailTM prescripDetailTM = tblViewMedicineDetails.getSelectionModel().getSelectedItem();
                if(prescripDetailTM==null){
                    return;
                }
                btnDelete.setDisable(false);

            }
        });




        cmbAppoinmentID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String selectedid = (String) cmbAppoinmentID.getSelectionModel().getSelectedItem();
                if (selectedid == null) {
                    btnAdd.setText("Add");
                    btnDelete.setDisable(true);
                } else {
                    try {
                        List<PrescriptionDTO> history = prescriptionBO.findAllPrescriptions();
                        for (PrescriptionDTO prescriptionDTO : history) {
                            if (prescriptionDTO.getAppoinmentid().equals(selectedid)) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error Alert");
                                alert.setHeaderText(null);
                                alert.setContentText("You have already issued a prescription for this appoinment.");
                                alert.showAndWait();
                                return;
                            }

                        }

                        AppoinmentDTO appoinmentDTO = appoinmentBO.findAppoinment(selectedid);
                        txtFieldPatientID.setText(appoinmentDTO.getPatientid());
                        txtFieldDocID.setText(appoinmentDTO.getDoctorid());
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for technical support.").show();
                        Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
                    }


                }
            }
        });


        cmbMedicineID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String selectedmediid = cmbMedicineID.getSelectionModel().getSelectedItem();
                if (selectedmediid == null) {
                    btnAdd.setText("Add");
                    btnDelete.setDisable(true);
                }
                ObservableList<PrescripDetailTM> details = tblViewMedicineDetails.getItems();
                for (PrescripDetailTM presdetail : details) {
                    if (selectedmediid == null) {
                        return;
                    }
                    if (selectedmediid.equals(presdetail.getMedicineid())) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning Alert");
                        alert.setHeaderText(null);
                        alert.setContentText("Sorry, This medicine is already added to the table.");
                        alert.showAndWait();
                        return;
                    }
                }
                if (selectedmediid == null) {
                    btnAdd.setText("Add");
                    btnDelete.setDisable(true);

                } else {
                    try {
//                      List<String> medods = medicineBO.getAllMedicineIDs();
//                      cmbMedicineID.setItems(FXCollections.observableList(medods));
                        MedicineDTO medicineDTO = medicineBO.findMedicine(selectedmediid);
                        txtFieldBrandName.setText(medicineDTO.getBrandname());
                        txtFieldDrugType.setText(medicineDTO.getDrugtype());
                        txtFieldMedName.setText(medicineDTO.getDrugname());

                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for technical support.").show();
                        Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
                    }

                }
            }
        });


    }


    public void btnAdd_OnAction(ActionEvent actionEvent) {
        String selectedmediid = cmbMedicineID.getSelectionModel().getSelectedItem();
        ObservableList<PrescripDetailTM> medicineDetailsItems = tblViewMedicineDetails.getItems();
        for (PrescripDetailTM presdetail : medicineDetailsItems) {
            if (selectedmediid.equals(presdetail.getMedicineid())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Alert");
                alert.setHeaderText(null);
                alert.setContentText("Sorry, This medicine is already added to the table.");
                alert.showAndWait();
                return;
            }
        }


        if (cmbAppoinmentID.getSelectionModel().getSelectedItem() != null && cmbMedicineID.getSelectionModel().getSelectedItem() != null) {
            ObservableList<PrescripDetailTM> details = tblViewMedicineDetails.getItems();
            details.add(new PrescripDetailTM(cmbMedicineID.getSelectionModel().getSelectedItem(),
                    txtFieldMedName.getText(), txtFieldBrandName.getText(), txtFieldDrugType.getText()));

            if (tblViewMedicineDetails.getItems().isEmpty()) {
                btnIssuePrescription.setDisable(true);
            } else {
                btnIssuePrescription.setDisable(false);
            }
            cmbMedicineID.getSelectionModel().clearSelection();
            txtFieldMedName.clear();
            txtFieldDrugType.clear();
            txtFieldBrandName.clear();

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Alert");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all required fields.");
            alert.showAndWait();
        }


    }

    public void btnNewPrescription_OnAction(ActionEvent actionEvent) {
        txtFieldPatientID.clear();
        cmbAppoinmentID.getSelectionModel().clearSelection();
        cmbMedicineID.getSelectionModel().clearSelection();
        tblViewMedicineDetails.getItems().clear();
        tblViewMedicineDetails.refresh();
        txtFieldMedName.clear();
        txtFieldDrugType.clear();
        txtFieldBrandName.clear();
        txtFieldDocID.clear();
        cmbAppoinmentID.setDisable(false);
        cmbMedicineID.setDisable(false);
        btnAdd.setDisable(false);
        txtFieldDate.setText(LocalDate.now() + "");
        if (tblViewMedicineDetails.getItems().isEmpty()) {
            btnIssuePrescription.setDisable(true);
        } else {
            btnIssuePrescription.setDisable(false);
        }

        // Generate a new id
        int maxId = 0;

        try {
            String lastPrescriptionId = prescriptionBO.getLastPrescriptionId();


            if (lastPrescriptionId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastPrescriptionId.replace("PR", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "PR00" + maxId;
            } else if (maxId < 100) {
                id = "PR0" + maxId;
            } else {
                id = "PR" + maxId;
            }
            txtFieldPrescriptionID.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
            Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
        }

    }

    public void btnIssuePrescription_OnAction(ActionEvent actionEvent) {
        List<PrescriptionDetailDTO> detailslist = new ArrayList<>();
        for (PrescripDetailTM detailtm : tblViewMedicineDetails.getItems()) {
            detailslist.add(new PrescriptionDetailDTO(detailtm.getMedicineid()));
        }

        PrescriptionDTO presdto = new PrescriptionDTO(txtFieldPrescriptionID.getText(),
                cmbAppoinmentID.getSelectionModel().getSelectedItem(),
                Date.valueOf(txtFieldDate.getText()), detailslist);

        try {
            prescriptionBO.issuePrescription(presdto);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Prescription Done !!");
            alert.showAndWait();
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("/lk/ijse/dep/hms/report/IssuePrescriptionMain.jasper"));
            Map<String, Object> params = new HashMap<>();
            params.put("presid", txtFieldPrescriptionID.getText());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
            btnNewPrescription_OnAction(actionEvent);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
            Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
        }
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {

       PrescripDetailTM prescriptionDTM = tblViewMedicineDetails.getSelectionModel().getSelectedItem();
        if (prescriptionDTM != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure whether you want to delete this medicine from prescription ?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                try {

                    tblViewMedicineDetails.getItems().remove(prescriptionDTM);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Delete Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Medicine deleted successfully.");
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

    public void btnBacktoDashboard_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/DoctorDashBoardForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.AnchrPaneIssuePres.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }
}

