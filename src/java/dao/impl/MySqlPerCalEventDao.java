/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.PerCalEventDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PerCalEvent;

/**
 *
 * @author GVra
 */
public class MySqlPerCalEventDao implements PerCalEventDao{

    @Override
    public boolean insertPerCalEvent(PerCalEvent event) {
         try {
            int validation = 0;

            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();
            pst = conn.prepareStatement("INSERT INTO perscalevents(created_by, director_username, title, start, end, category, location,"
                    + " description, repeat_frequency, repeat_time, starts_at, expiration, notification_time, "
                    + "notification_date ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, event.getCreated_by());
            pst.setString(2, event.getDirector_username());
            pst.setString(3, event.getTitle());

            java.util.Date dt = new java.util.Date();

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentTime = sdf.format(event.getStart_date());
            pst.setString(4, currentTime);
            currentTime = sdf.format(event.getEnd_date());
            pst.setString(5, currentTime);
            pst.setString(6, event.getCategory());
            pst.setString(7, event.getLocation());
            
            pst.setString(8, event.getDescription());

            if (event.getRepeatTime() == null) {
                pst.setNull(9, java.sql.Types.INTEGER);
                pst.setNull(10, java.sql.Types.VARCHAR);
                pst.setNull(11, java.sql.Types.DATE);
                pst.setNull(12, java.sql.Types.DATE);
                
            } else {
                pst.setInt(9, event.getRepeat_every());
                pst.setString(10, event.getRepeatTime());
                currentTime = sdf.format(event.getStartRepeatDate());
                pst.setString(11, currentTime);
                currentTime = sdf.format(event.getEndRepeatDate());
                pst.setString(12, currentTime);
            }
            pst.setInt(13, event.getNotificationTime());
            pst.setString(14, event.getNotificationDate());

            validation = pst.executeUpdate();
            conn.close();
            if (validation == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySqlFamCalEventDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<PerCalEvent> getPerCalEvents(String username) {
        
        
        try {
            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();

            List<PerCalEvent> events = new ArrayList<PerCalEvent>();

            pst = conn.prepareStatement("SELECT * FROM perscalevents WHERE created_by=?");
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                PerCalEvent event = new PerCalEvent();
                
               
                event.setidPerCalEvent(rs.getInt("idperCalEvents"));
                event.setCreated_by(rs.getString("created_by"));
                event.setDirector_username(rs.getString("director_username"));
                event.setTitle(rs.getString("title"));
                Timestamp timestamp = rs.getTimestamp("start");
                event.setStart_date(timestamp);
                event.setEnd_date(rs.getTimestamp("end"));
                event.setCategory(rs.getString("category"));
                event.setLocation(rs.getString("location"));
                
                event.setDescription(rs.getString("description"));
                event.setRepeat_every(rs.getInt("repeat_frequency"));
                event.setRepeatTime(rs.getString("repeat_time"));
                event.setStartRepeatDate(rs.getTimestamp("starts_at"));
                event.setEndRepeatDate(rs.getTimestamp("expiration"));
                event.setNotificationTime(rs.getInt("notification_time"));
                event.setNotificationDate(rs.getString("notification_date"));

                events.add(event);
            }
            conn.close();
            return events;

        } catch (SQLException ex) {
            Logger.getLogger(MySqlFamCalEventDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
        
        
        
    }

    @Override
    public PerCalEvent getPerCalEvent(int eventID) {
        try {
            PreparedStatement pst;
            Connection conn = MySqlDaoFactory.createConnection();

            
            PerCalEvent event = new PerCalEvent();
            pst = conn.prepareStatement("SELECT * FROM perscalevents WHERE idperCalEvents=?");
            pst.setInt(1, eventID);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                
                event.setidPerCalEvent(rs.getInt("idperCalEvents"));
                event.setCreated_by(rs.getString("created_by"));
                event.setDirector_username(rs.getString("director_username"));
                event.setTitle(rs.getString("title"));
                Timestamp timestamp = rs.getTimestamp("start");
                event.setStart_date(timestamp);
                event.setEnd_date(rs.getTimestamp("end"));
                event.setCategory(rs.getString("category"));
                event.setLocation(rs.getString("location"));
               
                event.setDescription(rs.getString("description"));
                event.setRepeat_every(rs.getInt("repeat_frequency"));
                event.setRepeatTime(rs.getString("repeat_time"));
                event.setStartRepeatDate(rs.getTimestamp("starts_at"));
                event.setEndRepeatDate(rs.getTimestamp("expiration"));
                event.setNotificationTime(rs.getInt("notification_time"));
                event.setNotificationDate(rs.getString("notification_date"));
            }
            conn.close();
            return event;

        } catch (SQLException ex) {
            Logger.getLogger(MySqlFamCalEventDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean deletePerCalEvent(int eventID) {
         try {
            int validation = 0;

            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();
            pst = conn.prepareStatement("DELETE FROM perscalevents WHERE idperCalEvents=?");
            pst.setInt(1, eventID);

            validation = pst.executeUpdate();
            if (validation == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySqlFamCalEventDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

   
    

    @Override
    public boolean updatePerCalEvent(PerCalEvent event) {
         
        
        try {
            int validation = 0;

            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();
            pst = conn.prepareStatement("UPDATE perscalevents SET created_by=?, director_username=?, title=?,"
                    + " start=?, end=?, category=?, location=?,"
                    + "description=?, repeat_frequency=?, repeat_time=?, starts_at=?,"
                    + " expiration=?, notification_time=?, notification_date=? WHERE idperCalEvents=?");
            pst.setString(1, event.getCreated_by());
            pst.setString(2, event.getDirector_username());
            pst.setString(3, event.getTitle());

            java.util.Date dt = new java.util.Date();

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentTime = sdf.format(event.getStart_date());
            pst.setString(4, currentTime);
            currentTime = sdf.format(event.getEnd_date());
            pst.setString(5, currentTime);
            pst.setString(6, event.getCategory());
            pst.setString(7, event.getLocation());
           
            pst.setString(8, event.getDescription());

            if (event.getRepeatTime() == null) {
                pst.setNull(9, java.sql.Types.INTEGER);
                pst.setNull(10, java.sql.Types.VARCHAR);
                pst.setNull(11, java.sql.Types.DATE);
                pst.setNull(12, java.sql.Types.DATE);
                
            } else {
                pst.setInt(9, event.getRepeat_every());
                pst.setString(10, event.getRepeatTime());
                currentTime = sdf.format(event.getStartRepeatDate());
                pst.setString(11, currentTime);
                currentTime = sdf.format(event.getEndRepeatDate());
                pst.setString(12, currentTime);
            }
            pst.setInt(13, event.getNotificationTime());
            pst.setString(14, event.getNotificationDate());
            pst.setInt(15, event.getidPerCalEvent());

            validation = pst.executeUpdate();
            conn.close();
            if (validation == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySqlFamCalEventDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
        
        
        
        
        
        
    }
    
    
    
    
    @Override
    public List<PerCalEvent> getPerCalEventsByCateg(String username,String cat) {
        
        
        try {
            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();

            List<PerCalEvent> events = new ArrayList<PerCalEvent>();

            pst = conn.prepareStatement("SELECT * FROM perscalevents WHERE (created_by=? and category=?)");
            pst.setString(1, username);
            pst.setString(2, cat);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                PerCalEvent event = new PerCalEvent();
                
               
                event.setidPerCalEvent(rs.getInt("idperCalEvents"));
                event.setCreated_by(rs.getString("created_by"));
                event.setDirector_username(rs.getString("director_username"));
                event.setTitle(rs.getString("title"));
                Timestamp timestamp = rs.getTimestamp("start");
                event.setStart_date(timestamp);
                event.setEnd_date(rs.getTimestamp("end"));
                event.setCategory(rs.getString("category"));
                event.setLocation(rs.getString("location"));
                
                event.setDescription(rs.getString("description"));
                event.setRepeat_every(rs.getInt("repeat_frequency"));
                event.setRepeatTime(rs.getString("repeat_time"));
                event.setStartRepeatDate(rs.getTimestamp("starts_at"));
                event.setEndRepeatDate(rs.getTimestamp("expiration"));
                event.setNotificationTime(rs.getInt("notification_time"));
                event.setNotificationDate(rs.getString("notification_date"));

                events.add(event);
            }
            conn.close();
            return events;

        } catch (SQLException ex) {
            Logger.getLogger(MySqlFamCalEventDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
        
        
        
    }
    
    
    
    
    
    
}




