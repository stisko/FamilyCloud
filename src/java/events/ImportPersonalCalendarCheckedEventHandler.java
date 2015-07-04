/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.FamCalEventDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FamCalEvent;

/**
 *
 * @author GVra
 */
public class ImportPersonalCalendarCheckedEventHandler extends EventHandlerBase{
    String path;
    @Override
    protected String getURL() {
        return path;
    }
    
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        
        FamCalEventDao mfam=mySqlFactory.getFamCalEventDao();
        
        FamCalEvent famcalev=new FamCalEvent();
        String ev=request.getParameter("id");
        
        
        int evId=Integer.parseInt(ev);
        
        famcalev=mfam.getFamCalEvent(evId);
        
        request.setAttribute("chec_ev", famcalev);
         
        path="ImportPersonalChecked.jsp";
        
    
    
    }
    
    
}
