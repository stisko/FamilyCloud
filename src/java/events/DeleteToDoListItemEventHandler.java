/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.ToDoListItemDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author costi_000
 */
public class DeleteToDoListItemEventHandler extends EventHandlerBase{
String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ToDoListItemDao myItemDAO = mySqlFactory.getToDoListItemDao();
        boolean success;
        
        int itemID;
        String test=request.getParameter("itemID");
        
        itemID= Integer.parseInt(test);
        success= myItemDAO.deleteItem(itemID);
        
        if(success){
            path="controller_servl?event=TODO&mtag=delete";

        }
    }
    
}
