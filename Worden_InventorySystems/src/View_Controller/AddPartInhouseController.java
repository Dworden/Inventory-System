package View_Controller;

import Model.Inhouse;
import Model.Inventory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dustin
 */
public class AddPartInhouseController implements Initializable {

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
    private Label MachineIDLabel;

    @FXML
    private Label MinLabel;

    @FXML
    private RadioButton InHouseRadioBtn;

    @FXML
    private RadioButton OutsourcedBtn;

    @FXML
    private Button SaveAddBtn;

    @FXML
    private Button CancelAddBtn;

    @FXML
    private TextField IdTxt;

    @FXML
    private TextField PartNameTxt;

    @FXML
    private TextField InvTxt;

    @FXML
    private TextField PriceTxt;

    @FXML
    private TextField MaxTxt;

    @FXML
    private TextField MinTxt;

    @FXML
    private TextField MachIdTxt;

    @FXML
    private Label AddPartLabel;
    
    
    
    //Switches Scenes to AddPartOutsource.fxml

    @FXML
    void OutsourceButtonAction(ActionEvent event) throws IOException {
        
        Parent addPartOutsourcePage = FXMLLoader.load(getClass().getResource("/View_Controller/AddPartOutsource.fxml"));
        Scene addPartOutsourceScene = new Scene(addPartOutsourcePage);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        app_stage.setScene(addPartOutsourceScene);
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
    
    //SaveButtonInHouseAction, Creates new Inhouse Object and add to AllParts in Inventory
    @FXML
    void SaveButtonInHouseAction(ActionEvent event) throws IOException{
       
        
        Inhouse configID;
        configID = new Inhouse();

        Inhouse newPart;
        newPart = new Inhouse(
                configID.configurePartID(),PartNameTxt.getText(),Double.parseDouble(PriceTxt.getText()),Integer.parseInt(InvTxt.getText()),
                Integer.parseInt(MinTxt.getText()),Integer.parseInt(MaxTxt.getText()),(Integer.parseInt(MachIdTxt.getText()))
        );
        
     
        
        Inventory partList;
        
        partList = new Inventory();
        partList.addPart(newPart);
        
        
        Parent mainPage = FXMLLoader.load(getClass().getResource("/View_Controller/Main.fxml"));
        Scene mainScene = new Scene(mainPage);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        app_stage.setScene(mainScene);
        app_stage.show();
        
        
    }



      /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        InHouseRadioBtn.setSelected(true);
     }   
    

}