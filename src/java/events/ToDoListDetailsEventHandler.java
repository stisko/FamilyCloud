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
import model.ToDoListItem;
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

        if(tag.equals("edit")){
            path="EditToDoItem.jsp";
        }else if(tag.equals("delete")){
            path="DeleteToDoList.jsp";
        }
        
        
    }
}
