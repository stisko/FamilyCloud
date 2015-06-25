/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.MealsPlanEvent;

/**
 *
 * @author costi_000
 */
public interface MealsPlanEventDao {
    
     public boolean insertMealsPlanEvent(MealsPlanEvent event);
    
    public boolean updateMealsPlanEvent(MealsPlanEvent event);
    
    public boolean deleteMealsPlanEvent(int id);
    
    public List<MealsPlanEvent> getMealsPlanEvents(String username);
    
    public MealsPlanEvent getMealsCalEvent(int eventID);

    public List<MealsPlanEvent> getMealsPlanEvents(String username, String type);
    
}
