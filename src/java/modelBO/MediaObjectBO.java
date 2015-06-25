/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelBO;

import model.MediaObject;

/**
 *
 * @author costi_000
 */
public class MediaObjectBO {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
    
   
    private String username;
    private byte[] content;
    
    
    
    
    public MediaObjectBO() {


        username=null;

        content=null;


}
    
    
    
    
    public MediaObjectBO toMediaObjectBO(MediaObject mobj){
    
        
        MediaObjectBO mobjbo =new MediaObjectBO();
        
        
        mobjbo.setContent(mobj.getContent());
        
        mobjbo.setUsername(mobj.getUsername());
        
                
        return mobjbo;
    
    
    
    
    }
    
}


