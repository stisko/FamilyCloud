/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.UserDao;
import dao.WallPostDao;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import model.WallPost;
import org.json.simple.JSONObject;

/**
 *
 * @author costi_000
 */
public class WallPostEventHandler extends EventHandlerBase{
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        WallPostDao myPostWallDAO = mySqlFactory.getWallPostDao();
        UserDao myUserDAO= mySqlFactory.getUserDao();
        
        User cur_user = (User) mySession.getAttribute("curr_user");
        User director_user = myUserDAO.getFamilyDirector(cur_user.getUsername());
        List<WallPost> familyPosts = new ArrayList<WallPost>();
        familyPosts = myPostWallDAO.getFamilyPosts(director_user.getUsername());
        request.setAttribute("cur_user", cur_user);
        request.setAttribute("familyPosts", familyPosts);
        
        
        JSONObject jsme = new JSONObject();

        if (request.getParameter("mtag") != null) {

            String temp = request.getParameter("mtag");

            if (temp.equals("insert")) {

                jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "<span class=\"glyphicon glyphicon-ok-circle\"></span> Wall Post Item Has Succefully Added!");

            }else if (temp.equals("delete")) {

                jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "<span class=\"glyphicon glyphicon-ok-circle\"></span> Wall Post Item Has Succefully Deleted!");

            }

            request.setAttribute("noti_message", jsme);

        }
        
        if(familyPosts.isEmpty()){
            request.setAttribute("emptya", 1);
        }else{
            request.setAttribute("emptya", 0);
        }
        
        path="FamilyWall.jsp";
        
    }
    
    
    
}
