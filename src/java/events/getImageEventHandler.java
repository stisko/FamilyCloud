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
import modelBO.UserBO;

/**
 *
 * @author costi_000
 */
public class getImageEventHandler extends EventHandlerBase{
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws IOException{
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao myUserDAO = mySqlFactory.getUserDao();
        UserBO myUserBO = new UserBO();
        
        String username=request.getParameter("username");
        User user = myUserDAO.getUser(username);
        response.setContentType("image/jpeg");
        response.getOutputStream().write(user.getPicture());
        
        path=null;
        
    }
    
}
