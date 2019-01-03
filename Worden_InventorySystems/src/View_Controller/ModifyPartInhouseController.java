package View_Controller;

import Model.Inhouse;
import Model.Inventory;
import Model.Part;
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

public class ModifyPartInhouseController implements Initializable {

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
    private Button Save_Add_Btn;

    @FXML
    private Button Cancel_Add_Btn;

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
    


    
    
    //Controller Functions.
    Inventory partList = new Inventory();
    Inhouse inhouse = new Inhouse();
    Part part;
    
    
    
    //Switches scene to ModifyPartOutsource.fxml
    @FXML
    void OutsourceButtonAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
            
        stage = (Stage) OutsourcedBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/ModifyPartOutsource.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ModifyPartOutsourceController controller = loader.getController();
        controller.setPart(part);
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
    
    //Save Modified Part
    @FXML
    void SaveModifiedInhouseButton(ActionEvent event) throws IOException {
         
        if (part instanceof Inhouse){

        inhouse = (Inhouse) part;
        
        inhouse.setName(PartNameTxt.getText());
        inhouse.setInStock(Integer.parseInt(InvTxt.getText()));
        inhouse.setPrice(Double.parseDouble(PriceTxt.getText()));
        inhouse.setMax(Integer.parseInt(MaxTxt.getText()));
        inhouse.setMin(Integer.parseInt(MinTxt.getText()));
        inhouse.setMachineID(Integer.parseInt(MachIdTxt.getText()));
        
        } else {
            int ID = Integer.parseInt(IdTxt.getText());
            partList.getPartList();
            partList.removePart(part.getPartID() - 1);
                        
            Inhouse newPart;
            newPart = new Inhouse(
            ID,PartNameTxt.getText(),Double.parseDouble(PriceTxt.getText()),Integer.parseInt(InvTxt.getText()),
            Integer.parseInt(MinTxt.getText()),Integer.parseInt(MaxTxt.getText()),(Integer.parseInt(MachIdTxt.getText())));
            partList.addPart(ID - 1, newPart);
        }
        Parent mainPage = FXMLLoader.load(getClass().getResource("/View_Controller/Main.fxml"));
        Scene mainScene = new Scene(mainPage);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        app_stage.setScene(mainScene);
        app_stage.show();
    }
    

    void setPart (Part part){
        this.part =  part;
        
        
        
        IdTxt.setText(Integer.toString(part.getPartID()));
        PartNameTxt.setText(part.getName());
        InvTxt.setText(Integer.toString(part.getInStock()));
        PriceTxt.setText(Double.toString(part.getPrice()));
        MaxTxt.setText(Integer.toString(part.getMax()));
        MinTxt.setText(Integer.toString(part.getMin()));
        
        if (part instanceof Inhouse){
           inhouse = (Inhouse) part;
           MachIdTxt.setText(Integer.toString(inhouse.getMachineID()));
        } else {
            MachIdTxt.setText("Needed");
        }
        
    }
 

    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
          InHouseRadioBtn.setSelected(true);
          

          
    }    
    
}
