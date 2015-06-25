/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.Date;
import modelBO.ToDoListItemBO;
import org.json.simple.JSONObject;
/**
 *
 * @author costi_000
 */
public class ToDoListItem {
    private int itemID;
    private String director_username;
    private String title;
    private String assignedTo;
    private String createdBy;
    private Date dueDate;
    private Date completedDate;
    private String status;
    
    
    public ToDoListItem(){
        
    }
    
    public ToDoListItemBO toToDoListItemBO(){
        ToDoListItemBO itemBO = new ToDoListItemBO();
        
        itemBO.setItemID(itemID);
        itemBO.setDirector_username(director_username);
        itemBO.setTitle(title);
        itemBO.setAssignedTo(assignedTo);
        itemBO.setCreatedBy(createdBy);
        itemBO.setDueDate(dueDate);
        itemBO.setCompletedDate(completedDate);
        itemBO.setStatus(status);

        return itemBO;
    }
    
    public JSONObject getToDoListItemJSON(){
        JSONObject obj= new JSONObject();
        obj.put("itemID", itemID);
        obj.put("directorUsername", director_username);
        obj.put("title", title);
        obj.put("assignedTo", assignedTo);
        obj.put("createdBy", createdBy);
        obj.put("dueDate", dueDate);
        obj.put("completedDate", completedDate);
        obj.put("status", status);
        
        return obj;
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
