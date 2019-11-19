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
import lk.ijse.dep.hms.business.custom.PrescriptionBO;
import lk.ijse.dep.hms.dto.PrescriptionHistoryDTO;
import lk.ijse.dep.hms.principle.UserLogin;
import lk.ijse.dep.hms.util.PrescriptionHistoryTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorPrescriptionFormController {
    public AnchorPane AnchrPanePrresHistory;
    public TableView<PrescriptionHistoryTM> tblViewHistory;
    private PrescriptionBO prescriptionBO = BOFactory.getInstance().getBO(BOTypes.PRESCRIPTION);

    public void initialize(){

       try{
           tblViewHistory.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("prescriptionid"));
           tblViewHistory.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("appoinmentid"));
           tblViewHistory.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("prescriptiondate"));
           tblViewHistory.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("patientid"));
           tblViewHistory.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("patientfname"));
           tblViewHistory.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("patientlname"));


           ObservableList<PrescriptionHistoryTM> prescriptionHistoryTMS = tblViewHistory.getItems();
           List<PrescriptionHistoryDTO> preshistory = prescriptionBO.getPrescriptionHistoryByDoc(UserLogin.getUserID());
           for(PrescriptionHistoryDTO ph :preshistory){
               //System.out.println(ph.getPrescriptionid());
               prescriptionHistoryTMS.add(new PrescriptionHistoryTM(ph.getPrescriptionid(),ph.getAppoinmentid(),ph.getPrescriptiondate(),
                       ph.getPatientid(),ph.getPatientfirstname(),ph.getPatientlastname()));
           }

       } catch (Exception e) {
           new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact us for the technical support.").show();
           Logger.getLogger("lk.ijse.dep.hms.controller").log(Level.SEVERE, null, e);
       }


    }





    public void btnBacktoDashboard_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/DoctorDashBoardForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.AnchrPanePrresHistory.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }
}
