/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.NotificationsDao;
import java.util.ArrayList;
import java.util.List;
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
        notifications_list = myNotificationsDao.getNotifications(cur_user.getUsername());

        request.setAttribute("notification_list", notifications_list);

        
            path = "Notifications.jsp";
        
    }

}
