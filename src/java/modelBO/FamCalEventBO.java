/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelBO;

import java.util.Date;
import java.util.List;
import model.FamCalEvent;
import org.json.simple.JSONObject;

/**
 *
 * @author costi_000
 */
public class FamCalEventBO {

    private int idFamCalEvent;
    private String created_by;
    private String director_username;
    private String title;
    private Date start_date;
    private Date end_date;
    private String category;
    private String location;
    private String visibility;
    private String participating_members;
    private String description;
    private String repeatTime;
    private int repeat_every;
    private Date startRepeatDate;
    private Date endRepeatDate;
    private int notificationTime;
    private String notificationDate;

    public FamCalEventBO() {
        idFamCalEvent = -1;
        created_by = null;
        director_username=null;
        title=null;
        start_date=null;
        end_date=null;
        category=null;
        location=null;
        visibility=null;
        participating_members=null;
        description=null;
        
        repeatTime=null;
        repeat_every=-1;
        startRepeatDate=null;
        endRepeatDate=null;
        
        notificationTime=-1;
        notificationDate=null;
    }
    
    public FamCalEventBO toFamCalEventBO(FamCalEvent event){
        FamCalEventBO eventBO= new FamCalEventBO();
        eventBO.setIdFamCalEvent(event.getIdFamCalEvent());
        eventBO.setCreated_by(event.getCreated_by());
        eventBO.setDirector_username(event.getDirector_username());
        eventBO.setTitle(event.getTitle());
        eventBO.setStart_date(event.getStart_date());
        eventBO.setEnd_date(event.getEnd_date());
        eventBO.setCategory(event.getCategory());
        eventBO.setLocation(event.getLocation());
        eventBO.setVisibility(event.getVisibility());
        eventBO.setParticipating_members(event.getParticipating_members());
        eventBO.setDescription(event.getDescription());
        eventBO.setRepeatTime(event.getDescription());
        eventBO.setRepeat_every(event.getRepeat_every());
        eventBO.setStartRepeatDate(event.getStartRepeatDate());
        eventBO.setEndRepeatDate(event.getEndRepeatDate());
        eventBO.setNotificationTime(event.getNotificationTime());
        eventBO.setNotificationDate(event.getNotificationDate());
        
        return eventBO;
    }
    
    

    /**
     * @return the idFamCalEvent
     */
    public int getIdFamCalEvent() {
        return idFamCalEvent;
    }

    /**
     * @param idFamCalEvent the idFamCalEvent to set
     */
    public void setIdFamCalEvent(int idFamCalEvent) {
        this.idFamCalEvent = idFamCalEvent;
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
    public String getVisibility() {
        return visibility;
    }

    /**
     * @param visibility the visibility to set
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * @return the participating_members
     */
    public String getParticipating_members() {
        return participating_members;
    }

    /**
     * @param participating_members the participating_members to set
     */
    public void setParticipating_members(String participating_members) {
        this.participating_members = participating_members;
    }

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

}
