/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.UserDao;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import org.json.simple.JSONObject;

/**
 *
 * @author costi_000
 */
public class ChangePasswordEventHandler extends EventHandlerBase{
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    @Override
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws IOException{
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao userdao = mySqlFactory.getUserDao();
        boolean success;
        
        String old= request.getParameter("old_password");
        String pswd= request.getParameter("password");
        User cur_user = (User) mySession.getAttribute("curr_user");
        if(cur_user.getPassword().equals(old)){
            cur_user.setPassword(pswd);
        }else{
            //minima lathous
        }
        
        
        
        success= userdao.changePassword(cur_user);
        if(success){
                        
        }else{
            
        }
        
    }
    
    
}
