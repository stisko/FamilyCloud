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
import modelBO.PerCalEventBO;

/**
 *
 * @author GVra
 */
public class viewPersonalCalendarEventHandler extends EventHandlerBase {
    
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        
        PerCalEventDao myEventDao= mySqlFactory.getPerCalEventDao();
        
        PerCalEvent event= new PerCalEvent();
        PerCalEventBO eventBO= new PerCalEventBO();
        
        String id= request.getParameter("id");
        int intId= Integer.parseInt(id);
        
        event= myEventDao.getPerCalEvent(intId);
        eventBO=eventBO.toPerCalEventBO(event);
        
        request.setAttribute("eventPersonal", eventBO);
        
        path="ViewPerCalEvent.jsp";
        
        
    }
    
    
    
}
