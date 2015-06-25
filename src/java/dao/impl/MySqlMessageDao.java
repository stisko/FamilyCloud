/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.MessageDao;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Message;

/**
 *
 * @author costi_000
 */
public class MySqlMessageDao implements MessageDao {

    @Override
    public boolean addMessage(Message msg) {
        try {
            int validation = 0;
            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();

            pst = conn.prepareStatement("INSERT INTO message (sender, receiver, message, file, date) VALUES(?,?,?,?,?)");
            pst.setString(1, msg.getSender());
            pst.setString(2, msg.getReceiver());
            pst.setString(3, msg.getMessage());
            if( msg.getFile().length != 0) {
                pst.setBinaryStream(4, new ByteArrayInputStream(msg.getFile()), msg.getFile().length);
            } else {
                pst.setNull(4, java.sql.Types.BLOB);
            }
            java.util.Date dt = new java.util.Date();

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentTime = sdf.format(dt);
            pst.setString(5, currentTime);

            validation = pst.executeUpdate();
            conn.close();
            if (validation == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySqlMessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;

    }

    @Override
    public List<Message> getMessages(String sender, String receiver) {
        try {
            
            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();
            List<Message> messages= new ArrayList<Message>();
            
            pst = conn.prepareStatement("SELECT * FROM message WHERE(( sender=? AND receiver=?) OR ( sender=? AND receiver=?)) ORDER BY date ASC");
            pst.setString(1, sender);
            pst.setString(2, receiver);
            pst.setString(3, receiver);
            pst.setString(4, sender);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Message msg = new Message();
                
                msg.setIdMessage(rs.getInt("idMessages"));
                msg.setSender(rs.getString("sender"));
                msg.setReceiver(rs.getString("receiver"));
                msg.setMessage(rs.getString("message"));
                Timestamp timestamp = rs.getTimestamp("date");
                msg.setCreation_date(timestamp);
                Blob blob = rs.getBlob("file");
                byte[] test;
                if (blob != null) {
                    test = blob.getBytes(1, (int) blob.length());
                    String test2 = new String(test);
                } else {
                    test = null;
                }
                msg.setFile(test);
                messages.add(msg);
                
            }
            conn.close();
            return messages;
        } catch (SQLException ex) {
            Logger.getLogger(MySqlMessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    
    @Override
    public Message getMessage(int messageid) {
        try {
            
            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();
            
            
            pst = conn.prepareStatement("SELECT * FROM message WHERE idMessages=?");
            pst.setInt(1, messageid);
            

            ResultSet rs = pst.executeQuery();
            Message msg = new Message();
            while (rs.next()) {
                
                
                msg.setIdMessage(rs.getInt("idMessages"));
                msg.setSender(rs.getString("sender"));
                msg.setReceiver(rs.getString("receiver"));
                msg.setMessage(rs.getString("message"));
                Timestamp timestamp = rs.getTimestamp("date");
                msg.setCreation_date(timestamp);
                Blob blob = rs.getBlob("file");
                byte[] test;
                if (blob != null) {
                    test = blob.getBytes(1, (int) blob.length());
                    String test2 = new String(test);
                } else {
                    test = null;
                }
                msg.setFile(test);
                
                
            }
            conn.close();
            
            return msg;
        } catch (SQLException ex) {
            Logger.getLogger(MySqlMessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

}
