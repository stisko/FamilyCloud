/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import model.Message;

/**
 *
 * @author costi_000
 */
public interface MessageDao {
    
    public boolean addMessage(Message msg);
    
    public List<Message> getMessages(String sender, String receiver);
    
    public Message getMessage(int messageid);
    
}
