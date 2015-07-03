/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.UserDao;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import modelBO.UserBO;
import org.json.simple.JSONObject;

/**
 *
 * @author costi_000
 */
public class MyFamilyEventHandler extends EventHandlerBase {

    private String path;

    protected String getURL() {
        return path;
    }

    @Override
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao myUserDAO = mySqlFactory.getUserDao();
        UserBO myUserBO = new UserBO();
        JSONObject director = new JSONObject();
        JSONObject curr_user2 = new JSONObject();
        JSONObject obj = new JSONObject();
        List<User> members_list = new ArrayList<User>();
        List<UserBO> family_list = new ArrayList<UserBO>();

        User cur_user = (User) mySession.getAttribute("curr_user");

        User director_user = myUserDAO.getFamilyDirector(cur_user.getUsername());
        director = director_user.getUserJSON(director_user.getUsername());
        curr_user2 = cur_user.getUserJSON(cur_user.getUsername());
        obj.put("director", director);
        obj.put("cur_user", cur_user);
        String hidden;
        if (cur_user.getDirector().equals("Y")) {
            hidden = "visibility:visible";
        } else {
            hidden = "display:none";
        }

        members_list = myUserDAO.getActiveFamilyMembersExceptMe(cur_user.getUsername());
//      request.setAttribute("director_fullname", director_user.getFirstName()+" "+ director_user.getLastName());
//        request.setAttribute("alert_message", "You are logged in!");
//        request.setAttribute("alert_class", "alert alert-success");
        obj.put("selected_user", director);
        obj.put("hidden", hidden);
        request.setAttribute("members", members_list);
        request.setAttribute("json", obj);

        family_list = myUserBO.family_toUserBo(myUserDAO.getFamilyMembers(cur_user.getUsername()));
        request.setAttribute("family", family_list);

        int i = (int) mySession.getAttribute("tag");
        if (i == 1) {
            mySession.setAttribute("tag", 0);
            request.setAttribute("jsp", "Myfamily.jsp");
            path = "HomePage.jsp";
        } else {

            JSONObject jsme = new JSONObject();

            if (request.getParameter("mtag") != null) {

                String temp = request.getParameter("mtag");

                if (temp.equals("insert")) {

                    jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                    jsme.put("message", "Family Member Has Been Succefully Added!");

                } else if (temp.equals("update")) {

                    jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                    jsme.put("message", "Family Member Has Been Succefully Updated!");

                } else if (temp.equals("delete")) {

                    jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                    jsme.put("message", "Family Member Has Been Succefully Deleted!");

                }

                request.setAttribute("noti_message", jsme);

            }
            path = "MyFamily.jsp";
        }

    }

}
