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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Notifications;
import model.User;

/**
 *
 * @author costi_000
 */
public class getNotificationsListEventHandler extends EventHandlerBase {

    String path;

    @Override
    protected String getURL() {
        return path;
    }

    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {

        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        NotificationsDao myNotificationsDao = mySqlFactory.getNotificationsDao();

        User cur_user = (User) mySession.getAttribute("curr_user");

        List<Notifications> notifications_list = new ArrayList<Notifications>();
        String tag= request.getParameter("tag");
        if(tag.equals("notifications")){
            notifications_list = myNotificationsDao.getNotifications(cur_user.getUsername());
        }else if(tag.equals("messages")){
            notifications_list= myNotificationsDao.getMessageNotifications(cur_user.getUsername());
        }
        
        if(notifications_list.isEmpty()){
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.print("<div class='alert alert-info'><span class='glyphicon glyphicon-info-sign'></span>  You have no notifications yet</div>");
            } catch (IOException ex) {
                Logger.getLogger(getNotificationsListEventHandler.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                out.close();
            }
        }else{
            request.setAttribute("notification_list", notifications_list);
            path = "Notifications.jsp";
        }
        

    }

}
