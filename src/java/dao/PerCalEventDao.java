/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.PerCalEvent;

/**
 *
 * @author GVra
 */
public interface PerCalEventDao {
    
    public boolean insertPerCalEvent(PerCalEvent event);
    
    
    public List<PerCalEvent> getPerCalEvents(String username);
    
    
    public PerCalEvent getPerCalEvent(int eventID);
    
    public boolean deletePerCalEvent(int eventID);
    
    public boolean updatePerCalEvent(PerCalEvent event);
    
    
    public List<PerCalEvent> getPerCalEventsByCateg(String username,String cat);
    
}
