/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.FamCalEvent;

/**
 *
 * @author costi_000
 */
public interface FamCalEventDao {
    
    public boolean insertFamCalEvent(FamCalEvent event);
    
    public boolean updateFamCalEvent(FamCalEvent event);
    
    public boolean deleteFamCalEvent(int id);
    
    public List<FamCalEvent> getFamCalEvents(String username);
    
    public FamCalEvent getFamCalEvent(int eventID);
    
    public List<FamCalEvent> getFamCalEventsByUser(String username);

    public List<FamCalEvent> getFamCalEvents(String category, String username);
    
}
