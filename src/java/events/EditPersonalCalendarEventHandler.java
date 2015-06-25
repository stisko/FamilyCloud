/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.PerCalEventDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.PerCalEvent;

/**
 *
 * @author GVra
 */
public class EditPersonalCalendarEventHandler extends EventHandlerBase{
    
    String path;
    
    
    @Override
    protected String getURL() {
       return path;
    }
    
    
     public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        
        
        
        PerCalEventDao myEventDao= mySqlFactory.getPerCalEventDao();
        
        PerCalEvent event= new PerCalEvent();
        
        String id= request.getParameter("id");
        String tag= request.getParameter("tag");
        int intId= Integer.parseInt(id);
        
        event=myEventDao.getPerCalEvent(intId);
        
        request.setAttribute("handleevent", event);
        
        if(tag.equals("delete")){
            path="DeletePerCalEvent.jsp";
        }else{
            path="EditPerCalEvent.jsp";
        }
        
        
        
     }
    
    
    
    
}
