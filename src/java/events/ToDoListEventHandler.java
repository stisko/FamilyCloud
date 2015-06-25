/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.ToDoListItemDao;
import dao.UserDao;
import modelBO.ToDoListItemBO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import modelBO.UserBO;

/**
 *
 * @author costi_000
 */
public class ToDoListEventHandler extends EventHandlerBase{
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ToDoListItemDao myItemDAO = mySqlFactory.getToDoListItemDao();
        UserDao myUserDAO = mySqlFactory.getUserDao();
        ToDoListItemBO itemBO= new ToDoListItemBO();
        List<ToDoListItemBO> famToDoList= new ArrayList<ToDoListItemBO>();
        
        User cur_user = (User) mySession.getAttribute("curr_user");
        User director = myUserDAO.getFamilyDirector(cur_user.getUsername());
        
        famToDoList=itemBO.family_toToDoListItemBO(myItemDAO.getFamilyToDoList(director.getUsername()));
        
        
     
        UserBO usBo=new UserBO();
        
        List<UserBO> assignedListTODO=new ArrayList<UserBO>();
        
        assignedListTODO=usBo.family_toUserBo(myUserDAO.getFamilyMembers(cur_user.getUsername()));


        request.setAttribute("userlistTODO", assignedListTODO);
        
        //mySession.setAttribute("famToDo", famToDoList);
        
        request.setAttribute("famToDo", famToDoList);
        
        request.setAttribute("cur", cur_user);
        
        path="ToDoList.jsp";
    }
}
