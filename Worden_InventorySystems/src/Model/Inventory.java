package Model;

import java.util.ArrayList;

/**
 *
 * @author Dustin
 */
public class Inventory {
    
    private static ArrayList<Product> products = new ArrayList();
    private static ArrayList<Part> allParts = new ArrayList();
 
    
    
    
    
    //Product ArrayList Methods
    public void addProduct(Product product){
        products.add(product);
    }
    
    public ArrayList<Product> getProductList(){
        return products;
    }
    
    public int getProductLength(){
        
        return products.size();
    }
    
    public void removeProduct(int ID){
        products.remove(ID);
    }
    
    
    
    //Part ArrayList Methods
    public void addPart(Part part){
        allParts.add(part);
    }
 
    public ArrayList<Part> getPartList(){
        return allParts;
    }
    
    public int getPartLength(){
        
         return allParts.size();       
    }
    
    public void removePart(int ID){
        allParts.remove(ID);
    }
    
    public void addPart (int ID, Part part){
        allParts.add(ID, part);
    }
    
   
    
}
