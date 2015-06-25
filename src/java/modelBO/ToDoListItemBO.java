/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelBO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ToDoListItem;

/**
 *
 * @author costi_000
 */
public class ToDoListItemBO {
    private int itemID;
    private String director_username;
    private String title;
    private String assignedTo;
    private String createdBy;
    private Date dueDate;
    private Date completedDate;
    private String status;
    
    public ToDoListItemBO(){
        director_username=null;
        title=null;
        assignedTo=null;
        createdBy=null;
        dueDate=null;
        completedDate=null;
        status=null;
        itemID=-1;
    }
    
    public ToDoListItemBO toToDoListItemBO(ToDoListItem item){
        ToDoListItemBO itemBO = new ToDoListItemBO();
        
        itemBO.setDirector_username(item.getDirector_username());
        itemBO.setTitle(item.getTitle());
        itemBO.setAssignedTo(item.getAssignedTo());
        itemBO.setCreatedBy(item.getCreatedBy());
        itemBO.setDueDate(item.getDueDate());
        itemBO.setCompletedDate(item.getCompletedDate());
        itemBO.setStatus(item.getStatus());
        itemBO.setItemID(item.getItemID());
        
        return itemBO;
    }
    
    public List<ToDoListItemBO> family_toToDoListItemBO(List<ToDoListItem> family){
        ToDoListItemBO itemBO = new ToDoListItemBO();
        
        List<ToDoListItemBO> family_list= new ArrayList<ToDoListItemBO>();
        
        for(int i=0;i<family.size();i++){
            family_list.add(toToDoListItemBO(family.get(i)));
        }
        
        return family_list;
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the assignedTo
     */
    public String getAssignedTo() {
        return assignedTo;
    }

    /**
     * @param assignedTo the assignedTo to set
     */
    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the completedDate
     */
    public Date getCompletedDate() {
        return completedDate;
    }

    /**
     * @param completedDate the completedDate to set
     */
    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the itemID
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
    
    
    
}
