/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.ToDoListItemDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ToDoListItem;

/**
 *
 * @author costi_000
 */
public class UpdateToDoListItemEventHandler extends EventHandlerBase{
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    @Override
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ToDoListItemDao myItemDAO = mySqlFactory.getToDoListItemDao();
        boolean success;
        int itemID;
        String test=request.getParameter("itemID");
        
        itemID= Integer.parseInt(test);
        String title= request.getParameter("title");
        String dueDate= request.getParameter("dueDate");
        String status= request.getParameter("status");
        String assignedTo= request.getParameter("assignedTo");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Date dueDate_d = null;
        
        try {
            dueDate_d = sdf.parse(dueDate);
        } catch (ParseException ex) {
            Logger.getLogger(UpdateToDoListItemEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        ToDoListItem item = myItemDAO.getItem(itemID);
        item.setTitle(title);
        item.setStatus(status);
        item.setDueDate(dueDate_d);
        item.setAssignedTo(assignedTo);
        if(status.equals("Completed")){
            Date completedDate= new Date();
            item.setCompletedDate(completedDate);
        }else{
            
        }
        
        
        
        
        success= myItemDAO.updateItem(item);
        
        if(success){
            path="controller_servl?event=TODO&mtag=update";
        }
        
        
        
        
        
    }
    
}
