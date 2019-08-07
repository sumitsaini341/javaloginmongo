package controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.bson.Document;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.mongodb.client.model.Filters.eq;


public class collection implements Initializable{

    private final static String HOST = "localhost";
    private final static int PORT = 27017;

    private String name;
    private String fname;
    private String mname;
    private String email;
    private String cdob;
    private String gender;
    private String address;
    private String city;
    private String czipcode;
    private int pos;

    @FXML
    private TableView<dbdata> table;
    @FXML
    private TableColumn<dbdata, String> cname;
    @FXML
    private TableColumn<dbdata, String> cfname,cmname;
    @FXML
    private TableColumn<dbdata, String> cemail;
    @FXML
    private Label status;
    @FXML
    private TableColumn<dbdata, String> dob;
    @FXML
    public TableColumn<dbdata, String> cgender;
    @FXML
    private TableColumn<dbdata, String> caddress;
    @FXML
    private TableColumn<dbdata, String> ccity;
    @FXML
    private TableColumn<dbdata, String> zipcode;

    entryform abs1 = new entryform();
    JFXTextField xyz = abs1.getAgentcode();

    Stage primaryStage = new Stage();
    public ObservableList<dbdata> list;
    public List entries = new ArrayList();

    MongoClient mongoClient = new MongoClient(HOST, PORT);
    MongoDatabase mongoDatabase = mongoClient.getDatabase("Confab");
    MongoCollection coll = mongoDatabase.getCollection(String.valueOf(xyz));
    MongoCursor<Document> cursor = coll.find().iterator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setEditable(true);

        try{
            for(int i = 0; i < coll.count(); i++){
                pos = i +1;

                Document doc = cursor.next();
                name = doc.getString("cname");
                fname = doc.getString("cfname");
                email = doc.getString("cemail");
                gender = doc.getString("cgender");


                //attend.add(new Attendees(pos, fname, lname, _email, _gender, ));
            }
           // list = FXCollections.observableArrayList(attend);


        }
        finally {
//          close the connection
            cursor.close();
        }

//      call the setTable method
        //setTable();
    }


}
