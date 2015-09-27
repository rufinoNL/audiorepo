package nl.rufino.daw.view;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.rufino.daw.util.AudioFunctions;
import nl.rufino.daw.util.WindowsFunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.guigarage.flatterfx.FlatterFX;

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
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		VBox root = new VBox();

		root.getChildren().addAll(lblText, btnStartCubaseReason);
		
		Scene scene = new Scene(root, 750, 1000);
		stage.setScene(scene);
		stage.show();
		FlatterFX.style();
	}

}
