/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelBO;

import java.util.Date;
import model.PerCalEvent;

/**
 *
 * @author GVra
 */
public class PerCalEventBO {
    
    
    private int idPerCalEvent;
    private String created_by;
    private String director_username;
    private String title;
    private Date start_date;
    private Date end_date;
    private String category;
    private String location;
       
    private String description;
    private String repeatTime;
    private int repeat_every;
    private Date startRepeatDate;
    private Date endRepeatDate;
    private int notificationTime;
    private String notificationDate;
    
    
    
    public PerCalEventBO() {
        idPerCalEvent = -1;
        created_by = null;
        director_username=null;
        title=null;
        start_date=null;
        end_date=null;
        category=null;
        location=null;
        
        description=null;
        
        repeatTime=null;
        repeat_every=-1;
        startRepeatDate=null;
        endRepeatDate=null;
        
        notificationTime=-1;
        notificationDate=null;
    }

    /**
     * @return the idFamCalEvent
     */
    public int getidPerCalEvent() {
        return idPerCalEvent;
    }

    /**
     * @param idFamCalEvent the idFamCalEvent to set
     */
    public void setidPerCalEvent(int idPerCalEvent) {
        this.idPerCalEvent = idPerCalEvent;
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
     * @return the start_date
     */
    public Date getStart_date() {
        return start_date;
    }

    /**
     * @param start_date the start_date to set
     */
    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    /**
     * @return the end_date
     */
    public Date getEnd_date() {
        return end_date;
    }

    /**
     * @param end_date the end_date to set
     */
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the visibility
     */
   

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the repeatTime
     */
    public String getRepeatTime() {
        return repeatTime;
    }

    /**
     * @param repeatTime the repeatTime to set
     */
    public void setRepeatTime(String repeatTime) {
        this.repeatTime = repeatTime;
    }

    /**
     * @return the repeat_every
     */
    public int getRepeat_every() {
        return repeat_every;
    }

    /**
     * @param repeat_every the repeat_every to set
     */
    public void setRepeat_every(int repeat_every) {
        this.repeat_every = repeat_every;
    }

    /**
     * @return the startRepeatDate
     */
    public Date getStartRepeatDate() {
        return startRepeatDate;
    }

    /**
     * @param startRepeatDate the startRepeatDate to set
     */
    public void setStartRepeatDate(Date startRepeatDate) {
        this.startRepeatDate = startRepeatDate;
    }

    /**
     * @return the endRepeatDate
     */
    public Date getEndRepeatDate() {
        return endRepeatDate;
    }

    /**
     * @param endRepeatDate the endRepeatDate to set
     */
    public void setEndRepeatDate(Date endRepeatDate) {
        this.endRepeatDate = endRepeatDate;
    }

    /**
     * @return the notificationTime
     */
    public int getNotificationTime() {
        return notificationTime;
    }

    /**
     * @param notificationTime the notificationTime to set
     */
    public void setNotificationTime(int notificationTime) {
        this.notificationTime = notificationTime;
    }

    /**
     * @return the notificationDate
     */
    public String getNotificationDate() {
        return notificationDate;
    }

    /**
     * @param notificationDate the notificationDate to set
     */
    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }
    
    
    public PerCalEventBO toPerCalEventBO(PerCalEvent event){
        PerCalEventBO pebo=new PerCalEventBO();
        
        
        pebo.setidPerCalEvent(event.getidPerCalEvent());
        pebo.setCreated_by(event.getCreated_by());
        pebo.setDirector_username(event.getDirector_username());
        pebo.setTitle(event.getTitle());
        pebo.setStart_date(event.getStart_date());
        pebo.setEnd_date(event.getEnd_date());
        pebo.setCategory(event.getCategory());
        pebo.setLocation(event.getLocation());
        
        pebo.setDescription(event.getDescription());
        pebo.setRepeatTime(event.getRepeatTime());
        pebo.setRepeat_every(event.getRepeat_every());
        pebo.setStartRepeatDate(event.getStartRepeatDate());
        pebo.setEndRepeatDate(event.getEndRepeatDate());
        pebo.setNotificationTime(event.getNotificationTime());
        pebo.setNotificationTime(event.getNotificationTime());
        
        
        
        
        return pebo;


}
    
}
