package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {

    BorderPane mainboard ;

    AddRecord addRecord = new AddRecord();

    //first page to show when the app is launched

    @Override
    public void start(Stage primaryStage) throws Exception{


        mainboard= new BorderPane();
        mainboard .setId("mainpanel");
        mainboard.setCenter(splashPanel());


        Scene  scene = new Scene(mainboard,1200,700); // scene size
        String css = this.getClass().getResource("/sample/css/style.css").toExternalForm(); //adding external css to the Node
        scene.setUserAgentStylesheet(css);

         primaryStage.setScene(scene);
        primaryStage.setTitle("SORTING");
        primaryStage.setResizable(false);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

 //root pane of the stage

    VBox  splashPanel(){
        VBox vBox = new VBox(sortImage(),title(),proceedBtn());
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

   // project  title

    Label title() {

        Label appname = new Label("PROJECT TOPIC: CONVERION OF A SORT ALGORITHM WRITTEN IN BASIC LANGUAGE INTO \n " +
                "JAVA PROGRAMMING LANGUAGE AND OUTPUTING IT INTO A SORTED AND UNSORTED TEXT FILE.  ");
        appname.setFont(Font.font("Arial", 22));
        appname.setAlignment(Pos.CENTER_LEFT);
        appname.setId("title");
        appname.setPadding(new Insets(60, 10, 12, 21));


        return appname;
    }

    //image
    ImageView sortImage(){

        String imagepath = this.getClass().getResource("/sample/css/sort.png").toExternalForm();

        Image image = new Image(imagepath);
        ImageView imageView = new ImageView(image);
        imageView.setId("imageview");
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        return imageView;
    }

    //proceed Btn ,onClicked it will set new node to the Scene

    Button proceedBtn (){

        Button proceed = new Button("PROCEED");
        proceed.setPrefSize(200, 35);
        proceed.setId("proceed");
        proceed.setAlignment(Pos.CENTER);
        proceed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

               mainboard.setLeft(addRecord.input());
               mainboard.setCenter(addRecord.field());
                mainboard.setBottom(devName());

            }
        });

return proceed;
    }


// developer name
    public Label devName() {

        Label devName= new Label("Akintunde Israel Segun ");

        devName.setId("proceed");
        devName.setAlignment(Pos.BASELINE_LEFT);

        return devName;
    }






}
