package nl.rufino.daw.view;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import nl.rufino.daw.data.MainViewData;
import nl.rufino.daw.data.entity.MusicEntity;
import nl.rufino.util.AudioFunctions;
import nl.rufino.util.WindowsFunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.guigarage.flatterfx.FlatterFX;

public class MainView extends Application implements EventHandler<ActionEvent>{
	static final Logger LOGGER = LogManager.getLogger(MainView.class);
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw_data");
	private EntityManager em = emf.createEntityManager();
	
	private Label lblText;
	private Button btnStartCubaseReason;
	private MainViewData data = new MainViewData(em);
	private TableView<MusicEntity> musicTable;

	//Start program
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		//Set style
		FlatterFX.style();
		
		lblText = new Label();
		lblText.setWrapText(true);
		btnStartCubaseReason = new Button();
		btnStartCubaseReason.setText("Start Cubase and Reason in ReWire");
		btnStartCubaseReason.setOnAction(this);
		
		//Column for musicId
		TableColumn<MusicEntity, Integer> musicIdColumn = new TableColumn<>("Id");
		musicIdColumn.setMinWidth(15);
		musicIdColumn.setCellValueFactory(new PropertyValueFactory<>("musicid"));	
		
		//Column for musicId
		TableColumn<MusicEntity, Integer> trackNameColumn = new TableColumn<>("Track name");
		trackNameColumn.setMinWidth(100);
		trackNameColumn.setCellValueFactory(new PropertyValueFactory<>("trackName"));	
		
		musicTable = new TableView<MusicEntity>();
		musicTable.setItems(data.getMusic());
		musicTable.getColumns().addAll(musicIdColumn, trackNameColumn);
		
		VBox root = new VBox();

		root.getChildren().addAll(lblText, btnStartCubaseReason, musicTable);
		
		Scene scene = new Scene(root, 750, 1000);
		stage.setScene(scene);
		stage.show();
	}
		
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

}
