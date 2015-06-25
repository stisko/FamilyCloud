/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.ToDoListItem;
import java.util.List;
import modelBO.ToDoListItemBO;

/**
 *
 * @author costi_000
 */
public interface ToDoListItemDao {
    
    public boolean insertItem(ToDoListItem item);
    
    public boolean deleteItem(int itemID);
    
    public boolean updateItem(ToDoListItem item);
    
    public ToDoListItem getItem(int itemID);
    
    public List<ToDoListItem> getFamilyToDoList(String username);
    
    public List<ToDoListItem> getSortedFamilyToDoList(String username, String sortvalue);
    
}
