/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.MealsPlanEventDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MealsPlanEvent;
import model.User;
import modelBO.MealsPlanEventBO;

/**
 *
 * @author costi_000
 */
public class viewMealsPlanEventHandler extends EventHandlerBase{
    String path;
    
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        MealsPlanEventDao myEventDao= mySqlFactory.getMealsPlanEventDao();
        MealsPlanEvent event= new MealsPlanEvent();
        MealsPlanEventBO eventBO= new MealsPlanEventBO();
        String id= request.getParameter("id");
        int intId= Integer.parseInt(id);
        event= myEventDao.getMealsCalEvent(intId);
        eventBO=event.toMealsPlanEventBO();
        User cur_user = (User) mySession.getAttribute("curr_user");
        request.setAttribute("cur_user", cur_user);
        request.setAttribute("event", eventBO);
        path="ViewMealsPlanEvent.jsp";
    }
    
    
    
}
