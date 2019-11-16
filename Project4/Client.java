import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.*;

public class Client extends Application{

	public void start(Stage primaryStage) {
	
		// Creating the Scene for the window
		Scene gameScene = createScene();
		
		// Setting the Stage
		primaryStage.setTitle("Tic-Tac-Toe");
		primaryStage.setScene(gameScene);
		primaryStage.show();
	}
	
	// This method will create and return the game scene
	public Scene createScene() {
		
		// Creating the Rows
		HBox row1 = createRow(1);
		
		// Creating the VBox that will hold the rows
		VBox rows = new VBox();
		
		// Adding the rows to the VBox
		rows.getChildren().add(row1);
		
		// Creating the Scene
		Scene gameScene = new Scene(rows, 400, 300);
		
		// Returning the Scene
		return gameScene;
	}
	
	
	// This method will create 1 row of buttons in a HBox
	public HBox createRow(int rowNum) {
		
		// Creating the HBox
		HBox buttonRow = new HBox();
		
		// Creating the Buttons
		Button b1 = new Button();
		Button b2 = new Button();
		Button b3 = new Button();
		
		// Creating the action events
		EventHandler<ActionEvent> b1Event = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				System.out.println("Button Pressed at row " + rowNum + " column 1");
			}
		};
		
		// Adding action events to buttons
		b1.setOnAction(b1Event);
		
		// Setting up the Button
		b1.setMinSize(100, 50);
		b1.setLayoutX(15);;
		
		// Adding buttons to HBox
		buttonRow.getChildren().add(b1);
		
		// Returning the button row
		return buttonRow;
	}
	
	public static void main(String[] args) {
		
		launch();
	}
}
