/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.FamCalEventDao;
import dao.UserDao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FamCalEvent;
import model.User;
import modelBO.UserBO;
import org.json.simple.JSONObject;

/**
 *
 * @author costi_000
 */
public class EditFamilyCalendarEventHandler extends EventHandlerBase{
    String path;
    
    
    @Override
    protected String getURL() {
        return path;
    }
    
    @Override
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        FamCalEventDao myEventDao= mySqlFactory.getFamCalEventDao();
        
        UserDao us=mySqlFactory.getUserDao();
        
        FamCalEvent event= new FamCalEvent();
        
        String id= request.getParameter("id");
        String tag= request.getParameter("tag");
        int intId= Integer.parseInt(id);
        
        event=myEventDao.getFamCalEvent(intId);
        User cur_user = (User) mySession.getAttribute("curr_user");
        request.setAttribute("cur_user", cur_user);
        request.setAttribute("event", event);
        
        if(tag.equals("delete")){
            path="DeleteFamCalEvent.jsp";
        }else{
            
            List<User> lf=new ArrayList<User>();
            
            lf=us.getFamilyMembersWithDirector(cur_user.getUsername());
            
            String temptag=",";
        
        
            String SelUsers;
        
            SelUsers = event.getParticipating_members();
        
            List<String> SelUserList = Arrays.asList(SelUsers.split("\\s*,\\s*"));
        
            
            
            JSONObject obj= new JSONObject();
            if(event.getVisibility().equals("Adults")){
                obj.put("adults", "checked");
                obj.put("all", "");
            }else{
                obj.put("adults", "");
                obj.put("all", "checked");
            }
            
            request.setAttribute("part_users", SelUserList);
            
            request.setAttribute("allusers", lf);
            
            request.setAttribute("visibility", obj);
            path="EditFamCalEvent.jsp";
        }
        
    }
    
}
