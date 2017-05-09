
package gui.scenes.controllers;

import gui.SceneController;
import gui.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;



public class RegisterController implements Initializable, _Scene {

    private SceneController controller;
 private ResourceBundle rs;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
rs = rb;


    }    
    
    @Override
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
