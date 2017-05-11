
package gui.scenes.controllers;

import exceptions.noCorrectBirthyearException;
import exceptions.noCorrectNameException;
import exceptions.userExistsException;
import gui.SceneController;
import gui.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.media.AudioClip;



public class RegisterController implements Initializable, _Scene {

    private SceneController controller;
    private ResourceBundle rs;
    
    
    @FXML TextField txtName;
    @FXML TextField txtBirthYear;
    @FXML Label lblError;
    @FXML Label lblRegister;
    @FXML Button btnMainMenu;
    @FXML Button btnRegister;
    
    //Error sound
    final AudioClip errorAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Error.mp3").toExternalForm());
    
    //Hover sound
    final AudioClip hoverAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Hover.mp3").toExternalForm());
    
    //Click sound
    final AudioClip clickAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Click.mp3").toExternalForm());
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    rs = rb;
    
    txtName.setPromptText(rs.getString("name"));
          
    
    txtName.setTooltip(new Tooltip(rs.getString("nameTooltip")));
    txtName.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
    txtBirthYear.setTooltip(new Tooltip(rs.getString("birthYearTooltip")));
    txtBirthYear.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
    lblError.setText("");
    
    
    
    }
    
    
    @FXML
    public void btnMainMenuClick(){
       
       clickAudioClip.play();
       
       controller.setScreen(Main.screen1ID);
       
       
    }
    
    @FXML
    public void btnMainMenuEnter(){
        
       hoverAudioClip.play();
       btnMainMenu.setScaleX(1.1);
       btnMainMenu.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnMainMenuExit(){
       
       btnMainMenu.setScaleX(1);
       btnMainMenu.setScaleY(1);
   
    }
    
    
    
    
    @FXML
    public void btnRegisterClick(){
       
       clickAudioClip.play();
       
       try {
           controller.getDC().register(txtName.getText(), Integer.parseInt(txtBirthYear.getText()));
           lblError.setText("succes");
       } catch(userExistsException | noCorrectBirthyearException | noCorrectNameException uex) {
           errorAudioClip.play();
        lblError.setText(uex.getMessage());
       } catch(NumberFormatException nEx) {
          errorAudioClip.play();
          lblError.setText(rs.getString("noNumber"));
       } catch(RuntimeException rtEx) {
            lblError.setText(rs.getString("noInternet"));
       }
       
       
       
    }
    
    @FXML
    public void btnRegisterEnter(){
        
       hoverAudioClip.play();
       btnRegister.setScaleX(1.1);
       btnRegister.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnRegisterExit(){
       
       btnRegister.setScaleX(1);
       btnRegister.setScaleY(1);
   
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
