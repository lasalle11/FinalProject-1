package login;

import DatabaseSQL.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DatabaseSQL.Query;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RegistrationForm extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Registration Form For New User");

		// Create the registration form grid pane
		GridPane gridPane = createRegistrationFormPane();
		// Add UI controls to the registration form grid pane
		addUIControls(gridPane);
		// Create a scene with registration form grid pane as the root node
		Scene scene = new Scene(gridPane, 800, 500);
		// Set the scene in primary stage
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	private GridPane createRegistrationFormPane() {
		// Instantiate a new Grid Pane
		GridPane gridPane = new GridPane();

		// Position the pane at the center of the screen, both vertically and
		// horizontally
		gridPane.setAlignment(Pos.CENTER);

		// Set a padding of 20px on each side
		gridPane.setPadding(new Insets(40, 40, 40, 40));

		// Set the horizontal gap between columns
		gridPane.setHgap(10);

		// Set the vertical gap between rows
		gridPane.setVgap(8);

		// Add Column Constraints

		// columnOneConstraints will be applied to all the nodes placed in
		// column one.
		ColumnConstraints columnOneConstraints = new ColumnConstraints(100,
				100, Double.MAX_VALUE);
		columnOneConstraints.setHalignment(HPos.RIGHT);

		// columnTwoConstraints will be applied to all the nodes placed in
		// column two.
		ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200,
				Double.MAX_VALUE);
		columnTwoConstrains.setHgrow(Priority.ALWAYS);

		gridPane.getColumnConstraints().addAll(columnOneConstraints,
				columnTwoConstrains);

		return gridPane;
	}

	private void addUIControls(final GridPane gridPane) {
		// Add Header
		Label headerLabel = new Label("Registration Form");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		gridPane.add(headerLabel, 0, 0, 2, 1);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

		// Add UserID Labbel
		Label UserIDLabel = new Label("Enter 3 Digit ID: ");
		gridPane.add(UserIDLabel, 0, 1);

		// Add UserID Text Field
		final TextField UserIDField = new TextField();
		gridPane.add(UserIDField, 1, 1);

		// Add First Name Label
		Label FnameLabel = new Label("First Name : ");
		gridPane.add(FnameLabel, 0, 2);

		// Add First Name Text Field
		final TextField FnameField = new TextField();
		FnameField.setPrefHeight(40);
		gridPane.add(FnameField, 1, 2);

		// Add Last Name Label
		Label LnameLabel = new Label("Last Name: ");
		gridPane.add(LnameLabel, 0, 3);

		// Add Last Name Text Field
		final TextField LnameField = new TextField();
		LnameField.setPrefHeight(40);
		gridPane.add(LnameField, 1, 3);

		// Label for Address
		Label addressLabel = new Label("Address");
		gridPane.add(addressLabel, 0, 4);

		// text field address
		final TextField addressField = new TextField();
		addressField.setPrefHeight(40);
		gridPane.add(addressField, 1, 4);

		// Label for state
		Label stateLabel = new Label("State");
		gridPane.add(stateLabel, 0, 5);

		// Text for state
		final TextField stateField = new TextField();
		stateField.setPrefHeight(40);
		gridPane.add(stateField, 1, 5);

		// label for zip
		Label zipLabel = new Label("Zip");
		gridPane.add(zipLabel, 0, 6);

		// TextField for zip
		final TextField zipField = new TextField();
		zipField.setPrefHeight(40);
		gridPane.add(zipField, 1, 6);

		// label username
		Label usernameLabel = new Label("Username");
		gridPane.add(usernameLabel, 0, 7);

		// textfield username
		final TextField usernameField = new TextField();
		usernameField.setPrefHeight(40);
		gridPane.add(usernameField, 1, 7);

		// label password
		Label passwordLabel = new Label("Password");
		gridPane.add(passwordLabel, 0, 8);

		// TextField
		final TextField passwordField = new TextField();
		passwordField.setPrefHeight(40);
		gridPane.add(passwordField, 1, 8);

		// Add Email Label
		Label emailLabel = new Label("Email: ");
		gridPane.add(emailLabel, 0, 9);

		// Add Email Text Field
		final TextField emailField = new TextField();
		emailField.setPrefHeight(40);
		gridPane.add(emailField, 1, 9);

		// Add SSN label
		Label ssnLabel = new Label("SSN");
		gridPane.add(ssnLabel, 0, 10);

		// Add ssn textfield
		final TextField ssnField = new TextField();
		ssnField.setPrefHeight(40);
		gridPane.add(ssnField, 1, 10);

		// Security Question Label
		Label SQLabel = new Label("Security Question:");
		gridPane.add(SQLabel, 0, 11);

		// Secuirty Quesiton Text Field
		final TextField SQField = new TextField();
		SQField.setPrefHeight(40);
		gridPane.add(SQField, 1, 11);

		// Securiyt Answer label
		Label SALabel = new Label("Security Answer");
		gridPane.add(SALabel, 0, 12);

		// Secuiryt Anser TextField
		final TextField SAField = new TextField();
		SAField.setPrefHeight(40);
		gridPane.add(SAField, 1, 12);

		// Add Submit Button
		Button submitButton = new Button("Submit");
		submitButton.setPrefHeight(40);
		submitButton.setDefaultButton(true);
		submitButton.setPrefWidth(100);
		gridPane.add(submitButton, 0, 13, 2, 1);
		GridPane.setHalignment(submitButton, HPos.CENTER);
		GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

		submitButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				
				
				
				try {
					String query = "insert Into user (userID, FirstName, LastName, address, zip, state, username, password, email, ssn, securityQuestion, securityAnswer)"
							+ "values (?,?,?,?,?,?,?,?,?,?,?,?)";
					Connection con = Query.getConnection();
					PreparedStatement pst = con.prepareStatement(query);

					pst.setString(1, UserIDField.getText());
					pst.setString(2, FnameField.getText());
					pst.setString(3, LnameField.getText());
					pst.setString(4, addressField.getText());
					pst.setString(5, zipField.getText());
					pst.setString(6, stateField.getText());
					pst.setString(7, usernameField.getText());
					pst.setString(8, passwordField.getText());
					pst.setString(9, emailField.getText());
					pst.setString(10, ssnField.getText());
					pst.setString(11, SQField.getText());
					pst.setString(12, SAField.getText());

					pst.executeUpdate();

					pst.close();

				} catch (Exception e) {

					e.printStackTrace();
				}

				showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene()
						.getWindow(), "Registration Successful!", "Welcome "
						+ FnameField.getText() + " " + LnameField.getText());
			}
			
			
			
			
		});
	}

	private void showAlert(Alert.AlertType alertType, Window owner,
			String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
