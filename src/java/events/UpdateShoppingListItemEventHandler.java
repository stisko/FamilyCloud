/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.ShoppingListDao;
import dao.UserDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ShoppingListItem;
import model.User;
import modelBO.ShoppingListBO;

/**
 *
 * @author GVra
 */
public class UpdateShoppingListItemEventHandler extends EventHandlerBase{

    String path;
     boolean success;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    
     public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response){
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ShoppingListDao myshopDAO =mySqlFactory.getShoppingListDao();
        
        UserDao myUserDAO = mySqlFactory.getUserDao();
        
        User cur_user = (User) mySession.getAttribute("curr_user");
        
        User director = myUserDAO.getFamilyDirector(cur_user.getUsername());
        
        
        ShoppingListBO splBO=new ShoppingListBO();
        
        String itemid=request.getParameter("itemid");
        
        int iitemid=Integer.parseInt(itemid);
        
        
        String title= request.getParameter("title");
        String status= request.getParameter("status");
        String assignedTo= request.getParameter("assignedto");
        String quantity= request.getParameter("quantity");
        String price= request.getParameter("price");
        String createdby =cur_user.getUsername();
        
        int pprice=Integer.parseInt(price);
        int qquantity=Integer.parseInt(quantity);
        splBO.setAssigned_to(assignedTo);
        splBO.setCreated_by(createdby);
        splBO.setDirector_username(director.getUsername());
        splBO.setPrice(pprice);
        splBO.setQuantity(qquantity);
        splBO.setShopstatus(status);
        splBO.setShoptitle(title);
        splBO.setShopitemID(iitemid);
        
        
        ShoppingListItem slitem=new ShoppingListItem();
        
        
        slitem.setAssigned_to(splBO.getAssigned_to());
        slitem.setCreated_by(splBO.getCreated_by());
        slitem.setDirector_username(splBO.getDirector_username());
        slitem.setPrice(splBO.getPrice());
        slitem.setQuantity(splBO.getQuantity());
        slitem.setShopstatus(splBO.getShopstatus());
        slitem.setShoptitle(splBO.getShoptitle());
        
        slitem.setShopitemID(splBO.getShopitemID());
        
        
        
        success= myshopDAO.updateItem(slitem);
        
        
        if(success){
            path="controller_servl?event=SHOPP";
        }
        
        
        
        
        
     }
    
    
    
    
    
    
    
    
    
    
    
}
