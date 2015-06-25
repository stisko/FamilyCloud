/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.NotificationsDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Notifications;
import model.User;

/**
 *
 * @author costi_000
 */
public class getNotificationDetailsEventHandler extends EventHandlerBase {

    String path;

    @Override
    protected String getURL() {
        return path;
    }

    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        NotificationsDao myNotificationsDao = mySqlFactory.getNotificationsDao();

        User cur_user = (User) mySession.getAttribute("curr_user");

        String str_id = request.getParameter("id");
        int id = Integer.parseInt(str_id);
        Notifications notification = new Notifications();
        notification = myNotificationsDao.getNotification(id);
        
        if (notification.getIsReadB().equals("N")) {
            boolean success = myNotificationsDao.MarkAsReadB(id);
        }
        request.setAttribute("notification", notification);

        path = "controller_servl?event=NOTIFICATIONS&notification_type="+notification.getNotification_type();
    }

}
