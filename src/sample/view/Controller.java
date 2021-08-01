package sample.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private void initialize(){

    }

    public void openMenu(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menuController.fxml"));
            Parent root = null;
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Finanzas");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
