package lk.ijse.dep.hms.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.hms.business.BOFactory;
import lk.ijse.dep.hms.business.BOTypes;
import lk.ijse.dep.hms.business.custom.AppoinmentBO;
import lk.ijse.dep.hms.dto.AppoinmentInfoDTO2;
import lk.ijse.dep.hms.principle.UserLogin;
import lk.ijse.dep.hms.util.AppoinmentHistoryTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorAppoinmentFormController {

    public AnchorPane AnchrPaneAppoinmentHist;
    public TableView<AppoinmentHistoryTM> tblViewAppoinmentHistory;

    private AppoinmentBO appoinmentBO = BOFactory.getInstance().getBO(BOTypes.APPOINMENT);
    //private PatientBO patientBO = BOFactory.getInstance().getBO(BOTypes.PATIENT);


    public void initialize() {

        try {

            tblViewAppoinmentHistory.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("appoinmentID"));
            tblViewAppoinmentHistory.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("patientID"));
            tblViewAppoinmentHistory.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("fname"));
            tblViewAppoinmentHistory.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("lname"));
            tblViewAppoinmentHistory.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("gender"));
            tblViewAppoinmentHistory.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("email"));

            List<AppoinmentInfoDTO2> infolist = appoinmentBO.getAppoinmentsInfobyDocID(UserLogin.getUserID());
            ObservableList<AppoinmentHistoryTM> appoinmentHistoryTMS = tblViewAppoinmentHistory.getItems();

            for (AppoinmentInfoDTO2 appoinmentInfoDTO2 : infolist) {
                appoinmentHistoryTMS.add(new AppoinmentHistoryTM(appoinmentInfoDTO2.getAppoinmentid(),
                        appoinmentInfoDTO2.getPatientid(),
                        appoinmentInfoDTO2.getPatientfname(),
                        appoinmentInfoDTO2.getPatientlname(),
                        appoinmentInfoDTO2.getGender(),
                        appoinmentInfoDTO2.getPatientemail()));
            }


//            List<AppoinmentDTO> appoinmentDTOS = appoinmentBO.findAppoinmentsByDoctorID(UserLogin.getUserID());
//            ObservableList<AppoinmentHistoryTM> appoinmentHistoryTMS = tblViewAppoinmentHistory.getItems();
//
//            for (AppoinmentDTO appoinmentDTO : appoinmentDTOS) {
//
//                PatientDTO patientDTO = patientBO.findPatient(appoinmentDTO.getPatientid());
//                appoinmentHistoryTMS.add(new AppoinmentHistoryTM(appoinmentDTO.getAppoinmentid(),
//                        appoinmentDTO.getPatientid(),
//                        patientDTO.getPatientfirstname(),
//                        patientDTO.getPatientlastname(),
//                        patientDTO.getGender(),
//                        patientDTO.getPatientemail()));
//            }


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
            Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
        }


    }


    public void btnBacktoDashboard_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/DoctorDashBoardForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.AnchrPaneAppoinmentHist.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }
}
