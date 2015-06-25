/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import dao.DaoFactory;
import dao.UserDao;
import dao.WallPostDao;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import model.WallPost;
import modelBO.UserBO;
import modelBO.WallPostBO;

/**
 *
 * @author costi_000
 */
public class getImagePostEventHandler extends EventHandlerBase{
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws IOException{
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        WallPostDao myWallPostDAO = mySqlFactory.getWallPostDao();

        String postid=request.getParameter("postid");
        int postID= Integer.parseInt(postid);

        WallPost post= myWallPostDAO.getPost(postID);

        response.setContentType("image/jpeg");
        response.getOutputStream().write(post.getFile());

        path=null;
        
    }
    
}
