package controllers;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import org.bson.Document;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;




public class entryform implements Initializable{

    private final static String HOST = "localhost";
    private final static int PORT = 27017;
    @FXML
    private JFXTextField cname;
    @FXML
    private JFXTextField cfname,cmname;
    @FXML
    private JFXTextField cemail;
    @FXML
    private Label status;
    @FXML
    private JFXDatePicker dob;
    @FXML
    public ChoiceBox<String> cgender;
    @FXML
    private JFXTextField caddress;
    @FXML
    private JFXTextField ccity;
    @FXML
    private JFXTextField zipcode;
    @FXML
    private JFXTextField agentcode;
    @FXML
    private Button b1,b2,b3,b4,b5,b6;
    @FXML
    private JFXTextField Remarks;


    public JFXTextField getAgentcode() {
        return agentcode;
    }


    ObservableList<String> list  = FXCollections.observableArrayList("Male", "Female");
    Stage primaryStage = new Stage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cgender.setItems(list);
    }

    public void getFieldValues(ActionEvent event){
        try{
//          create a connection to mongodb server

            MongoClient mongoClient = new MongoClient(HOST, PORT);

//          create a database name
            MongoDatabase mongoDatabase = mongoClient.getDatabase("agent collection");
            MongoCollection coll = mongoDatabase.getCollection(String.valueOf(agentcode));
            Document doc = new Document("cname", cname.getText())
                    .append("cfname", cfname.getText())
                    .append("cmname", cmname.getText())
                    .append("cemail", cemail.getText())
                    .append("cgender", cgender.getValue())
                    .append("caddress", caddress.getText())
                    .append("zipcode", zipcode.getText())
                    .append("ccity", ccity.getText())
                    .append("dob", dob.getValue());

            coll.insertOne(doc);
            status.setText("Saved Successfully!!!");

            cname.setText("");
            cmname.setText("");
            cfname.setText(" ");
            cemail.setText("");
            cgender.setValue(null);
            zipcode.setText("");
            ccity.setText("");
            dob.setValue(null);
            caddress.setText("");


        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            status.setText("Failed to save");
        }
    }
    public void getData() throws Exception {
        Stage stage = (Stage)b3.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("collection.fxml"));
        primaryStage.setTitle("Collection List");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();

    }
}
