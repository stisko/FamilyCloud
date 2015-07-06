/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DaoFactory;
import dao.FamCalEventDao;
import dao.NotificationsDao;
import dao.UserDao;
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
import model.Notifications;
import model.User;

/**
 *
 * @author costi_000
 */
public class MySqlNotificationsDao implements NotificationsDao {

    @Override
    public boolean UpdateNotificationsEvent(Notifications notifications) {
        try {
            int validation;

            PreparedStatement pst;
            Connection conn = MySqlDaoFactory.createConnection();
            pst = conn.prepareStatement("UPDATE notifications SET usernameA=?, usernameB=?, notification_type=?, message=?, date_created=?, referred_id=?, isReadA=?,isReadB=?"
                    + "WHERE idnotifications=?");
            pst.setString(1, notifications.getUsernameA());
            pst.setString(2, notifications.getUsernameB());
            pst.setString(3, notifications.getNotification_type());
            pst.setString(4, notifications.getMessage());

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(notifications.getDate_created());
            pst.setString(5, currentTime);

            pst.setInt(6, notifications.getReferred_id());
            pst.setString(7, notifications.getIsReadA());
            pst.setString(8, notifications.getIsReadB());
            pst.setInt(9, notifications.getIdnotifications());

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
    public boolean MarkAsReadA(int id) {
        try {
            int validation;

            PreparedStatement pst;
            Connection conn = MySqlDaoFactory.createConnection();
            pst = conn.prepareStatement("UPDATE notifications SET isReadA='Y'"
                    + "WHERE idnotifications=?");
            pst.setInt(1, id);

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
    public boolean MarkAsReadB(int id) {
        try {
            int validation;

            PreparedStatement pst;
            Connection conn = MySqlDaoFactory.createConnection();
            pst = conn.prepareStatement("UPDATE notifications SET isReadB='Y'"
                    + "WHERE idnotifications=?");
            pst.setInt(1, id);

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
    public List<Notifications> getNotifications(String username) {
        try {
            PreparedStatement pst;
            Connection conn = MySqlDaoFactory.createConnection();

            List<Notifications> notifications_list = new ArrayList<Notifications>();

            pst = conn.prepareStatement("SELECT * FROM notifications WHERE (usernameB=? and notification_type!='messages') order by date_created DESC");
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Notifications notifications = new Notifications();

                notifications.setIdnotifications(rs.getInt("idnotifications"));
                notifications.setUsernameA(rs.getString("usernameA"));
                notifications.setUsernameB(rs.getString("usernameB"));
                Timestamp timestamp = rs.getTimestamp("date_created");
                notifications.setDate_created(timestamp);
                notifications.setMessage(rs.getString("message"));
                notifications.setNotification_type(rs.getString("notification_type"));
                notifications.setIsReadA(rs.getString("isreadA"));
                notifications.setIsReadB(rs.getString("isReadB"));
                notifications.setReferred_id(rs.getInt("referred_id"));

                if (notifications.getNotification_type().equals("famcalevents")) {
                    DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
                    UserDao myUserDao = mySqlFactory.getUserDao();

                    User myUser = new User();
                    String event_title = notifications.getMessage();
                    myUser = myUserDao.getUser(notifications.getUsernameA());

                    notifications.setMessage("<b>"+myUser.getLastName() + " " + myUser.getFirstName()+"</b>" + " added a Family Calendar event with title: " + event_title);
                } else if (notifications.getNotification_type().equals("famcalevents_update")) {
                    DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
                    UserDao myUserDao = mySqlFactory.getUserDao();

                    User myUser = new User();

                    myUser = myUserDao.getUser(notifications.getUsernameA());

                    String event_title = notifications.getMessage();
                    notifications.setMessage("<b>"+myUser.getLastName() + " " + myUser.getFirstName()+"</b>"+ " updated a Family Calendar event with title: " + event_title);

                } else if (notifications.getNotification_type().equals("famcalevents_delete")) {
                    DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
                    UserDao myUserDao = mySqlFactory.getUserDao();

                    User myUser = new User();

                    myUser = myUserDao.getUser(notifications.getUsernameA());

                    String event_title = notifications.getMessage();
                    notifications.setMessage("<b>"+myUser.getLastName() + " " + myUser.getFirstName()+"</b>" + " deleted a Family Calendar event with title: " + event_title);

                } else if (notifications.getNotification_type().equals("shopping_list")) {
                    DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
                    UserDao myUserDao = mySqlFactory.getUserDao();

                    User myUser = new User();

                    myUser = myUserDao.getUser(notifications.getUsernameA());

                    String shop_title = notifications.getMessage();
                    notifications.setMessage("<b>"+myUser.getLastName() + " " + myUser.getFirstName()+"</b>" + " inserted a Shopping List event with title: " + shop_title);
                } else if (notifications.getNotification_type().equals("shopping_list_update")) {
                    DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
                    UserDao myUserDao = mySqlFactory.getUserDao();

                    User myUser = new User();

                    myUser = myUserDao.getUser(notifications.getUsernameA());

                    String shop_title = notifications.getMessage();
                    notifications.setMessage("<b>"+myUser.getLastName() + " " + myUser.getFirstName()+"</b>" + " updated a Shopping List event with title: " + shop_title);
                } else if (notifications.getNotification_type().equals("shopping_list_delete")) {
                    DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
                    UserDao myUserDao = mySqlFactory.getUserDao();

                    User myUser = new User();

                    myUser = myUserDao.getUser(notifications.getUsernameA());

                    String shop_title = notifications.getMessage();
                    notifications.setMessage("<b>"+myUser.getLastName() + " " + myUser.getFirstName()+"</b>" + " deleted a Shopping List event with title: " + shop_title);
                } else if (notifications.getNotification_type().equals("todo_list")) {
                    DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
                    UserDao myUserDao = mySqlFactory.getUserDao();

                    User myUser = new User();

                    myUser = myUserDao.getUser(notifications.getUsernameA());

                    String todo_title = notifications.getMessage();
                    notifications.setMessage("<b>"+myUser.getLastName() + " " + myUser.getFirstName()+"</b>" + " created a ToDo List event with title: " + todo_title);
                } else if (notifications.getNotification_type().equals("todo_list_update")) {
                    DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
                    UserDao myUserDao = mySqlFactory.getUserDao();

                    User myUser = new User();

                    myUser = myUserDao.getUser(notifications.getUsernameA());

                    String todo_title = notifications.getMessage();
                    notifications.setMessage("<b>"+myUser.getLastName() + " " + myUser.getFirstName()+"</b>" + " updated a ToDo List event with title: " + todo_title);
                } else if (notifications.getNotification_type().equals("todo_list_delete")) {
                    DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
                    UserDao myUserDao = mySqlFactory.getUserDao();

                    User myUser = new User();

                    myUser = myUserDao.getUser(notifications.getUsernameA());

                    String todo_title = notifications.getMessage();
                    notifications.setMessage("<b>"+myUser.getLastName() + " " + myUser.getFirstName()+"</b>" + " deleted a ToDo List event with title: " + todo_title);
                } else if (notifications.getNotification_type().equals("wall_post")) {

                    DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
                    UserDao myUserDao = mySqlFactory.getUserDao();

                    User myUser = new User();

                    myUser = myUserDao.getUser(notifications.getUsernameA());

                    notifications.setMessage("<b>"+myUser.getLastName() + " " + myUser.getFirstName()+"</b>" + " posted to Family Wall");
                }

                notifications_list.add(notifications);
            }
            conn.close();
            return notifications_list;

        } catch (SQLException ex) {
            Logger.getLogger(MySqlFamCalEventDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Notifications> getMessageNotifications(String username) {
        try {
            PreparedStatement pst;
            Connection conn = MySqlDaoFactory.createConnection();

            List<Notifications> notifications_list = new ArrayList<Notifications>();

            pst = conn.prepareStatement("SELECT * FROM notifications WHERE (usernameB=? and notification_type='messages') order by date_created");
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Notifications notifications = new Notifications();

                notifications.setIdnotifications(rs.getInt("idnotifications"));
                notifications.setUsernameA(rs.getString("usernameA"));
                notifications.setUsernameB(rs.getString("usernameB"));
                Timestamp timestamp = rs.getTimestamp("date_created");
                notifications.setDate_created(timestamp);
                notifications.setMessage(rs.getString("message"));
                notifications.setNotification_type(rs.getString("notification_type"));
                notifications.setIsReadA(rs.getString("isreadA"));
                notifications.setIsReadB(rs.getString("isReadB"));
                notifications.setReferred_id(rs.getInt("referred_id"));

                DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
                UserDao myUserDao = mySqlFactory.getUserDao();

                User myUser = new User();
                myUser = myUserDao.getUser(notifications.getUsernameA());
                notifications.setMessage("User " + myUser.getLastName() + " " + myUser.getFirstName() + " messaged you.");

                notifications_list.add(notifications);
            }
            conn.close();
            return notifications_list;

        } catch (SQLException ex) {
            Logger.getLogger(MySqlFamCalEventDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Notifications getNotification(int id) {
        try {
            PreparedStatement pst;
            Connection conn = MySqlDaoFactory.createConnection();

            pst = conn.prepareStatement("SELECT * FROM notifications WHERE idnotifications=?");
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            Notifications notifications = new Notifications();
            while (rs.next()) {

                notifications.setIdnotifications(rs.getInt("idnotifications"));
                notifications.setUsernameA(rs.getString("usernameA"));
                notifications.setUsernameB(rs.getString("usernameB"));
                Timestamp timestamp = rs.getTimestamp("date_created");
                notifications.setDate_created(timestamp);
                notifications.setMessage(rs.getString("message"));
                notifications.setNotification_type(rs.getString("notification_type"));
                notifications.setIsReadA(rs.getString("isreadA"));
                notifications.setIsReadB(rs.getString("isReadB"));

            }
            conn.close();
            return notifications;

        } catch (SQLException ex) {
            Logger.getLogger(MySqlFamCalEventDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean insertNotification(Notifications notification) {
        try {
            int validation = 0;

            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();
            pst = conn.prepareStatement("INSERT INTO notifications(usernameA, usernameB, notification_type, message, date_created, reffered_id, isReadA, isReadB ) VALUES(?,?,?,?,?,?,?,?)");
            pst.setString(1, notification.getUsernameA());
            pst.setString(2, notification.getUsernameB());
            pst.setString(3, notification.getNotification_type());
            pst.setString(4, notification.getMessage());

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(notification.getDate_created());
            pst.setString(5, currentTime);

            pst.setInt(6, notification.getReferred_id());
            pst.setString(7, notification.getIsReadA());
            pst.setString(8, notification.getIsReadB());

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
    public int getUnreadNotifications(String username) {
        try {
            PreparedStatement pst;
            Connection conn = MySqlDaoFactory.createConnection();

            int count = 0;
            pst = conn.prepareStatement("SELECT COUNT(*) FROM notifications WHERE (usernameB=? and isReadB='N' and notification_type!='messages')");
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
            }
            conn.close();
            return count;

        } catch (SQLException ex) {
            Logger.getLogger(MySqlFamCalEventDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    @Override
    public int getUnreadMessageNotifications(String username) {
        try {
            PreparedStatement pst;
            Connection conn = MySqlDaoFactory.createConnection();

            int count = 0;
            pst = conn.prepareStatement("SELECT COUNT(*) FROM notifications WHERE (usernameB=? and isReadB='N' and notification_type='messages')");
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
            }
            conn.close();
            return count;

        } catch (SQLException ex) {
            Logger.getLogger(MySqlFamCalEventDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
