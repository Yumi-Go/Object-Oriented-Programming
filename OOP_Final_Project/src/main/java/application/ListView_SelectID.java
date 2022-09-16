package application;

import controller.Connection;
import controller.Controller;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListView_SelectID {
	
	private Controller control = new Controller(Connection.getEntityManagerFactory());

	static Integer selected;
	static ListView<Integer> listView;
	static ObservableList<Integer> listSelected;
	
	public Integer select() {
		
		listView = new ListView<>();
		Stage window = new Stage();
		Button selectButton = new Button("Select");
		Button cancelButton = new Button("Cancel");

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Select ID");
		window.setMinWidth(350);
		
		
		for (int i = 0; i < control.getListViewItems_ID().size(); i++) {
			listView.getItems().add(control.getListViewItems_ID().get(i));
		}	
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		selectButton.setOnAction(e -> {
			listSelected = listView.getSelectionModel().getSelectedItems();
			selected = listSelected.get(0);
			window.close();
		});
		
		cancelButton.setOnAction(e -> {
			listSelected = listView.getSelectionModel().getSelectedItems();
			selected = null;
			window.close();
		});		
		
		HBox selectButtonHbox = new HBox();
		selectButtonHbox.setPadding(new Insets(0, 0, 0, 0));
		selectButtonHbox.setSpacing(5);
		selectButtonHbox.getChildren().add(selectButton);
		selectButtonHbox.getChildren().add(cancelButton);
		selectButtonHbox.setAlignment(Pos.CENTER);
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(listView, selectButtonHbox);
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(0, 0, 10, 0));
		selectButtonHbox.prefWidthProperty().bind(layout.widthProperty());
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		return selected;
		
	}
	
}
