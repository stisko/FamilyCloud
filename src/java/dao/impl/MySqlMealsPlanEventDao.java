/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.MealsPlanEventDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FamCalEvent;
import model.MealsPlanEvent;

/**
 *
 * @author costi_000
 */
public class MySqlMealsPlanEventDao implements MealsPlanEventDao {

    @Override
    public boolean insertMealsPlanEvent(MealsPlanEvent event) {
        try {
            int validation = 0;

            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();
            pst = conn.prepareStatement("INSERT INTO mealsplan(created_by, director_username, title, start_date, meal_type, location,"
                    + "description, repeat_frequency, repeat_time, starts_at, expiration, notification_time, "
                    + "notification_date ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, event.getCreated_by());
            pst.setString(2, event.getDirector_username());
            pst.setString(3, event.getTitle());

            

            pst.setDate(4, new java.sql.Date(event.getStart_date().getTime()));
            pst.setString(5, event.getMealType());
            pst.setString(6, event.getLocation());
            pst.setString(7, event.getDescription());

            if (event.getRepeatTime() == null) {
                pst.setNull(8, java.sql.Types.INTEGER);
                pst.setNull(9, java.sql.Types.VARCHAR);
                pst.setNull(10, java.sql.Types.DATE);
                pst.setNull(11, java.sql.Types.DATE);

            } else {
                pst.setInt(8, event.getRepeat_every());
                pst.setString(9, event.getRepeatTime());
                pst.setDate(10, new java.sql.Date(event.getStartRepeatDate().getTime()));
                pst.setDate(11, new java.sql.Date(event.getEndRepeatDate().getTime()));
            }
            pst.setInt(12, event.getNotificationTime());
            pst.setString(13, event.getNotificationDate());

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
    public boolean updateMealsPlanEvent(MealsPlanEvent event) {
        try {
            int validation;

            PreparedStatement pst;
            Connection conn = MySqlDaoFactory.createConnection();
            pst = conn.prepareStatement("UPDATE mealsplan SET created_by=?, director_username=?, title=?,"
                    + " start_date=?, meal_type=?, location=?,"
                    + " description=?, repeat_frequency=?, repeat_time=?, starts_at=?,"
                    + " expiration=?, notification_time=?, notification_date=? WHERE idmealsplan=?");
            pst.setString(1, event.getCreated_by());
            pst.setString(2, event.getDirector_username());
            pst.setString(3, event.getTitle());

            java.util.Date dt = new java.util.Date();

            pst.setDate(4, new java.sql.Date(event.getStart_date().getTime()));
            pst.setString(5, event.getMealType());
            pst.setString(6, event.getLocation());
            pst.setString(7, event.getDescription());

            if (event.getRepeatTime() == null) {
                pst.setNull(8, java.sql.Types.INTEGER);
                pst.setNull(9, java.sql.Types.VARCHAR);
                pst.setNull(10, java.sql.Types.DATE);
                pst.setNull(11, java.sql.Types.DATE);

            } else {
                pst.setInt(8, event.getRepeat_every());
                pst.setString(9, event.getRepeatTime());
                pst.setDate(10, new java.sql.Date(event.getStartRepeatDate().getTime()));
                pst.setDate(11, new java.sql.Date(event.getEndRepeatDate().getTime()));
            }
            pst.setInt(12, event.getNotificationTime());
            pst.setString(13, event.getNotificationDate());
            pst.setInt(14, event.getIdMealsPlanEvent());

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
    public boolean deleteMealsPlanEvent(int id) {
        try {
            int validation = 0;

            PreparedStatement pst;
            Connection conn = MySqlDaoFactory.createConnection();
            pst = conn.prepareStatement("DELETE FROM mealsplan WHERE idmealsplan=?");
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
    public List<MealsPlanEvent> getMealsPlanEvents(String username) {
        try {
            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();

            List<MealsPlanEvent> events = new ArrayList<MealsPlanEvent>();

            pst = conn.prepareStatement("SELECT * FROM mealsplan WHERE director_username=?");
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                MealsPlanEvent event = new MealsPlanEvent();
                event.setIdMealsPlanEvent(rs.getInt("idmealsplan"));
                event.setCreated_by(rs.getString("created_by"));
                event.setDirector_username(rs.getString("director_username"));
                event.setTitle(rs.getString("title"));
                event.setStart_date(new java.sql.Date(rs.getDate("start_date").getTime()));
                event.setMealType(rs.getString("meal_type"));
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
    public MealsPlanEvent getMealsCalEvent(int eventID) {
        try {
            PreparedStatement pst;
            Connection conn = MySqlDaoFactory.createConnection();

            MealsPlanEvent event = new MealsPlanEvent();
            pst = conn.prepareStatement("SELECT * FROM mealsplan WHERE idmealsplan=?");
            pst.setInt(1, eventID);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                event.setIdMealsPlanEvent(rs.getInt("idmealsplan"));
                event.setCreated_by(rs.getString("created_by"));
                event.setDirector_username(rs.getString("director_username"));
                event.setTitle(rs.getString("title"));
                event.setStart_date(new java.sql.Date(rs.getDate("start_date").getTime()));
                event.setMealType(rs.getString("meal_type"));
                event.setLocation(rs.getString("location"));
                event.setDescription(rs.getString("description"));
                event.setRepeat_every(rs.getInt("repeat_frequency"));
                event.setRepeatTime(rs.getString("repeat_time"));
                if (rs.getDate("starts_at") != null) {
                    event.setStartRepeatDate(new java.sql.Date(rs.getDate("starts_at").getTime()));
                    event.setEndRepeatDate(new java.sql.Date(rs.getDate("expiration").getTime()));
                }
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
    public List<MealsPlanEvent> getMealsPlanEvents(String username, String type) {
        try {
            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();

            List<MealsPlanEvent> events = new ArrayList<MealsPlanEvent>();

            pst = conn.prepareStatement("SELECT * FROM mealsplan WHERE (director_username=? and meal_type=?)");
            pst.setString(1, username);
            pst.setString(2, type);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                MealsPlanEvent event = new MealsPlanEvent();
                event.setIdMealsPlanEvent(rs.getInt("idmealsplan"));
                event.setCreated_by(rs.getString("created_by"));
                event.setDirector_username(rs.getString("director_username"));
                event.setTitle(rs.getString("title"));
                event.setStart_date(new java.sql.Date(rs.getDate("start_date").getTime()));
                event.setMealType(rs.getString("meal_type"));
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
