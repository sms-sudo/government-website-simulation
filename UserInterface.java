/*
 * Salwa Abdalla 
 * ICS3U Culminating Assignment: 1/21/2019
 * Jeff Radulovic
 * 
 * This GUI is a beta version user interface of a beta website for the Government of Kadin
 * The features include:
 * 		- a login/sign up interaction (create an account or load in an old one)
 * 		- viewing Terms and Conditions of visiting my website
 *  	- viewing a profile with basic information
 * 		- sending messages to fellow employees
 *  	- reading past messages sent to and sent from fellow employees
 *  	- viewing and searching jobs offered
 *  	- reading a story homepage (randomly switches order every time the browser is reloaded)
 *  	- view a Privacy Policy for visiting and using my website
 * 
 */

//importing the various libraries to be used throughout
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ScrollPane;

public class UserInterface extends Application {

	//initializing three ArrayLists to hold information from csv files
	private static ArrayList<Employees> Employees_List;
	private static ArrayList<JobList> JobList_List;
	private static ArrayList<Message> Message_List;
	
	//initializing the employee to be logged in
	private static Employees LoggedInEmployee;
	
	//creating paths to images used multiple times
	private String BackgroundPath = "Images/BackgroundImage2.jpg";
	private String FlagPath = "Images/FlagHolder.png";
	private String valid_EmployeesPath = "./src/Files/Employees.csv";
	private String valid_JobListPath = "./src/Files/JobListings.csv";
	private String valid_MessagePath = "./src/Files/Messages.csv";
	
	//creating font sizes, and fonts to be used stylistically
	private int titlesize = 18;
	private Font title = Font.font("name", FontWeight.BOLD, titlesize);
	private int labelsize = 15;
	private Font label = Font.font("name", FontWeight.SEMI_BOLD, labelsize);
	private int smalllablesize = 13;
	private Font smalllabel = Font.font("name", FontWeight.SEMI_BOLD, smalllablesize);
	
	//creating booleans to aid in sending messages and searching
	boolean level1 = false;
	boolean level2 = false;
	boolean level3 = false;
	boolean newfilterdraw = true;
	
	//creating stylistic integers for spacing, sizes and padding
	private static int style1 = 150;
	private static int style2 = 100;
	private static int style3 = 15;
	private static int style4 = 50;
	private static int style5 = 25;
	private static int style6 = 10;
	private static int style7 = 300;
	private static int style8 = 350;
	private static int style9 = 450;
	private static int style10 = 250;
	private static int style11 = 200;
	private static int style12 = 500;
	private static int style13 = 75;
	private int VisibleRowCount = 5;
	private int[] S_ScreenSize = {650, 500};
	private int[] L_ScreenSize = {1000, 800};
	
	//creating array list to hold constantly changing information
	private ArrayList<String> filtersEntered = new ArrayList<>();
	private ArrayList<Long> encryption_key = new ArrayList<>();
	
	//launching the GUI in the main method
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
//------------------------------------------------------------Creating instances of classes------//
		//includes instances from Search, WriteInformation, LoadInformation, Random, and Cipher
		
		Search search = new Search();
		WriteInformation writer = new WriteInformation();
		LoadInformation loader = new LoadInformation();
		Random random = new Random();
		Cipher cipher = new Cipher();
		
//-----------------------------------------------------------Loading Information-----------------//
		//reading information from the accompanying files
		
		Employees_List = loader.loadEmployees(valid_EmployeesPath);
		JobList_List = loader.loadJobLists(valid_JobListPath);
		Message_List = loader.loadMessages(valid_MessagePath);

//---------------------------------------------------------------Default Image Set Up------------//
		//creating the intro scene and the log in scene with accompanying boxes
		//image on intro scene is being loaded in and styled
		
		HBox h1 = new HBox();
		VBox v5 = new VBox();
		
		Scene First_scene = new Scene(h1, L_ScreenSize[0], L_ScreenSize[1]);
		Scene Second_scene = new Scene(v5, L_ScreenSize[0], L_ScreenSize[1]);

		Image image1 = new Image(BackgroundPath);
		ImageView imageView1 = new ImageView();
		imageView1.setImage(image1);
		imageView1.setFitHeight(L_ScreenSize[1]);
		imageView1.setFitWidth(L_ScreenSize[0] / 2);

//-----------------------------------------------------------------Login or Sign Up--------------//
		//creating the first page: giving user to options of logging in or signing up
		//terms and conditions (user manual) also included to launch a new stage
		
		VBox Display1 = new VBox();
		Display1.setPadding(new Insets(style1, style1, style1, style1));

		Image image2 = new Image(FlagPath);
		ImageView imageView2 = new ImageView();
		imageView2.setImage(image2);
		imageView2.setFitHeight(style2);
		imageView2.setPreserveRatio(true);

		Text text3 = new Text("Government of Kadin");
		text3.setFont(title);

		Button button3 = new Button("Login");
		Button button4 = new Button("Sign Up");

		Hyperlink hyperlink1 = new Hyperlink();
		hyperlink1.setText("Terms and Conditions");

		Display1.getChildren().addAll(imageView2, text3, button3, button4, hyperlink1);
		Display1.setSpacing(style5);
		Display1.setAlignment(Pos.CENTER);

		h1.getChildren().addAll(imageView1, Display1);

//------------------------------------------------------------------Login Form-------------------//
		//creating the login page (with username and password)
		//using a gridpane to style how they appear in relation to each other
		
		Text text4 = new Text("Login");
		text4.setFont(label);
		Text text1 = new Text("Username");
		Text text2 = new Text("Password");

		TextField textField1 = new TextField();
		TextField textField2 = new TextField();

		Button button1 = new Button("Submit");
		Button button2 = new Button("Clear");

		Hyperlink hyperlink2 = new Hyperlink();
		hyperlink2.setText("Return to Main Menu");

		GridPane gridPane1 = new GridPane();
		gridPane1.setMinSize(L_ScreenSize[0] / 2, L_ScreenSize[1]);
		gridPane1.setPadding(new Insets(style3, style3, style3, style3));

		gridPane1.setVgap(style3);
		gridPane1.setHgap(style3);
		gridPane1.setAlignment(Pos.CENTER);

		Label label1 = new Label();

		gridPane1.add(text4, 0, 0);
		gridPane1.add(text1, 0, 1);
		gridPane1.add(textField1, 1, 1);
		gridPane1.add(text2, 0, 2);
		gridPane1.add(textField2, 1, 2);
		gridPane1.add(button1, 0, 3);
		gridPane1.add(button2, 1, 3);
		gridPane1.add(hyperlink2, 0, 4);
		gridPane1.add(label1, 1, 5);

//-----------------------------------------------------------------Sign Up Form------------------//
		//creating the signup page that collects the required information to make an account
		//includes using basic text/textfields and combo boxes
		//Setting up of the combo boxes, and using a tooltip to allow users to hover their
		//mouse and see why we are collecting such information
		//also includes a hyperlink to return to main menu (if they don't want to sign up)
		
		
		Text text5 = new Text("Sign Up");
		text5.setFont(label);
		Text text6 = new Text("It's free and it always will be");

		TextField textField3 = new TextField("First Name");
		TextField textField4 = new TextField("Last Name");

		HBox h2 = new HBox();
		h2.getChildren().addAll(textField3, textField4);

		TextField textField5 = new TextField("Username");
		TextField textField6 = new TextField("Password");

		Text text7 = new Text("Birthday");

		HBox h3 = new HBox();

		String month_names[] = { "January", "February", "March", "April", "May", "June", "July", 
				"August", "September", "October", "November", "December" };
		ComboBox<String> month_DropDown = 
				new ComboBox<String>(FXCollections.observableArrayList(month_names));
		month_DropDown.setVisibleRowCount(VisibleRowCount);
		month_DropDown.getSelectionModel().select(1);
		month_DropDown.setMinWidth(style2);

		String day_names[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
				"14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
				"28", "29", "30", "31" };
		ComboBox<String> day_DropDown = 
				new ComboBox<String>(FXCollections.observableArrayList(day_names));
		day_DropDown.setVisibleRowCount(VisibleRowCount);
		day_DropDown.getSelectionModel().select(0);

		String year_names[] = { "2000", "2001", "2002", "2003" };
		ComboBox<String> year_DropDown = 
				new ComboBox<String>(FXCollections.observableArrayList(year_names));
		year_DropDown.setVisibleRowCount(VisibleRowCount);
		year_DropDown.getSelectionModel().select(2);
		year_DropDown.setMinWidth(style13);

		Text text9 = new Text(" [?]");
		Tooltip tooltip1 = new Tooltip();
		tooltip1.setText("Providing your birthday helps \nmake sure you get the right\n experience"
				+ " for your age.");
		Tooltip.install(text9, tooltip1);

		h3.getChildren().addAll(month_DropDown, day_DropDown, year_DropDown, text9);

		String location_names[] = { "Toronto", "Brampton", "Mississauga", "Ottawa" };
		ComboBox<String> location_DropDown = 
				new ComboBox<String>(FXCollections.observableArrayList(location_names));
		location_DropDown.setVisibleRowCount(VisibleRowCount);
		location_DropDown.getSelectionModel().select(0);
		Text text10 = new Text(" [?]");
		Tooltip tooltip2 = new Tooltip();
		tooltip2.setText("Providing your location helps \nmake sure you get the right\n "
				+ "notification for your area.");
		Tooltip.install(text10, tooltip2);

		HBox h4 = new HBox();
		h4.getChildren().addAll(location_DropDown, text10);
		h4.setAlignment(Pos.CENTER);

		Text text8 = new Text("Location");

		Label label2 = new Label();
		label2.setFont(smalllabel);
		label2.setTextFill(Color.RED);

		Button button5 = new Button("Sign Up");

		Hyperlink hyperlink3 = new Hyperlink();
		hyperlink3.setText("Return to Main Menu");

		VBox v3 = new VBox();
		v3.getChildren().addAll(text5, text6, h2, textField5, textField6, text7, h3, text8, 
				h4, button5, hyperlink3, label2);
		v3.setPadding(new Insets(style1, style1, style1, style1));
		v3.setSpacing(style5);
		v3.setAlignment(Pos.CENTER);

//---------------------------------------------------------Event Action Handlers-----------------//
		//Event Handlers for all the buttons and hyperlinks located on the main menu, log in page
		//and the sign up page
		
		// Login Button - located on Log in page to actually log in
		//checks if the username and password match with an existing employee
		//if not, returns a message to the user
		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int EmployeeIndex = search.searchIndexEmployees(Employees_List, textField1.getText(),
						textField2.getText());
				System.out.println(EmployeeIndex);
				if (EmployeeIndex != -1) {
					System.out.println(Employees_List.get(EmployeeIndex));
					LoggedInEmployee = Employees_List.get(EmployeeIndex);
					textField1.clear(); textField2.clear();
					primaryStage.setScene(Second_scene);
				} else {
					label1.setText("Sorry, we don't \nrecognize this account.");
					label1.setFont(smalllabel);
					label1.setTextFill(Color.RED);
				}
			}
		});

		// Clear button - located on Log in page to clear username and password fields
		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				textField1.clear();
				textField2.clear();
				label1.setText("");
			}
		});

		// Login Button - located on Main Menu to open login page
		button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				h1.getChildren().clear();
				h1.getChildren().addAll(imageView1, gridPane1);
			}
		});

		// Sign Up Button - located on Main Menu to open sign up page
		button4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				h1.getChildren().clear();
				h1.getChildren().addAll(imageView1, v3);
			}
		});

		// Sign Up Button - located on Sign up page to actually sign up
		//checks user properly entered information into the textfields and
		//returns a message if they haven't
		button5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//getting input from textfields
				String fn = textField3.getText();
				String ln = textField4.getText();
				String un = textField5.getText();
				String pswd = textField6.getText();

				//getting day of birth chosen
				String day = day_DropDown.getValue();
				
				//translating month chosen to number
				int monthnum = -1;
				for (int i = 0; i < month_names.length; i++) {
					if (month_names[i].equals(month_DropDown.getValue())) {
						monthnum = i + 1;
					}
				}
				//getting the year of birth chosen
				String year = year_DropDown.getValue();
				
				//combining birth information to be able to write into file
				String Birthdate;
				if (monthnum < 10) {
					Birthdate = day + "/0" + monthnum + "/" + year;
				} else
					Birthdate = day + "/" + monthnum + "/" + year;
				
				//getting location
				String Location = location_DropDown.getValue();

				//creating employee num
				int EmployeeNum = Employees_List.size() + 1;
				String Enum;
				if (EmployeeNum <= 9)
					Enum = "000" + EmployeeNum;
				else
					Enum = "00" + EmployeeNum;
				
				Employees employee;
				//making a boolean to check if valid info is allowed
				boolean takenusername = false;
				boolean takenpassword = false;

				//checking if username or password is already taken
				for (int i = 0; i < Employees_List.size(); i++) {
					if (un.equals(Employees_List.get(i).getUsername()))
						takenusername = true;
					if (pswd.equals(Employees_List.get(i).getPassword()))
						takenpassword = true;
				}

				//checking the user changed default values
				//if valid, create employee and write into file
				//if not valid, return a message to user to say refill in textfields
				if (!fn.equals("First Name") && !ln.equals("Last Name") && !un.equals("Username")
						&& !pswd.equals("Password")) {
					if (fn.length() != 0 && ln.length() != 0 && un.length() != 0 && 
							pswd.length() != 0) {

						if (takenusername) {
							label2.setText("Username already taken, \nplease choose another one");
						} else if (takenpassword) {
							label2.setText("Password already taken, \nplease choose another one");
						}

						else {
							employee = new Employees(Enum, fn, ln, un, pswd, "Sudo", 
									Birthdate, Location);
							label2.setText("");
							LoggedInEmployee = employee;
							try {
								writer.createEmployee(employee, Employees_List);
								primaryStage.setScene(Second_scene);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					} else {
						label2.setText("Please completly fill in the fields.");
					}
				}

				else {
					label2.setText("Please completly fill in the fields.");
				}

			}
		});

		//creating stage to show when hyperlink1 (Terms and Conditions is pressed)
		VBox termsbox = new VBox();
		termsbox.setPadding(new Insets(style4, style13, style4, style13)); 
		Scene scene4 = new Scene(termsbox, S_ScreenSize[0], S_ScreenSize[1]);
		Stage stage4 = new Stage();
		stage4.setScene(scene4);
		stage4.setTitle("Terms and Conditions");
		
		Text termsTitle = new Text("Terms and Conditions");
		termsTitle.setFont(title);
		
		Text termsText = new Text("\r\n"
				+ "\r\nLast updated: January 2018\r\n\r\n By accessing or using the website operated "
				+ "\nat www.pwc.com/ca and such other locations as made available from time to time "
				+ "\n(collectively, the Government of Kadin website) and the services offered through "
				+ "\nthe Website, you (“you” and, together with all persons accessing or using the "
				+ "\nWebsite, collectively, the Users”) signify that you have read, understand and "
				+ "\nagree to be bound by these Terms and Conditions (the “Terms and Conditions”) with "
				+ "\nthe Government of Kadin, we in all respects with respect "
				+ "\nto the Website.\r\n\r\nPLEASE READ THESE TERMS AND CONDITIONS CAREFULLY AS THEY "
				+ "\nCONTAIN IMPORTANT INFORMATION REGARDING YOUR LEGAL RIGHTS, REMEDIES "
				+ "\nAND OBLIGATIONS."
				+ "\n THESE INCLUDE, BUT ARE NOT LIMITED TO, VARIOUS LIMITATIONS AND EXCLUSIONS, "
				+ "\nAND INDEMNITIES.\r\n\r\nYour use of the Website is subject to these Terms and "
				+ "\nConditions. If you are not willing to be bound by each and every term or condition,"
				+ "\n or if any representation made herein by you is not true, you may not use, "
				+ "\nand must cease using, the Website.\r\n");
		
		termsbox.getChildren().addAll(termsTitle, termsText);
		
		// Terms and Conditions Button - to open up User Interface Guide
		hyperlink1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("This link is clicked");
				stage4.show();
				// Make this show the user interface guide (Required)
			}
		});

		// Return to Main Menu Button - to return to option between login and sign up
		hyperlink2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				h1.getChildren().clear();
				h1.getChildren().addAll(imageView1, Display1);
			}
		});

		// Return to Main Menu Button - to return to option between login and sign up
		hyperlink3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				h1.getChildren().clear();
				h1.getChildren().addAll(imageView1, Display1);
			}
		});
		
		//showing the primary stage and setting name and scene
		primaryStage.setTitle("Salwa Abdalla ICS Culminating");
		primaryStage.setScene(First_scene);
		primaryStage.show();

//----------------------------------------------------------LOGGED IN DISPLAY--------------------//
		
		
//--------------------------------------------------------------TOP BANNER-----------------------//
		//creating the top banner of the logged in page
		//includes a flag image, the name and a logout hyperlink
		
		HBox h6a = new HBox();
		h6a.setMinWidth(L_ScreenSize[0]);
		h6a.setAlignment(Pos.TOP_RIGHT);
		Hyperlink hyperlink4 = new Hyperlink();
		hyperlink4.setText("Logout");
		hyperlink4.setAlignment(Pos.TOP_RIGHT);
		h6a.getChildren().add(hyperlink4);
				
		HBox h6 = new HBox();
		HBox h61 = new HBox();
		HBox h7 = new HBox();
		VBox v6 = new VBox();
		
		ImageView imageView3 = new ImageView();
		imageView3.setImage(image2);
		imageView3.setFitHeight(style4);
		imageView3.setPreserveRatio(true); 	
		
		Text t1 = new Text("	Government\n	  of Kadin");
		t1.setFont(title);
		t1.minWidth(style7);

		h61.getChildren().addAll(imageView3, t1);
		h61.setAlignment(Pos.CENTER_LEFT);
		h61.setPadding(new Insets(0, style12, style5, style4));
		h6.getChildren().addAll(h61);
		
		//creating a stylistic separator
		Separator separator = new Separator();
		separator.setMinWidth(L_ScreenSize[0]);
		
		//creating a menu bar to allow the user to move from page to page
		//is the connection to the different features of my beta website
		
		MenuBar menuBar = new MenuBar();
		
		//creating Profile drop down menu
        Menu menuProfile = new Menu("  Profile  ");
        MenuItem menuItem1a = new MenuItem("  View  ");
        menuProfile.getItems().add(menuItem1a);
        
        //creating Messenger drop down menu
        Menu menuMessenger = new Menu("  Messenger  ");
        MenuItem menuItem1b = new MenuItem("  Send New Message  ");
        MenuItem menuItem2b = new MenuItem("  Past Messages  ");
        menuMessenger.getItems().add(menuItem1b);
        menuMessenger.getItems().add(menuItem2b);
        
        //creating Jobs drop down menu
        Menu menuJob = new Menu("  Jobs  ");
        MenuItem menuItem1c = new MenuItem("  View  ");
        menuJob.getItems().add(menuItem1c);
        
        //creating Services drop down menu
        Menu menuService = new Menu("  Services  ");
        MenuItem menuItem1e = new MenuItem("  Hompage  ");
        MenuItem menuItem2e = new MenuItem("  Privacy  ");
        menuService.getItems().add(menuItem1e);
        menuService.getItems().add(menuItem2e);
        
        //adding all drop down menus to the Menu Bar
        //stylistically organizing the size and alignment
        menuBar.getMenus().addAll(menuProfile, menuMessenger, menuJob, menuService);
        menuBar.setMinWidth(L_ScreenSize[0]);
        h7.getChildren().add(menuBar);
        h7.setMinWidth(L_ScreenSize[0]);
        h7.setAlignment(Pos.CENTER);
		
        //creating the top banner and including Padding (stylistic)
		v5.getChildren().addAll(h6a, h6, separator, h7, v6);
		v6.setPadding(new Insets(style4, style13, style4, style13)); 
		
//---------------------------------------------------------------Home Page-----------------------//
		//creating the home page outline, with randomly (order) changing stories for each
		//time you log in
		
		Text t2 = new Text("What's New\n\n");
		t2.setFont(title);
		t2.setFill(Color.DARKBLUE);
		
		//Creating one story instance
		HBox h8a = new HBox();
		VBox v7a = new VBox();
		
		Image image3 = new Image(BackgroundPath); //change image
		ImageView imageView4 = new ImageView();
		imageView4.setImage(image3);
		imageView4.setFitHeight(style2); 
		imageView4.setFitWidth(style2); 
		
		h8a.getChildren().addAll(imageView4, v7a);
				
		Text t3a = new Text("	The Power of Data, The Power of Change");
		t3a.setFont(title);
		
		Text t3b = new Text("	Biomedical Scientists are riding a powerful wave of change in how"
				+ " we conduct our research\n	Increasingly, biomedical science is being performed"
				+ " at the interface of wet-lab experimentation\n	and high powered computational"
				+ " and systems approaches. Artificial intelligence...");
		t3b.setFont(label);
		
		v7a.getChildren().addAll(t3a, t3b);
		v7a.setMaxWidth(style7);
		
		//creating another story instance
		HBox h9a = new HBox();
		VBox v8a = new VBox();
		
		Image image4 = new Image(BackgroundPath); //change image
		ImageView imageView5 = new ImageView();
		imageView5.setImage(image4);
		imageView5.setFitHeight(style2);
		imageView5.setFitWidth(style2); 
		
		h9a.getChildren().addAll(imageView5, v8a);
		
		Text t4a = new Text("	AI and Machine Learning");
		t4a.setFont(title);
		
		Text t4b = new Text("	In the summer of 1956, a small group of academics gathered for a "
				+ "workshop at Dartmouth \n	College. The meeting; billed as an attempt to "
				+ "'find out how to make machines use language, form \n	abstractions and concepts,"
				+ " solve the kinds of problems now reserved for humans, and improve "
				+ "\n	themsleves,' succeeded in giving birth to a field...");
		t4b.setFont(label);
		
		v8a.getChildren().addAll(t4a, t4b);
		v8a.setMaxWidth(style7);
		
		//creating another story instance
		HBox h10a = new HBox();
		VBox v9a = new VBox();
		
		Image image5 = new Image(BackgroundPath); //change image
		ImageView imageView6 = new ImageView();
		imageView6.setImage(image5);
		imageView6.setFitHeight(style2);
		imageView6.setFitWidth(style2);
		
		h10a.getChildren().addAll(imageView6, v9a);
		
		Text t5a = new Text("	The Importance of Nuance");
		t5a.setFont(title);
		
		Text t5b = new Text("	In 2016, researchers in Heidelberg, Germany, built a sphisticated "
				+ "computer model, called \n	a neural network, to identify melanomas based on "
				+ "clinical images. They fed it more than 100,000 \n	photographs of lesions "
				+ "labeled 'malignant' or 'bengin' and let it reverse-engineer its own "
				+ "\n	methods for differentiating them...");
		t5b.setFont(label);
		
		v9a.getChildren().addAll(t5a, t5b);
		v9a.setMaxWidth(style7);
		
		//creating another story instance
		HBox h11a = new HBox();
		VBox v9a1 = new VBox();
		
		Image image51 = new Image(BackgroundPath); //change image
		ImageView imageView61 = new ImageView();
		imageView61.setImage(image51);
		imageView61.setFitHeight(style2);
		imageView61.setFitWidth(style2);
		
		h11a.getChildren().addAll(imageView61, v9a1);
		
		Text t5a1 = new Text("	To Boldy Go --Remote");
		t5a1.setFont(title);
		
		Text t5b1 = new Text("	 The history of telemedicine - providing patient care from a "
				+ "distance using \n	telecommunications technology - dates somewhat "
				+ "surprisingly, to ancient times, when the Greeks \n	and Romance used "
				+ "smoke signals and beacons to warn neighboring villages of disease outbreaks"
				+ "\n	or to announce births and deaths. With the development of radio...");
		t5b1.setFont(label);
		
		v9a1.getChildren().addAll(t5a1, t5b1);
		v9a1.setMaxWidth(style7);
		
		VBox newsStoriesBox = new VBox();
		ScrollPane scrollpane5 = new ScrollPane();
		scrollpane5.setContent(newsStoriesBox);
		scrollpane5.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		@SuppressWarnings("serial")
		ArrayList<HBox> NewStories = new ArrayList<HBox>() {{
		    add(h8a);
		    add(h9a);
		    add(h10a);
		    add(h11a);
		}};
		
		Collections.shuffle(NewStories);
		newsStoriesBox.getChildren().addAll(NewStories.get(0), NewStories.get(1), NewStories.get(2),
				NewStories.get(3));

		v6.getChildren().addAll(t2, scrollpane5);
		h8a.setPadding(new Insets(style3, 0, style3, 0)); 
		h9a.setPadding(new Insets(style3, 0, style3, 0)); 
		h10a.setPadding(new Insets(style3, 0, style3, 0)); 
		
//--------------------------------------------------------Profile --> VIEW ----------------------//
		//creating the profile page		
		
		HBox h5 = new HBox();
		h5.setPadding(new Insets(style5, style12, style5, style4));
		
		VBox v7 = new VBox();
		VBox v8 = new VBox();
		
		v7.setPadding(new Insets(style5, style5, style5, style5));
		v8.setPadding(new Insets(style5, style5, style5, style5)); 
		
		h5.getChildren().addAll(v7, v8);
		
		Image image6 = new Image(BackgroundPath); //change image
		ImageView imageView7 = new ImageView();
		imageView7.setImage(image6);
		imageView7.setFitHeight(style2); 
		imageView7.setFitWidth(style2);

		v7.getChildren().addAll(imageView7);
		v7.setSpacing(style6); 
		
		Text name = new Text("Name:");
		Text birthday = new Text("Birthday:");
		Text employeeNum = new Text("Employee Number:");
		Text location = new Text("Location: ");
				
		v8.getChildren().addAll(name, birthday, employeeNum, location);
		v8.setSpacing(style6); 
		
//--------------------------------------Messenger --> Send New Message --------------------------//
		//creating a GUI section to allow the user to send messages
		//to those Employee Number s/he knows
		
		VBox v9 = new VBox();
		
		//Creating a step by step message sender to make sure all information
		//needed to create and write a message is successfully taken
		
		//allowing them to choose which level of Encryption they want
		Text t3 = new Text("Step One: Select Encryption Level");
		t3.setFont(title);
		//make it so only sudo users can click LEVEL THREE
		
		HBox h8 = new HBox();
		h8.setSpacing(style6); 
		h8.setAlignment(Pos.CENTER);
		
		Button b2 = new Button("Level 1");
		
		//using booleans to keep track of which level has been chosen
		b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	level1 = true;
            	level2 = false;
            	level3 = false;
            	System.out.println(level1 + " " + level2 + " " + level3);
            }
        });
		
		//creating a tooltip to allow users to know which each level refers to
		Text t4 = new Text(" [?]");
		Tooltip tooltip3 = new Tooltip();
		tooltip3.setText("A Simple Caesar Cipher, with a simple offset");
		Tooltip.install(t4, tooltip3);
		
		Button b3 = new Button("Level 2");
		
		b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	level1 = false;
            	level2 = true;
            	level3 = false;
            	System.out.println(level1 + " " + level2 + " " + level3);
            }
        });

		Text t5 = new Text(" [?]");
		Tooltip tooltip4 = new Tooltip();
		tooltip4.setText("A Cipher with two keys: one addition and one mulitplication step");
		Tooltip.install(t5, tooltip4);
		
		Button b4 = new Button("Level 3");
		
		b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	level1 = false;
            	level2 = false;
            	level3 = true;
            	System.out.println(level1 + " " + level2 + " " + level3);
            }
        });
		
		Text t6 = new Text(" [?]");
		Tooltip tooltip5 = new Tooltip();
		tooltip5.setText("A RSA Cipher with two keys: one private and one public");
		Tooltip.install(t6, tooltip5);
		
		h8.getChildren().addAll(b2, t4, b3, t5, b4, t6);
		
		//Second Step: Select an Employee to send the message to
		Text t7 = new Text("Step Two: Choose Recipient");
		t7.setFont(title);
		
		//make a list of employees in the system for user to choose from
		ArrayList<String> employeenames = new ArrayList<>();
		for (int i = 0; i < Employees_List.size(); i++) {
			employeenames.add(" " + Employees_List.get(i).getFirstName() + " " + 
		Employees_List.get(i).getLastName());
		}
		ComboBox<String> Employee_DropDown = 
				new ComboBox<String>(FXCollections.observableArrayList(employeenames));
		Employee_DropDown.setVisibleRowCount(VisibleRowCount);
		Employee_DropDown.getSelectionModel().select(1); 
		Employee_DropDown.setMinWidth(style2);
		
		//Third Step: Generating a Valid key for the encryption/decryption
		Text t8 = new Text("Step Three: Generate Key");
		t8.setFont(title);
		
		Text t9 = new Text("Key: ");
		Button b5 = new Button("Generate");
		
		//if the generate button is pressed, find which level is being used to encrypt/decrypt
		//and generate the corresponding keys
		b5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //clear the past encryption key: in case being used again
            	encryption_key.clear();
            	
            	//if level1, generate a simple cipher key meant for the ASCII table with 128
            	//different keys/characters
            	if (level1)
            		encryption_key.add((long)random.nextInt(Cipher.modulo));
            	
            	//if level2, generate the addition and multiplication key that makes a valid
            	//decryption (look at the cipher class for more information)
            	else if (level2) {
            		long key_b = random.nextInt(1000); //making a max of 1000 for the multiplier
            		while (cipher.ValidKey(key_b, Cipher.modulo) == false) 
            			key_b = random.nextInt(1000); 
            		encryption_key.add((long) key_b);
            		encryption_key.add((long) random.nextInt(Cipher.modulo));
            	}
            	
            	//if level3, generate the RSA public and private keys that make for a 
            	//valid decryption
            	else if (level3) {
            		long[] Keys = cipher.generateKeys();
            		encryption_key.add(Keys[0]);
            		encryption_key.add(Keys[1]);
            		encryption_key.add(Keys[2]);
            		//the Public Key is index 0 and 2, and the Private Key is index 1 and 2
            	}
            	
            	//if no level is selected and the user attempts to generate a key
            	//it advises them to select one
            	else {
            		t9.setText("Please select a level of encryption above");
            		t9.setFill(Color.RED);
            	}
            	
            	//once the key is generated, it is displayed on the screen
            	if (level1 || level2 || level3) {
            		t9.setText("Key: " + encryption_key);
            		t9.setFill(Color.BLACK);
            	}
            	
            }
        });
		
		HBox h9 = new HBox();
		h9.getChildren().addAll(t9, b5);
		h9.setSpacing(style6); 
		h9.setAlignment(Pos.CENTER);
		
		//creating a textfield to have the user input their message into to encrypt
		TextField messagetextfield = new TextField("Write message here...");
		messagetextfield.setPrefSize(style10, style2); 
		messagetextfield.setMaxSize(style10, style11);
		Button b6 = new Button("Send Message");	  
		Text t10 = new Text("");
		t10.setFill(Color.RED);
		
		//Stylistically organizing the send messages page
		v9.getChildren().addAll(t3, h8, t7, Employee_DropDown, t8, h9, messagetextfield, b6, t10);
		v9.setPadding(new Insets(style5, style6, style5, style6)); 
		v9.setSpacing(style6);
		v9.setAlignment(Pos.CENTER);
		
		//creating a reset button (for all choices)
		Button resetSendMessage = new Button("RESET");
		
		//Sending the message to another user by the click of a button
		b6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Employees selectede = Employees_List.get(0);
            	//find the employee that is selected by comparing the firstname and last name
            	for (int i = 0; i < Employees_List.size(); i++) {
            		//if the first and last name match, the selected employee is at that index
            		if (Employee_DropDown.getValue().equals(" " + 
            	Employees_List.get(i).getFirstName() + " " + 
            				Employees_List.get(i).getLastName())) {
            			selectede = Employees_List.get(i);
            		}
            	}
            	
            	//if the textfield hasn't changed from the default value, prompt user
            	if (messagetextfield.getText().equals("Write message here...")) {
            		t10.setText("Please enter your message in the textfield above");
            	}
            	
            	//if the user hasn't generated the encryption key
            	else if (encryption_key == null) {
            		t10.setText("Please generate an encryption key");
            	}
            	
            	//if the level1 encryption is being use (only has one key)
            	else if (encryption_key.size() == 1){
            		//encrypting the message into a list of longs
            		long[] EncryptedMessage = cipher.encrypt(messagetextfield.getText(),
            				encryption_key.get(0));
            		//creating the instance of a message with correct info
            		Message newmessage = new Message(selectede.getEmployeeNum(), 
            				LoggedInEmployee.getEmployeeNum(), 1, EncryptedMessage, encryption_key);
            		try {
            			//if you can write into the file (which should exist)
						writer.createMessage(newmessage, Employees_List, Message_List);
					} catch (IOException e) {
						e.printStackTrace();
					}
            		t10.setText("Message succesfully sent!");
            		v9.getChildren().add(resetSendMessage);
            		
            	}
            	
            	//repeat the above step twice more for the addition/multiplication cipher
            	//and the RSA cipher
            	
            	else if (encryption_key.size() == 2) {
            		long[] EncryptedMessage = cipher.encrypt(messagetextfield.getText(), 
            				encryption_key.get(0), encryption_key.get(1));
            		Message newmessage = new Message(selectede.getEmployeeNum(), 
            				LoggedInEmployee.getEmployeeNum(), 2, EncryptedMessage, encryption_key);
            		try {
						writer.createMessage(newmessage, Employees_List, Message_List);
					} catch (IOException e) {
						e.printStackTrace();
					}
            		t10.setText("Message succesfully sent!");
            		v9.getChildren().add(resetSendMessage);
            	}
            	else if (encryption_key.size() == 3) {
            		long[] publickey = {encryption_key.get(0), encryption_key.get(2)};
            		long[] EncryptedMessage = cipher.encrypt(messagetextfield.getText(), publickey);
            		Message newmessage = new Message(selectede.getEmployeeNum(), 
            				LoggedInEmployee.getEmployeeNum(), 3, EncryptedMessage, encryption_key);
            		try {
						writer.createMessage(newmessage, Employees_List, Message_List);
					} catch (IOException e) {
						e.printStackTrace();
					}
            		t10.setText("Message succesfully sent!");
            		v9.getChildren().add(resetSendMessage);
            	}
            }
        });
		
		//reset button to send a new message on a clear page
		resetSendMessage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	//sets the default values up again for the textfields, the keys
            	//and the keys generated
                v9.getChildren().remove(resetSendMessage);
                t10.setText("");
                t9.setText("Key: ");
                messagetextfield.setText("Write message here...");
                level1 = false; level2 = false; level3 = false;
                Employee_DropDown.getSelectionModel().select(1);
                encryption_key.clear();
            }
        });
		
		
//------------------------------------------------Messenger --> View Messages-----------------//	
		//A page to allow users to pull up chats with other employees and see
		//past messages that they have sent and that were sent to them		
		
		HBox h11 = new HBox();
		VBox v10 = new VBox();
		VBox v11 = new VBox();
		
		h11.getChildren().addAll(v10, v11);
		
		Text t11 = new Text("Employees");
		t11.setFont(title);
		
		ScrollPane scrollpane1 = new ScrollPane();
		VBox v12 = new VBox();
		v12.setMinHeight(style8);
		scrollpane1.setContent(v12);
		
		v10.getChildren().addAll(t11, scrollpane1);
		v10.setMinSize(style1, style9);
		
		//displays the names of all the employees on the side
		for (int i = 0; i < Employees_List.size(); i++) {
			Text nametag = new Text(Employees_List.get(i).getFirstName() + ' ' + 
					Employees_List.get(i).getLastName());
			v12.getChildren().add(nametag);
		}
		
		//allows users to search through employees by EMPLOYEE NUMBER
		TextField namesearch = new TextField("Enter Employee Number here...");
		namesearch.setAlignment(Pos.CENTER);
		Text namesearchresult = new Text();  
		namesearchresult.setFont(label); 
		
		ScrollPane scrollpane2 = new ScrollPane();
		VBox v13 = new VBox();
		v13.setMinHeight(style8);
		scrollpane2.setContent(v13);
		
		v11.getChildren().addAll(namesearch, namesearchresult, v13);
		v11.setMinSize(style7, style9);
		
		//Employee search for employee chat Event Handler
        namesearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	String ESN = namesearch.getText();
            	namesearchresult.setText("");
            	v13.getChildren().clear();
            	
            	//if searching their own employee number, display all of their messages
            	for (int i = 0; i < Employees_List.size(); i++) {
            		if (ESN.equals(LoggedInEmployee.getEmployeeNum())) {
                		namesearchresult.setText("Currently Viewing All Messages");
                	}
            		//else; show which employee number they took
            		else if (ESN.equals(Employees_List.get(i).getEmployeeNum())){
            			namesearchresult.setText("Employee Chat selected:\n " + 
            		Employees_List.get(i).getFirstName() + " " + Employees_List.get(i).getLastName());
            		}
            	}
            	//if the employee doesn't exist
            	if (namesearchresult.getText().equals(""))
            		namesearchresult.setText("Employee Number does not exist");
            	
            	//for the logged in employees personal messages
            	for (int i = 0; i < LoggedInEmployee.getPersonalMessages().size(); i++) {
            		//if the sender or recipient matches the employee number being searched, display
            		if (ESN.toLowerCase().equals(LoggedInEmployee.getPersonalMessages().get(i).getRecipientEmployeeNum())) {
            				String messagedecrypted = 
            						LoggedInEmployee.getPersonalMessages().get(i).getDecryptedMessage();
							Text message = new Text("	" + (i+1) + ") " + messagedecrypted);
							message.setFill(Color.RED);
	            			v13.getChildren().add(message);
	            			v13.setSpacing(style3);
            		}
            		else if (ESN.toLowerCase().equals(LoggedInEmployee.getPersonalMessages().get(i).getSenderEmployeeNum())) {
        				String messagedecrypted = 
        						LoggedInEmployee.getPersonalMessages().get(i).getDecryptedMessage();
						Text message = new Text("	" + (i+1) + ") " + messagedecrypted);
						message.setFill(Color.BLACK);
            			v13.getChildren().add(message);
            			v13.setSpacing(style3); 
        		}	
            			
            	}
            }
        });

//-------------------------------------------------------------Jobs --> View --------------------//
		
        //creating an array list to hold jobs that fulfill the filters searched
        ArrayList<JobList> Filtered_Jobs = new ArrayList<>();
        
        //creating the stylistic outline
        HBox h12 = new HBox();
        VBox v14 = new VBox();
        v14.setSpacing(style3); 
        v14.setPadding(new Insets(style4, style13, style6, style13)); 
        v14.setMinWidth(style1);
        
        VBox v15 = new VBox();
        v15.setSpacing(style3); 
        v15.setPadding(new Insets(style4, style13, style4, style13)); 
        v15.setMinWidth(L_ScreenSize[0] - style1);
        
        h12.getChildren().add(v14);
        
        //have the filters searched displayed and have have a clear filters button
        TextField filtersearch = new TextField();
        Text filters = new Text("Filters: ");
        filters.setFont(label);
        v14.getChildren().addAll(filtersearch, filters);
        Button clearfilters = new Button("Clear Filters");
        v14.getChildren().addAll(clearfilters);
       
        //in the beginning display all jobs (no filters)
        for (int i = 0; i < JobList_List.size(); i++) {
        	Text joblisting = new Text("	"  + (i+1) + ") " + JobList_List.get(i));
        	joblisting.setFont(label);
        	v15.getChildren().add(joblisting);
        }
        
        //scroll pane for showing all the jobs (in case too large to sdisplay on screen)
        ScrollPane scrollpane3 = new ScrollPane();
		v15.setMinHeight(style7);
		scrollpane3.setContent(v15);
		h12.getChildren().add(scrollpane3);
		
		//for an enter being pressed in the filter
		filtersearch.setOnAction(new EventHandler<ActionEvent>() {
            @SuppressWarnings("unlikely-arg-type")
			@Override
            public void handle(ActionEvent event) {
            	//for each filter, if it already exists, don't search again
            	for (int i = 0; i < filtersEntered.size(); i++) {
            		if (filtersEntered.get(i).toLowerCase().equals(filtersearch.getText().toLowerCase())) {
            			filtersEntered.remove(i);
            			newfilterdraw = false;
            		}
            	}
            	
            	//add the filter to the list of filters displayed
            	filtersEntered.add(filtersearch.getText());
            	v14.getChildren().remove(clearfilters);
            	Text newfilter;
            	if (newfilterdraw) {
            		newfilter = new Text(filtersEntered.get(filtersEntered.size() - 1));
            		v14.getChildren().add(newfilter);
            	}
            	v14.getChildren().add(clearfilters);
            	v15.getChildren().clear();
            	
            	//if the textfield is not empty (so something is being searched
                if (!filtersEntered.isEmpty()) {
                	//get rid of the old joblist being displayed
                    v15.getChildren().clear();
					for (int i = filtersEntered.size() - 1; i < filtersEntered.size(); i++) {
						for (int i1 = 0; i1 < JobList_List.size(); i1++) {
							// check if the job title matches
							if (filtersEntered.get(i).toLowerCase()
									.equals(JobList_List.get(i1).getJobTitle().toLowerCase())) {
								Filtered_Jobs.add(JobList_List.get(i1));
							}
							// check if the location matches
							else if (filtersEntered.get(i).toLowerCase()
									.equals(JobList_List.get(i1).getLocation().toLowerCase())) {
								Filtered_Jobs.add(JobList_List.get(i1));
							}
							// check if the rank matches
							else if (filtersEntered.get(i).toLowerCase()
									.equals(JobList_List.get(i1).getRank().toLowerCase())) {
								Filtered_Jobs.add(JobList_List.get(i1));
							}
							// check if the job is available matches
							else if (filtersEntered.get(i).toLowerCase().equals(JobList_List.get(i1).isAvailable())) {
								Filtered_Jobs.add(JobList_List.get(i1));
							} else {
								JobList_List.remove(i1);
                			}
                		}
                	}
                }
                //if the filter searched gave a list of jobs that fulfill the search
                if (!Filtered_Jobs.isEmpty()) {
                	for (int i = 0; i < Filtered_Jobs.size(); i++) {
                		if (newfilterdraw) {
	                    	Text joblisting = new Text("	"  + (i+1) + ") " + Filtered_Jobs.get(i));
	                    	joblisting.setFont(label);
	                    	v15.getChildren().add(joblisting);
                		}
                    }
                }
                //if no jobs fulfill the filters, reload the joblist from the file
                else {
                	newfilterdraw = true;
                	JobList_List = loader.loadJobLists("./src/Files/JobListings.csv");
                }
        
            }
        });
		
		//if the button is clicked, clear the past filters and show the list of jobs again
        clearfilters.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	newfilterdraw = true;
            	v14.getChildren().clear();
            	Filtered_Jobs.clear();
            	JobList_List = loader.loadJobLists("./src/Files/JobListings.csv");
            	v14.getChildren().addAll(filtersearch, filters, clearfilters);
            	v15.getChildren().clear();
            	for (int i = 0; i < JobList_List.size(); i++) {
                	Text joblisting = new Text("	"  + (i+1) + ") " + JobList_List.get(i));
                	joblisting.setFont(label);
                	v15.getChildren().add(joblisting);
                }
            }
        });
         
//--------------------------------------------------Services --> Privacy ------------------------//
        //display the privacy policy of my site (for legal purposes)
        //enter the text into a scrollpane
        
        VBox v16 = new VBox();
        v16.setPadding(new Insets(style4, style13, style6, style13));
        v16.setMinWidth(L_ScreenSize[0]); 
        
        Text privacytitle = new Text("Privacy Policy");
        privacytitle.setFont(title);
        
        ScrollPane scrollpane4 = new ScrollPane();
        Text privacypolicytext = new Text("Last updated: 1/20/2019\nThe Government of Kadin operates www.Kadin.gov. This page informs you"
        		+ "\nof our policies regarding the collection, use and disclosure of Personal Information "
        		+ "\nwe receive from users of the Site.We use your Personal Information only for providing "
        		+ "\nand improving the Site. By using the Site, you agree to the collection and use"
        		+ "\nof information in accordance with this policy\n"
        		+ "\nWhile using our site, we may ask you to provide us with certain personally identifiable"
        		+ "\ninformation that can be used to contact or identify you. Personally identifiable information"
        		+ "\nmay includ, but is not limited to your name, birthday, location and rank.\n"
        		+ "\nUnlike many site operators, we do not collect information that your browser sends whenever"
        		+ "\nyou visit our Site. This includes ignoring IP address, browser type, browser version, the pages"
        		+ "\nof our Site that you visit, the time and date of your visit, the time spent on those pages"
        		+ "\nand other statistics.\n"
        		+ "\nThe security of your Personal Information is important to us, but remember that no method of"
        		+ "\ntransmission over the Internet, or method of electronic storage, is 100% secure. While we"
        		+ "\nstrive to use commercially acceptable means to protect your Personal Information, we"
        		+ "\ncannot guarantee its absolute security."); 
        privacypolicytext.setFont(label);
        scrollpane4.setContent(privacypolicytext);
        
        v16.getChildren().addAll(privacytitle, scrollpane4);
        
        
//-------------------------------------------------------Jobs --> Apply -------------------------//
        
        //finish this page
        //finish the hyperlink on the first screen (for terms and conditions)
        //get rid of the search bar at the top of the entire logged in screen
        //finish adding text to the homepage (and make a scrollbar to have 5 different stories that appear in different order)
		
//-------------------------------------------------------Menu Button Actions---------------------//
		
        //Profile ---> View Button
        menuItem1a.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	//change to the correct hbox display
                v5.getChildren().clear();
                v5.getChildren().addAll(h6a, h6, separator, h7, h5);
                
                //if the logged in employee exists, display the profile
                if (LoggedInEmployee != null) {
        			name.setText("Name: " + LoggedInEmployee.getFirstName() + " " + LoggedInEmployee.getLastName());
        			birthday.setText("Birthday: " + LoggedInEmployee.getBirthdate());
        			employeeNum.setText("Employee Number: " + LoggedInEmployee.getEmployeeNum());
        			location.setText("Location: " + LoggedInEmployee.getLocation());

        		}
            }
        });
                
        //Messenger ---> View Button
        menuItem1b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	//change to the correct vbox display
                v5.getChildren().clear();
                v5.getChildren().addAll(h6a, h6, separator, h7, v9);
            }
        });
        
        //Messenger ---> Send Button
        menuItem2b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	//change to the correct hbox display
                v5.getChildren().clear();
                v5.getChildren().addAll(h6a, h6, separator, h7, h11);
                v13.getChildren().clear();
                //reset the text and textfields
                namesearchresult.setText("");
                messagetextfield.setText("Write message here...");
            }
        });
        
        //Jobs ---> View Button
        menuItem1c.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	//change to the correct hbox
                v5.getChildren().clear();
                v5.getChildren().addAll(h6a, h6, separator, h7, h12);
            }
        });
        
        //Services ---> Homepage Button
        menuItem1e.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {      
            	//change to the correct vbox display
                v5.getChildren().clear();
                v5.getChildren().addAll(h6a, h6, separator, h7, v6);
            }
        });
        
        //Services ---> Privacy Button
        menuItem2e.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	//change to the correct vbox display
                v5.getChildren().clear();
                v5.getChildren().addAll(h6a, h6, separator, h7, v16);
            }
        });
        		
		// Logout and return to the sign welcome page
		hyperlink4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//reset the primary stage and clear all the displays to reset the fields
				primaryStage.setScene(First_scene);
				h1.getChildren().clear();
				h1.getChildren().addAll(imageView1, Display1);
				v5.getChildren().clear();
                v5.getChildren().addAll(h6a, h6, separator, h7, v6);
			}
		});
        
	}
}