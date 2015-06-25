/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.ShoppingListItem;

/**
 *
 * @author GVra
 */
public interface ShoppingListDao {
    
    
    public boolean insertShoppingItem(ShoppingListItem shl);
    
    
    
    public List<ShoppingListItem> getFamilyShoppingList(String username);
    
    
    public ShoppingListItem getshopItem(int item);
    
    
   

    public boolean updateItem(ShoppingListItem sli);
    
    
    public boolean deleteItem(int item);
    
  
    
}
