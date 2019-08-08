package controllers;

import base1.app;

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
    private TableColumn<dbdata, Integer> id;
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
    @FXML
    private Button update;

    entryform abs1 = new entryform();
    JFXTextField xyz = abs1.getAgentcode();

    Stage primaryStage = new Stage();
    public ObservableList<dbdata> list;
    public List<dbdata> entries = new ArrayList<dbdata>();

    MongoClient mongoClient = new MongoClient(HOST, PORT);
    MongoDatabase mongoDatabase = mongoClient.getDatabase("Confab");
    MongoCollection<Document> coll = mongoDatabase.getCollection(String.valueOf(xyz));
    MongoCursor<Document> cursor = coll.find().iterator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setEditable(true);

        commit1(cursor);

//      call the setTable method
        setTable();
        status.setText("");
    }
    public void addentry() throws Exception{
//      get the current window
        Stage stage = (Stage)update.getScene().getWindow();

//      close the current window
        stage.close();

        /* load the main class window */
        app mainClass = new app();

        mainClass.start(primaryStage);
    }

    public void editentry() {
        dbdata selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem == null){
//          display an error message if no row was selected
            status.setText("Please select a row and perform this action again");
        }
        else{
            name = selectedItem.getCname();
            fname = selectedItem.getCfname();
            mname = selectedItem.getCmname();
            email = selectedItem.getCemail();
            gender = selectedItem.getCgender();
            cdob = selectedItem.getDob();
            address = selectedItem.getCaddress();
            city = selectedItem.getCcity();
            czipcode = selectedItem.getZipcide();

//          here i am using the email as my primary key to find each document to update it in the database
            coll.updateOne(eq("id", pos), new Document("cname", cname.getText())
                    .append("cfname", cfname.getText())
                    .append("cmname", cmname.getText())
                    .append("cemail", cemail.getText())
                    .append("cgender", cgender.getText())
                    .append("caddress", caddress.getText())
                    .append("zipcode", zipcode.getText())
                    .append("ccity", ccity.getText())
                    .append("dob", dob.getText()));

//          call the rePopulateTable method
            rePopulateTable();

//          call the setTable method
            setTable();

//          hide the error message
            status.setText("");
        }

    }


    public void deleteentry(){
//      get the selected row
        dbdata selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem == null){
//          display an error message
            status.setText("Please select a row and perform this action again");
        }
        else{
//          get the value of the selected email column
            int pos =  selectedItem.getId();

//          here i am using the email as my primary key to find each document to delete from the database
            coll.deleteOne(eq("id", pos));

//          call the rePopulateTable method
            rePopulateTable();

//          call the setTable method
            setTable();

//          hide the error message
            status.setText("");
        }
    }
    private void setTable() {
        table.setEditable(true);

        //first column as name of the evaluee
        cname.setCellFactory(TextFieldTableCell.forTableColumn());
        //get the new value and call the method
        cname.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<dbdata, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<dbdata, String> event) {
                ((dbdata)event.getTableView() .getItems() .get(event.getTablePosition().getRow()))
                        .setCname(event.getNewValue());
            }
        });

        cfname.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<dbdata, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<dbdata, String> event) {
                ((dbdata)event.getTableView() .getItems() .get(event.getTablePosition().getRow()))
                        .setCfname(event.getNewValue());
            }
        });

        cmname.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<dbdata, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<dbdata, String> event) {
                ((dbdata)event.getTableView() .getItems() .get(event.getTablePosition().getRow()))
                        .setCmname(event.getNewValue());
            }
        });

        cemail.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<dbdata, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<dbdata, String> event) {
                ((dbdata)event.getTableView() .getItems() .get(event.getTablePosition().getRow()))
                        .setCemail(event.getNewValue());
            }
        });

        dob.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<dbdata, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<dbdata, String> event) {
                ((dbdata)event.getTableView() .getItems() .get(event.getTablePosition().getRow()))
                        .setDob(event.getNewValue());
            }
        });

        cgender.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<dbdata, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<dbdata, String> event) {
                ((dbdata)event.getTableView() .getItems() .get(event.getTablePosition().getRow()))
                        .setCgender(event.getNewValue());
            }
        });

        caddress.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<dbdata, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<dbdata, String> event) {
                ((dbdata)event.getTableView() .getItems() .get(event.getTablePosition().getRow()))
                        .setCaddress(event.getNewValue());
            }
        });

        ccity.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<dbdata, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<dbdata, String> event) {
                ((dbdata)event.getTableView() .getItems() .get(event.getTablePosition().getRow()))
                        .setCcity(event.getNewValue());
            }
        });

        zipcode.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<dbdata, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<dbdata, String> event) {
                ((dbdata)event.getTableView() .getItems() .get(event.getTablePosition().getRow()))
                        .setZipcode(event.getNewValue());
            }
        });
        id.setCellValueFactory(new PropertyValueFactory<dbdata, Integer>("id"));
        cname.setCellValueFactory(new PropertyValueFactory<dbdata, String>("cname"));
        cfname.setCellValueFactory(new PropertyValueFactory<dbdata, String>("cfname"));
        cmname.setCellValueFactory(new PropertyValueFactory<dbdata, String>("cmname"));
        cemail.setCellValueFactory(new PropertyValueFactory<dbdata, String>("cemail"));
        dob.setCellValueFactory(new PropertyValueFactory<dbdata, String>("dob"));
        cgender.setCellValueFactory(new PropertyValueFactory<dbdata, String>("cgender"));
        caddress.setCellValueFactory(new PropertyValueFactory<dbdata, String>("caddress"));
        ccity.setCellValueFactory(new PropertyValueFactory<dbdata,String>("ccity"));
        zipcode.setCellValueFactory(new PropertyValueFactory<dbdata,String>("zipcode"));
    }

    private void rePopulateTable() {
        MongoCursor<Document> cursor = coll.find().iterator();
        entries.clear();
        commit1(cursor);


    }

    private void commit1(MongoCursor<Document> cursor) {
        try{
            for(int i = 0; i < coll.count(); i++){
                pos = i +1;

                Document doc = cursor.next();
                name = doc.getString("cname");
                fname = doc.getString("cfname");
                email = doc.getString("cemail");
                gender = doc.getString("cgender");
                mname = doc.getString("cmname");
                cdob = doc.getString("dob");
                address = doc.getString("caddress");
                city = doc.getString("ccity");
                czipcode = doc.getString("zipcode");

                entries.add(new dbdata(pos,name,fname,mname,email,cdob,gender,address,city,czipcode));
            }
            list = FXCollections.observableArrayList(entries);

        }
        finally {
//          close the connection
            cursor.close();
        }
    }


}
