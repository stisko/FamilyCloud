/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import modelBO.WallPostBO;
import org.json.simple.JSONObject;

/**
 *
 * @author costi_000
 */
public class WallPost {
    private int idWallPost;
    private String director_username;
    private String created_by;
    private String message;
    private Date creation_time;
    private byte[] file;

    
    public WallPost(){
        
    }
    
    public WallPostBO toWallPostBO(){
        WallPostBO postBO= new WallPostBO();
        postBO.setCreated_by(created_by);
        postBO.setIdWallPost(idWallPost);
        postBO.setMessage(message);
        postBO.setDirector_username(director_username);
        postBO.setFile(file);
        postBO.setCreation_time(creation_time);
        
       
        return postBO;
    }
    
    public JSONObject getWallPostJSON(){
        JSONObject obj = new JSONObject();
        obj.put("idWallPost", idWallPost);
        obj.put("created_by", created_by);
        obj.put("message", message);
        obj.put("director_username", director_username);
        obj.put("file", file);
        obj.put("creation_time", creation_time);
        
        return obj;
    }
    
    /**
     * @return the idWallPost
     */
    public int getIdWallPost() {
        return idWallPost;
    }

    /**
     * @param idWallPost the idWallPost to set
     */
    public void setIdWallPost(int idWallPost) {
        this.idWallPost = idWallPost;
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
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the creation_time
     */
    public Date getCreation_time() {
        return creation_time;
    }

    /**
     * @param creation_time the creation_time to set
     */
    public void setCreation_time(Date creation_time) {
        this.creation_time = creation_time;
    }

    /**
     * @return the file
     */
    public byte[] getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(byte[] file) {
        this.file = file;
    }
}
