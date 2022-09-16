package application;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class AlertWindows {
	
	
	public static void exitAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("Do you want to exit?");
//		alert.setContentText("Don't forget to save before exiting.");
//		ButtonType exitButtonType1 = new ButtonType("Save (before Exit)");
		ButtonType exitButtonType1 = new ButtonType("Exit");
		ButtonType exitButtonType2 = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(exitButtonType1, exitButtonType2);
		Optional<ButtonType> response = alert.showAndWait();
		if (response.get() == exitButtonType1) {
			System.exit(0);
		}
	}
	
	
	public static void AddFail() {
		Alert alert_addFail = new Alert(AlertType.INFORMATION);
		alert_addFail.setTitle("Save");
		alert_addFail.setHeaderText(null);
		alert_addFail.setContentText("Fail to Save.");
		alert_addFail.showAndWait();
	}
	
	
	public static void RemoveFail() {
		Alert alert_removeFail = new Alert(AlertType.INFORMATION);
		alert_removeFail.setTitle("Remove");
		alert_removeFail.setHeaderText(null);
		alert_removeFail.setContentText("Fail to Remove.");
		alert_removeFail.showAndWait();
	}

	
}