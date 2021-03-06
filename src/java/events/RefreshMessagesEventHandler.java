/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.MessageDao;
import dao.UserDao;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Message;
import model.User;
import org.json.simple.JSONObject;

/**
 *
 * @author costi_000
 */
public class RefreshMessagesEventHandler extends EventHandlerBase{
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        MessageDao myMessageDao = mySqlFactory.getMessageDao();
        UserDao myUserDAO = mySqlFactory.getUserDao();
        JSONObject obj = new JSONObject();
        
        User cur_user = (User) mySession.getAttribute("curr_user");
        String receiver= request.getParameter("receiver");
        obj.put("cur_user", cur_user);
        List<Message> msglist= new ArrayList<Message>();
        msglist= myMessageDao.getMessages(cur_user.getUsername(), receiver);
        
        request.setAttribute("json", obj);
        request.setAttribute("messages", msglist);
        
        path="ChatBody.jsp";

        
    }
    
}
