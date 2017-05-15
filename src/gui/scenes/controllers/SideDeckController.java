

package gui.scenes.controllers;

import gui.CardGUI;
import gui.Main;
import gui.SceneController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javafx.scene.media.AudioClip;

import javafx.scene.text.Font;




public class SideDeckController implements Initializable, _Scene {
    //lagnuage
    private ResourceBundle rs;
    //scene controller
    SceneController controller;
    
    private int selectedCardsAmount;
    private boolean[] filledSlots;
    
    
       
    //Hover sound
    final AudioClip hoverAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Hover.mp3").toExternalForm());
    
    //Click sound
    final AudioClip clickAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Click.mp3").toExternalForm());
    
    
   
    
    
    @FXML private Button btnShop;
    @FXML private Button btnConfirm;
     @FXML private Button btnCancel1;
    @FXML private GridPane ownedCards;
    @FXML private GridPane selectedCards;
        
    
    
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println(Font.loadFont(getClass().getResourceAsStream("../../assets/css/upheavtt.ttf"), 14).getName());
        rs = rb;
        hoverAudioClip.setVolume(0.5);
        clickAudioClip.setVolume(0.5);
        btnConfirm.setDisable(true);
      
       
    }
    
    
    
    
    @Override
    public void setScreenParent(SceneController screenParent){
        controller = screenParent;
        String[][] selectedCardsArray = new String[0][2];
        controller.getDC().makeMatch();
        controller.getDC().selectPlayer("tyreg");
        controller.getDC().selectPlayerWithoutMatchDeck("tyreg");
        int column =   0;
        int row = 0;
        filledSlots = new boolean[6];
         for (String[] card: controller.getDC().getPlayerCards())
        {
           String imageUrl = "";
           switch(card[0]) {
               case "+": case "-":
                    imageUrl = "gui/assets/img/game/cards/" + card[0] + String.valueOf(Math.abs(Integer.parseInt(card[1]))) + ".png";
              break;
              case "+/-":
                  imageUrl = "gui/assets/img/game/cards/" + "±" + String.valueOf(Math.abs(Integer.parseInt(card[1]))) + ".png";
              break;
              case "D":
                 imageUrl = "gui/assets/img/game/cards/" + "D" + ".png";
              break;
              case "1+/-2":
                 imageUrl =  "gui/assets/img/game/cards/" + "1±2" + ".png";
              break;
               case "xT":
                 imageUrl = "gui/assets/img/game/cards/±" + String.valueOf(Math.abs(Integer.parseInt(card[1])))+ "T" + ".png";
              break;
               case "2&4": case "3&6":
                 imageUrl = "gui/assets/img/game/cards/" + card[0] + ".png";
              break;
           }
            System.out.println(imageUrl);
           CardGUI cardGUI = new CardGUI(imageUrl, card[0], Integer.parseInt(card[1]));
           cardGUI.setOnMouseClicked(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event)
               {
                   //Get the source of the event
                    CardGUI cardGUI  = (CardGUI) event.getSource();
                    
                    //If the clicked card is interactable the event is caused
                    if(cardGUI.isInteractable() && selectedCardsAmount < 6) {
                    cardGUI.setInteractable(false);
                 //   ((CardGUI) event.getSource()).setInteractable(false);
                    
                 //Make a new card that will be placed on the selectedCards Gridpane
                    CardGUI newCardGUI = new CardGUI(cardGUI.getUrl(), cardGUI.getType(),cardGUI.getValue(),  GridPane.getColumnIndex(cardGUI), GridPane.getRowIndex(cardGUI));
                    
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
                                        filledSlots[GridPane.getColumnIndex(oldCardGUI)] = false;
                                        selectedCardsAmount--;
                                        if(selectedCardsAmount < 6){
                                            btnConfirm.setDisable(true);
                                        }
                                    
                                }
                            }
                            }
                        });
                        newCardGUI.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event)
                            {

                                hoverAudioClip.play();
                                ((CardGUI) event.getSource()).setScaleX(1.2);
                                ((CardGUI) event.getSource()).setScaleY(1.2);

                            }
                        });
                        newCardGUI.setOnMouseExited(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event)
                            {
                                ((CardGUI) event.getSource()).setScaleX(1);
                                ((CardGUI) event.getSource()).setScaleY(1);
                            }
                        });
                        selectedCards.add(newCardGUI, indexFirstEmpty(), 0);
                        selectedCardsAmount++;
                        filledSlots[indexFirstEmpty()] = true;
                        if(selectedCardsAmount >= 6) {
                            btnConfirm.setDisable(false);
                        }
                        cardGUI.setImage(new Image("gui/assets/img//game/cards/back.png"));
                    }
            
               }
            });
            cardGUI.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event)
                {
                   if (((CardGUI) event.getSource()).isInteractable())
                   {
                        hoverAudioClip.play();
                       ((CardGUI) event.getSource()).setScaleX(1.2);
                       ((CardGUI) event.getSource()).setScaleY(1.2);
                   }
               }
           });
           cardGUI.setOnMouseExited(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event)
               {
                    ((CardGUI) event.getSource()).setScaleX(1);
                    ((CardGUI) event.getSource()).setScaleY(1);
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
    
    private int indexFirstEmpty() {
      int index = 0;
      while(filledSlots[index] == true) {
          index++;
      }
      return index;
    }
//btnLanguage
    @FXML
    public void btnConfirmClick()
    {
        clickAudioClip.play();
        String[][] cards = new String[6][2];
        int index = 0;
        for (Node card : selectedCards.getChildren())
        {
            cards[index][0] = ((CardGUI) card).getType();
            cards[index][1] = String.valueOf(((CardGUI) card).getValue());
            index++;
        }
        controller.getDC().makeMatchDeck(cards);
        controller.getDC().selectPlayer("pater");
        controller.getDC().selectPlayerWithoutMatchDeck("pater");
        controller.getDC().makeMatchDeck(cards);
        controller.loadScreen(Main.screen7ID, Main.screen7File);
        controller.setScreen(Main.screen7ID);
    }

    @FXML
    public void btnShopClick()
    {
        clickAudioClip.play();
        controller.loadScreen(Main.screen9ID, Main.screen9File);
        controller.setScreen(Main.screen9ID);

    }
    
    @FXML
    public void btnShopEnter()
    {
        hoverAudioClip.play();
       btnShop.setScaleX(1.1);
       btnShop.setScaleY(1.1);
        

    }
    
    @FXML
    public void btnShopExit()
    {
       btnShop.setScaleX(1);
       btnShop.setScaleY(1);
        

    }
    
    
    
    @FXML
    public void btnCancel1Click()
    {
        clickAudioClip.play();
        

    }
    
    @FXML
    public void btnCancel1Enter()
    {
        hoverAudioClip.play();
       btnCancel1.setScaleX(1.1);
       btnCancel1.setScaleY(1.1);
        

    }
    
    @FXML
    public void btnCancel1Exit()
    {
       btnCancel1.setScaleX(1);
       btnCancel1.setScaleY(1);
        

    }
    

    
    
    @FXML
    public void btnConfirmEnter()
    {
        hoverAudioClip.play();
       btnConfirm.setScaleX(1.1);
       btnConfirm.setScaleY(1.1);
        

    }
    
    @FXML
    public void btnConfirmExit()
    {
       btnConfirm.setScaleX(1);
       btnConfirm.setScaleY(1);
        

    }
  
}
