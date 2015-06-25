/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import modelBO.ShoppingListBO;
import org.json.simple.JSONObject;

/**
 *
 * @author GVra
 */
public class ShoppingListItem {
    
     private int shopitemID;    
    private String director_username;    
    private String shoptitle;    
    private int quantity;   
    private int price;
    private String shopstatus;     
    private String assigned_to;
    private String created_by;
    
    
    public ShoppingListItem(){
    
    
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
    
    
    
    public ShoppingListBO toShoppingListBO(){
    
        ShoppingListBO slBO = new ShoppingListBO();
        
        
        slBO.setDirector_username(director_username);
        slBO.setAssigned_to(assigned_to);
        slBO.setPrice(price);
        slBO.setQuantity(quantity);
        slBO.setShopitemID(shopitemID);
        slBO.setShopstatus(shopstatus);
        slBO.setShoptitle(shoptitle);
        slBO.setCreated_by(created_by);
        
        
        return slBO;
    
    }
    
    
    
    
    public JSONObject getShoppingListItemJSON(){
        JSONObject obj= new JSONObject();
        
        
        obj.put("itemID", shopitemID);
        obj.put("directorUsername", director_username);
        obj.put("shoptitle", shoptitle);
        obj.put("quantity", quantity);
        obj.put("price", price);
        obj.put("shopstatus", shopstatus);
        obj.put("assigned_to", assigned_to);
        obj.put("created_by", created_by);
        
        return obj;
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
