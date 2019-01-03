package Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    
   private ObservableList <Part> associatedParts = FXCollections.observableArrayList();
   
   private int productID;
   private int inStock;
   private int min;
   private int max;
   
   private double price;
   
   private String productName;
   
   //Blank Constructor
   public Product (){};
   
   
   public Product(int productID, int inStock, int min, int max, double price, String productName, ObservableList part){
       
       this.productID = productID;
       this.inStock = inStock;
       this.min = min;
       this.max = max;
       this.price = price;
       this.productName = productName;
       this.associatedParts = part;
       
   };
   
   //Getter / Setter for productName
   public void setName(String name){
       productName = name;
   }
   
   public String getName(){
       return productName;
   }
   
   //Getter / Setter for price
   public void setPrice(double cost){
       price = cost;
   }
   
   public double getPrice(){
       return price;
   }
   
   //Getter / Setter for inStock
   public void setInStock(int count){
       inStock = count;
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
   public void setMax (int high){
       max = high;
   }
   
   public int getMax(){
       return max;
   }
   
   //Getter / Setter for productID
   public void setProductID(int ID){
       productID = ID;
   }
   
   public int getProductID(){
       return productID;
   }
   
   
   //Part List Methods
   public ObservableList<Part> getPartList(){
       return associatedParts;
   }
    
   public void addAssociatedPart(Part part){
       associatedParts.add(part);
   }
   
   public void removeAssociatedPart(int ID){
       associatedParts.remove(ID);
   }
   
   //Configures ID
   public int configID(){
       Inventory listSize;
       listSize = new Inventory();
       int partID = listSize.getProductLength() + 1;
       
       return partID;
   }
    
}
