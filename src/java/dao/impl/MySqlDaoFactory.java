package dao.impl;

import dao.DaoFactory;
import dao.FamCalEventDao;
import dao.MealsPlanEventDao;
import dao.MediaObjectDao;
import dao.MessageDao;
import dao.NotificationsDao;
import dao.PerCalEventDao;
import dao.ShoppingListDao;
import dao.ToDoListItemDao;
import dao.UserDao;
import dao.WallPostDao;
import java.sql.*;

public class MySqlDaoFactory extends DaoFactory {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/famdb?useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8";

    public static Connection createConnection() {
        Connection conn = null;

        try {
            Class.forName(DRIVER);

            conn = DriverManager.getConnection(DBURL, "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public UserDao getUserDao() {
        return new MySqlUserDao();
    }

    @Override
    public ToDoListItemDao getToDoListItemDao() {
        return new MySqlToDoListItemDao();
    }

    @Override
    public MediaObjectDao getMediaObjectDao() {
        return new MySqlMediaObjectDao();
    }

    public WallPostDao getWallPostDao() {
        return new MySqlWallPostDao();
    }

    @Override
    public MessageDao getMessageDao() {
        return new MySqlMessageDao();
    }

    @Override
    public FamCalEventDao getFamCalEventDao() {
        return new MySqlFamCalEventDao();
    }

    public MealsPlanEventDao getMealsPlanEventDao() {
        return new MySqlMealsPlanEventDao();
    }

    @Override
    public NotificationsDao getNotificationsDao() {
        return new MySqlNotificationsDao();
    }

    @Override
    public PerCalEventDao getPerCalEventDao() {
        return new MySqlPerCalEventDao();
    }

    @Override
    public ShoppingListDao getShoppingListDao() {
        return new MySqlShoppingListDao();
    }

}
