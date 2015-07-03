
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
public class InsertShoppingListEventHandler extends EventHandlerBase {
    
    String path;
    
    protected String getURL() {
        return path;
    }
    
    
    @Override
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response){
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ShoppingListDao myshopDAO =mySqlFactory.getShoppingListDao();
        
        UserDao myUserDAO = mySqlFactory.getUserDao();
        
        User cur_user = (User) mySession.getAttribute("curr_user");
        
        User director = myUserDAO.getFamilyDirector(cur_user.getUsername());
        
        
        
        ShoppingListBO shplBO=new ShoppingListBO();
        
        String director_username= director.getUsername();
        
        String title= request.getParameter("title");
        String status= request.getParameter("status");
        String assignedTo= request.getParameter("assignedto");
        String quantity= request.getParameter("quantity");
        String price= request.getParameter("price");
        String createdby =cur_user.getUsername();
        
        
        shplBO.setDirector_username(director_username);
        shplBO.setAssigned_to(assignedTo);
        shplBO.setPrice(Integer.parseInt(price));
        shplBO.setShopstatus(status);
        shplBO.setQuantity(Integer.parseInt(quantity));
        shplBO.setShoptitle(title);
        
        shplBO.setCreated_by(createdby);
        
        ShoppingListItem spi= new ShoppingListItem();
        
        
        
        spi.setAssigned_to(shplBO.getAssigned_to());
        spi.setDirector_username(shplBO.getDirector_username());
        spi.setPrice(shplBO.getPrice());
        spi.setQuantity(shplBO.getQuantity());
        spi.setShopstatus(shplBO.getShopstatus());
        spi.setShoptitle(shplBO.getShoptitle());
        
        spi.setCreated_by(shplBO.getCreated_by());
        
        
        
        
        
        
        if(myshopDAO.insertShoppingItem(spi)){
            path="controller_servl?event=SHOPP&mtag=insert";
        }else{
            path=null;
        }
        
        
        
        
        
      
        
        
        
        
    }
    
}
