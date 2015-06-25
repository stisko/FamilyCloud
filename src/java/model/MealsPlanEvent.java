/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import modelBO.MealsPlanEventBO;

/**
 *
 * @author costi_000
 */
public class MealsPlanEvent {

    private int idMealsPlanEvent;
    private String created_by;
    private String director_username;
    private String title;
    private Date start_date;
    private String mealType;
    private String location;
    private String description;
    private String repeatTime;
    private int repeat_every;
    private Date startRepeatDate;
    private Date endRepeatDate;
    private int notificationTime;
    private String notificationDate;

    public MealsPlanEvent() {

    }
    
    public MealsPlanEventBO toMealsPlanEventBO(){
        MealsPlanEventBO event= new MealsPlanEventBO();
        event.setIdMealsPlanEvent(idMealsPlanEvent);
        event.setCreated_by(created_by);
        event.setDirector_username(director_username);
        event.setTitle(title);
        event.setStart_date(start_date);
        event.setMealType(mealType);
        event.setLocation(location);
        event.setDescription(description);
        event.setRepeatTime(repeatTime);
        event.setRepeat_every(repeat_every);
        event.setStartRepeatDate(startRepeatDate);
        event.setEndRepeatDate(endRepeatDate);
        event.setNotificationTime(notificationTime);
        event.setNotificationDate(notificationDate);
        
        return event;
    }

    /**
     * @return the idMealsPlanEvent
     */
    public int getIdMealsPlanEvent() {
        return idMealsPlanEvent;
    }

    /**
     * @param idMealsPlanEvent the idMealsPlanEvent to set
     */
    public void setIdMealsPlanEvent(int idMealsPlanEvent) {
        this.idMealsPlanEvent = idMealsPlanEvent;
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
     * @return the mealType
     */
    public String getMealType() {
        return mealType;
    }

    /**
     * @param mealType the mealType to set
     */
    public void setMealType(String mealType) {
        this.mealType = mealType;
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
    
    

}
