/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.FamCalEventDao;
import dao.PerCalEventDao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FamCalEvent;
import model.PerCalEvent;

/**
 *
 * @author GVra
 */
public class ImportAndSaveCheckedEventsEventHandler extends EventHandlerBase{
    
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        
        
        String ids =request.getParameter("idpar");
        
        
        String temptag=",";
        
        
        
        
       
        
        List<String> eventsC = Arrays.asList(ids.split("\\s*,\\s*"));
        List<FamCalEvent> eventsfm = new ArrayList<FamCalEvent>();
        
        
        FamCalEventDao fmcl= mySqlFactory.getFamCalEventDao();
        PerCalEventDao percl=mySqlFactory.getPerCalEventDao();
        
        FamCalEvent fmev=new FamCalEvent();
        
        
        for(int i = 0; i < eventsC.size(); i++){
            
            int evId;
           evId=Integer.parseInt( eventsC.get(i));
           
           
               
               fmev=fmcl.getFamCalEvent(evId);
                  eventsfm.add(fmev); 
                  
                  
           
        
        }
        
        for(int k=0;k<eventsfm.size();k++){
                PerCalEvent event=new PerCalEvent();
                
                event.setCategory(eventsfm.get(k).getCategory());
                event.setCreated_by(eventsfm.get(k).getCreated_by());
                event.setDescription(eventsfm.get(k).getDescription());
                event.setDirector_username(eventsfm.get(k).getDirector_username());
                
                event.setEnd_date(eventsfm.get(k).getEnd_date());
                event.setEndRepeatDate(eventsfm.get(k).getEndRepeatDate());
                event.setLocation(eventsfm.get(k).getLocation());
                event.setNotificationDate(eventsfm.get(k).getNotificationDate());
                event.setNotificationTime(eventsfm.get(k).getNotificationTime());
                event.setRepeatTime(eventsfm.get(k).getRepeatTime());
                event.setStartRepeatDate(eventsfm.get(k).getStartRepeatDate());
                event.setStart_date(eventsfm.get(k).getStart_date());
                event.setTitle(eventsfm.get(k).getTitle());
                
                event.setRepeat_every(eventsfm.get(k).getRepeat_every());
                
                
                percl.insertPerCalEvent(event);
                
                
        
        }
        
        
       
        
        
        
        path="controller_servl?event=PERCAL";
        
        
    }
    
    
    
}
