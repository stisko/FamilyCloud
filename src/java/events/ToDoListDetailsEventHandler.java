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
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ShoppingListItem;
import model.ToDoListItem;
import model.User;
import modelBO.ToDoListItemBO;
import modelBO.UserBO;
import org.json.simple.JSONObject;

/**
 *
 * @author costi_000
 */
public class ToDoListDetailsEventHandler extends EventHandlerBase{
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    @Override
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ToDoListItemDao myItemDAO = mySqlFactory.getToDoListItemDao();
        UserDao myUserDAO=mySqlFactory.getUserDao();
        
        JSONObject selected_item= new JSONObject();
        JSONObject obj= new JSONObject();
        
        int itemID;
        String test=request.getParameter("itemID");
        
        itemID= Integer.parseInt(test);
        String tag=request.getParameter("tag");
        ToDoListItem selected_item_j= myItemDAO.getItem(itemID);
        selected_item= selected_item_j.getToDoListItemJSON();
        
        if(selected_item_j.getStatus().equals("Pending")){
            obj.put("pending", "checked");
            obj.put("completed", "");
        }else{
            obj.put("completed", "checked");
            obj.put("pending", "");
        }
        
        obj.put("item", selected_item);
        request.setAttribute("json", obj);
        
         User cur_user = (User) mySession.getAttribute("curr_user");
        User director = myUserDAO.getFamilyDirector(cur_user.getUsername());
        
        
        
        UserBO usBo=new UserBO();
        
        List<UserBO> asignedlist =new ArrayList<UserBO>();
        
        asignedlist=usBo.family_toUserBo(myUserDAO.getFamilyMembersWithDirector(cur_user.getUsername()));
        
        
        request.setAttribute("userslist", asignedlist);
        
        
        String temptag=",";
        
       
        ToDoListItem slitem=myItemDAO.getItem(itemID);
        String SelUsers;
       SelUsers = slitem.getAssignedTo();
       //  slitem.getAssigned_to();
        
        List<String> SelUserList = Arrays.asList(SelUsers.split("\\s*,\\s*"));
        
        request.setAttribute("seluserlist", SelUserList);
        
      //  asignedlist=usBo.family_toUserBo(myUserDAO.getFamilyMembers(cur_user.getUsername()));
        
        

        if(tag.equals("edit")){
            path="EditToDoItem.jsp";
        }else if(tag.equals("delete")){
            path="DeleteToDoList.jsp";
        }
        
        
    }
}
