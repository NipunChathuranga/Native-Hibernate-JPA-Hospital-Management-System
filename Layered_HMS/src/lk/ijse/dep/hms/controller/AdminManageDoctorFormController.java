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
import javafx.util.Callback;
import lk.ijse.dep.hms.business.BOFactory;
import lk.ijse.dep.hms.business.BOTypes;
import lk.ijse.dep.hms.business.custom.DoctorBO;
import lk.ijse.dep.hms.business.exception.AlreadyExistsInAppoinmentException;
import lk.ijse.dep.hms.dto.DoctorDTO;
import lk.ijse.dep.hms.util.DoctorTM;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminManageDoctorFormController {

    public TableColumn clmnFee;
    DecimalFormat df = new DecimalFormat("0.00");
    @FXML
    private AnchorPane AnchrPaneManageDoctor;
    @FXML
    private TextField txtFieldDoctorID;
    @FXML
    private TextField txtFieldDoctorLName;
    @FXML
    private TextField txtFieldDoctorFName;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldPassword;
    @FXML
    private JFXButton btnSave;
    @FXML
    private TableView<DoctorTM> tblViewDoctors;
    @FXML
    private JFXButton btnNewDoctor;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXComboBox<String> cmbSpecialization;
    @FXML
    private TextField txtFieldFee;
    private DoctorBO doctorBO = BOFactory.getInstance().getBO(BOTypes.DOCTOR);

    public void initialize() {

        txtFieldDoctorID.setDisable(true);
        txtFieldDoctorFName.setDisable(true);
        txtFieldDoctorLName.setDisable(true);
        txtFieldFee.setDisable(true);
        cmbSpecialization.setDisable(true);
        txtFieldEmail.setDisable(true);
        txtFieldPassword.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);

        tblViewDoctors.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("doctorid"));
        tblViewDoctors.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("firstname"));
        tblViewDoctors.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lastname"));
        tblViewDoctors.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("fee"));
        tblViewDoctors.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("specialization"));
        tblViewDoctors.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblViewDoctors.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("password"));

        ObservableList<String> speclist = cmbSpecialization.getItems();
        speclist.add("Immunologist");
        speclist.add("Anesthesiologist");
        speclist.add("Cardiologist");
        speclist.add("Gastroenterologist");
        speclist.add("Dermatologist");
        DecimalFormat df = new DecimalFormat("0.00");


        try {

            ObservableList<DoctorTM> doctorTMS = tblViewDoctors.getItems();
            List<DoctorDTO> doctors = doctorBO.findAllDoctors();
            for (DoctorDTO doctorDTO : doctors) {
                doctorTMS.add(new DoctorTM(doctorDTO.getDoctorid(), doctorDTO.getFirstname(), doctorDTO.getLastname(), doctorDTO.getFee(),
                        doctorDTO.getSpecialization(), doctorDTO.getEmail(), doctorDTO.getPassword()));
            }

            changeCellFormatFee();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
            Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
        }


        tblViewDoctors.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DoctorTM>() {
            @Override
            public void changed(ObservableValue<? extends DoctorTM> observable, DoctorTM oldValue, DoctorTM newValue) {
                DoctorTM selecteditem = tblViewDoctors.getSelectionModel().getSelectedItem();

                if (selecteditem == null) {
                    btnSave.setText("Save");
                    btnDelete.setDisable(true);
                } else {
                    btnSave.setText("Update");
                    btnDelete.setDisable(false);
                    btnSave.setDisable(false);
                    txtFieldDoctorFName.setDisable(false);
                    txtFieldDoctorLName.setDisable(false);
                    txtFieldFee.setDisable(false);
                    cmbSpecialization.setDisable(false);
                    txtFieldEmail.setDisable(false);
                    txtFieldPassword.setDisable(false);
                    txtFieldDoctorID.setText(selecteditem.getDoctorid());
                    txtFieldDoctorFName.setText(selecteditem.getFirstname());
                    txtFieldDoctorLName.setText(selecteditem.getLastname());
                    txtFieldFee.setText(df.format(selecteditem.getFee()));
                    cmbSpecialization.getSelectionModel().select(selecteditem.getSpecialization());
                    txtFieldEmail.setText(selecteditem.getEmail());
                    txtFieldPassword.setText(selecteditem.getPassword());

                }

            }
        });


    }


    @FXML
    void btnDelete_OnAction(ActionEvent event) {
        DoctorTM selectedItem = tblViewDoctors.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure whether you want to delete this doctor ?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get() == ButtonType.YES) {

                try {
                    doctorBO.deleteDoctor(selectedItem.getDoctorid());
                    tblViewDoctors.getItems().remove(selectedItem);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Delete Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Doctor deleted successfully.");
                    alert.showAndWait();

                } catch (AlreadyExistsInAppoinmentException e) {
                    new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
                    Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
                }


            }


        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a doctor");
            alert.showAndWait();


        }

    }

    @FXML
    void btnNewDoctor_OnAction(ActionEvent event) {

        txtFieldDoctorFName.clear();
        txtFieldDoctorLName.clear();
        txtFieldFee.clear();
        cmbSpecialization.getSelectionModel().clearSelection();
        txtFieldEmail.clear();
        txtFieldPassword.clear();
        btnSave.setText("Save");
        btnSave.setDisable(false);
        btnDelete.setDisable(false);
        txtFieldDoctorFName.setDisable(false);
        txtFieldDoctorLName.setDisable(false);
        txtFieldFee.setDisable(false);
        cmbSpecialization.setDisable(false);
        txtFieldEmail.setDisable(false);
        txtFieldPassword.setDisable(false);
        // Generate a new id
        int maxId = 0;

        try {
            String lastDoctorId = doctorBO.getLastDoctorId();


            if (lastDoctorId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastDoctorId.replace("D", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "D00" + maxId;
            } else if (maxId < 100) {
                id = "D0" + maxId;
            } else {
                id = "D" + maxId;
            }
            txtFieldDoctorID.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
            Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
        }


    }

    @FXML
    void btnSave_OnAction(ActionEvent event) {

        String id = txtFieldDoctorID.getText();
        String fname = txtFieldDoctorFName.getText();
        String lname = txtFieldDoctorLName.getText();
        String fee = txtFieldFee.getText();
        String spec = cmbSpecialization.getSelectionModel().getSelectedItem();
        String email = txtFieldEmail.getText();
        String password = txtFieldPassword.getText();

        if (fname.matches("[A-Z]+[a-z]+$")) {
            if (lname.matches("[A-Z]+[a-z]+$")) {
                if (fee.matches("([0-9]+\\.[0]{2})")) {
                    if (spec != null) {
                        if (email.matches("[a-z]+[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")) {
                            if (password.matches("^[a-zA-Z]\\w{2,12}$")) {
                                if (btnSave.getText().equals("Save")) {
                                    DoctorDTO doctorDTO = new DoctorDTO(id, fname, lname, Double.parseDouble(fee), spec, email, password);
                                    try {
                                        doctorBO.saveDoctor(doctorDTO);
                                        ObservableList<DoctorTM> doctors = tblViewDoctors.getItems();
                                        doctors.add(new DoctorTM(id, fname, lname, Double.parseDouble(fee), spec, email, password));
                                        changeCellFormatFee();
                                        btnNewDoctor_OnAction(event);
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Doctor Information");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Doctor added successfully !!");
                                        alert.showAndWait();
                                        return;

                                    } catch (Exception e) {
                                        new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
                                        Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
                                    }


                                }

                                DoctorTM doctorTM = tblViewDoctors.getSelectionModel().getSelectedItem();
                                DoctorDTO doctorDTO = new DoctorDTO(id, fname, lname, Double.parseDouble(fee), spec, email, password);
                                try {

                                    doctorBO.updateDoctor(doctorDTO);
                                    doctorTM.setFirstname(fname);
                                    doctorTM.setLastname(lname);
                                    doctorTM.setFee(Double.parseDouble(fee));
                                    doctorTM.setSpecialization(spec);
                                    doctorTM.setEmail(email);
                                    doctorTM.setPassword(password);
                                    changeCellFormatFee();
                                    tblViewDoctors.refresh();
                                    btnNewDoctor_OnAction(event);
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Doctor Information");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Doctor updated successfully !!");
                                    alert.showAndWait();

                                } catch (Exception e) {
                                    new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for technical support.").show();
                                    Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
                                }


                            } else {

                                txtFieldPassword.requestFocus();
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Invalid Credentials Warning Alert");
                                alert.setHeaderText(null);
                                alert.setContentText("Invalid password, please try again.");
                                alert.showAndWait();


                            }


                        } else {

                            txtFieldEmail.requestFocus();
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Invalid Credentials Warning Alert");
                            alert.setHeaderText(null);
                            alert.setContentText("Invalid email, please try again.");
                            alert.showAndWait();


                        }


                    } else {


                        cmbSpecialization.requestFocus();
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Invalid Credentials Warning Alert");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid specialization, please try again.");
                        alert.showAndWait();


                    }


                } else {

                    txtFieldFee.requestFocus();
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Invalid Credentials Warning Alert");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid fee, please try again.");
                    alert.showAndWait();


                }


            } else {
                txtFieldDoctorLName.requestFocus();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Credentials Warning Alert");
                alert.setHeaderText(null);
                alert.setContentText("Invalid last name, please try again.");
                alert.showAndWait();
            }


        } else {
            txtFieldDoctorFName.requestFocus();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Credentials Warning Alert");
            alert.setHeaderText(null);
            alert.setContentText("Invalid first name, please try again.");
            alert.showAndWait();
        }


    }

    public void btnBacktoDashboard_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/AdminDashBoardForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.AnchrPaneManageDoctor.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    private void changeCellFormatFee() {
        //Showing double value up to two decimal places in TableView

        clmnFee.setCellFactory(new Callback<TableColumn, TableCell>() {
            public TableCell call(TableColumn p) {
                TableCell cell = new TableCell<DoctorTM, Double>() {
                    @Override
                    public void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : getString());
                        setGraphic(null);
                    }

                    private String getString() {
                        String ret = "";
                        if (getItem() != null) {
                            String gi = getItem().toString();
                            NumberFormat df = DecimalFormat.getInstance();
                            df.setMinimumFractionDigits(2);
                            df.setRoundingMode(RoundingMode.DOWN);

                            ret = df.format(Double.parseDouble(gi));
                        } else {
                            ret = "0.00";
                        }
                        return ret;
                    }
                };

                cell.setStyle("-fx-alignment: top-left;");
                return cell;
            }
        });


    }


}
