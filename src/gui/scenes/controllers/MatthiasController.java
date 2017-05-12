

package gui.scenes.controllers;

import gui.CardGUI;
import gui.SceneController;
import gui.Main;
import java.net.URL;
import java.security.interfaces.RSAKey;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javafx.scene.media.AudioClip;

import javafx.scene.text.Font;




public class MatthiasController implements Initializable, _Scene {
    //lagnuage
    private ResourceBundle rs;
    //scene controller
    SceneController controller;
    
    private int selectedCardsAmount;
    
    
    
   
    
    //Hover sound
    final AudioClip hoverAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Hover.mp3").toExternalForm());
    
    //Click sound
    final AudioClip clickAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Click.mp3").toExternalForm());
    
    
   
    
    
    @FXML private Button btnCancel;
    @FXML private Button btnConfirm;
    @FXML private GridPane ownedCards;
    @FXML private GridPane selectedCards;
        
    
    
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println(Font.loadFont(getClass().getResourceAsStream("../../assets/css/upheavtt.ttf"), 14).getName());
        rs = rb;
        hoverAudioClip.setVolume(0.5);
        clickAudioClip.setVolume(0.5);
        
      
       
    }
    
    
    
    
    @Override
    public void setScreenParent(SceneController screenParent){
        controller = screenParent;
        String[][] selectedCardsArray = new String[0][2];
        controller.getDC().makeMatch();
        controller.getDC().selectPlayer("pater");
        controller.getDC().selectPlayerWithoutMatchDeck("pater");
        int column =   0;
        int row = 0;
         for (String[] card: controller.getDC().getPlayerCards())
        {
           String imageUrl = "";
           switch(card[0]) {
               case "+": case "-":
                    imageUrl = "gui/assets/img/game/cards/" + card[0] + card[1] + ".png";
              break;
              case "+/-":
                  imageUrl = "gui/assets/img//game/cards/" + "±" + card[1] + ".png";
              break;
              case "D":
                 imageUrl = "gui/assets/img//game/cards/" + "D" + ".png";
              break;
              case "1+/2":
                 imageUrl =  "gui/assets/img//game/cards/" + "1±2" + ".png";
              break;
               case "xT":
                 imageUrl = "gui/assets/img//game/cards/" +  "±T" + ".png";
              break;
               case "2&4": case "3&6":
                 imageUrl = "gui/assets/img//game/cards/" + card[0] + ".png";
              break;
           }
            
           CardGUI cardGUI = new CardGUI(imageUrl, card[0], Integer.parseInt(card[1]));
           cardGUI.setOnMouseClicked(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event)
               {
                   //Get the source of the event
                    CardGUI cardGUI  = (CardGUI) event.getSource();
                    
                    //If the clicked card is interactable the event is caused
                    if(cardGUI.isInteractable()) {
                    cardGUI.setInteractable(false);
                 //   ((CardGUI) event.getSource()).setInteractable(false);
                    
                 //Make a new card that will be placed on the selectedCards Gridpane
                    CardGUI newCardGUI = new CardGUI(cardGUI.getUrl(), cardGUI.getType(),cardGUI.getValue(), GridPane.getRowIndex(cardGUI), GridPane.getColumnIndex(cardGUI));
                    
                    //Event to put the selectedCard back to the ownedCards Gridpane
                    newCardGUI.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event)
                        {
                            CardGUI cardGUI = null;
                            for (Node node : ownedCards.getChildren())
                            {
                                 CardGUI oldCardGUI  = (CardGUI) event.getSource();
                                if (GridPane.getColumnIndex((CardGUI)node) == oldCardGUI.getIndeces()[0] && GridPane.getRowIndex((CardGUI)node) == oldCardGUI.getIndeces()[1])
                                {
                                        cardGUI = (CardGUI) node;
                                        cardGUI.setInteractable(true);
                                        cardGUI.setImage(new Image(cardGUI.getUrl()));
                                        selectedCards.getChildren().remove(oldCardGUI);
                                    
                                }
                            }
                           }
                       });
                       selectedCards.add(newCardGUI, selectedCardsAmount++, 0);
                       cardGUI.setImage(new Image("gui/assets/img//game/cards/back.png"));
                   }
               }
            });  
         //  ownedCards.getChildren().add(cardGUI);
         ownedCards.add(cardGUI, column , row);
         column++;
         if(column == 6) {
             row++;
             column = 0;
         }
        }
 
    };
    
//btnLanguage
    
  
}
