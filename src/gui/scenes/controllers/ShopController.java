

package gui.scenes.controllers;

import gui.CardGUI;
import gui.SceneController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javafx.scene.media.AudioClip;

import javafx.scene.text.Font;




public class ShopController implements Initializable, _Scene {
    //lagnuage
    private ResourceBundle rs;
    //scene controller
    SceneController controller;
    
    
    
    
   
    
    //Hover sound
    final AudioClip hoverAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Hover.mp3").toExternalForm());
    
    //Click sound
    final AudioClip clickAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Click.mp3").toExternalForm());
    
    
   
    
    

    @FXML private Button btnBuy;
    @FXML private GridPane buyableCards1; //rij 1 van buyable kaarten (8 x 1)
    @FXML private GridPane buyableCards2; //rij 2 (5 x 1)
    @FXML private ImageView imgSelectedCard; //@matthias, wnr je over een kaart hovert word(t) die hier in het groot getoont
    @FXML private Label lblCredits;
    @FXML private Label lblCost;
    @FXML private Label lblExplanation;
    
    private int columnsGrid1 = 8;
    private int amountOfCards = 0;
    private CardGUI selectedCardGUI;
    
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println(Font.loadFont(getClass().getResourceAsStream("../../assets/css/upheavtt.ttf"), 14).getName());
        rs = rb;
        hoverAudioClip.setVolume(0.5);
        clickAudioClip.setVolume(0.5);
        btnBuy.setDisable(true);
        imgSelectedCard.setImage(new Image("gui/assets/img//game/cards/back.png"));
        

        
        //assert btnMain != null;
        


    }
    
    
    
    
    @Override
    public void setScreenParent(SceneController screenParent){
        controller = screenParent;
        int index = 0;
        lblCredits.setText(String.valueOf(controller.getDC().getCurrentPlayerCredits()));
        System.out.println(String.valueOf(controller.getDC().getCurrentPlayerCredits()));
        for (String[] card : controller.getDC().showBuyableCards())
        {
             String imageUrl = "";
           switch(card[0]) {
               case "+": case "-":
                    imageUrl = "gui/assets/img/game/cards/" + card[0] + card[1] + ".png";
              break;
              case "+/-":
                  imageUrl = "gui/assets/img/game/cards/" + "±" + card[1] + ".png";
              break;
              case "D":
                 imageUrl = "gui/assets/img/game/cards/" + "D" + ".png";
              break;
              case "1+/-2":
                 imageUrl =  "gui/assets/img/game/cards/" + "1±2" + ".png";
              break;
               case "xT":
                 imageUrl = "gui/assets/img/game/cards/±" + card[1]+ "T" + ".png";
              break;
               case "2&4": case "3&6":
                 imageUrl = "gui/assets/img/game/cards/" + card[0] + ".png";
              break;
           }
            System.out.println(imageUrl);
          CardGUI cardGUI = new CardGUI(imageUrl, card[0], Integer.parseInt(card[1]), Integer.parseInt(card[2]), ++amountOfCards, true);
          cardGUI.setOnMouseClicked(new EventHandler<MouseEvent>() {
                 @Override
                 public void handle(MouseEvent event)
                 {
                     
                     CardGUI cardGUI = (CardGUI) event.getSource();
                     if (cardGUI.isInteractable())
                     {
                         imgSelectedCard.setImage(new Image(cardGUI.getUrl()));
                         lblCost.setText(String.valueOf(cardGUI.getPrice()));
                         selectedCardGUI = cardGUI;
                         btnBuy.setDisable(false);
                     }
                 }
             });
          if(index >= columnsGrid1) {
               buyableCards2.add(cardGUI, index - columnsGrid1, 0);
               index++;
               
          } else {
               buyableCards1.add(cardGUI, index++, 0);
          }
          
           
        }
        
    };


    

    
    //btnMain
    
    public void btnBuyClick(){
     
        clickAudioClip.play();
        try
        {
         String[] availableCard = new String[3];
        availableCard[0] = selectedCardGUI.getType();
        availableCard[1] = String.valueOf(selectedCardGUI.getValue());
        availableCard[2] = String.valueOf(selectedCardGUI.getPrice());
        controller.getDC().buyCard(availableCard);
        btnBuy.setDisable(true);
        selectedCardGUI.setInteractable(false);
        selectedCardGUI.setImage(new Image("gui/assets/img//game/cards/back.png"));
        imgSelectedCard.setImage(new Image("gui/assets/img//game/cards/back.png"));
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            lblExplanation.setText(e.getMessage());
        }
       
    }
    
    @FXML
    public void btnBuyEnter(){
        
       hoverAudioClip.play();
       btnBuy.setScaleX(1.1);
       btnBuy.setScaleY(1.1);
        
    }
    
    @FXML
    public void btnBuyExit(){
       
       btnBuy.setScaleX(1);
       btnBuy.setScaleY(1);
       
        
    }

    @FXML
    private void btnMainClick(ActionEvent event) {
    }
}
