package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddProductController implements Initializable {

    @FXML
    private Button SearchBtn;

    @FXML
    private Button AddBtn;

    @FXML
    private Button DeleteBtn;

    @FXML
    private Button CancelBtn;

    @FXML
    private Button SaveBtn;

    @FXML
    private Label AddProductLabel;

    @FXML
    private Label IdLabel;

    @FXML
    private Label NameLabel;

    @FXML
    private Label InvLabel;

    @FXML
    private Label PriceLabel;

    @FXML
    private Label MaxLabel;

    @FXML
    private Label MinLabel;

    @FXML
    private TextField IdTxt;

    @FXML
    private TextField NameTxt;

    @FXML
    private TextField InvTxt;

    @FXML
    private TextField PriceTxt;

    @FXML
    private TextField MaxTxt;

    @FXML
    private TextField MinTxt;

    @FXML
    private TextField SearchTxt;

    @FXML
    private TableView<Part> AllParts;

    @FXML
    private TableColumn<Part, Integer> SearchIDColumn;

    @FXML
    private TableColumn<Part, String> SearchNameColumn;

    @FXML
    private TableColumn<Part, Integer> SearchInvColumn;

    @FXML
    private TableColumn<Part, Double> SearchPriceColumn;

    @FXML
    private TableView<Part> ProductTable;

    @FXML
    private TableColumn<Part, Integer> IdColumn;

    @FXML
    private TableColumn<Part, String> NameColumn;

    @FXML
    private TableColumn<Part, Integer> InvColumn;

    @FXML
    private TableColumn<Part, Double> PriceColumn;
    
    Part part;
    Inventory list = new Inventory();
    
    ObservableList <Part> partList = FXCollections.observableArrayList();
    ObservableList <Part> partSelected = FXCollections.observableArrayList();
    

    //Cancel current scene and switches to Main.fxml
    @FXML
    void CancelButtonAction(ActionEvent event) throws IOException{
        
        Parent mainPage = FXMLLoader.load(getClass().getResource("/View_Controller/Main.fxml"));
        Scene mainScene = new Scene(mainPage);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        app_stage.setScene(mainScene);
        app_stage.show();
    }
    
    //Search Button
    @FXML void SearchButtonAction(ActionEvent event) {
        
        int id = Integer.parseInt(SearchTxt.getText());
        partList = FXCollections.observableArrayList(list.getPartList());
        AllParts.getItems().clear();
        AllParts.getColumns().setAll(SearchIDColumn, SearchNameColumn, SearchInvColumn, SearchPriceColumn); 
           
        SearchIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        SearchNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        SearchInvColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        SearchPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        AllParts.getItems().add(partList.get(id-1));
   
    
    }
    
    //Add Button
    @FXML void AddButtonAction(ActionEvent event){
      
        part = AllParts.getSelectionModel().getSelectedItem();
        partSelected.add(part);
        ProductTable.getColumns().setAll(IdColumn,NameColumn,InvColumn,PriceColumn);
                
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        ProductTable.getItems().add(part);
         
    }
    
    //Save Button
    @FXML
    void SaveButtonAction(ActionEvent event) throws IOException{
        
        Product configID;
        configID = new Product();
        
        Product product;
        if (partSelected.isEmpty()){
            
        }else{
        product = new Product(
                configID.configID(), Integer.parseInt(InvTxt.getText()), Integer.parseInt(MinTxt.getText()),
                 Integer.parseInt(MaxTxt.getText()), Double.parseDouble(PriceTxt.getText()), NameTxt.getText(), partSelected);
        
        
        Inventory productList;
        
        productList = new Inventory();
        productList.addProduct(product);
      
        
        Parent mainPage = FXMLLoader.load(getClass().getResource("/View_Controller/Main.fxml"));
        Scene mainScene = new Scene(mainPage);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        app_stage.setScene(mainScene);
        app_stage.show();
        }
    }
    
    //Delete Buttons
    @FXML void DeleteButtonAction (ActionEvent event){
        int ID;
        ObservableList<Part> partsSelected;
        ObservableList<Part> allParts;
        allParts = ProductTable.getItems();
        partsSelected = ProductTable.getSelectionModel().getSelectedItems();
        ID = ProductTable.getSelectionModel().getSelectedIndex();
        partsSelected.forEach(allParts::remove);
        partSelected.remove(ID);
    }
  

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partList = FXCollections.observableArrayList(list.getPartList());
        
        
        
        if (partList.isEmpty()){
            
        }
        
        else{
            //Table View
            
            for (int i = 0; i < partList.size(); i++){
            AllParts.getColumns().setAll(SearchIDColumn, SearchNameColumn, SearchInvColumn, SearchPriceColumn); 
           
            SearchIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
            SearchNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            SearchInvColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
            SearchPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            AllParts.getItems().add(partList.get(i));
            }
        }
    }
        
    }

