import java.io.*;
import java.net.*;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Client extends Application{
	
	public static final String HOST = "127.0.0.1";
	public static final double WIDTH = 100;
	public static final double HEIGHT = 50;
	
	
	private static PrintWriter out;
	private BufferedReader in;
	private Socket socket;
	
	// Button names are r = row, c = column
	private Button r0c0;
	private Button r0c1;
	private Button r0c2;
	private Button r1c0;
	private Button r1c1;
	private Button r1c2;
	private Button r2c0;
	private Button r2c1;
	private Button r2c2;
	
	// Player Variables
	private int playerNum;
	
	// This is used to track which buttons have been used
	private boolean[][] used; // row / column
	
	public void start(Stage primaryStage) {
		
		// Creating Buttons
		r0c0 = new Button();
		r0c1 = new Button();
		r0c2 = new Button();
		r1c0 = new Button();
		r1c1 = new Button();
		r1c2 = new Button();
		r2c0 = new Button();
		r2c1 = new Button();
		r2c2 = new Button();
		
		// Adding eventHandlers
		r0c0.setOnAction(createEventHandler(0,0));
		r0c1.setOnAction(createEventHandler(0,1));
		r0c2.setOnAction(createEventHandler(0,2));
		r1c0.setOnAction(createEventHandler(1,0));
		r1c1.setOnAction(createEventHandler(1,1));
		r1c2.setOnAction(createEventHandler(1,2));
		r2c0.setOnAction(createEventHandler(2,0));
		r2c1.setOnAction(createEventHandler(2,1));
		r2c2.setOnAction(createEventHandler(2,2));
		
		//Setting up the Buttons
		r0c0.setPrefSize(WIDTH,  HEIGHT);
		r0c1.setPrefSize(WIDTH,  HEIGHT);
		r0c2.setPrefSize(WIDTH,  HEIGHT);
		r1c0.setPrefSize(WIDTH,  HEIGHT);
		r1c1.setPrefSize(WIDTH,  HEIGHT);
		r1c2.setPrefSize(WIDTH,  HEIGHT);
		r2c0.setPrefSize(WIDTH,  HEIGHT);
		r2c1.setPrefSize(WIDTH,  HEIGHT);
		r2c2.setPrefSize(WIDTH,  HEIGHT);
		
		// Creating HBoxes to hold Buttons
		HBox row0 = new HBox();
		HBox row1 = new HBox();
		HBox row2 = new HBox();
		
		// Setting up the rows
		row0.setPadding(new Insets(15, 15, 15, 15));
		row1.setPadding(new Insets(15, 15, 15, 15));
		row2.setPadding(new Insets(15, 15, 15, 15));
		
		// Adding the Buttons
		row0.getChildren().add(r0c0);
		row0.getChildren().add(r0c1);
		row0.getChildren().add(r0c2);
		row1.getChildren().add(r1c0);
		row1.getChildren().add(r1c1);
		row1.getChildren().add(r1c2);
		row2.getChildren().add(r2c0);
		row2.getChildren().add(r2c1);
		row2.getChildren().add(r2c2);
		
		// Creating VBox to hold the rows
		VBox rows = new VBox();
		
		// Setting up the VBox
		rows.setPadding(new Insets(15, 15, 15, 15));
		
		// Adding the rows
		rows.getChildren().add(row0);
		rows.getChildren().add(row1);
		rows.getChildren().add(row2);
		
		// Creating the Scene
		Scene scene = new Scene(rows, 400, 300);
		
		// Setting the stage
		primaryStage.setTitle("Tic Tac Toe");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		try {
			
			socket = null;
			out = null;
			in = null;
			
			try {
				socket = new Socket(HOST, Server.PORT);
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				playerNum = in.read();
				
				System.out.println(playerNum);
				
				if(playerNum == 1) {
				
				}
				
			} catch(Throwable e) { System.out.println(e.toString()); }
		} catch(Throwable e) { System.out.println(e.toString()); }
		
		// Setting up the used array
		used = new boolean[3][3];
		for(int i = 0; i < 3; i ++) {
			
			for(int j = 0; j < 3; j++) {
				
				used[i][j] = false;
			}
		}
	}
	
	public EventHandler<ActionEvent> createEventHandler(int row, int column) {
		
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
				claimSpace(playerNum, row, column);
			}
		};
		
		return event;
	}
	
	public void claimSpace(int player, int row, int column) {
		
		// Checking if this move is the 
		if(player == playerNum) {
			
			
			// Nested switch statements check for row then column
			switch(row) {
			
			case 0: 
				switch(column) {
				
				case 0: r0c0.setStyle("-fx-background-color: #173ad4");
					used[0][0] = true;
					out.println(playerNum + "," + row + "," + column);
					break;
				
				case 1: r0c1.setStyle("-fx-background-color: #173ad4");
					used[0][1] = true;
					out.println(playerNum + "," + row + "," + column);
					break;
					
				case 2: r0c2.setStyle("-fx-background-color: #173ad4");
					used[0][2] = true;
					out.println(playerNum + "," + row + "," + column);
					break;
				}
				break;
			case 1: 
				switch(column) {
				
				case 0: r1c0.setStyle("-fx-background-color: #173ad4");
					used[1][0] = true;
					out.println(playerNum + "," + row + "," + column);
					break;
				
				case 1: r1c1.setStyle("-fx-background-color: #173ad4");
					used[1][1] = true;
					out.println(playerNum + "," + row + "," + column);
					break;
					
				case 2: r1c2.setStyle("-fx-background-color: #173ad4");
					used[1][2] = true;
					out.println(playerNum + "," + row + "," + column);
					break;
				}
				break;
			case 2: 
				switch(column) {
				
				case 0: r2c0.setStyle("-fx-background-color: #173ad4");
					used[2][0] = true;
					out.println(playerNum + "," + row + "," + column);
					break;
				
				case 1: r2c1.setStyle("-fx-background-color: #173ad4");
					used[2][1] = true;
					out.println(playerNum + "," + row + "," + column);
					break;
					
				case 2: r2c2.setStyle("-fx-background-color: #173ad4");
					used[2][2] = true;
					out.println(playerNum + "," + row + "," + column);
					break;
				}
				break;
			}
			socket.notify();
			// disableAll();
		} else {
			
			// Nested switch statements check for row then column
				switch(row) {
						
					case 0: 
						switch(column) {
							
							case 0: r0c0.setStyle("-fx-background-color: #d41717");
								used[0][0] = true;
								break;
							
							case 1: r0c1.setStyle("-fx-background-color: #d41717");
								used[0][1] = true;
								break;
								
							case 2: r0c2.setStyle("-fx-background-color: #d41717");
								used[0][2] = true;
								break;
							}
							break;
						case 1: 
							switch(column) {
							
							case 0: r1c0.setStyle("-fx-background-color: #d41717");
								used[1][0] = true;
								break;
							
							case 1: r1c1.setStyle("-fx-background-color: #d41717");
								used[1][1] = true;
								break;
								
							case 2: r1c2.setStyle("-fx-background-color: #d41717");
								used[1][2] = true;
								break;
							}
							break;
						case 2: 
							switch(column) {
							
							case 0: r2c0.setStyle("-fx-background-color: #d41717");
								used[2][0] = true;
								break;
							
							case 1: r2c1.setStyle("-fx-background-color: #d41717");
								used[2][1] = true;
								break;
								
							case 2: r2c2.setStyle("-fx-background-color: #d41717");
								used[2][2] = true;
								break;
							}
						break;
				}
				
				try {
					
					socket.wait();
				} catch(Throwable e) { System.out.println(e.toString()); }
				// enableUnused();
		}
		
	}
	
	public void enableUnused() {
		
		r0c0.setDisable(!used[0][0]);
		r0c1.setDisable(!used[0][1]);
		r0c2.setDisable(!used[0][2]);
		r1c0.setDisable(!used[1][0]);
		r1c1.setDisable(!used[1][1]);
		r1c2.setDisable(!used[1][2]);
		r2c0.setDisable(!used[2][0]);
		r2c1.setDisable(!used[2][1]);
		r2c2.setDisable(!used[2][2]);
	}
	
	public void disableAll() {
		
		r0c0.setDisable(true);
		r0c1.setDisable(true);
		r0c2.setDisable(true);
		r1c0.setDisable(true);
		r1c1.setDisable(true);
		r1c2.setDisable(true);
		r2c0.setDisable(true);
		r2c1.setDisable(true);
		r2c2.setDisable(true);
	}
	
	public static void main(String[] args) {
		
		
		launch();
	}
}
