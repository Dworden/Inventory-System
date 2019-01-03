package View_Controller;

import Model.Inventory;
import Model.Outsourced;
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

public class ModifyPartOutsourceController implements Initializable {

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
    private Label CompanyNameLabel;

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
    private TextField CompanyNameTxt;

    @FXML
    private Label AddPartLabel;
    
    
    
    
    
    Inventory partList = new Inventory();
    Outsourced outsource = new Outsourced();
    Part part;
    
    //Switches to ModifyPartInhouse.fxml

    @FXML
    void InhouseButtonAction(ActionEvent event) throws IOException {
        
        Stage stage;
        Parent root;
            
        stage = (Stage) InHouseRadioBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/ModifyPartInhouse.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ModifyPartInhouseController controller = loader.getController();
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
    
    //Ssves Modified Part
    @FXML
    void SaveModifiedOutsourceButton(ActionEvent event) throws IOException {
        
        if (part instanceof Outsourced){
            
        outsource = (Outsourced) part;
        
        outsource.setName(PartNameTxt.getText());
        outsource.setInStock(Integer.parseInt(InvTxt.getText()));
        outsource.setPrice(Double.parseDouble(PriceTxt.getText()));
        outsource.setMax(Integer.parseInt(MaxTxt.getText()));
        outsource.setMin(Integer.parseInt(MinTxt.getText()));
        outsource.setCompanyName(CompanyNameTxt.getText());
        }else{
            int ID = Integer.parseInt(IdTxt.getText());
             partList.getPartList();
             partList.removePart(part.getPartID()- 1);
             
        
             Outsourced newPart;
             newPart = new Outsourced(
             ID,PartNameTxt.getText(),Double.parseDouble(PriceTxt.getText()),Integer.parseInt(InvTxt.getText()),
             Integer.parseInt(MinTxt.getText()),Integer.parseInt(MaxTxt.getText()),CompanyNameTxt.getText() );
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
        
        if (part instanceof Outsourced){
            outsource = (Outsourced) part;
            CompanyNameTxt.setText(outsource.getCompanyName());
        }else{
            CompanyNameTxt.setText("Needed");
        }
        
    }

    



    /**
     * Initializes the controller class.
     */
 
    public void initialize(URL url, ResourceBundle rb) {
        OutsourcedBtn.setSelected(true);
        
        
       
    }    
    
}
