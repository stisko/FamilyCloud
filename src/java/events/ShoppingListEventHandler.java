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
public class ShoppingListEventHandler extends EventHandlerBase{
    
    
    private String path;
    
    
    protected String getURL(){
    
    
    
        return path;
    
    }
    
    
    @Override
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response){
    
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao myUserDAO = mySqlFactory.getUserDao();
        
        ShoppingListBO lshl=new ShoppingListBO();
        
        User cur_user = (User) mySession.getAttribute("curr_user");
        User director = myUserDAO.getFamilyDirector(cur_user.getUsername());
    
        List<ShoppingListBO> famshoplist= new ArrayList<ShoppingListBO>();
        
        
        ShoppingListDao ishpDao=mySqlFactory.getShoppingListDao();
        
        
        famshoplist=lshl.family_toShoppingListBO(ishpDao.getFamilyShoppingList(director.getUsername()));
        
        JSONObject jsme = new JSONObject();

        if (request.getParameter("mtag") != null) {

            String temp = request.getParameter("mtag");

            if (temp.equals("insert")) {

                jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "<span class=\"glyphicon glyphicon-ok-circle\"></span> ShoppinList Item Has Succefully Added!");

            } else if (temp.equals("update")) {

                jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "<span class=\"glyphicon glyphicon-ok-circle\"></span> ShoppinList Item Has Succefully Updated!");

            } else if (temp.equals("delete")) {

                jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "<span class=\"glyphicon glyphicon-ok-circle\"></span> ShoppinList Item Has Succefully Deleted!");

            }

            request.setAttribute("noti_message", jsme);

        }
        
        
        UserBO usBo=new UserBO();
        
        List<UserBO> asignedlist =new ArrayList<UserBO>();
        
        asignedlist=usBo.family_toUserBo(myUserDAO.getFamilyMembersWithDirector(cur_user.getUsername()));
        
      
        
        request.setAttribute("userslist", asignedlist);
        
        
      //  System.out.println("EEEEEEEEEE"+ishpDao.getFamilyShoppingList(director.getUsername()).get(1).getShoptitle());
        
        
        request.setAttribute("familyShopList", famshoplist);
        
        
        request.setAttribute("cuRRENTuserDr", cur_user);
       
        
        
      //  request.setAttribute("visib", hidd);
        
        if(famshoplist.isEmpty()){
            request.setAttribute("emptya", 1);
        }else{
            request.setAttribute("emptya", 0);
        }
        
        
        
         path="ShoppingList.jsp";
    
    }
    
    
    
    
}
