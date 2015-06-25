/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.ShoppingListDao;
import dao.UserDao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ShoppingListItem;
import model.User;
import modelBO.ShoppingListBO;
import modelBO.UserBO;
import org.json.simple.JSONObject;


/**
 *
 * @author GVra
 */
public class ShoppingListDetailsEventHandler extends EventHandlerBase{

    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
       // ToDoListItemDao myItemDAO = mySqlFactory.getToDoListItemDao();
        
        
        ShoppingListDao sldao =mySqlFactory.getShoppingListDao();
        
        JSONObject selected_item= new JSONObject();
        JSONObject obj= new JSONObject();
        
        String ITEMID =request.getParameter("itemid");
        String TAG=request.getParameter("tag");
        
        int item_id=Integer.parseInt(ITEMID);
        
        
        
        ShoppingListItem slitem = sldao.getshopItem(item_id);
        
        selected_item=slitem.getShoppingListItemJSON();
        
        
        UserDao myUserDAO=mySqlFactory.getUserDao();
        
        
        User cur_user = (User) mySession.getAttribute("curr_user");
        User director = myUserDAO.getFamilyDirector(cur_user.getUsername());
        
        UserBO usBo=new UserBO();
        
        List<UserBO> asignedlist =new ArrayList<UserBO>();
        
        asignedlist=usBo.family_toUserBo(myUserDAO.getFamilyMembers(cur_user.getUsername()));
        
      
        
        request.setAttribute("userslist", asignedlist);
        
        
        String temptag=",";
        
        
        String SelUsers;
        
        SelUsers = slitem.getAssigned_to();
        
        List<String> SelUserList = Arrays.asList(SelUsers.split("\\s*,\\s*"));
        
        request.setAttribute("seluserlist", SelUserList);
        
        
        if(slitem.getShopstatus().equals("Pending")){
            obj.put("pending", "checked");
            obj.put("completed", "");
        }else{
            obj.put("completed", "checked");
            obj.put("pending", "");
        }
        
        obj.put("selectedShopItem", selected_item);
       // obj.put("selectedUsers",shopl);
        
     //   request.setAttribute("listofusers", shopl);
        request.setAttribute("json", obj);
        
        if(TAG.equals("edit")){
            path="EditShoppingItem.jsp";
        }else if(TAG.equals("delete")){
            path="DeleteShoppingList.jsp";
        }
        
        
        
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
}
