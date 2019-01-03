package Model;

/**
 *
 * @author Dustin
 */

public class Inhouse extends Part{
    
    private int machineID;

    //blank constructor
    public Inhouse(){};


 
    public Inhouse(int partID, String partName, double price, int inStock, int min, int max, int machineID) {
        super(partID, partName, price, inStock, min, max);
        this.machineID = machineID;
       
    }
    
    //Set Machine ID
    public void setMachineID(int ID){
        
        machineID = ID;
        
    }
    
    
    // Text field ID- MachIdTxt, in scenes AddPart.fxml and ModifyPart.fxml
    public int getMachineID(){
        
        return machineID;
    }
    
    //Configure Part ID
    public int configurePartID(){
       Inventory listSize;
       listSize = new Inventory();
       int partID = listSize.getPartLength() + 1;
       
       return partID;
       
    }
    
}
