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
import model.User;
import modelBO.ShoppingListBO;

/**
 *
 * @author GVra
 */
public class ShopListSorted extends EventHandlerBase{

    String path;
    @Override
    protected String getURL() {
        return path;
    }
    
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        
        ShoppingListDao myItemDAO = mySqlFactory.getShoppingListDao();
        
        UserDao myUserDAO = mySqlFactory.getUserDao();
        
        ShoppingListBO itemBO= new ShoppingListBO();
        List<ShoppingListBO> SortedfamSHOPList= new ArrayList<ShoppingListBO>();
        
         User cur_user = (User) mySession.getAttribute("curr_user");
        User director = myUserDAO.getFamilyDirector(cur_user.getUsername());
        
        String keysort=request.getParameter("sortedTag");
        
        String asc_desc_tag=request.getParameter("ssa");
        
        String keysort2="status";        
        
        
        
       SortedfamSHOPList= itemBO.family_toShoppingListBO(myItemDAO.getSortedFamilyShoppingList(director.getUsername(), keysort, asc_desc_tag));
        
        
        
               
        
        request.setAttribute("SortedfamSHOP", SortedfamSHOPList);
        request.setAttribute("cuRRENTuserDr", cur_user);
        
        
        path="ShopListSorted.jsp";
        
    }
    
    
    
}
