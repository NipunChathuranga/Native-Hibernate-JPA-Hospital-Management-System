package lk.ijse.dep.hms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminDashBoardFormController {
    public AnchorPane AnchrPaneAdminDashboard;
    public ProgressIndicator pgb;

    public void btnManageDoctors_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/AdminManageDoctorForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.AnchrPaneAdminDashboard.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    public void btnManagePatients_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/AdminManagePatientForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.AnchrPaneAdminDashboard.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    public void btnManageMedicine_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/AdminManageMedicineForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.AnchrPaneAdminDashboard.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    public void btnMakeAppoinments_OnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/AdminMakeAppoinmentForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.AnchrPaneAdminDashboard.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    public void btnLogout_OnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/DashBoardForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.AnchrPaneAdminDashboard.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    public void btnBackUp_OnAction(ActionEvent actionEvent) {

//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Save the DB Backup");
//        fileChooser.getExtensionFilters().
//                add(new FileChooser.ExtensionFilter("SQL File", "*.sql"));
//        File file = fileChooser.showSaveDialog(this.AnchrPaneAdminDashboard.getScene().getWindow());
//        if (file != null) {
//
//            // Now, we have to backup the DB...
//            // Long running task == We have to backup
//            this.AnchrPaneAdminDashboard.getScene().setCursor(Cursor.WAIT);
//            this.pgb.setVisible(true);
//
//            Task<Void> task = new Task<Void>() {
//                @Override
//                protected Void call() throws Exception {
//
//                    String[] commands;
//                    if (DBConnection.password.length() > 0){
//                        commands = new String[]{"mysqldump", "-h", DBConnection.host, "-u", DBConnection.username,
//                                "-p" + DBConnection.password,"--port",DBConnection.port, DBConnection.db, "--result-file", file.getAbsolutePath() + ((file.getAbsolutePath().endsWith(".sql")) ? "" : ".sql")};
//                    }else{
//                        commands = new String[]{"mysqldump", "-h", DBConnection.host, "-u", DBConnection.username, "--port",DBConnection.port,
//                                DBConnection.db, "--result-file", file.getAbsolutePath() + ((file.getAbsolutePath().endsWith(".sql")) ? "" : ".sql")};
//                    }
//
//                    Process process = Runtime.getRuntime().exec(commands);
//                    int exitCode = process.waitFor();
//                    if (exitCode != 0) {
//                        BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//                        br.lines().forEach(System.out::println);
//                        br.close();
//                        throw new RuntimeException("Wadea Kachal");
//                    } else {
//                        return null;
//                    }
//                }
//            };
//
//            task.setOnSucceeded(event -> {
//                this.pgb.setVisible(false);
//                this.AnchrPaneAdminDashboard.getScene().setCursor(Cursor.DEFAULT);
//                new Alert(Alert.AlertType.INFORMATION,"Backup process has been done successfully").show();
//            });
//
//            task.setOnFailed(event -> {
//                this.pgb.setVisible(false);
//                this.AnchrPaneAdminDashboard.getScene().setCursor(Cursor.DEFAULT);
//                new Alert(Alert.AlertType.ERROR,"Failed to back up. Contact us for the technical support").show();
//            });
//
//            new Thread(task).start();
//        }
//
//
//
//
//


    }

    public void btnRestore_OnAction(ActionEvent actionEvent) {
    }
}
