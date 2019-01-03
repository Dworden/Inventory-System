package Model;

/**
 *
 * @author Dustin
 */
public class Outsourced extends Part {
    
    private String companyName;
    
    //Blank Constructor
    public Outsourced(){}; 
    

    public Outsourced(int partID, String partName, double price, int inStock, int min, int max, String companyName) {
        super(partID, partName, price, inStock, min, max);
        this.companyName = companyName;
    }
    

    //Get Company Name
    
    public String getCompanyName(){
        return companyName;
    }
    
    //Set Company Name
    
    public void setCompanyName(String name){
        
        companyName = name;
    }
    
    //Configure Part ID
    public int configurePartID(){
       Inventory listSize;
       listSize = new Inventory();
       int partID = listSize.getPartLength() + 1;
       
       return partID;
       
    }
}
