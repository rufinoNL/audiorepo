package nl.rufino.daw.view;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nl.rufino.daw.main.Start;
import nl.rufino.daw.util.AudioFunctions;
import nl.rufino.daw.util.WindowsFunctions;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DawMainView extends Application{
	static final Logger LOGGER = LogManager.getLogger(DawMainView.class);
	
	private Label lblText;
	private Button btnStartCubaseReason;
	
	public DawMainView(){}
	
	public DawMainView(String[] launchParams){
		
		launch(launchParams);
	}

	@Override
	public void start(Stage stage) throws Exception {
		lblText = new Label("");
		btnStartCubaseReason = new Button("Start Cubase and Reason in ReWire");
		
		btnStartCubaseReason.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				lblText.setText("Copying template and creating new folder to make epic beats...");
				File[] fileToStart = AudioFunctions.createNewTemplate();
				
				try {
					lblText.setText("Starting Cubase...");
					WindowsFunctions.startApplication("C:\\Program Files\\Steinberg\\Cubase 6\\Cubase6.exe", 
							fileToStart[0].toString());
					
					//Wait 25 seconds for Reason to Start...
					Thread.sleep(25000);
					
					lblText.setText("Starting Reason...");
					WindowsFunctions.startApplication("C:\\Program Files\\Propellerhead\\Reason\\Reason.exe", 
							fileToStart[1].toString());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		VBox root = new VBox();
		root.getChildren().addAll(lblText, btnStartCubaseReason);
		
		Scene scene = new Scene(root, 750, 1000);
		stage.setScene(scene);
		stage.show();
	}

}
