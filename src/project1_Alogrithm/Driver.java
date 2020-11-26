package project1_Alogrithm;

import javax.swing.JOptionPane;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;


public class Driver extends Application {
	int []leds=null;
	int []powers=null;
	int cost[][]=null;
	int arrows[][]=null;
	int number;
	static String Str="";
	
	public void start(Stage stage) throws Exception {
		
		

		// Create panes and set the properties
		GridPane gPane = new GridPane();
		BorderPane p = new BorderPane();
		gPane.setAlignment(Pos.TOP_CENTER);
		p.setCenter(gPane);
		gPane.setVgap(15);
		gPane.setHgap(15);

		// Create a Title label and set its properties
		Label Title = new Label("Welcome");
		Title.setFont(Font.font("Andalus", FontWeight.BOLD, 40));
		Title.setPadding(new Insets(50, 20, 20, 222));
		p.setTop(Title);

		// Create a label and set its properties
		Label UF = new Label("Numbers of leds");
		UF.setFont(Font.font("Times New Roman", FontWeight.BOLD, 22));
		gPane.add(UF, 0, 3);

		TextField uf = new TextField();// Create a field to write in
		uf.setPromptText("type the number of leds");
		uf.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
		uf.setFocusTraversable(false);
		
		
		gPane.add(uf, 0, 4);

		
		TextField txt = new TextField();// Create a field to write in
		txt.setPromptText("Type the value of leds");
		
		txt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
		txt.setFocusTraversable(false);
		gPane.add(txt, 0, 5);

		
		Button buttonNext = new Button("Next");
		gPane.add(buttonNext, 1, 5);

		// Create panes and set the properties
		GridPane gPane2 = new GridPane();
		
		gPane2.setPadding(new Insets(15, 15, 15, 15));
		
		gPane2.setVgap(15);
		gPane2.setHgap(30);

		// Create a Title label and set its properties
		Label Title2 = new Label("Max Led Lighting");
		
		Title2.setFont(Font.font("Andalus", FontWeight.BOLD, 30));
		
	
		
		
		RadioButton printNumber = new RadioButton("Print number of leds ");
		printNumber.setFont(Font.font("Andalus", FontWeight.BOLD, 16));
		
		RadioButton printLeds = new RadioButton("Print lighting leds ");
		printLeds.setFont(Font.font("Andalus", FontWeight.BOLD,16));
		
		RadioButton printTable = new RadioButton("Print table of leds ");
	    printTable.setFont(Font.font("Andalus", FontWeight.BOLD, 16));
	    
	    RadioButton TableFile = new RadioButton("Print table of leds in File ");
	    TableFile.setFont(Font.font("Andalus", FontWeight.BOLD, 16));
	    
		RadioButton design = new RadioButton("show the desig ");
		design.setFont(Font.font("Andalus", FontWeight.BOLD, 16));
		
		ToggleGroup tg = new ToggleGroup();

		printNumber.setToggleGroup(tg);
		printLeds.setToggleGroup(tg);
		printTable.setToggleGroup(tg);
		TableFile.setToggleGroup(tg);
		design.setToggleGroup(tg);
		

		Button submit = new Button("Submit");
		Button Back = new Button("<-Back");
		
		gPane2.setAlignment(Pos.CENTER);
		gPane2.setHgap(15);
		gPane2.setVgap(15);

		gPane2.add(Title2, 0, 0);
		gPane2.add(printNumber, 0, 1);
		gPane2.add(printLeds, 0, 2);
		
		gPane2.add(printTable, 0, 3);
		gPane2.add(TableFile, 0, 4);
		gPane2.add(design, 0, 5);
	
		gPane2.add(Back, 0, 7);
		gPane2.add(submit, 1, 7);
		submit.setVisible(false);

		Scene scene2 = new Scene(gPane2, 480, 450); // Create a scene
		TextArea TA=new TextArea();
		
		//button click then read the numbers of led
		buttonNext.setOnAction(e -> {
			try {
				if ((uf.getText().isEmpty()) || txt.getText().isEmpty())
					throw new IllegalArgumentException();// To throw Exception if there

				number = Integer.parseInt(uf.getText());

				TextInputDialog textinput = new TextInputDialog();

				textinput.setTitle("Duplicate Number");// show dialog if there duplicate numbers

				String[] arr = (txt.getText()).split(" ");

				//dialog message will show if the user doesn't insert correct number 
				if (arr.length < number || arr.length > number)
					JOptionPane.showMessageDialog(null, "insert the correct number of leds");

				else {

					 leds = new int[number];
					 powers = new int[number];
					 
					 //array of leds
					for (int i = 0; i < number; i++) {
						leds[i] = Integer.parseInt(arr[i]);
					}
					
					//fill powers array and check if there duplicate number
					for (int i = 0; i < number; i++) {
						
						
						if (powers[leds[i] - 1] == 0 && (leds[i] > 0 && leds[i] <= number))
							powers[leds[i] - 1] = leds[i];
						else {
							//while user still insert duplicate number a message still display to him 
							while (powers[leds[i] - 1] != 0 || (leds[i] == 0 || leds[i] > number)) {
								textinput.getDialogPane().setContentText("insert number insted number " + leds[i]);
								Optional<String> res = textinput.showAndWait();
								TextField input = textinput.getEditor();
								int var = Integer.parseInt(input.getText());

								leds[i] = var;//new value
							}
							powers[leds[i] - 1] = leds[i];
						}

						

					}

					/*for (int i = 0; i < number; i++) {
						if (powers[i] == 0)
							JOptionPane.showMessageDialog(null, "there is missed number will fill automatically");
						powers[i] = i + 1;
					}*/

					 cost = new int[number + 1][number + 1];
					 arrows = new int[number + 1][number + 1];

					stage.setScene(scene2);
					TA.setText("");
				}
				printNumber.setSelected(false);
				printLeds.setSelected(false);
				printTable.setSelected(false);
				TableFile.setSelected(false);
				design.setSelected(false);
				

				
			} catch (Exception E) {
				JOptionPane.showMessageDialog(null, E);// Handling with Exception via show special message
			}
		});

		// Create a leds File label and set its properties
		Label LF = new Label("File of leds");
		LF.setFont(Font.font("Times New Roman", FontWeight.BOLD, 22));
		gPane.add(LF, 0, 8);

		TextField lf = new TextField();// Create a field to write in
		lf.setPromptText("Choose the file from browser");
		lf.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
		lf.setFocusTraversable(false);
		gPane.add(lf, 0, 9);

		// create a File chooser
		FileChooser fileCourse = new FileChooser();

		// create a Browser button
		Button buttonled = new Button("Browser");
		gPane.add(buttonled, 1, 9);

		// buttonled clicked do the instruction
		buttonled.setOnAction(e -> {

			// Create a File that selected by user
			File selectedFile = fileCourse.showOpenDialog(stage);
			if (selectedFile != null) {
				lf.setText(selectedFile.getAbsolutePath());
			}

		});

		// filtering the types of files to make them selectable in the dialog to select
		// files
		fileCourse.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.txt")

		);

		// create a button
		Button next = new Button("Next");
		gPane.add(next, 1, 10);// Place nodes in the pane

		Scene scene = new Scene(p, 600, 500); // Create a scene

		// Finish clicked do the instruction
		next.setOnAction(e -> {
			try {
				if (lf.getText().isEmpty())
					throw new IllegalArgumentException();// To throw Exception if there
				
				try {
					File led = new File(lf.getText());
					Scanner Read = new Scanner(led);

					number = Integer.parseInt(Read.nextLine().trim());

					 leds = new int[number];

					String[] arr = (Read.nextLine()).split(" ");
					for (int i = 0; i < number; i++)
						leds[i] = Integer.parseInt(arr[i]);
					Read.close();

					 powers = new int[number];

					for (int i = 0; i < number; i++) {
						powers[i] = i + 1;

					}
				    cost = new int[number + 1][number + 1];
					 arrows = new int[number + 1][number + 1];

					Read.close();
					TA.setText("");
					
				} catch (FileNotFoundException e1) {
					System.out.println("An error occurred.");
					e1.printStackTrace();

				}
				stage.setScene(scene2);
				printNumber.setSelected(false);
				printLeds.setSelected(false);
				printTable.setSelected(false);
				TableFile.setSelected(false);
				design.setSelected(false);
				
			} catch (Exception E) {
				JOptionPane.showMessageDialog(null, E);// Handling with Exception via show special message
			}

		});
		
		
	
		
		
		
		
		
		BorderPane p2 = new BorderPane();
		
		
		
		p2.setCenter(TA);
		TA.setPadding(new Insets(8, 8, 8, 8));
		
		Stage newStage=new Stage();
		newStage.setTitle("Print Selected");
		
		Scene scene3=new Scene(p2,550,480);
		newStage.setScene(scene3);
		
		//print number of ligthing leds
		printNumber.setOnAction(e -> {
			create_table(arrows, cost, powers, leds, number);
		    TA.appendText("\n\nThe Number of Maximum lighting leds: "+ cost[number][number]
		    		+"\n_____________________________________________________________________________\n\n");
			submit.setVisible(true);
			
		});
		
		
		//print lighting leds
		printLeds.setOnAction(e -> {
			Str="";
			 create_table(arrows, cost, powers, leds, number);
			 TA.appendText("\n\nThe Lighting Leds: "+ print_pairs(arrows, leds, number, number)
			    		+"\n_____________________________________________________________________________\n\n");
			 
			submit.setVisible(true);
			
		});
		
		//print table
		printTable.setOnAction(e -> {
			create_table(arrows, cost, powers, leds, number);
			Print_table(arrows,cost, TA, number, leds, powers);
			TA.appendText("\n\n_____________________________________________________________________________________\n\n");
			submit.setVisible(true);
			
		});
		
		//print table in file
		TableFile.setOnAction(e -> {
			create_table(arrows, cost, powers, leds, number);
			try {
				printInFile(cost, number, leds, powers);
				JOptionPane.showMessageDialog(null, "The File Is Ready",null, JOptionPane.PLAIN_MESSAGE);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			submit.setVisible(true);
			
		});

		design.setOnAction(e -> {

			submit.setVisible(true);
			
		});
		
		
		design.setOnAction(e->{
			Str="";
			create_table(arrows, cost, powers, leds, number);
			print_pairs(arrows, leds, number, number);
			BorderPane BP=new BorderPane();
			GridPane GP=new GridPane();
			ScrollPane SP=new ScrollPane();
			
			GP.setAlignment(Pos.TOP_CENTER);
			BP.setRight(GP);
			GP.setVgap(15);
			GP.setHgap(15);
			SP.setContent(BP);
			SP.setPadding(new Insets(50, 50, 50, 140));
			

			Label Title3 = new Label("Circuit");
			Title3.setFont(Font.font("Andalus", FontWeight.BOLD, 40));
			Title3.setPadding(new Insets(50, 20, 20, 50));
			BP.setTop(Title3);
			
			Label LabLed=new Label("The leds");
			LabLed.setFont(Font.font("Andalus", FontWeight.BOLD, 16));
			GP.add(LabLed, 0, 0);
			Label LabPower=new Label("Source of power");
			LabPower.setFont(Font.font("Andalus", FontWeight.BOLD, 16));
			GP.add(LabPower, 5, 0);
			
      		Circle [] circle1=new Circle[leds.length];
		
			Label []labels=new Label[leds.length];
			RadioButton []RB=new RadioButton[powers.length];
			
			for(int i=0;i<circle1.length;i++) {
				circle1[leds[i]-1]=new Circle(i*20,i*20,7);
				circle1[leds[i]-1].setFill(Color.WHITE);
				GP.add(circle1[leds[i]-1], 1, 2+i);
				
				labels[i]=new Label("led "+leds[i]+" ");
				labels[i].setFont(Font.font("Andalus", FontWeight.BOLD, 14));
				GP.add(labels[i], 0, 2+i);
				RB[i]=new RadioButton("source "+powers[i]+" ");	
				GP.add(RB[i], 5, 2+i);
			}
			
			String var="";
			for(int i=0;i<Str.length();i++) {
				if(Character.isDigit(Str.charAt(i)))
					var+=Str.charAt(i);
			}
				
			
			int []nums=new int[var.length()];
			
         for(int i=0;i<var.length();i++) {
        	 
				nums[i]=(Integer.parseInt(String.valueOf(var.charAt(i))))-1;
				
			}
			
			
			for(int i=0;i<nums.length;i++) {
				
				circle1[nums[i]].setFill(Color.ORANGE);
				RB[nums[i]].setSelected(true);
			}
			
			
			Scene newScene=new Scene(SP, 600, 600);
			Stage newStage1=new Stage();
			newStage1.setScene(newScene);
			newStage1.show();
			
			
		});
		
		
		
		submit.setOnAction(e->{
			newStage.show();
		});
		
		
		Back.setOnAction(e->{
			stage.setScene(scene);
		});
		
		
		

		stage.setTitle("Max LED Lighting"); // Set the stage title
		stage.setScene(scene); // Place the scene in the stage
		stage.show(); // Display the stage

	}

	public static void main(String[] args) throws FileNotFoundException {
		launch(args);

		// print_pairs(arrows, leds, n, n);

	}

	public static void create_table(int[][] arrows, int[][] cost, int[] powers, int[] leds, int n) {
       
		
		// 1=diagonal 2=left 3=up
		// code of schedule
		for (int i = 0; i <= n; i++)
			cost[i][0] = 0;
		for (int j = 0; j <= n; j++)
			cost[0][j] = 0;

		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++) {

				//if the led equal power -diagonal value +1- 
				if (leds[i - 1] == powers[j - 1]) {

					cost[i][j] = cost[i - 1][j - 1] + 1;
					arrows[i][j] = 1;

				}

				else
 
					//else if the the previous horizontal value greater than the previous vertical value  -take left value-
				if (cost[i][j - 1] > cost[i - 1][j]) {

					cost[i][j] = cost[i][j - 1];
					arrows[i][j] = 2;

				} else {
					//else if the the previous horizontal value less than the previous vertical value or equal it  -take previous vertical value value-
					cost[i][j] = cost[i - 1][j];
					arrows[i][j] = 3;

				}

			}
		
		
	}
	
	public static void Print_table(int [][] arrows,int [][]cost,TextArea TA,int number,int[]leds,int[]powers) {
		 TA.appendText("\n The Table:\n\n\t\t\t0\t\t\t");
		for(int i=0;i<number;i++) 		
			  TA.appendText(powers[i]+"\t\t\t");
		//TA.appendText("\n__________________________________________________________________________________________");
		for(int i=0;i<=number;i++) {
			
		  TA.appendText("\n\n\n");
		  if(i>0)
			  TA.appendText(leds[i-1]+"\t\t\t");
		  else 
			  TA.appendText("0\t\t\t");
		for(int j=0;j<=number;j++) {
			
			   if(arrows[i][j]==3)
				 TA.appendText(cost[i][j]+"↑\t\t\t");
				else if(arrows[i][j]==2)
					 TA.appendText(cost[i][j]+"←\t\t\t");
				else if(arrows[i][j]==1)
					TA.appendText(cost[i][j]+"↖\t\t\t");
				else
					TA.appendText(cost[i][j]+"\t\t\t");
		}}
		
	}

	//find the lighting leds
	public static String print_pairs(int[][] arrows, int[] leds, int i, int j) {
		
		
		if (i == 0 || j == 0) {

			return Str ;
		}

		//diagonal
		else if (arrows[i][j] == 1) {
			
			print_pairs(arrows, leds, i - 1, j - 1);
			Str+=(leds[i - 1] + " ");
			

			
         //up
		} else if (arrows[i][j] == 3) {
			print_pairs(arrows, leds, i - 1, j);
          
		//left
		} else {
			print_pairs(arrows, leds, i, j - 1);
		}
		
		
		return Str;

	}
	
	public static void printInFile(int [][]cost,int number,int[]leds,int[]powers) throws FileNotFoundException {
	File file2 = new File("TableFile.txt");

	PrintWriter table1 = new PrintWriter(file2);

	table1.print("\nThe Table: \n\n\n\t\t\t\t0 \t\t\t");
	
	for(int i=0;i<number;i++) 		
	table1.print(powers[i]+" \t\t\t");
	
	//table1.print("\n\n__________________________________________________________________________________________");
	
	for(int i=0;i<=number;i++) {

		table1.print("\n\n\n");
	   if(i>0)
		  table1.print(leds[i-1]+"\t\t\t\t");
	  else 
		  table1.print("0 \t\t\t\t");
	for(int j=0;j<=number;j++) {
		
		table1.print(cost[i][j]+" \t\t\t");
	}}

	table1.close();

	}



}