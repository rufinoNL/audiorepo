package nl.rufino.daw.program;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guigarage.flatterfx.FlatterFX;

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
import nl.rufino.daw.data.entity.MusicEntity;
import nl.rufino.util.AudioFunctions;
import nl.rufino.util.WindowsFunctions;

public class Program extends Application implements EventHandler<ActionEvent>{
	
	static final Logger LOGGER = LoggerFactory.getLogger(Program.class);
	private Label lblMessages;
	private Button btnStartCubaseReason;
	private TableView<MusicEntity> musicTable = new TableView<MusicEntity>();
	private String audioApplicationReason = WindowsFunctions.retrieveProperties("config.properties").getProperty("audio.application.reason");
	private String audioApplicationCubase = WindowsFunctions.retrieveProperties("config.properties").getProperty("audio.application.cubase");
	
	//private EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw_data");
	//private EntityManager em = emf.createEntityManager();
	//private MainViewData data = new MainViewData(em);


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {	
		setupView();
		configureTable();
		loadScreen(stage);
	}

	private void loadScreen(Stage stage) {
		VBox root = new VBox();
		root.getChildren().addAll(lblMessages, btnStartCubaseReason, musicTable);
		
		Scene scene = new Scene(root, 750, 1000);
		stage.setScene(scene);
		stage.show();
	}
		
	@Override
	public void handle(ActionEvent event) {
		message("Copying template and creating new folder");
		File[] filesToStart = AudioFunctions.createNewTemplate();
		
		try {
			message("Starting Cubase...");
			WindowsFunctions.startApplication(audioApplicationCubase, 
					filesToStart[0].toString());
			
			//TODO: why?
			Thread.sleep(25000);
			
			message("Starting Reason...");
			WindowsFunctions.startApplication(audioApplicationReason, 
					filesToStart[1].toString());
			message("");
			
		} catch (IOException e) {
			LOGGER.error("",e);
		} catch (InterruptedException e) {
			LOGGER.error("",e);
		}
	}
	
	private void setupView() {
		//Set style
		FlatterFX.style();
		configureLabelForMessages();
		configureStartButton();
	}

	private void configureLabelForMessages() {
		lblMessages = new Label();
		lblMessages.setWrapText(true);
	}

	private void configureStartButton() {
		btnStartCubaseReason = new Button();
		btnStartCubaseReason.setText("Start Cubase and Reason in ReWire");
		btnStartCubaseReason.setOnAction(this); //fires handle
	}
	
	private void configureTable() {
		//Column for id
		TableColumn<MusicEntity, Integer> musicIdColumn = new TableColumn<>("Id");
		musicIdColumn.setMinWidth(15);
		musicIdColumn.setCellValueFactory(new PropertyValueFactory<>("musicid"));
		
		//Column for track name
		TableColumn<MusicEntity, Integer> trackNameColumn = new TableColumn<>("Track name");
		trackNameColumn.setMinWidth(200);
		trackNameColumn.setCellValueFactory(new PropertyValueFactory<>("trackName"));
		
		//musicTable.setItems(data.getMusic());
		//musicTable.getColumns().addAll(musicIdColumn, trackNameColumn);
	}

	private void message(String message) {
		lblMessages.setText(message);
	}

}
