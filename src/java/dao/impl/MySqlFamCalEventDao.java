/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.FamCalEventDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FamCalEvent;

/**
 *
 * @author costi_000
 */
class MySqlFamCalEventDao implements FamCalEventDao {

    @Override
    public boolean insertFamCalEvent(FamCalEvent event) {
        try {
            int validation = 0;

            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();
            pst = conn.prepareStatement("INSERT INTO famcalevents(created_by, director_username, title, start, end, category, location, visibility,"
                    + " participating_members, description, repeat_frequency, repeat_time, starts_at, expiration, notification_time, "
                    + "notification_date ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
            pst.setString(8, event.getVisibility());
            pst.setString(9, event.getParticipating_members());
            pst.setString(10, event.getDescription());

            if (event.getRepeatTime() == null) {
                pst.setNull(11, java.sql.Types.INTEGER);
                pst.setNull(12, java.sql.Types.VARCHAR);
                pst.setNull(13, java.sql.Types.DATE);
                pst.setNull(14, java.sql.Types.DATE);
                
            } else {
                pst.setInt(11, event.getRepeat_every());
                pst.setString(12, event.getRepeatTime());
                currentTime = sdf.format(event.getStartRepeatDate());
                pst.setString(13, currentTime);
                currentTime = sdf.format(event.getEndRepeatDate());
                pst.setString(14, currentTime);
            }
            pst.setInt(15, event.getNotificationTime());
            pst.setString(16, event.getNotificationDate());

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
    public boolean updateFamCalEvent(FamCalEvent event) {
        try {
            int validation = 0;

            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();
            pst = conn.prepareStatement("UPDATE famcalevents SET created_by=?, director_username=?, title=?,"
                    + " start=?, end=?, category=?, location=?, visibility=?,"
                    + " participating_members=?, description=?, repeat_frequency=?, repeat_time=?, starts_at=?,"
                    + " expiration=?, notification_time=?, notification_date=? WHERE idfamCalEvents=?");
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
            pst.setString(8, event.getVisibility());
            pst.setString(9, event.getParticipating_members());
            pst.setString(10, event.getDescription());

            if (event.getRepeatTime() == null) {
                pst.setNull(11, java.sql.Types.INTEGER);
                pst.setNull(12, java.sql.Types.VARCHAR);
                pst.setNull(13, java.sql.Types.DATE);
                pst.setNull(14, java.sql.Types.DATE);
                
            } else {
                pst.setInt(11, event.getRepeat_every());
                pst.setString(12, event.getRepeatTime());
                currentTime = sdf.format(event.getStartRepeatDate());
                pst.setString(13, currentTime);
                currentTime = sdf.format(event.getEndRepeatDate());
                pst.setString(14, currentTime);
            }
            pst.setInt(15, event.getNotificationTime());
            pst.setString(16, event.getNotificationDate());
            pst.setInt(17, event.getIdFamCalEvent());

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
    public boolean deleteFamCalEvent(int id) {
        try {
            int validation = 0;

            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();
            pst = conn.prepareStatement("DELETE FROM famcalevents WHERE idfamCalEvents=?");
            pst.setInt(1, id);

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
    public List<FamCalEvent> getFamCalEvents(String username) {
        try {
            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();

            List<FamCalEvent> events = new ArrayList<FamCalEvent>();

            pst = conn.prepareStatement("SELECT * FROM famcalevents WHERE director_username=?");
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                FamCalEvent event = new FamCalEvent();
                event.setIdFamCalEvent(rs.getInt("idfamCalEvents"));
                event.setCreated_by(rs.getString("created_by"));
                event.setDirector_username(rs.getString("director_username"));
                event.setTitle(rs.getString("title"));
                Timestamp timestamp = rs.getTimestamp("start");
                event.setStart_date(timestamp);
                event.setEnd_date(rs.getTimestamp("end"));
                event.setCategory(rs.getString("category"));
                event.setLocation(rs.getString("location"));
                event.setVisibility(rs.getString("visibility"));
                event.setParticipating_members(rs.getString("participating_members"));
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
    public FamCalEvent getFamCalEvent(int eventID) {
        try {
            PreparedStatement pst;
            Connection conn = MySqlDaoFactory.createConnection();

            
FamCalEvent event = new FamCalEvent();
            pst = conn.prepareStatement("SELECT * FROM famcalevents WHERE idfamCalEvents=?");
            pst.setInt(1, eventID);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                
                event.setIdFamCalEvent(rs.getInt("idfamCalEvents"));
                event.setCreated_by(rs.getString("created_by"));
                event.setDirector_username(rs.getString("director_username"));
                event.setTitle(rs.getString("title"));
                Timestamp timestamp = rs.getTimestamp("start");
                event.setStart_date(timestamp);
                event.setEnd_date(rs.getTimestamp("end"));
                event.setCategory(rs.getString("category"));
                event.setLocation(rs.getString("location"));
                event.setVisibility(rs.getString("visibility"));
                event.setParticipating_members(rs.getString("participating_members"));
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
    public List<FamCalEvent> getFamCalEventsByUser(String username) {
        try {
            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();

            List<FamCalEvent> events = new ArrayList<FamCalEvent>();

            pst = conn.prepareStatement("SELECT * FROM famcalevents WHERE (created_by=? or participating_members LIKE ?)");
            pst.setString(1, username);
            pst.setString(2, "%"+username+"%");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                FamCalEvent event = new FamCalEvent();
                event.setIdFamCalEvent(rs.getInt("idfamCalEvents"));
                event.setCreated_by(rs.getString("created_by"));
                event.setDirector_username(rs.getString("director_username"));
                event.setTitle(rs.getString("title"));
                Timestamp timestamp = rs.getTimestamp("start");
                event.setStart_date(timestamp);
                event.setEnd_date(rs.getTimestamp("end"));
                event.setCategory(rs.getString("category"));
                event.setLocation(rs.getString("location"));
                event.setVisibility(rs.getString("visibility"));
                event.setParticipating_members(rs.getString("participating_members"));
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
    public List<FamCalEvent> getFamCalEvents(String category, String username) {
        try {
            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();

            List<FamCalEvent> events = new ArrayList<FamCalEvent>();

            pst = conn.prepareStatement("SELECT * FROM famcalevents WHERE (director_username=? and category=?)");
            pst.setString(1, username);
            pst.setString(2, category);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                FamCalEvent event = new FamCalEvent();
                event.setIdFamCalEvent(rs.getInt("idfamCalEvents"));
                event.setCreated_by(rs.getString("created_by"));
                event.setDirector_username(rs.getString("director_username"));
                event.setTitle(rs.getString("title"));
                Timestamp timestamp = rs.getTimestamp("start");
                event.setStart_date(timestamp);
                event.setEnd_date(rs.getTimestamp("end"));
                event.setCategory(rs.getString("category"));
                event.setLocation(rs.getString("location"));
                event.setVisibility(rs.getString("visibility"));
                event.setParticipating_members(rs.getString("participating_members"));
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
