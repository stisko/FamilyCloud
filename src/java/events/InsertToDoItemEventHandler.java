/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.ToDoListItemDao;
import dao.UserDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ToDoListItem;
import model.User;
import modelBO.ToDoListItemBO;

/**
 *
 * @author costi_000
 */
public class InsertToDoItemEventHandler extends EventHandlerBase{
    String path;
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response){
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ToDoListItemDao myItemDAO = mySqlFactory.getToDoListItemDao();
        UserDao myUserDAO = mySqlFactory.getUserDao();
        
        
        ToDoListItemBO itemBO= new ToDoListItemBO();
        
        User cur_user = (User) mySession.getAttribute("curr_user");
        
        User director = myUserDAO.getFamilyDirector(cur_user.getUsername());
        
        String director_username= director.getUsername();
        String title= request.getParameter("title");
        String status= request.getParameter("status");
        String assignedTo= request.getParameter("assignedTo");
        String dueDate=request.getParameter("dueDate");
        String createdBy= cur_user.getUsername();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dueDate_d = null;
        
        try {
            dueDate_d = sdf.parse(dueDate);
        } catch (ParseException ex) {
            Logger.getLogger(UpdateProfileEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        itemBO.setDirector_username(director_username);
        itemBO.setTitle(title);
        itemBO.setStatus(status);
        itemBO.setAssignedTo(assignedTo);
        itemBO.setCreatedBy(createdBy);
        itemBO.setDueDate(dueDate_d);
        
        
        ToDoListItem item= new ToDoListItem();
        
        item.setAssignedTo(itemBO.getAssignedTo());
        item.setDirector_username(itemBO.getDirector_username());
        item.setCreatedBy(itemBO.getCreatedBy());
        item.setDueDate(itemBO.getDueDate());
        item.setStatus(itemBO.getStatus());
        item.setTitle(itemBO.getTitle());
        
        if(myItemDAO.insertItem(item)){
            path="controller_servl?event=TODO";
        }else{
            path=null;
        }
        
        
        
    }
    
}
