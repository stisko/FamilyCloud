/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import dao.DaoFactory;
import dao.MessageDao;
import dao.UserDao;
import dao.WallPostDao;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Message;
import model.User;
import model.WallPost;
import modelBO.UserBO;
import modelBO.WallPostBO;

/**
 *
 * @author costi_000
 */
public class getImageMessageEventHandler extends EventHandlerBase{
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws IOException{
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        MessageDao myMessageDAO = mySqlFactory.getMessageDao();

        String messageid=request.getParameter("messageid");
        int messageID= Integer.parseInt(messageid);

        Message post= myMessageDAO.getMessage(messageID);

        response.setContentType("image/jpeg");
        response.getOutputStream().write(post.getFile());

        path=null;
        
    }
    
}
