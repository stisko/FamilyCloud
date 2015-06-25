/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelBO;

import java.util.ArrayList;
import java.util.List;
import model.ShoppingListItem;

/**
 *
 * @author GVra
 */
public class ShoppingListBO {
    
    
     private int shopitemID;    
    private String director_username;    
    private String shoptitle;    
    private int quantity;   
    private int price;
    private String shopstatus;     
    private String assigned_to;
    private String created_by;
    
    
    
    public ShoppingListBO(){



      shopitemID =-1;    
    director_username=null;    
    shoptitle=null;    
     quantity=-1;   
     price=-1;
     shopstatus=null;     
     assigned_to=null;


}

    /**
     * @return the shopitemID
     */
    public int getShopitemID() {
        return shopitemID;
    }

    /**
     * @param shopitemID the shopitemID to set
     */
    public void setShopitemID(int shopitemID) {
        this.shopitemID = shopitemID;
    }

    /**
     * @return the director_username
     */
    public String getDirector_username() {
        return director_username;
    }

    /**
     * @param director_username the director_username to set
     */
    public void setDirector_username(String director_username) {
        this.director_username = director_username;
    }

    /**
     * @return the shoptitle
     */
    public String getShoptitle() {
        return shoptitle;
    }

    /**
     * @param shoptitle the shoptitle to set
     */
    public void setShoptitle(String shoptitle) {
        this.shoptitle = shoptitle;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the shopstatus
     */
    public String getShopstatus() {
        return shopstatus;
    }

    /**
     * @param shopstatus the shopstatus to set
     */
    public void setShopstatus(String shopstatus) {
        this.shopstatus = shopstatus;
    }

    /**
     * @return the assigned_to
     */
    public String getAssigned_to() {
        return assigned_to;
    }

    /**
     * @param assigned_to the assigned_to to set
     */
    public void setAssigned_to(String assigned_to) {
        this.assigned_to = assigned_to;
    }
    
    
    public ShoppingListBO toShoppingListBO(ShoppingListItem shitem){
    
    ShoppingListBO sss=new ShoppingListBO();
    
    
    sss.setAssigned_to(shitem.getAssigned_to());
    sss.setDirector_username(shitem.getDirector_username());
    sss.setPrice(shitem.getPrice());
    sss.setQuantity(shitem.getQuantity());
    sss.setShopitemID(shitem.getShopitemID());
    sss.setShopstatus(shitem.getShopstatus());
    sss.setShoptitle(shitem.getShoptitle());
    sss.setCreated_by(shitem.getCreated_by());
    
        
        return sss;
    
    }
    
    
    
     public List<ShoppingListBO> family_toShoppingListBO(List<ShoppingListItem> family){
        ShoppingListBO itemBO = new ShoppingListBO();
        
        List<ShoppingListBO> family_list= new ArrayList<ShoppingListBO>();
        
        for(int i=0;i<family.size();i++){
           family_list.add(toShoppingListBO(family.get(i)));
        }
        
        return family_list;
    }

    /**
     * @return the created_by
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * @param created_by the created_by to set
     */
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
    
    
    
}
