/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import modelBO.MediaObjectBO;

/**
 *
 * @author costi_000
 */
public class MediaObject {
    
    
    
     private String username;
    private byte[] content;
    
    
    
    
    public MediaObject() {


        username=null;

        content=null;


}
    
    public MediaObjectBO toMediaObjectBO(){
        MediaObjectBO objBO= new MediaObjectBO();
        objBO.setContent(content);
        objBO.setUsername(username);
        
        return objBO;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the content
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(byte[] content) {
        this.content = content;
    }
    
}
