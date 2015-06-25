/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.MealsPlanEventDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MealsPlanEvent;
import org.json.simple.JSONObject;

/**
 *
 * @author costi_000
 */
public class EditMealsPlanEventHandler extends EventHandlerBase{
    String path;

    @Override
    protected String getURL() {
        return path;
    }
    
    @Override
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        MealsPlanEventDao myEventDao= mySqlFactory.getMealsPlanEventDao();
        
        MealsPlanEvent event= new MealsPlanEvent();
        
        String id= request.getParameter("id");
        String tag= request.getParameter("tag");
        int intId= Integer.parseInt(id);
        
        event=myEventDao.getMealsCalEvent(intId);
        JSONObject obj= new JSONObject();
        
        
        if(event.getMealType().equals("Dinner")){
            obj.put("dinner", "checked");
            obj.put("lunch", "");
        }else if(event.getMealType().equals("Lunch")){
            obj.put("dinner", "");
            obj.put("lunch", "checked");
        }
        request.setAttribute("type", obj);
        request.setAttribute("mealsEvent", event);
        if(tag!=null && tag.equals("delete")){
            path="DeleteMealsPlanEvent.jsp";
        }else{
            path="EditMealsPlanEvent.jsp";
        }
        
    }
    
    
    
}
