package application;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import controller.Connection;
import controller.Controller;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
 
public class Main extends Application {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceConfig");
	EntityManager em = emf.createEntityManager();
	EntityTransaction tx = em.getTransaction();

	private Stage window;
	private static  Button ExitButton;
	private Controller control = new Controller(Connection.getEntityManagerFactory());
	private ListView_SelectID selectID = new ListView_SelectID();

	/** elements commonly used in tab 1 and tab 2 */
	private InputValidation inputChecker = new InputValidation();
	
	/** tab1 elements */
	private Label tab1_NameLabel, tab1_IDlabel, tab1_DOBlabel, tab1_EmailLabel, tab1_PhoneNumLabel;
	private Button tab1_AddButton, tab1_UpdateButton, tab1_RemoveButton, tab1_ListButton, tab1_ClearButton, tab1_DummyButton;
	private TextField tab1_FirstNameTextField, tab1_MiddleNameTextField, tab1_LastNameTextField, tab1_IDtextField, tab1_EmailTextField, tab1_PhoneNumTextField;
	private TextArea tab1_textArea;

	/** tab2 elements */
	private Label tab2_StudentIdComboBoxLabel, tab2_ModuleNameLabel, tab2_GradeLabel;
	private Button tab2_AddButton, tab2_ListButton, tab2_ClearButton;
	private TextField tab2_ModuleTextField, tab2_GradeTextField;
	private TextArea tab2_textArea;
    private ComboBox<Integer> tab2_StudentIDcomboBox = new ComboBox<Integer>();
	private ObservableList<Integer> studentIDcomboBoxItems = FXCollections.observableArrayList();
	
	/** tab3 elements */
	private Label tab3_StudentIdComboBoxLabel, tab3_NameLabel, tab3_EmailLabel, tab3_PhoneNumberLabel, tab3_DOBlabel;
	private Button tab3_DisplayButton, tab3_OrderByGradeButton, tab3_OrderByModuleButton, tab3_1stClassModuleButton;
	private TextField tab3_NameTextField, tab3_EmailTextField, tab3_PhoneNumberTextField, tab3_DOBtextField;
	private TableView tab3_TableView;
	private TextArea tab3_textArea;
	private ObservableList<Map<String, String>> tab3_TableViewData = FXCollections.observableArrayList();
	private ObservableList<Map<String, String>> tab4_TableViewData = FXCollections.observableArrayList();
	private static ComboBox<Integer> tab3_StudentIDcomboBox;
	
	public static final String tab3_ModuleColumnKey = "Module";
	public static final String tab3_GradeColumnKey = "Grade";
	
	/** tab3 elements */
	private Button tab4_DisplayButton;
	private TextArea tab4_textArea;

	
	
	public static void main(String[] args) {
        launch(args);
    }
    
       
    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	window = primaryStage;
		control = new Controller(Connection.getEntityManagerFactory());

		TabPane tabPane = new TabPane();
		tabPane.setSide(Side.LEFT);

		Tab tab1 = new Tab("Tab1");
		Tab tab2 = new Tab("Tab2");
		Tab tab3 = new Tab("Tab3");
		Tab tab4 = new Tab("Tab4");
      
		tabPane.getTabs().add(tab1);
		tabPane.getTabs().add(tab2);
		tabPane.getTabs().add(tab3);
		tabPane.getTabs().add(tab4);

		tab1.setClosable(false);
		tab2.setClosable(false);
		tab3.setClosable(false);
		tab4.setClosable(false);
		
		
		/** tab1 Start! */
		GridPane tab1_root = new GridPane();
		tab1_root.setPadding(new Insets(10, 10, 10, 10));
	    tab1_root.setHgap(0);
	    tab1_root.setVgap(5);
	    tab1.setContent(tab1_root);
	             
	    tab1_NameLabel = new Label("Name *");
	    tab1_NameLabel.prefWidthProperty().bind(Bindings.divide(tab1_root.widthProperty(), 1.0));
	    tab1_FirstNameTextField = new TextField();
	    tab1_FirstNameTextField.prefWidthProperty().bind(Bindings.divide(tab1_root.widthProperty(), 1.0));
	    tab1_FirstNameTextField.setPromptText("First Name *");
	    tab1_MiddleNameTextField = new TextField();
	    tab1_MiddleNameTextField.prefWidthProperty().bind(Bindings.divide(tab1_root.widthProperty(), 1.0));
	    tab1_MiddleNameTextField.setPromptText("Middle Name");
	    tab1_LastNameTextField = new TextField();
	    tab1_LastNameTextField.prefWidthProperty().bind(Bindings.divide(tab1_root.widthProperty(), 1.0));
	    tab1_LastNameTextField.setPromptText("Last Name *");
	    
	    HBox tab1_NameTextFieldHbox = new HBox();
	    tab1_NameTextFieldHbox.setPadding(new Insets(0, 0, 0, 0));
	    tab1_NameTextFieldHbox.setSpacing(5);
	    tab1_NameTextFieldHbox.getChildren().add(tab1_FirstNameTextField);
	    tab1_NameTextFieldHbox.getChildren().add(tab1_MiddleNameTextField);
	    tab1_NameTextFieldHbox.getChildren().add(tab1_LastNameTextField);
	    
	    
	    tab1_IDlabel = new Label("Student ID *");
	    tab1_IDtextField = new TextField();
	      
	    tab1_EmailLabel = new Label("E-mail");
	    tab1_EmailTextField = new TextField();
	      
	    tab1_PhoneNumLabel = new Label("Phone Number");
	    tab1_PhoneNumTextField = new TextField();      
	    tab1_PhoneNumTextField.textProperty().addListener(new ChangeListener<String>() {
	    	@Override
	    	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	    		if (!newValue.matches("\\d*")) {
	    			tab1_PhoneNumTextField.setText(newValue.replaceAll("[^\\d]", ""));
	    		}
	    	}
	    });
	    
	    tab1_DOBlabel = new Label("Date of Birth");
	    DatePicker tab1_DOBdatePicker = new DatePicker();
	    tab1_DOBdatePicker.setValue(null);

	    tab1_textArea = new TextArea();
	    tab1_textArea.setEditable(false);
	    tab1_textArea.prefHeightProperty().bind(Bindings.divide(tab1_root.widthProperty(), 1.0));
	    tab1_textArea.prefHeightProperty().bind(Bindings.divide(tab1_root.heightProperty(), 1.0));
	      
	    tab1_AddButton = new Button();
	    tab1_AddButton.setText("ADD");
	    tab1_AddButton.setOnAction(e -> {
	    	String firstName = tab1_FirstNameTextField.getText();
	    	String middleName = tab1_MiddleNameTextField.getText();
	    	String lastName = tab1_LastNameTextField.getText();
	        String id = tab1_IDtextField.getText();
	        String email = tab1_EmailTextField.getText();
	        if (email == null) {
	        	email = "null";
	        }
	        String phoneNumber = tab1_PhoneNumTextField.getText();
	        if (phoneNumber == null) {
	        	phoneNumber = "null";
	        }
	        LocalDate dob = tab1_DOBdatePicker.getValue();
	        if (inputChecker.tab1_isInputValid(firstName, middleName, lastName, id, email, phoneNumber, dob) == true) {
	        	control.tab1_addStudent(firstName, middleName, lastName, id, email, phoneNumber, dob);
	        	tab1_FirstNameTextField.setText("");
	        	tab1_MiddleNameTextField.setText("");
	        	tab1_LastNameTextField.setText("");
	        	tab1_IDtextField.setText("");
	        	tab1_EmailTextField.setText("");
	        	tab1_PhoneNumTextField.setText("");
	        	tab1_DOBdatePicker.setValue(null);
//	        	System.out.println(control.tab1_LoadAllStudents()); // for check
	        }
	    });

	    tab1_UpdateButton = new Button();
	    tab1_UpdateButton.setText("UPDATE");
	    tab1_UpdateButton.setOnAction(e -> {
	    	
	    	Integer selectedID = selectID.select();
	    	tab1_FirstNameTextField.setText(control.tab1_GetStudentToBeUpdated(selectedID).getName().getFirstName());
        	tab1_MiddleNameTextField.setText(control.tab1_GetStudentToBeUpdated(selectedID).getName().getMiddleName());
        	tab1_LastNameTextField.setText(control.tab1_GetStudentToBeUpdated(selectedID).getName().getLastName());
        	tab1_IDtextField.setText(control.tab1_GetStudentToBeUpdated(selectedID).getID().toString());
        	tab1_EmailTextField.setText(control.tab1_GetStudentToBeUpdated(selectedID).getEmail());
        	tab1_PhoneNumTextField.setText(control.tab1_GetStudentToBeUpdated(selectedID).getPhoneNumber());
        	tab1_DOBdatePicker.setValue(control.tab1_GetStudentToBeUpdated(selectedID).getDOB());
	    });
	    
	    tab1_RemoveButton = new Button();
	    tab1_RemoveButton.setText("REMOVE");
	    tab1_RemoveButton.setOnAction(e -> control.tab1_RemoveStudent(selectID.select()));
	    
	    
	    tab1_ListButton = new Button();
	    tab1_ListButton.setText("LIST");
	    tab1_ListButton.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent event) {
	    		tab1_textArea.setText(control.tab1_getStringForTextarea());
	            tab1_FirstNameTextField.setText("");
	            tab1_MiddleNameTextField.setText("");
	            tab1_LastNameTextField.setText("");
	            tab1_IDtextField.setText("");
	            tab1_DOBdatePicker.setValue(null);
	    	}
	    });
	      
	    tab1_ClearButton = new Button();
	    tab1_ClearButton.setText("CLEAR");
	    tab1_ClearButton.setOnAction(e -> tab1_textArea.setText(""));
	    
	    tab1_DummyButton = new Button();
	    tab1_DummyButton.setText("Create Dummy Objects");
	    tab1_DummyButton.setOnAction(e -> {

	    	/** Add an extra button that creates a loop that creates dummy people objects
	    	 * and adds them to some collection (stored in the Heap Space)
	    	 * until the application runs out of memory.
	    	 * Note how long it took and the memory at the point of exception.
	    	 * Set the VM size to half of normal then use the same button 
	    	 * and observe what happens/how long it takes to get the out of memory exception.
	    	 * Attach a Word document which shows your analysis of this memory leak 
	    	 * and explain in your own words what is happening.  */
	    	
	    	
	    	
	    	
	    	
	    });
	    
	    
	    
	      
	    HBox tab1_ButtonHbox = new HBox();
	    tab1_ButtonHbox.setPadding(new Insets(10, 0, 0, 0));
	    tab1_ButtonHbox.setSpacing(5);
	    tab1_ButtonHbox.prefWidthProperty().bind(tab1_root.widthProperty());
	    tab1_ButtonHbox.getChildren().add(tab1_AddButton);
	    tab1_ButtonHbox.getChildren().add(tab1_UpdateButton);
	    tab1_ButtonHbox.getChildren().add(tab1_RemoveButton);
	    tab1_ButtonHbox.getChildren().add(tab1_ListButton);
	    tab1_ButtonHbox.getChildren().add(tab1_ClearButton);
	    tab1_ButtonHbox.getChildren().add(tab1_DummyButton);	      
	    
	    tab1_root.add(tab1_NameLabel, 0, 0, 3, 1);
	    tab1_root.add(tab1_NameTextFieldHbox, 1, 0, 3, 1);
	    tab1_root.add(tab1_IDlabel, 0, 1);
	    tab1_root.add(tab1_IDtextField, 1, 1, 3, 1);
	    tab1_root.add(tab1_EmailLabel, 0, 2);
	    tab1_root.add(tab1_EmailTextField, 1, 2, 3, 1);
	    tab1_root.add(tab1_PhoneNumLabel, 0, 3);
	    tab1_root.add(tab1_PhoneNumTextField, 1, 3, 3, 1);          
	    tab1_root.add(tab1_DOBlabel, 0, 4);
	    tab1_root.add(tab1_DOBdatePicker, 1, 4, 3, 1);
	    tab1_root.add(tab1_ButtonHbox, 0, 5, 4, 1);
	    tab1_root.add(tab1_textArea, 0, 6, 4, 1);
	    	    
	    /** tab1 End! */
	      
	      
	    /** tab2 Start! */
	    GridPane tab2_root = new GridPane();
	    tab2_root.setPadding(new Insets(10, 10, 10, 10));
	    tab2_root.setHgap(0);
	    tab2_root.setVgap(5);          
	    tab2.setContent(tab2_root);
	      
	    tab2_StudentIdComboBoxLabel = new Label("Student ID *");
	    tab2_StudentIdComboBoxLabel.prefWidthProperty().bind(Bindings.divide(tab2_root.widthProperty(), 1.0));
	      
	    tab2_StudentIDcomboBox.setPromptText("Select Student");
	    tab2_StudentIDcomboBox.setItems(studentIDcomboBoxItems);
	    tab2_StudentIDcomboBox.setEditable(false);
	      
	    Button tab2_StudentIDrefreshButton = new Button("Refresh Data");
	    tab2_StudentIDrefreshButton.setOnAction(event -> {
	    	studentIDcomboBoxItems.setAll(control.getStudentIDcomboBoxItems());
	    });

	    HBox tab2_comboHbox = new HBox();
	    tab2_comboHbox.setPadding(new Insets(0, 0, 0, 0));
	    tab2_comboHbox.setSpacing(5);
	      
	    tab2_comboHbox.getChildren().add(tab2_StudentIDcomboBox);
	    tab2_comboHbox.getChildren().add(tab2_StudentIDrefreshButton);
	      
	    tab2_ModuleNameLabel = new Label("Module *");
	    tab2_ModuleTextField = new TextField();
	    tab2_GradeLabel = new Label("Grade");
	    tab2_GradeTextField = new TextField();	   
	    
	    tab2_textArea = new TextArea();
	    tab2_textArea.setEditable(false);
	    tab2_textArea.prefWidthProperty().bind(Bindings.divide(tab2_root.widthProperty(), 1.0));
	    tab2_textArea.prefHeightProperty().bind(Bindings.divide(tab2_root.heightProperty(), 1.0));
	      
	    HBox tab2_textAreaHbox = new HBox();
	    tab2_textAreaHbox.setPadding(new Insets(0, 0, 0, 0));
	    tab2_textAreaHbox.setSpacing(5);           
	    tab2_textAreaHbox.getChildren().add(tab2_textArea);
	      
	    tab2_AddButton = new Button();
	    tab2_AddButton.setText("ADD");	    
	    tab2_AddButton.setOnAction(e -> {
	    	Integer id = tab2_StudentIDcomboBox.getSelectionModel().getSelectedItem();
	        String module = tab2_ModuleTextField.getText();
	        String strGrade = tab2_GradeTextField.getText();
	        if (strGrade == null) {
	        	strGrade = "null";
	        }
	        System.out.println("\nMain.java -> tab2_AddButton -> before tab2_isInputValid: \nid: " 
	        + id + " / module: " + module + " / strGrade: " + strGrade); // for check
	        	        
	        if (inputChecker.tab2_isInputValid(id, module, strGrade) == true) {
	        	
	        	System.out.println("(before addModuleAndGrade) studentID: " + id 
	        			+ ", module: " + module + ", strGrade: " + strGrade); // for check
	        	
		        control.tab2_addModuleGrade(id, module, strGrade);
		        tab2_StudentIDcomboBox.setPromptText("Select Student");
		        tab2_ModuleTextField.setText("");
		        tab2_GradeTextField.setText("");
	        }
	        
	    });
	    
	    
	    tab2_ListButton = new Button();
	    tab2_ListButton.setText("LIST");
	    tab2_ListButton.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent event) {
	    		tab2_textArea.setText("");
	    		tab2_textArea.setText(control.tab2_getStringForTextarea());
	            tab2_ModuleTextField.setText("");
	            tab2_GradeTextField.setText("");
	    	}
	    });
	      
	    tab2_ClearButton = new Button();
	    tab2_ClearButton.setText("CLEAR");
	    tab2_ClearButton.setOnAction(e -> tab2_textArea.setText(""));
	      
	    HBox tab2_ButtonHbox = new HBox();
	    tab2_ButtonHbox.setPadding(new Insets(10, 0, 0, 0));
	    tab2_ButtonHbox.setSpacing(5);

	    tab2_ButtonHbox.getChildren().add(tab2_AddButton);
	    tab2_ButtonHbox.getChildren().add(tab2_ListButton);
	    tab2_ButtonHbox.getChildren().add(tab2_ClearButton);
	    tab2_ButtonHbox.prefWidthProperty().bind(tab2_root.widthProperty());
	    	    
	    tab2_root.add(tab2_StudentIdComboBoxLabel, 0, 0, 3, 1);
	    tab2_root.add(tab2_comboHbox, 1, 0);          
	    tab2_root.add(tab2_ModuleNameLabel, 0, 1, 2, 1);
	    tab2_root.add(tab2_ModuleTextField, 1, 1, 3, 1);
	    tab2_root.add(tab2_GradeLabel, 0, 2);
	    tab2_root.add(tab2_GradeTextField, 1, 2, 3, 1);
	    tab2_root.add(tab2_ButtonHbox, 0, 3, 3, 1);
	    tab2_root.add(tab2_textAreaHbox, 0, 4, 3, 1);
	    
	      
	    /** tab2 End! */

	    
	      
	    /** tab3 Start! */

	    GridPane tab3_root = new GridPane();
	    tab3_root.setPadding(new Insets(10, 10, 10, 10));
	    tab3_root.setHgap(0);
	    tab3_root.setVgap(5);
	    tab3.setContent(tab3_root);

	    tab3_StudentIdComboBoxLabel = new Label("Student ID");
	    tab3_StudentIDcomboBox = new ComboBox<Integer>();
	    tab3_StudentIDcomboBox.setPromptText("Select Student");
	    tab3_StudentIDcomboBox.setItems(studentIDcomboBoxItems);
	    studentIDcomboBoxItems.setAll(control.getStudentIDcomboBoxItems());
	      
	    TableColumn<Map<String, String>, String> moduleColumn = new TableColumn<>("Module");
	    TableColumn<Map<String, String>, Integer> gradeColumn = new TableColumn<>("Grade");
	      
	    moduleColumn.setCellValueFactory(new MapValueFactory(tab3_ModuleColumnKey));
	    gradeColumn.setCellValueFactory(new MapValueFactory(tab3_GradeColumnKey));

	    tab3_TableView = new TableView<>(tab3_TableViewData);
	      
	    tab3_TableView.setEditable(true);
	    tab3_TableView.prefWidthProperty().bind(Bindings.divide(tab3_root.widthProperty(), 1.0));
	    tab3_TableView.prefHeightProperty().bind(Bindings.divide(tab3_root.heightProperty(), 1.0));
	      
	    tab3_TableView.getSelectionModel().setCellSelectionEnabled(true);
	    tab3_TableView.getColumns().setAll(moduleColumn, gradeColumn);

	    Callback<TableColumn<Map<String, String>, String>, TableCell<Map<String, String>, String>> cellFactoryForMap_module = new Callback<TableColumn<Map<String, String>, String>, TableCell<Map<String, String>, String>>() {
	    	public TableCell<Map<String, String>, String> call(TableColumn<Map<String, String>, String> p) {
	    		return new TextFieldTableCell(new StringConverter() {
	    			public String toString(Object t) {
	    				return t.toString();
	    			}
	    			public Object fromString(String string) {
	    				return string;
	    			}
	    		});
	    	}
	    };
	    
	    Callback<TableColumn<Map<String, String>, Integer>, TableCell<Map<String, String>, Integer>> cellFactoryForMap_grade = new Callback<TableColumn<Map<String, String>, Integer>, TableCell<Map<String, String>, Integer>>() {
	    	public TableCell<Map<String, String>, Integer> call(TableColumn<Map<String, String>, Integer> p) {
	    		return new TextFieldTableCell(new StringConverter() {
	    			public String toString(Object t) {
	    				return t.toString();
	    			}
	    			public Object fromString(String string) {
	    				return string;
	    			}
	    		});
	    	}
	    };
	    
	    moduleColumn.setCellFactory(cellFactoryForMap_module);
	    gradeColumn.setCellFactory(cellFactoryForMap_grade);
	         
	    tab3_TableView.prefWidthProperty().bind(Bindings.divide(tab3_root.widthProperty(), 1.0));
	    moduleColumn.prefWidthProperty().bind(tab3_TableView.widthProperty().multiply(0.5));
	    gradeColumn.prefWidthProperty().bind(tab3_TableView.widthProperty().multiply(0.5));    
	    tab3_TableView.setPlaceholder(new Label("No rows to display"));
	    
	    HBox tab3_TableViewHbox = new HBox();
	    tab3_TableViewHbox.setPadding(new Insets(10, 0, 0, 0));
	    tab3_TableViewHbox.setSpacing(5);
	       
	    tab3_TableViewHbox.getChildren().add(tab3_TableView);
	       
	    tab3_DisplayButton = new Button("Display Data");
	    tab3_DisplayButton.setOnAction(event -> {
	    	tab3_TableView.getItems().clear();
	    	tab3_GenerateDataForMap(tab3_StudentIDcomboBox.getValue());
	    });
	                
	    tab3_OrderByGradeButton = new Button("Order By Grade");
	    tab3_OrderByGradeButton.setOnAction(event -> {
	    	tab3_TableView.getItems().clear();
//	        tableViewData.generateDataForMap_OrderByGrade(tab3_StudentIDcomboBox.getValue());
	        tab3_GenerateDataForMap_SortByGrade(tab3_StudentIDcomboBox.getValue());
	    });
	                
	    tab3_OrderByModuleButton = new Button("Order By Module");
	    tab3_OrderByModuleButton.setOnAction(event -> {
	    	tab3_TableView.getItems().clear();
	    	tab3_GenerateDataForMap_SortByModule(tab3_StudentIDcomboBox.getValue());
	    });
	    
	    tab3_1stClassModuleButton = new Button("1st Class Honor");
	    tab3_1stClassModuleButton.setOnAction(event -> {
	    	tab3_TableView.getItems().clear();
	    	tab3_GenerateDataForMap_1stClassModule(tab3_StudentIDcomboBox.getValue());
	    });
	    

	    HBox tab3_comboHbox = new HBox();
	    tab3_comboHbox.setPadding(new Insets(0, 0, 0, 0));
	    tab3_comboHbox.setSpacing(5);

	    tab3_comboHbox.getChildren().add(tab3_StudentIDcomboBox);
	    tab3_comboHbox.getChildren().add(tab3_DisplayButton);
	    tab3_comboHbox.getChildren().add(tab3_OrderByGradeButton);
	    tab3_comboHbox.getChildren().add(tab3_OrderByModuleButton);
	    tab3_comboHbox.getChildren().add(tab3_1stClassModuleButton);
	    
	    tab3_NameLabel = new Label("Name");
	    tab3_NameTextField = new TextField();
	    tab3_EmailLabel = new Label("Email");
	    tab3_EmailTextField = new TextField();
	    tab3_PhoneNumberLabel = new Label("Phone Number");
	    tab3_PhoneNumberTextField = new TextField();
	    tab3_DOBlabel = new Label("Date Of Birth");
	    tab3_DOBtextField = new TextField();

	    tab3_StudentIDcomboBox.setOnAction(event -> {
	    	Integer selectedID_ComboBox = tab3_StudentIDcomboBox.getValue();
	        tab3_NameTextField.setText(control.tab3_getNameToDisplay(selectedID_ComboBox));
	        tab3_EmailTextField.setText(control.tab3_GetEmailToDisplay(selectedID_ComboBox));
	        tab3_PhoneNumberTextField.setText(control.tab3_GetPhoneNumberToDisplay(selectedID_ComboBox));
	        tab3_DOBtextField.setText(control.tab3_GetDOBtoDisplay(selectedID_ComboBox).toString());
	    });
	      
	    tab3_root.add(tab3_StudentIdComboBoxLabel, 0, 0);
	    tab3_root.add(tab3_comboHbox, 0, 1, 2, 1);
	    tab3_root.add(tab3_NameLabel, 0, 2);
	    tab3_root.add(tab3_NameTextField, 1, 2, 2, 1);
	    tab3_root.add(tab3_EmailLabel, 0, 3);
	    tab3_root.add(tab3_EmailTextField, 1, 3, 2, 1);
	    tab3_root.add(tab3_PhoneNumberLabel, 0, 4);
	    tab3_root.add(tab3_PhoneNumberTextField, 1, 4, 2, 1);
	    tab3_root.add(tab3_DOBlabel, 0, 5);
	    tab3_root.add(tab3_DOBtextField, 1, 5, 2, 1);
	    tab3_root.add(tab3_TableViewHbox, 0, 6, 2, 1);
	    
	    /** tab3 End! */
			    
	    
	    
	    /** tab4 Start! */

	    GridPane tab4_root = new GridPane();
	    tab4_root.setPadding(new Insets(10, 10, 10, 10));
	    tab4_root.setHgap(0);
	    tab4_root.setVgap(5);
	    tab4.setContent(tab4_root);

	    tab4_DisplayButton = new Button("Display All Students List");
	    tab4_DisplayButton.setOnAction(event -> {
	    	tab4_textArea.setText("");
	    	tab4_textArea.setText(control.tab4_DisplayAllStudents());
	    });
	    
	    HBox tab4_ButtonHbox = new HBox();
	    tab4_ButtonHbox.setPadding(new Insets(10, 0, 0, 0));
	    tab4_ButtonHbox.setSpacing(5);
	    tab4_ButtonHbox.getChildren().add(tab4_DisplayButton);

	    tab4_textArea = new TextArea();
	    tab4_textArea.setEditable(false);
	    tab4_textArea.prefWidthProperty().bind(Bindings.divide(tab4_root.widthProperty(), 1.0));
	    tab4_textArea.prefHeightProperty().bind(Bindings.divide(tab4_root.heightProperty(), 1.0));
	   
	    tab4_root.add(tab4_ButtonHbox, 0, 0);
	    tab4_root.add(tab4_textArea, 0, 1);
	   
	    /** tab4 End! */
	    
		
		/** Exit Button which is shared from all tabs */

		ExitButton = new Button("EXIT");
		ExitButton.setOnAction(e -> AlertWindows.exitAlert());
      
		HBox ButtonHbox = new HBox();
		ButtonHbox.setPadding(new Insets(0, 10, 10, 10));
		ButtonHbox.setSpacing(5);
		ButtonHbox.setAlignment(Pos.BASELINE_RIGHT);
		ButtonHbox.getChildren().add(ExitButton);

		VBox layout = new VBox();
		layout.getChildren().addAll(tabPane, ButtonHbox);
		Scene scene = new Scene(layout, 600, 500);
       
		window.setTitle("MTU Student Record System");
		window.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.show();
          
    }

      
      private ObservableList<Map<String, String>> tab3_GenerateDataForMap(Integer studentID) {
      	
  		List<String> moduleList = new ArrayList<String>();
  		List<Integer> gradeList = new ArrayList<Integer>();

  		control.tab3_mapForTableView(studentID).entrySet().forEach(entry -> {
  			moduleList.add(entry.getKey());
  			gradeList.add(entry.getValue());
  		});
        
  		for (int i = 0; i < moduleList.size(); i++) {
  			Map<String, String> dataRow = new LinkedHashMap<>();
  			dataRow.put(tab3_ModuleColumnKey, moduleList.get(i)); // put module name
  			dataRow.put(tab3_GradeColumnKey, gradeList.get(i).toString()); // put grade
  			tab3_TableViewData.add(dataRow);

  		}
//  		System.out.println("\nModule & Grade list of all students: " + control.getModuleAndGradeHashMapForAllStudents()); // for check
  		return tab3_TableViewData;
      }
      
      
      public ObservableList<Map<String, String>> tab3_GenerateDataForMap_SortByGrade(Integer studentID) {
    	  
    	  List<String> moduleList = new ArrayList<String>();
    	  List<Integer> gradeList = new ArrayList<Integer>();
    	  LinkedHashMap<String, Integer> mapSortByGrade = 
    			  control.tab3_sortByValueMap(control.tab3_mapForTableView(studentID)); // sort by value(grade)
    	  
    	  mapSortByGrade.entrySet().forEach(entry -> {
    		  moduleList.add(entry.getKey());
    		  gradeList.add(entry.getValue());
    	  });
    	  
    	  for (int i = 0; i < moduleList.size(); i++) {
    		  Map<String, String> dataRow = new LinkedHashMap<>();
    		  dataRow.put(tab3_ModuleColumnKey, moduleList.get(i));
    		  dataRow.put(tab3_GradeColumnKey, gradeList.get(i).toString());
    		  tab3_TableViewData.add(dataRow);
    	  }

    	  return tab3_TableViewData;
      }
      
      
      public ObservableList<Map<String, String>> tab3_GenerateDataForMap_SortByModule(Integer studentID) {
    	  
    	  List<String> moduleList = new ArrayList<String>();
    	  List<Integer> gradeList = new ArrayList<Integer>();
    	  
    	  LinkedHashMap<String, Integer> mapSortByModule = control.tab3_mapForTableView(studentID);
    	  // use treemap to sort by module name
    	  TreeMap<String, Integer> tempMap = new TreeMap<String, Integer>(mapSortByModule);
    	  mapSortByModule.clear();
    	  mapSortByModule.putAll(tempMap);
    	  System.out.println("map for tableview ordered by module name: " + mapSortByModule); // for check

    	  mapSortByModule.entrySet().forEach(entry -> {
    		  moduleList.add(entry.getKey());
    		  gradeList.add(entry.getValue());
    	  });
    	  
    	  for (int i = 0; i < moduleList.size(); i++) {
    		  Map<String, String> dataRow = new LinkedHashMap<>();
    		  dataRow.put(tab3_ModuleColumnKey, moduleList.get(i));
    		  dataRow.put(tab3_GradeColumnKey, gradeList.get(i).toString());
    		  tab3_TableViewData.add(dataRow);
    	  }    	  
    	  
    	  return tab3_TableViewData;
    	  
      }
      
      
      public ObservableList<Map<String, String>> tab3_GenerateDataForMap_1stClassModule(Integer studentID) {
    	  
    	  List<String> moduleList = new ArrayList<String>();
    	  List<Integer> gradeList = new ArrayList<Integer>();
    	  LinkedHashMap<String, Integer> mapSortByGrade = 
    			  control.tab3_sortByValueMap(control.tab3_mapForTableView(studentID)); // sort by value(grade)
    	  
    	  mapSortByGrade.entrySet().forEach(entry -> {
    		  if (entry.getValue() >= 70) {
        		  moduleList.add(entry.getKey());
        		  gradeList.add(entry.getValue());    			  
    		  }
    	  });
    	  
    	  for (int i = 0; i < moduleList.size(); i++) {
    		  Map<String, String> dataRow = new LinkedHashMap<>();
    		  dataRow.put(tab3_ModuleColumnKey, moduleList.get(i));
    		  dataRow.put(tab3_GradeColumnKey, gradeList.get(i).toString());
    		  tab3_TableViewData.add(dataRow);
    	  }
    	  
    	  return tab3_TableViewData;
      
      }
      
      

      
      

}