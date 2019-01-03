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

public class ModifyProductController  implements Initializable {

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
    private Label ModifyProductLabel;

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
    Product product;
    Inventory list = new Inventory();
    
    ObservableList <Part> partList = FXCollections.observableArrayList();
    ObservableList <Part> associatedPart ;
    ObservableList <Part> partSelected = FXCollections.observableArrayList();
    


    //Updates Product being modified
    @FXML
    void SaveButtonAction (ActionEvent event) throws IOException{
  
        product.setName(NameTxt.getText());
        product.setInStock(Integer.parseInt(InvTxt.getText()));
        product.setPrice(Double.parseDouble(PriceTxt.getText()));
        product.setMax(Integer.parseInt(MaxTxt.getText()));
        product.setMin(Integer.parseInt(MinTxt.getText()));
        
        Parent mainPage = FXMLLoader.load(getClass().getResource("/View_Controller/Main.fxml"));
        Scene mainScene = new Scene(mainPage);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        app_stage.setScene(mainScene);
        app_stage.show();
    }
    
    
    //Cancel current scene and switches to Main.fxml
    @FXML
    void CancelButtonAction(ActionEvent event) throws IOException{
        
        Parent mainPage = FXMLLoader.load(getClass().getResource("/View_Controller/Main.fxml"));
        Scene mainScene = new Scene(mainPage);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        app_stage.setScene(mainScene);
        app_stage.show();
        
    }
    
    //Removes Part from table and Associated Part List
    @FXML void DeleteButtonAction (ActionEvent event){
        int ID;
        ObservableList<Part> partsSelected;
        ObservableList<Part> allParts;
        allParts = ProductTable.getItems();
        partsSelected = ProductTable.getSelectionModel().getSelectedItems();
        ID = ProductTable.getSelectionModel().getSelectedIndex();
        partsSelected.forEach(allParts::remove);
        product.removeAssociatedPart(ID);
        
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
    
     //Add Button, add part to associated part list and product's table
    @FXML void AddButtonAction(ActionEvent event){
      
        part = AllParts.getSelectionModel().getSelectedItem();
        partSelected.add(part);
        ProductTable.getColumns().setAll(IdColumn,NameColumn,InvColumn,PriceColumn);
                
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        ProductTable.getItems().add(part);
        product.addAssociatedPart(part);
         
    }
    
    
    //Passed Object to controller.
    void setProduct(Product product){
        this.product = product;
        IdTxt.setText(Integer.toString(product.getProductID()));
        NameTxt.setText(product.getName());
        InvTxt.setText(Integer.toString(product.getInStock()));
        PriceTxt.setText(Double.toString(product.getPrice()));
        MaxTxt.setText(Integer.toString(product.getMax()));
        MinTxt.setText(Integer.toString(product.getMin()));
        associatedPart = FXCollections.observableArrayList(product.getPartList());
        
        for (int i = 0; i < associatedPart.size(); i++){
                ProductTable.getColumns().setAll(IdColumn,NameColumn,InvColumn,PriceColumn);
                
                IdColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
                NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                InvColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
                PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                ProductTable.getItems().add(associatedPart.get(i));}
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        
        
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
