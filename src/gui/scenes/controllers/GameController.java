package gui.scenes.controllers;

import gui.CardGUI;
import gui.Main;
import gui.SceneController;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javafx.scene.media.AudioClip;

import javafx.scene.text.Font;
import javafx.util.Duration;

public class GameController implements Initializable, _Scene {

    //lagnuage
    private ResourceBundle rs;
    //scene controller
    SceneController controller;

    //Hover sound
    final AudioClip hoverAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Hover.mp3").toExternalForm());

    //Click sound
    final AudioClip clickAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Click.mp3").toExternalForm());
    
    //Hover sound
    final AudioClip startAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/StartMatch.mp3").toExternalForm());
    
    //Hover sound
    final AudioClip standAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Stand.mp3").toExternalForm());
    
    //Hover sound
    final AudioClip endAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/EndTurn.mp3").toExternalForm());
    
    //Hover sound
    final AudioClip victoryAudioClip = new AudioClip(getClass().getResource("../../assets/sfx/sounds/Victory.mp3").toExternalForm());

    @FXML
    private Button btnEndTurn;
    @FXML
    private Button btnStand;
    @FXML
    private GridPane player1Deck;
    @FXML
    private GridPane player2Deck;
    @FXML
    private GridPane player1SideDeck;
    @FXML
    private GridPane player2SideDeck;
    @FXML
    private Label lblScore;
    @FXML
    private Label lblPlayer1;
    @FXML
    private Label lblPlayer2;
    @FXML
    private Label lblPlayer1Score;
    @FXML
    private Label lblPlayer2Score;
    @FXML private Button btnSign;
    private ImageView image = new ImageView((new Image(getClass().getResourceAsStream("../../assets/img/menu/sign.png"))));
     private ImageView imagev = new ImageView((new Image(getClass().getResourceAsStream("../../assets/img/menu/value.png"))));
    @FXML
    private ImageView imgMsg;
    @FXML
    private Button btnChange;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtSaveName;
    @FXML
    private Label lblSave;
    
    @FXML
    private Button btnValue;
    
    private List<GridPane> playerSideDecks;
    private List<GridPane> playerGameBoards;
    private List<Label> scores;
    private List<Label> names;
    private int[] columnCounters;
    private int[] rowCounters;
    private boolean wantSave = false;
    
    
    @Override

    public void initialize(URL url, ResourceBundle rb)
    {
        startAudioClip.setVolume(1.5);
        startAudioClip.play();
        
        setupCustomTooltipBehavior(0, 10000000, 0);
        
        
        
        System.out.println(Font.loadFont(getClass().getResourceAsStream("../../assets/css/upheavtt.ttf"), 14).getName());
        rs = rb;
        hoverAudioClip.setVolume(0.5);
        clickAudioClip.setVolume(0.5);

        //assert btnLanguage != null;
        playerSideDecks = new ArrayList<>();
        playerSideDecks.add(player1SideDeck);
        playerSideDecks.add(player2SideDeck);

        playerGameBoards = new ArrayList<>();
        playerGameBoards.add(player1Deck);
        playerGameBoards.add(player2Deck);

        scores = new ArrayList<>();
        scores.add(lblPlayer1Score);
        scores.add(lblPlayer2Score);

        names = new ArrayList<>();
        names.add(lblPlayer1);
        names.add(lblPlayer2);

        columnCounters = new int[2];
        rowCounters = new int[2];

        lblPlayer1Score.setText("0");
        lblPlayer2Score.setText("0");
        
        imgMsg.setVisible(false);
        btnChange.setVisible(false);
        btnSave.setVisible(false);
        lblSave.setVisible(false);
        txtSaveName.setVisible(false);
        
        btnSave.setText("Save");
        btnChange.setText("Next Round");
        
    }

    @Override
    public void setScreenParent(SceneController screenParent)
    {
        controller = screenParent;

        btnSign.setGraphic(image);
        btnValue.setGraphic(imagev);
        
        
        btnSign.setTooltip(new Tooltip(rs.getString("changeSign")));
        btnEndTurn.setTooltip(new Tooltip(rs.getString("endTurn")));
        btnValue.setTooltip(new Tooltip(rs.getString("changeValue")));
        btnStand.setTooltip(new Tooltip(rs.getString("stand")));
        
        controller.getDC().startNewRound();
        nextTurn();
        int counter = 0;
        for (String name : controller.getDC().getChosenPlayerNames())
        {
            names.get(counter++).setText(name);
        }
        counter = 0;

        lblScore.setText(String.valueOf(controller.getDC().getMatchScore()[0]) + String.valueOf(controller.getDC().getMatchScore()[1]));
        int columnCounter = 0;
        for (String[] cardPlayer1 : controller.getDC().getMatchDeckCurrentPlayer())
        {
            String imageUrl = determineImgUrl(cardPlayer1);

            CardGUI cardGUI = new CardGUI(imageUrl);
            cardGUI.setInteractable(true);
            // playerSideDecks.get(controller.getDC().getCurrentPlayerIndex()).add(cardGUI, columnCounter++, rowCounter);
            cardGUI.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event)
                {
                    CardGUI cardGUI = (CardGUI) event.getSource();
                    if (cardGUI.isInteractable())
                    {
                        CardGUI cardGUIBoard = new CardGUI(cardGUI.getUrl());
                        cardGUIBoard.setRotate(cardGUI.getRotate());
                        playerGameBoards.get(controller.getDC().getCurrentPlayerIndex()).add(cardGUIBoard, columnCounters[controller.getDC().getCurrentPlayerIndex()]++, rowCounters[controller.getDC().getCurrentPlayerIndex()]);
                        if (columnCounters[controller.getDC().getCurrentPlayerIndex()] == 3)
                        {
                            columnCounters[controller.getDC().getCurrentPlayerIndex()] = 0;
                            rowCounters[controller.getDC().getCurrentPlayerIndex()]++;
                        }
                        //    controller.getDC().playCard(GridPane.getColumnIndex(cardGUI));
                        boolean cardFound = false;
                        int cardIndex = 1;
                        for (Node card : playerSideDecks.get(controller.getDC().getCurrentPlayerIndex()).getChildren())
                        {
                            CardGUI cardGUI2 = (CardGUI) card;
                            if (cardGUI2 != null && !cardFound && !cardGUI2.getUrl().equals("gui/assets/img/game/cards/back.png"))
                            {
                                if (cardGUI == cardGUI2)
                                {
                                    cardFound = true;
                                } else
                                {
                                    cardIndex++;
                                }
                            }
                        }

                        //   System.out.println(String.valueOf(GridPane.getColumnIndex(cardGUI)));
                        controller.getDC().playCard(cardIndex);
                        if(cardGUI.getUrl().equals("gui/assets/img/game/cards/2&4.png") || cardGUI.getUrl().equals("gui/assets/img/game/cards/3&6.png")) {
                            redrawGameboardCurrent(cardGUI);
                        }
                        System.out.println(String.valueOf(cardIndex));
                        cardGUI.setInteractable(false);
                        cardGUI.setImage(new Image("gui/assets/img/game/cards/back.png"));
                        cardGUI.setUrl("gui/assets/img/game/cards/back.png");
                        lblPlayer1Score.setText(String.valueOf(controller.getDC().getPlayerScores()[0]));
                        lblPlayer2Score.setText(String.valueOf(controller.getDC().getPlayerScores()[1]));
                    }
                }
            });
              cardGUI.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event)
                {

                    hoverAudioClip.play();
                     if( ((CardGUI) event.getSource()).isInteractable()) {
                    ((CardGUI) event.getSource()).setScaleX(1.2);
                    ((CardGUI) event.getSource()).setScaleY(1.2);                                           
                    }

                }
            });
            cardGUI.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event)
                {
                    if( ((CardGUI) event.getSource()).isInteractable()) {
                    ((CardGUI) event.getSource()).setScaleX(1);
                    ((CardGUI) event.getSource()).setScaleY(1);                                           
                    }
                }
            });

            playerSideDecks.get(controller.getDC().getCurrentPlayerIndex()).add(cardGUI, columnCounter++, 0);
            /*   if (columnCounter == 3)
            {
                columnCounter = 0;
                rowCounter++;
            }*/
        }
        columnCounter = 0;
        for (String[] cardPlayer1 : controller.getDC().showMatchDeckOtherPlayer())
        {
            String imageUrl = determineImgUrl(cardPlayer1);

            //int rowCounter = 0;
            CardGUI cardGUI = new CardGUI("gui/assets/img/game/cards/back.png");
            cardGUI.setUrl(imageUrl);
            
            cardGUI.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event)
                {
                    CardGUI cardGUI = (CardGUI) event.getSource();
                    if (cardGUI.isInteractable())
                    {
                        CardGUI cardGUIBoard = new CardGUI(cardGUI.getUrl());
                        cardGUIBoard.setRotate(cardGUI.getRotate());
                        playerGameBoards.get(controller.getDC().getCurrentPlayerIndex()).add(cardGUIBoard, columnCounters[controller.getDC().getCurrentPlayerIndex()]++, rowCounters[controller.getDC().getCurrentPlayerIndex()]);
                        if (columnCounters[controller.getDC().getCurrentPlayerIndex()] == 3)
                        {
                            columnCounters[controller.getDC().getCurrentPlayerIndex()] = 0;
                            rowCounters[controller.getDC().getCurrentPlayerIndex()]++;
                        }

                        boolean cardFound = false;
                        int cardIndex = 1;
                        for (Node card : playerSideDecks.get(controller.getDC().getCurrentPlayerIndex()).getChildren())
                        {
                            CardGUI cardGUI2 = (CardGUI) card;
                            if (cardGUI2 != null && !cardFound && !cardGUI2.getUrl().equals("gui/assets/img/game/cards/back.png"))
                            {
                                if (cardGUI == cardGUI2)
                                {
                                    cardFound = true;
                                } else
                                {
                                    cardIndex++;
                                }
                            }
                        }

                        controller.getDC().playCard(cardIndex);
                        if (cardGUI.getUrl().equals("gui/assets/img/game/cards/2&4.png") || cardGUI.getUrl().equals("gui/assets/img/game/cards/3&6.png"))
                        {
                            redrawGameboardCurrent(cardGUI);
                        }
                        System.out.println(String.valueOf(cardIndex));
                        cardGUI.setInteractable(false);
                        cardGUI.setImage(new Image("gui/assets/img/game/cards/back.png"));
                        cardGUI.setUrl("gui/assets/img/game/cards/back.png");
                        lblPlayer1Score.setText(String.valueOf(controller.getDC().getPlayerScores()[0]));
                        lblPlayer2Score.setText(String.valueOf(controller.getDC().getPlayerScores()[1]));
                    }

                }
            });
            cardGUI.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event)
                {

                   if( ((CardGUI) event.getSource()).isInteractable()) {
                    ((CardGUI) event.getSource()).setScaleX(1.2);
                    ((CardGUI) event.getSource()).setScaleY(1.2);                                           
                    }

                }
            });
            cardGUI.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event)
                {
                    if( ((CardGUI) event.getSource()).isInteractable()) {
                    ((CardGUI) event.getSource()).setScaleX(1);
                    ((CardGUI) event.getSource()).setScaleY(1);                                           
                    }
                }
            });

            playerSideDecks.get((controller.getDC().getCurrentPlayerIndex() + 1) % 2).add(cardGUI, columnCounter++, 0);
            /*   if(columnCounter == 3) {
                columnCounter = 0;
                rowCounter++;
            }*/
        }

    }

    ;


    private String determineImgUrl(String[] card)
    {
        String imageUrl = "";
        switch (card[0])
        {
            case "+":
            case "-":
                imageUrl = "gui/assets/img/game/cards/" + card[0] + String.valueOf(Math.abs(Integer.parseInt(card[1]))) + ".png";
                break;
            case "+/-":
                imageUrl = "gui/assets/img/game/cards/" + "±" + String.valueOf(Math.abs(Integer.parseInt(card[1]))) + ".png";
                break;
            case "D":
                imageUrl = "gui/assets/img/game/cards/" + "D" + ".png";
                break;
            case "1+/-2":
                imageUrl = "gui/assets/img/game/cards/" + "1±2" + ".png";
                break;
            case "xT":
                imageUrl = "gui/assets/img/game/cards/±" + String.valueOf(Math.abs(Integer.parseInt(card[1]))) + "T" + ".png";
                break;
            case "2&4":
            case "3&6":
                imageUrl = "gui/assets/img/game/cards/" + card[0] + ".png";
                break;
            case "setDeckCard":
                imageUrl = "gui/assets/img/game/cards/" + card[1] + ".png";
                break;
        }
        return imageUrl;
    }

    //btnEndTurn
    @FXML
    public void btnEndTurnClick()
    {

        endAudioClip.play();

        if (controller.getDC().roundEnded() & !controller.getDC().matchEnded())
        {
            //newRound();
        imgMsg.setVisible(true);
        btnChange.setVisible(true);
        btnSave.setVisible(true);
        lblSave.setVisible(true);
        //txtSaveName.setVisible(true);
        btnStand.setDisable(true);
        btnEndTurn.setDisable(true);
        btnSign.setDisable(true);
        btnValue.setDisable(true);
        
         disableCardClick();
        } else {
            nextTurn();
        }
        if (controller.getDC().matchEnded())
        {
            showVictoryScreen();
        }
        

    }

    @FXML
    public void btnEndTurnEnter()
    {

        hoverAudioClip.play();
        btnEndTurn.setScaleX(1.1);
        btnEndTurn.setScaleY(1.1);

    }

    @FXML
    public void btnEndTurnExit()
    {

        btnEndTurn.setScaleX(1);
        btnEndTurn.setScaleY(1);

    }

    //btnStand
    @FXML
    public void btnStandClick()
    {

        standAudioClip.play();
        controller.getDC().freezeBoard();
        nextTurn();
        if (controller.getDC().roundEnded() & !controller.getDC().matchEnded())
        {
            //newRound();
            imgMsg.setVisible(true);
            btnChange.setVisible(true);
            btnSave.setVisible(true);
            lblSave.setVisible(true);
            txtSaveName.setVisible(true);
            btnStand.setDisable(true);
            btnEndTurn.setDisable(true);
            disableCardClick();
        }
        if (controller.getDC().matchEnded())
        {
            showVictoryScreen();
        }
        //

    }

    @FXML
    public void btnStandEnter()
    {

        hoverAudioClip.play();
        btnStand.setScaleX(1.1);
        btnStand.setScaleY(1.1);

    }

    @FXML
    public void btnStandExit()
    {

        btnStand.setScaleX(1);
        btnStand.setScaleY(1);

    }

    private void nextTurn()
    {
        controller.getDC().nextTurn();
        String[] lastPlayedCardArray;
        lastPlayedCardArray = controller.getDC().giveLastCardPlayed();
        String imageUrl = determineImgUrl(lastPlayedCardArray);
        CardGUI lastPlayedCard = new CardGUI(imageUrl);

        playerGameBoards.get(controller.getDC().getCurrentPlayerIndex()).add(lastPlayedCard, columnCounters[controller.getDC().getCurrentPlayerIndex()]++, rowCounters[controller.getDC().getCurrentPlayerIndex()]);
        if (columnCounters[controller.getDC().getCurrentPlayerIndex()] == 3)
        {
            columnCounters[controller.getDC().getCurrentPlayerIndex()] = 0;
            rowCounters[controller.getDC().getCurrentPlayerIndex()]++;
        }
        System.out.println(Arrays.toString(columnCounters));
        for (Node card : playerSideDecks.get(controller.getDC().getCurrentPlayerIndex()).getChildren())
        {
            if (card != null && !((CardGUI) card).getUrl().equals("gui/assets/img/game/cards/back.png"))
            {
                CardGUI cardGUI = (CardGUI) card;
                cardGUI.setImage(new Image(cardGUI.getUrl()));
                cardGUI.setInteractable(true);
            }
        }
        for (Node card : playerSideDecks.get((controller.getDC().getCurrentPlayerIndex() + 1) % 2).getChildren())
        {
            if (card != null && !((CardGUI) card).getUrl().equals("gui/assets/img/game/cards/back.png"))
            {
                CardGUI cardGUI = (CardGUI) card;
                cardGUI.setImage(new Image("gui/assets/img/game/cards/back.png"));
                cardGUI.setInteractable(false);
            }
        }

        scores.get(controller.getDC().getCurrentPlayerIndex()).setText(String.valueOf(controller.getDC().getPlayerScores()[controller.getDC().getCurrentPlayerIndex()]));

    }

    private void showVictoryScreen()
    {
        btnEndTurn.setDisable(true);
        victoryAudioClip.play();
        
        controller.message = controller.getDC().whoWon() + " won the match";//rs plz
       controller.button1 = "new game";
       controller.button2 = "main menu";
       controller.messageId = "4";
       
       controller.loadScreen(Main.screen10ID, Main.screen10File);
       controller.setScreen(Main.screen10ID);
        
        System.out.println(  controller.getDC().whoWon());
    }

    private void newRound()
    {
        /*   for (Node card : playerGameBoards.get(0).getChildren())
        {
             
         //   playerSideDecks.get(0).getChildren().clear();
        }*/

        int teller = playerGameBoards.get(0).getChildren().size() - 1;
        while (!playerGameBoards.get(0).getChildren().isEmpty())
        {
            CardGUI cardGUI = (CardGUI) playerGameBoards.get(0).getChildren().get(teller--);
            playerGameBoards.get(0).getChildren().remove(cardGUI);
        }
        /*  for (Node card : playerGameBoards.get(1).getChildren())
        {
           CardGUI cardGUI = (CardGUI) card;
         //   cardGUI.setImage(new Image("gui/assets/img//game/cards/back.png"));
            playerGameBoards.get(1).getChildren().remove(cardGUI);
           
        }*/
        // playerGameBoards.get(0).getChildren().clear();
        //   playerGameBoards.get(1).getChildren().clear();
        teller = playerGameBoards.get(1).getChildren().size() - 1;
        while (!playerGameBoards.get(1).getChildren().isEmpty())
        {
            CardGUI cardGUI = (CardGUI) playerGameBoards.get(1).getChildren().get(teller--);
            playerGameBoards.get(1).getChildren().remove(cardGUI);
        }
        columnCounters = new int[2];
        rowCounters = new int[2];
        controller.getDC().startNewRound();

        lblPlayer1Score.setText("0");
        lblPlayer2Score.setText("0");

        lblScore.setText(String.valueOf(controller.getDC().getMatchScore()[0]) + String.valueOf(controller.getDC().getMatchScore()[1]));

        nextTurn();
    }
    
    
    @FXML
    public void btnSignClick()
    {

        clickAudioClip.play();
        int index = 1;
         for (Node card : playerSideDecks.get(controller.getDC().getCurrentPlayerIndex()).getChildren())
        {
            
            if (card != null && ((CardGUI) card).getUrl().substring(0, 27).equals("gui/assets/img/game/cards/±"))
            {
                System.out.println("testSign");
                CardGUI cardGUI = (CardGUI) card;
                cardGUI.setRotate(cardGUI.getRotate() + 180);
                controller.getDC().changeCardSign(index++);
            }
        }
        
        

    }

    @FXML
    public void btnSignEnter()
    {

        hoverAudioClip.play();
        btnSign.setScaleX(1.1);
        btnSign.setScaleY(1.1);

    }

    @FXML
    public void btnSignExit()
    {

        btnSign.setScaleX(1);
        btnSign.setScaleY(1);

    }
    
    
    private void redrawGameboardCurrent(CardGUI cardCaller) {
         for (Node card : playerGameBoards.get(controller.getDC().getCurrentPlayerIndex()).getChildren())
        {
            CardGUI cardGUI = (CardGUI) card;
            if (cardGUI != null && cardCaller.getUrl().equals("gui/assets/img/game/cards/2&4.png"))
            {
              
                switch(cardGUI.getUrl().substring(26, 28)) {
                    case "+2":
                        cardGUI.setUrl("gui/assets/img/game/cards/-2.png");
                        cardGUI.setImage(new Image("gui/assets/img/game/cards/-2.png"));
                        break;
                    case "+4":
                          cardGUI.setUrl("gui/assets/img/game/cards/-4.png");
                        cardGUI.setImage(new Image("gui/assets/img/game/cards/-4.png"));
                        break;
                    case "-2":
                         cardGUI.setUrl("gui/assets/img/game/cards/+2.png");
                        cardGUI.setImage(new Image("gui/assets/img/game/cards/+2.png"));
                        break;
                    case "-4":
                        cardGUI.setUrl("gui/assets/img/game/cards/+4.png");
                        cardGUI.setImage(new Image("gui/assets/img/game/cards/+4.png"));
                        break;
                     case "±2": case "±4":
                         cardGUI.setRotate(cardGUI.getRotate() + 180);
                         break;
                        
                }
            } else if(cardGUI != null && cardCaller.getUrl().equals("gui/assets/img/game/cards/3&6.png")) 
            {
                  switch(cardGUI.getUrl().substring(27, 28)) {
                    case "+3":
                        cardGUI.setUrl("gui/assets/img/game/cards/-3.png");
                        cardGUI.setImage(new Image("gui/assets/img/game/cards/-3.png"));
                        break;
                    case "+6":
                          cardGUI.setUrl("gui/assets/img/game/cards/-6.png");
                        cardGUI.setImage(new Image("gui/assets/img/game/cards/-6.png"));
                        break;
                    case "-3":
                         cardGUI.setUrl("gui/assets/img/game/cards/+3.png");
                        cardGUI.setImage(new Image("gui/assets/img/game/cards/+3.png"));
                        break;
                    case "-6":
                        cardGUI.setUrl("gui/assets/img/game/cards/+6.png");
                        cardGUI.setImage(new Image("gui/assets/img/game/cards/+6.png"));
                        break;
                     case "±3": case "±6":
                         cardGUI.setRotate(cardGUI.getRotate() + 180);
                         break;
                }
            }
        }
    }
    @FXML
    public void btnChangeClick() {
        imgMsg.setVisible(false);
        btnChange.setVisible(false);
        btnSave.setVisible(false);
        lblSave.setVisible(false);
        txtSaveName.setVisible(false);
        btnStand.setDisable(false);
        btnEndTurn.setDisable(false);
        btnSign.setDisable(false);
        btnValue.setDisable(false);
        newRound();
    }
    
    @FXML
    public void btnChangeEnter() {
        hoverAudioClip.play();
        btnChange.setScaleX(1.05);
        btnChange.setScaleY(1.05);
    }
    
      @FXML
    public void btnChangeExit() {
        btnChange.setScaleX(1);
        btnChange.setScaleY(1);
    }
    
    @FXML
    public void btnSaveClick() {
        txtSaveName.setVisible(true);
        lblSave.setText("geef een naam aan je save");
        wantSave = true;
        
        if (wantSave) {
            try
        {
          controller.getDC().saveMatch(txtSaveName.getText());
          lblSave.setText("Save succes");
        } catch (Exception e)
        {
            lblSave.setText(e.getMessage());
        }
        }
      
    }
    
    @FXML
    public void btnSaveEnter() {
        hoverAudioClip.play();
        btnSave.setScaleX(1.05);
        btnSave.setScaleY(1.05);
    }
    
      @FXML
    public void btnSaveExit() {
        btnSave.setScaleX(1);
        btnSave.setScaleY(1);
    }
    
    
    @FXML
    public void btnValueClick()
    {

        clickAudioClip.play();
           int index = 1;
         for (Node card : playerSideDecks.get(controller.getDC().getCurrentPlayerIndex()).getChildren())
        {
            
            if (card != null && ((CardGUI) card).getUrl().substring(0, 29).equals("gui/assets/img/game/cards/1±2"))
            {             
                CardGUI cardGUI = (CardGUI) card;
                cardGUI.setRotate(cardGUI.getRotate() + 180);
                controller.getDC().changeCardValue(index++);
            }
            index++;
        }
        
        

    }

    @FXML
    public void btnValueEnter()
    {

        hoverAudioClip.play();
        btnValue.setScaleX(1.1);
        btnValue.setScaleY(1.1);

    }

    @FXML
    public void btnValueExit()
    {

        btnValue.setScaleX(1);
        btnValue.setScaleY(1);

    }
    
    private void setupCustomTooltipBehavior(int openDelayInMillis, int visibleDurationInMillis, int closeDelayInMillis) {
        try {
             
            Class TTBehaviourClass = null;
            Class<?>[] declaredClasses = Tooltip.class.getDeclaredClasses();
            for (Class c:declaredClasses) {
                if (c.getCanonicalName().equals("javafx.scene.control.Tooltip.TooltipBehavior")) {
                    TTBehaviourClass = c;
                    break;
                }
            }
            if (TTBehaviourClass == null) {
                // abort
                return;
            }
            Constructor constructor = TTBehaviourClass.getDeclaredConstructor(
                    Duration.class, Duration.class, Duration.class, boolean.class);
            if (constructor == null) {
                // abort
                return;
            }
            constructor.setAccessible(true);
            Object newTTBehaviour = constructor.newInstance(
                    new Duration(openDelayInMillis), new Duration(visibleDurationInMillis), 
                    new Duration(closeDelayInMillis), false);
            if (newTTBehaviour == null) {
                // abort
                return;
            }
            Field ttbehaviourField = Tooltip.class.getDeclaredField("BEHAVIOR");
            if (ttbehaviourField == null) {
                // abort
                return;
            }
            ttbehaviourField.setAccessible(true);
             
            // Cache the default behavior if needed.
            Object defaultTTBehavior = ttbehaviourField.get(Tooltip.class);
            ttbehaviourField.set(Tooltip.class, newTTBehaviour);
             
        } catch (Exception e) {
            System.out.println("Aborted setup due to error:" + e.getMessage());
        }
    }
    private void disableCardClick()
    {
        for (Node card : playerSideDecks.get(controller.getDC().getCurrentPlayerIndex()).getChildren())
        {
            if (card != null && !((CardGUI) card).getUrl().equals("gui/assets/img/game/cards/back.png"))
            {
                CardGUI cardGUI = (CardGUI) card;

                cardGUI.setInteractable(false);
            }
        }
        for (Node card : playerSideDecks.get((controller.getDC().getCurrentPlayerIndex() + 1) % 2).getChildren())
        {
            if (card != null && !((CardGUI) card).getUrl().equals("gui/assets/img/game/cards/back.png"))
            {
                CardGUI cardGUI = (CardGUI) card;
                cardGUI.setInteractable(false);
            }
        }

    }
    
}
