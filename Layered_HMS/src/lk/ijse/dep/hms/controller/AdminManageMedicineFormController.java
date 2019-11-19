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
import lk.ijse.dep.hms.business.custom.MedicineBO;
import lk.ijse.dep.hms.business.exception.AlreadyExistsInPrescriptionDetailException;
import lk.ijse.dep.hms.dto.MedicineDTO;
import lk.ijse.dep.hms.util.MedicineTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminManageMedicineFormController {

    public JFXButton btnNewMedicine;
    @FXML
    private AnchorPane AnchrPaneManageMedicine;

    @FXML
    private TextField txtFieldMedicineID;

    @FXML
    private TextField txtFieldBrandName;

    @FXML
    private TextField txtFieldDrugName;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXComboBox<String> cmbMedicineType;

    @FXML
    private TableView<MedicineTM> tblViewMedicineDetails;


    @FXML
    private JFXButton btnDelete;

    private MedicineBO medicineBO = BOFactory.getInstance().getBO(BOTypes.MEDICINE);

    public void initialize() {
        tblViewMedicineDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("medicineid"));
        tblViewMedicineDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("drugname"));
        tblViewMedicineDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("brandname"));
        tblViewMedicineDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("drugtype"));

        ObservableList<String> typelist = cmbMedicineType.getItems();
        typelist.add("Tablet");
        typelist.add("Capsule");
        typelist.add("Liquid");

        txtFieldMedicineID.setDisable(true);
        txtFieldDrugName.setDisable(true);
        txtFieldBrandName.setDisable(true);
        cmbMedicineType.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);

        try {

            List<MedicineDTO> medicineDTOS = medicineBO.findAllMedicines();
            ObservableList<MedicineTM> medicinelist = tblViewMedicineDetails.getItems();
            for (MedicineDTO medicineDTO : medicineDTOS) {
                medicinelist.add(new MedicineTM(medicineDTO.getMedicineid(), medicineDTO.getDrugname(), medicineDTO.getBrandname(),
                        medicineDTO.getDrugtype()));
            }


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
            Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
        }


        tblViewMedicineDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MedicineTM>() {
            @Override
            public void changed(ObservableValue<? extends MedicineTM> observable, MedicineTM oldValue, MedicineTM newValue) {
                MedicineTM selectedItem = tblViewMedicineDetails.getSelectionModel().getSelectedItem();
                if (selectedItem == null) {
                    btnSave.setText("Save");
                    btnDelete.setDisable(true);
                    return;
                }

                btnSave.setText("Update");
                btnDelete.setDisable(false);
                btnSave.setDisable(false);
                txtFieldMedicineID.setText(selectedItem.getMedicineid());
                txtFieldDrugName.setText(selectedItem.getDrugname());
                txtFieldBrandName.setText(selectedItem.getBrandname());
                cmbMedicineType.getSelectionModel().select(selectedItem.getDrugtype());
                txtFieldDrugName.setDisable(false);
                txtFieldBrandName.setDisable(false);
                cmbMedicineType.setDisable(false);
            }
        });


    }


    @FXML
    void btnDelete_OnAction(ActionEvent event) {
        MedicineTM selectedmedicineTM = tblViewMedicineDetails.getSelectionModel().getSelectedItem();
        if (selectedmedicineTM != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure whether you want to delete this medicine ?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                try {
                    medicineBO.findMedicine(selectedmedicineTM.getMedicineid());
                    try {
                        medicineBO.deleteMedicine(selectedmedicineTM.getMedicineid());
                        tblViewMedicineDetails.getItems().remove(selectedmedicineTM);
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Delete Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Medicine deleted successfully.");
                        alert.showAndWait();


                    } catch (AlreadyExistsInPrescriptionDetailException e) {
                        new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
                    }


                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
                    Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
                }


            }


        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a medicine");
            alert.showAndWait();
        }


    }

    @FXML
    void btnSave_OnAction(ActionEvent event) {

        String name = txtFieldDrugName.getText();
        String brand = txtFieldBrandName.getText();
        String type = cmbMedicineType.getSelectionModel().getSelectedItem();

        if (name.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) {
            if (brand.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) {
                if (type != null) {
                    if (btnSave.getText().equals("Save")) {
                        ObservableList<MedicineTM> medlist = tblViewMedicineDetails.getItems();
                        MedicineDTO medicineDTO = new MedicineDTO(txtFieldMedicineID.getText(), name, brand, type);

                        try {

                            medicineBO.saveMedicine(medicineDTO);
                            medlist.add(new MedicineTM(txtFieldMedicineID.getText(), name, brand, type));
                            btnNewMedicine_OnAction(event);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Medicine Information");
                            alert.setHeaderText(null);
                            alert.setContentText("Medicine added successfully !!");
                            alert.showAndWait();


                        } catch (Exception e) {
                            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for technical support.").show();
                            Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
                        }


                    } else {

                        MedicineTM selecteditem = tblViewMedicineDetails.getSelectionModel().getSelectedItem();
                        try {
                            MedicineDTO medicineDTO = new MedicineDTO(selecteditem.getMedicineid(), txtFieldDrugName.getText(), txtFieldBrandName.getText(),
                                    cmbMedicineType.getSelectionModel().getSelectedItem());
                            medicineBO.updateMedicine(medicineDTO);
                            selecteditem.setDrugname(txtFieldDrugName.getText());
                            selecteditem.setBrandname(txtFieldBrandName.getText());
                            selecteditem.setDrugtype(cmbMedicineType.getSelectionModel().getSelectedItem());
                            tblViewMedicineDetails.refresh();
                            btnNewMedicine_OnAction(event);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information");
                            alert.setHeaderText(null);
                            alert.setContentText("Medicine updated successfully !!");
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
                    alert.setContentText("Invalid medicine type, please try again.");
                    alert.showAndWait();


                }


            } else {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Alert");
                alert.setHeaderText(null);
                alert.setContentText("Invalid brand name, please try again.");
                alert.showAndWait();


            }


        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Alert");
            alert.setHeaderText(null);
            alert.setContentText("Invalid medicine name, please try again.");
            alert.showAndWait();
        }


    }


    public void btnBacktoDashboard_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/AdminDashBoardForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.AnchrPaneManageMedicine.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    public void btnNewMedicine_OnAction(ActionEvent actionEvent) {
        txtFieldDrugName.clear();
        txtFieldBrandName.clear();
        cmbMedicineType.getSelectionModel().clearSelection();
        txtFieldDrugName.setDisable(false);
        txtFieldBrandName.setDisable(false);
        cmbMedicineType.setDisable(false);
        btnSave.setDisable(false);
        btnDelete.setDisable(false);
        cmbMedicineType.getSelectionModel().clearSelection();

        // Generate a new id
        int maxId = 0;

        try {
            String lastMedicineId = medicineBO.getLastMedicineId();


            if (lastMedicineId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastMedicineId.replace("M", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "M00" + maxId;
            } else if (maxId < 100) {
                id = "M0" + maxId;
            } else {
                id = "M" + maxId;
            }
            txtFieldMedicineID.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
            Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
        }


    }
}
