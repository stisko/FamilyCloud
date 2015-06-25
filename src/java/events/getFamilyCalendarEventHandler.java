/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.UserDao;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author costi_000
 */
public class getFamilyCalendarEventHandler extends EventHandlerBase{
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao myUserDao= mySqlFactory.getUserDao();
        
        User cur_user = (User) mySession.getAttribute("curr_user");
        List<User> fmembers= new ArrayList<User>();
        
        fmembers=myUserDao.getFamilyMembersWithDirector(cur_user.getUsername());
        
        request.setAttribute("fmembers", fmembers);
        
        path="FamilyCalendar.jsp";
    }
    
}
