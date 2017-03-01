package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class MessageBox {
    
    static void display(String title, String message) {
        Stage stage = new Stage();
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(310);
        stage.setMinHeight(140);
        
        Label lblMessage = new Label(message);
        Button btnClose = new Button("Sluit");
        
        btnClose.setOnAction(e -> stage.close());
        
        VBox layout = new VBox(15);
        layout.getChildren().addAll(lblMessage,btnClose);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
        
    }
    
}
