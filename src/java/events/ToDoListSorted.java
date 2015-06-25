/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.ToDoListItemDao;
import dao.UserDao;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import modelBO.ToDoListItemBO;

/**
 *
 * @author GVra
 */
public class ToDoListSorted extends EventHandlerBase{

    String path;
    
    @Override
    protected String getURL() {
        
        return path;
    }
    
    
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ToDoListItemDao myItemDAO = mySqlFactory.getToDoListItemDao();
        UserDao myUserDAO = mySqlFactory.getUserDao();
        
        ToDoListItemBO itemBO= new ToDoListItemBO();
        List<ToDoListItemBO> SortedfamToDoList= new ArrayList<ToDoListItemBO>();
        
         User cur_user = (User) mySession.getAttribute("curr_user");
        User director = myUserDAO.getFamilyDirector(cur_user.getUsername());
        
        String keysort=request.getParameter("sortedTag");
        
        String keysort2="status";
        
        SortedfamToDoList=itemBO.family_toToDoListItemBO( myItemDAO.getSortedFamilyToDoList(director.getUsername(), keysort));
               
        
        request.setAttribute("SortedfamToDo", SortedfamToDoList);
        
        
        path="ToDoListSorted.jsp";
        
    }
    
}
