/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.UserDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelBO.UserBO;

/**
 *
 * @author costi_000
 */
public class DeleteMemberEventHandler extends EventHandlerBase{
    String path;
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response){
        
        
        UserBO myUserBO = new UserBO();
        
        String username= request.getParameter("username");
        
        if(delete_member(username)==true){
            path="controller_servl?event=MYFAMILY";
        }
        
        
    }

    private boolean delete_member(String username) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao myUserDAO = mySqlFactory.getUserDao();
        
        
        return myUserDAO.deleteUser(username);
    }
    
}
