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
import org.json.simple.JSONObject;

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
        List<User> fmembers_exceptme= new ArrayList<User>();
        
        fmembers=myUserDao.getFamilyMembersWithDirector(cur_user.getUsername());
        
        request.setAttribute("fmembers", fmembers);
        
        
        JSONObject jsme = new JSONObject();

        if (request.getParameter("mtag") != null) {

            String temp = request.getParameter("mtag");

            if (temp.equals("insert")) {

                jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "Family Calendar Event Has Been Succefully Added!");

            } else if (temp.equals("update")) {

                jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "Family Calendar Event Has Been Succefully Updated!");

            } else if (temp.equals("delete")) {

                jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "Family Calendar Event Has Been Succefully Deleted!");

            }

            request.setAttribute("noti_message", jsme);

        }
        
        path="FamilyCalendar.jsp";
    }
    
}
