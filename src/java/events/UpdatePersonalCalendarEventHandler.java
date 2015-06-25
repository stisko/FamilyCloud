/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.PerCalEventDao;
import dao.UserDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.PerCalEvent;
import model.User;
import modelBO.PerCalEventBO;

/**
 *
 * @author GVra
 */
public class UpdatePersonalCalendarEventHandler extends EventHandlerBase{
    
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        try {
            boolean success;
            DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
            PerCalEventDao myPerCalEvent = mySqlFactory.getPerCalEventDao();
            UserDao myUserDAO = mySqlFactory.getUserDao();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;

            PerCalEventBO eventBO = new PerCalEventBO();
            PerCalEvent event = new PerCalEvent();
            User cur_user = (User) mySession.getAttribute("curr_user");
            User director = myUserDAO.getFamilyDirector(cur_user.getUsername());

            String id= request.getParameter("id");
            String title = request.getParameter("title");
            String start = request.getParameter("start");
            String end = request.getParameter("end");
            String category = request.getParameter("category");
            String location = request.getParameter("location");
            
            String description = request.getParameter("description");
            String repeat_time = request.getParameter("repeat-time");
            String repeat_every = request.getParameter("repeat_every");
            String starts_at = request.getParameter("starts_at");
            String expiration = request.getParameter("expiration_date");
            String notification_time = request.getParameter("notification_time");
            String notification_date = request.getParameter("notification_date");
            String status = request.getParameter("status");

            event.setidPerCalEvent(Integer.parseInt(id));
            event.setCreated_by(cur_user.getUsername());
            event.setDirector_username(director.getUsername());
            event.setTitle(title);
            event.setStart_date(sdf.parse(start));
            event.setEnd_date(sdf.parse(end));
            event.setCategory(category);
            event.setLocation(location);
            
            event.setDescription(description);
            if (status.equals("true")) {
                event.setRepeatTime(repeat_time);
                event.setRepeat_every(Integer.parseInt(repeat_every));
                event.setStartRepeatDate(sdf.parse(starts_at));
                event.setEndRepeatDate(sdf.parse(expiration));
            }
            event.setNotificationTime(Integer.parseInt(notification_time));
            event.setNotificationDate(notification_date);

            eventBO.toPerCalEventBO(event);

            success = myPerCalEvent.updatePerCalEvent(event);
            
            path="controller_servl?event=PERCAL";
        } catch (ParseException ex) {
            Logger.getLogger(UpdateFamilyCalendarEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
}
