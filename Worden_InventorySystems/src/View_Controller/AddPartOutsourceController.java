package View_Controller;

import Model.Inventory;
import Model.Outsourced;
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

public class AddPartOutsourceController implements Initializable{

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
    private TextField CompanyNameTxt;

    @FXML
    private Label AddPartLabel;
    
    //Switches Scene to AddPartInhouse.fxml

    @FXML
    void InhouseButtonAction(ActionEvent event) throws IOException {
            
        Parent addPartInhousePage = FXMLLoader.load(getClass().getResource("/View_Controller/AddPartInhouse.fxml"));
        Scene addPartInhouseScene = new Scene(addPartInhousePage);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        app_stage.setScene(addPartInhouseScene);
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

    //SaveButtonInHouseAction
    @FXML
    void SaveButtonOutsourcedAction(ActionEvent event) throws IOException{
        Outsourced configID;
        configID = new Outsourced();
        
        Outsourced newPart;
        newPart = new Outsourced(
                configID.configurePartID(),PartNameTxt.getText(),Double.parseDouble(PriceTxt.getText()),Integer.parseInt(InvTxt.getText()),
                Integer.parseInt(MinTxt.getText()),Integer.parseInt(MaxTxt.getText()),CompanyNameTxt.getText()
        );
        
        Inventory partsList;
        partsList = new Inventory();
        partsList.addPart(newPart);
        
        
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
        OutsourcedBtn.setSelected(true);
    }    
    
}
