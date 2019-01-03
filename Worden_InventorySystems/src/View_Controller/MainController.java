package View_Controller;

import Model.Inhouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dustin
 */


public class MainController {
    
    @FXML
    private Pane PartsPane;

    @FXML
    private Button MainAddPartBtn;

    @FXML
    private Button MainDeletePartBtn;

    @FXML
    private Button MainModifyPartBtn;

    @FXML
    private Button MainSearchPartsBtn;

    @FXML
    private TextField PartSearchTxt;

    @FXML
    private Label PartsLabel;

    @FXML
    private TableView<Part> PartsTable;

    @FXML
    private TableColumn<Part, Integer> PartIdColumn;

    @FXML
    private TableColumn<Part, String> PartNameColumn;

    @FXML
    private TableColumn<Part, Integer> PartInvColumn;

    @FXML
    private TableColumn<Part, Double> PartPriceColumn;
    
    @FXML
    private Pane ProductPane;

    @FXML
    private Button MainAddProductBtn;

    @FXML
    private Button MainDeleteProductBtn;

    @FXML
    private Button MainModifyProductBtn;

    @FXML
    private Button MainProductSearchBtn;

    @FXML
    private TextField ProductSearchTxt;

    @FXML
    private Label ProductsLabel;

    @FXML
    private TableView<Product> ProductTable;

    @FXML
    private TableColumn<Product, Integer> ProductIdColumn;

    @FXML
    private TableColumn<Product, String> ProductNameColumn;

    @FXML
    private TableColumn<Product, Integer> ProductInvColumn;

    @FXML
    private TableColumn<Product, Double> ProductPriceColumn;

    @FXML
    private Button Main_Exit_Btn;

    @FXML
    private Label MainLabel;

    
   static ObservableList <Part> dataPart = FXCollections.observableArrayList();
   static ObservableList <Product> dataProduct = FXCollections.observableArrayList();
   final Inventory list = new Inventory();
    
    /*******************************************************
     *  Parts Pane Controls                                *
     *******************************************************/
    
    
    //Add Button Parts Pane
    @FXML
    void addPartButtonAction(ActionEvent event) throws IOException {

        Parent addPartPage = FXMLLoader.load(getClass().getResource("/View_Controller/AddPartInhouse.fxml"));
        Scene addPartScene = new Scene(addPartPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(addPartScene);
        app_stage.show();
        
    }
    
    
    //Modify Button Parts Pane
    @FXML
    void modifyPartButtonAction(ActionEvent event) throws IOException{
        
        //list is inventory item.
        Part partSelected;
        partSelected = PartsTable.getSelectionModel().getSelectedItem();
        
        if (partSelected instanceof Inhouse){
            
            //PROPER WAY OF TRANSFER SCREENS AND OBJECTS.
            Stage stage;
            Parent root;
            
            stage = (Stage) MainModifyPartBtn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/ModifyPartInhouse.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            ModifyPartInhouseController controller = loader.getController();
            controller.setPart(partSelected);
            
        } else {
            Stage stage;
            Parent root;
            
            stage = (Stage) MainModifyPartBtn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/ModifyPartOutsource.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            ModifyPartOutsourceController controller = loader.getController();
            controller.setPart(partSelected);
           
        }
        
        
    }
    
    
    //Delete Button Parts Pane
    @FXML
    void deletePartButtonAction(ActionEvent event) throws IOException{
        
        //Remove data from table
        int ID;
        ObservableList<Part> partSelected;
        ObservableList<Part> allParts;
        allParts = PartsTable.getItems();
        partSelected = PartsTable.getSelectionModel().getSelectedItems();
        ID = PartsTable.getSelectionModel().getSelectedIndex();
        partSelected.forEach(allParts::remove);
        
        //Remove Obj from list and reset ID.
        Inventory partList = new Inventory();
        partList.removePart(ID);
        
        ArrayList <Part> newId =  list.getPartList();
        for (int i = ID; i < newId.size(); i++){
            
            if(newId.get(i) instanceof Inhouse){
                Inhouse inhouse = new Inhouse();
                inhouse = (Inhouse) newId.get(i);
                newId.get(i).setPartID(inhouse.getPartID() - 1);
            } else {
                Outsourced outsource = new Outsourced();
                outsource = (Outsourced) newId.get(i);
                newId.get(i).setPartID(outsource.getPartID() - 1);
            }
        }
       
        
        
    }
    
   //Search Button Parts Pane
    @FXML
    void searchPartButtonAction(ActionEvent event) throws IOException{
        int id = Integer.parseInt(PartSearchTxt.getText());
        dataPart = FXCollections.observableArrayList(list.getPartList());
        PartsTable.getItems().clear();
        PartsTable.getColumns().setAll(PartIdColumn, PartNameColumn, PartInvColumn, PartPriceColumn); 
           
            PartIdColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
            PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            PartInvColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
            PartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            PartsTable.getItems().add(dataPart.get(id-1));
        
        
    }
    
    

    

    


    /*******************************************************
     *  Product Pane Controls                              *
     *******************************************************/
    
    
    //Add Button Product Pane
    @FXML
    void addProductButtonAction(ActionEvent event) throws IOException{

        Parent modifyProductPage = FXMLLoader.load(getClass().getResource("/View_Controller/AddProduct.fxml"));
        Scene modifyProductScene = new Scene(modifyProductPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifyProductScene);
        app_stage.show();
        
    }
    
    //Modify Button Product Pane
    @FXML
    void modifyProductButtonAction(ActionEvent event) throws IOException{
        
        Product productSelected;
        productSelected = ProductTable.getSelectionModel().getSelectedItem();
        Stage stage;
        Parent root;
            
        stage = (Stage) MainModifyProductBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/ModifyProduct.fxml"));
        root = loader.load();
        ModifyProductController controller = loader.getController();
        controller.setProduct(productSelected);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    //Delete Button Product Pane
    @FXML
    void deleteProductButton(ActionEvent event) throws IOException{
        int ID;
        ObservableList<Product> productSelected;
        ObservableList<Product> allProducts;
        allProducts = ProductTable.getItems();
        productSelected = ProductTable.getSelectionModel().getSelectedItems();
        ID = ProductTable.getSelectionModel().getSelectedIndex();
        productSelected.forEach(allProducts::remove);
        //Remove Obj from list and reset ID.
        Inventory productList = new Inventory();
        productList.removeProduct(ID);
        
        ArrayList <Product> newId =  list.getProductList();
        
        for (int i = ID; i < newId.size(); i++){
            
            
            newId.get(i).setProductID(newId.get(i).getProductID() - 1);
            
        }
        
    }
    
    //Search Button Product Pane
    @FXML
    void searchProductButtonAction(ActionEvent event) throws IOException{
        int id = Integer.parseInt(ProductSearchTxt.getText());
        dataProduct = FXCollections.observableArrayList(list.getProductList());
        ProductTable.getItems().clear();
        ProductTable.getColumns().setAll(ProductIdColumn, ProductNameColumn, ProductInvColumn, ProductPriceColumn);
                
                ProductIdColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
                ProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                ProductInvColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
                ProductPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                ProductTable.getItems().add(dataProduct.get(id-1));
        
    }

     /*******************************************************
     *  Anchor Pane Controls                              *
     *******************************************************/
    
    @FXML
    void exitButtonAction(ActionEvent event) throws IOException{
        Platform.exit();
    }

    /*
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        
        
        dataPart = FXCollections.observableArrayList(list.getPartList());
        dataProduct = FXCollections.observableArrayList(list.getProductList());
        
        
        if (dataPart.isEmpty()){}
        
        else{
            //Table View
            
            for (int i = 0; i < dataPart.size(); i++){
            PartsTable.getColumns().setAll(PartIdColumn, PartNameColumn, PartInvColumn, PartPriceColumn); 
           
            PartIdColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
            PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            PartInvColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
            PartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            PartsTable.getItems().add(dataPart.get(i));
            }
      
        }
        
        if (dataProduct.isEmpty()){}
        
        else {
        
            for (int i = 0; i < dataProduct.size(); i++){
                ProductTable.getColumns().setAll(ProductIdColumn, ProductNameColumn, ProductInvColumn, ProductPriceColumn);
                
                ProductIdColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
                ProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                ProductInvColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
                ProductPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                ProductTable.getItems().add(dataProduct.get(i));
            }
        }
    
    }    
      
 
}
