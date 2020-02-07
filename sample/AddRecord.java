package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class AddRecord {

    TextArea field;
    int counter = 0;

    ArrayList<String> datastore = new ArrayList<>();
    HashMap<String, String> map = new HashMap<>();

 // input pane

    public   VBox input() {

        // creat folder to input insert names
        createfolder();

        VBox vpane = new VBox();
        vpane.setAlignment(Pos.CENTER);
        vpane.setId("centernode");
        vpane.setMaxWidth(1200);
        vpane.setMaxHeight(700);
        vpane.setSpacing(20);
        vpane.setPadding(new Insets(20, 20, 20, 20));


        TextField filename = new TextField();
        filename.setPromptText("Enter FileName ");
        filename.setPrefWidth(450);
        filename.setPrefHeight(30);
        filename.setId("name");

        TextField name = new TextField();
        name.setPromptText("Full Name");
        name.setPrefWidth(450);
        name.setPrefHeight(30);
        name.setId("name");

        TextField department = new TextField();
        department.setPromptText("department");
        department.setPrefWidth(250);
        department.setPrefHeight(30);
        department.setId("matric");

        Label error = new Label("");

        Button add_record = new Button("ADD RECORD");
        add_record.setPrefSize(150, 15);
        add_record.setId("proceed");
        add_record.setAlignment(Pos.CENTER);
        add_record.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

               // check if field values is not null
                if (isEmpty(filename) || isEmpty(name) || isEmpty(department)) {
                    error.setText("Fill the Box");
                } else {

                   if (counter ==1){

                       counter= 0;

                       field.clear();
                       String name_text = totext(name); // return field value in string
                       String department_text = totext(department);

                       String input =  name_text + "\t\t\t\t" + department_text + " \n";
                       field.appendText(input);

                       datastore.add(name_text);
                       map.put(name_text, department_text);

                       name.clear();
                       department.clear();

                   }else{
                       String name_text = totext(name);
                       String department_text = totext(department);

                       String input =  name_text + "\t\t\t\t" + department_text + " \n";
                       field.appendText(input);

                       datastore.add(name_text.toUpperCase());
                       map.put(name_text.toUpperCase(), department_text);

                       name.clear();
                       department.clear();
                   }

                }
            }
        });

        Button clear_record = new Button("CLEAR RECORD");
        clear_record.setPrefSize(150, 15);
        clear_record.setId("proceed");
        clear_record.setAlignment(Pos.CENTER);
        clear_record.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                counter = 0;
                field.clear();

                datastore.clear();
                map.clear();
            }
        });


        Button sort_record = new Button("SORT RECORD");
        sort_record.setPrefSize(150, 15);
        sort_record.setId("proceed");
        sort_record.setAlignment(Pos.CENTER);
        sort_record.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                counter =1;
               // System.out.println(field.getText().toString());

                try {

                    createFileoftheproject(filename.getText().toString()+"unsort",field);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                Algo();


                field.clear();

                field.appendText("\n\n================  Sorted List ============== \n");

                for (int i = 0; i < datastore.size(); i++) {

                    String input = datastore.get(i) + "\t\t\t\t" + map.get(datastore.get(i)) + " \n";
                    field.appendText(input);
                }
                try {
                    createFileoftheproject(filename.getText().toString()+"sort",field);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                datastore.clear();
                map.clear();
            }
        });


        HBox hBox = new HBox(add_record, clear_record, sort_record);
        hBox.setSpacing(3);

        vpane.getChildren().addAll(image(), filename, name, department, error, hBox);

        return vpane;
    }

    //

    private  ImageView image(){

        String imagepath = this.getClass().getResource("/sample/css/sort.png").toExternalForm();

        Image image = new Image(imagepath);
        ImageView imageView = new ImageView(image);
        imageView.setId("imageview");
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        return imageView;
    }


    public TextArea field() {

        field = new TextArea("\n");
        field.setEditable(true);
        field.setWrapText(true);
        field.setScrollLeft(300);
        field.setScrollTop(300);

        field.setId("field");

        return field;
    }


    // algorithm function
   private void Algo() {


        int i = 0;

        while (i < (datastore.size() - 1)) {

            String main = datastore.get(i).toUpperCase();
            String next = datastore.get(i + 1).toUpperCase();

         System.out.println("comaparing "+ main +" and "+next +" at loop "+ i);

            for (int j = 0; j < next.length(); j++) {

                if (main.charAt(j) > next.charAt(j) ) {
                    System.out.println(main.charAt(j) + " > " + next.charAt(j));

                    datastore.set(i, next);
                    datastore.set((i + 1), main);

                    System.out.println(datastore.get((i+1)) + " > " +datastore.get(i));
                    System.out.println("reversed back to "+datastore.get(i) + " > " +datastore.get((i+1)));

                    i = 0;
                    break;

                } else if (main.charAt(j) == next.charAt(j)) {
                    continue;
                } else {
                    i++;
                    break;
                }
            }
        }

    }

    // create folder for this project on user System  if not exist
   private void createfolder(){

        String home = System.getProperty("user.home");
        System.out.println(home+"/Documents");

        File newfolder = new File(home+"/Documents/sorting");

        if (newfolder.mkdir()){

            System.out.println("success");


        }else{
            System.out.println("already created");

        }

    }

    // create file
   private void createFileoftheproject(String filename,TextArea field) throws FileNotFoundException, UnsupportedEncodingException {

        String home = System.getProperty("user.home");
        PrintWriter writer = new PrintWriter(home+"/Documents/project405/"+filename+".txt", "UTF-8");
            writer.println(field.getText().toString());
            writer.close();

    }

   //convert textinputcontrol value to string and trim it
    private String totext(TextField field) {
        return field.getText().trim();
    }

    private Boolean isEmpty(TextField data) {
        return data.getText().trim().contentEquals("") || data.getText().isEmpty();
    }
}
