package Model;

/**
 *
 * @author Dustin
 */
public abstract class Part {
    
    private String partName;
    
    private int partID;
    private int inStock;
    private int min;
    private int max;
    private double price;
    
    
    //Blank Constructor
    Part(){};
    //Constructor
    Part (int partID, String partName, double price, int inStock, int min, int max){
        
        this.partID = partID;
        this.partName = partName;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
        
    }
    
    //Getter / Setter for name
    public void setName(String name){
        
        partName = name;
        
    }
    
    public String getName(){
        return partName;
    }
    
    //Getter / Setter for price
    public void setPrice(double cost){
        
        price = cost;
        
    }
    
    public double getPrice(){
        return price;
    }
    
    //Getter / Setter for inStock
    public void setInStock (int amount){
        inStock = amount;
    }
    
    public int getInStock(){
        return inStock;
    }
    
    //Getter / Setter for min
    public void setMin(int low){
        min = low;
    }
    
    public int getMin(){
        return min;
    }
    
    //Getter / Setter for max
    public void setMax(int high){
        max = high;
    }
    
    public int getMax(){
        return max;
    }
    
    //Getter / Setter PartID
    public void setPartID(int ID){
        partID = ID;
    }
    
    public int getPartID(){
        return partID;
    }
    
    
 
}
