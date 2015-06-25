

package dao;



import dao.impl.MySqlDaoFactory;



public abstract class DaoFactory {

    public static final int MYSQL = 1;

    public abstract UserDao getUserDao();
    
    public abstract MediaObjectDao getMediaObjectDao();
    
    public abstract ToDoListItemDao getToDoListItemDao();
    
    public abstract WallPostDao getWallPostDao();
    
    public abstract MessageDao getMessageDao();
    
    public abstract FamCalEventDao getFamCalEventDao();
    
    public abstract MealsPlanEventDao getMealsPlanEventDao();
    
    public abstract NotificationsDao getNotificationsDao();
    
    public abstract PerCalEventDao getPerCalEventDao();
    
    public abstract ShoppingListDao getShoppingListDao();
    
    public static DaoFactory getDaoFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL:
                return new MySqlDaoFactory();
            default:
                return null;
        }
    }
}