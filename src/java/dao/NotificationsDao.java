/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Notifications;

/**
 *
 * @author costi_000
 */
public interface NotificationsDao {
    
    public boolean UpdateNotificationsEvent(Notifications notifications);
    
    public boolean MarkAsReadA(int id);
    
    public boolean MarkAsReadB(int id);

    public List<Notifications> getNotifications(String username);
    
    public Notifications getNotification(int id);
    
    public boolean insertNotification(Notifications notification);
    
    public int getUnreadNotifications(String username);
    
}
