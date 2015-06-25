/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import modelBO.MessageBO;
import org.json.simple.JSONObject;

/**
 *
 * @author costi_000
 */
public class Message {

    private int idMessage;
    private String sender;
    private String receiver;
    private String message;
    private byte[] file;
    private Date creation_date;
    
    public Message(){
        
    }
    
    public MessageBO toMessageBO(){
        MessageBO msgBO= new MessageBO();
        msgBO.setIdMessage(idMessage);
        msgBO.setSender(sender);
        msgBO.setReceiver(receiver);
        msgBO.setMessage(message);
        msgBO.setFile(file);
        msgBO.setCreation_date(creation_date);
        
        return msgBO;
    }
    
    

    /**
     * @return the idMessage
     */
    public int getIdMessage() {
        return idMessage;
    }

    /**
     * @param idMessage the idMessage to set
     */
    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    /**
     * @return the sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * @return the receiver
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * @param receiver the receiver to set
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
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

    /**
     * @return the creation_date
     */
    public Date getCreation_date() {
        return creation_date;
    }

    /**
     * @param creation_date the creation_date to set
     */
    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }
    
    
}
