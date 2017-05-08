
package gui.scenes.controllers;

import gui.SceneController;
import gui.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class RegisterController implements Initializable, _Scene {

    SceneController controller;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setScreenParent(SceneController screenParent){
        controller = screenParent;
    }

    @FXML
    private void goToScreen1(ActionEvent event){
       controller.setScreen(Main.screen1ID);
    }
    
    @FXML
    private void goToScreen2(ActionEvent event){
       controller.setScreen(Main.screen2ID);
    }
}
