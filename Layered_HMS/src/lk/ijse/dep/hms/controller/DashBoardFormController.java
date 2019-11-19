package lk.ijse.dep.hms.controller;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class DashBoardFormController {


    @FXML
    private AnchorPane AnchrPaneDashBoard;

    @FXML
    void btnAdminLogin_OnAction(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/AdminLoginForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.AnchrPaneDashBoard.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    @FXML
    void btnAdmin_playMouseEnterAnimation(MouseEvent event) {
        playMouseEnterAnimation(event);
    }

    @FXML
    void btnAdmin_playMouseExitAnimation(MouseEvent event) {
        playMouseExitAnimation(event);
    }

    @FXML
    void btnDoctorLogin_OnAction(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/hms/view/DoctorLoginForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.AnchrPaneDashBoard.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();

    }

    @FXML
    void btnDoctor_playMouseEnterAnimation(MouseEvent event) {
        playMouseEnterAnimation(event);

    }

    @FXML
    void btnDoctor_playMouseExitAnimation(MouseEvent event) {
        playMouseExitAnimation(event);

    }

    public void playMouseExitAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(100), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
        }
    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(100), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }

    }


}
