package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		System.out.println("onMenuItemDepartmentAction");
	} 
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	} 
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
	}

	// synchronized não interrompe o processamento durante o multithreading
	private synchronized void loadView(String absoluteName) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		try {
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			
			// getRoot() pega o primeiro elemento da view
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			// pega uma referencia do primeiro filho do vbox principal
			Node mainMenu = mainVBox.getChildren().get(0);
			
			// limpa os dependentes da view main
			mainVBox.getChildren().clear();
			
			// adiciona a referencia do menu que foi pega acima
			mainVBox.getChildren().add(mainMenu);
			
			// adiciona o novo vbox about com seus dependentes na view
			mainVBox.getChildren().addAll(newVBox.getChildren());
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
