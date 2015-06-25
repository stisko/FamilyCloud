/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.NotificationsDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author costi_000
 */
public class getUnreadNotificationsEventHandler extends EventHandlerBase{
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = null;
            DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
            NotificationsDao myNotificationsDao= mySqlFactory.getNotificationsDao();
            
            User cur_user = (User) mySession.getAttribute("curr_user");
            
            int count= myNotificationsDao.getUnreadNotifications(cur_user.getUsername());
            
            out = response.getWriter();
            
            out.print(count);
        } catch (IOException ex) {
            Logger.getLogger(getUnreadNotificationsEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
