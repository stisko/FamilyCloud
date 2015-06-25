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
public class MemberProfileEventHandler extends EventHandlerBase {

    String path;

    @Override
    protected String getURL() {
        return path;
    }

    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao myUserDAO = mySqlFactory.getUserDao();
        UserBO myUserBO = new UserBO();
        JSONObject selected_user = new JSONObject();
        JSONObject obj = new JSONObject();

        User cur_user = (User) mySession.getAttribute("curr_user");

        String username = request.getParameter("username");
        String tag = request.getParameter("tag");
        User selected_user_j = myUserDAO.getUser(username);
        selected_user = selected_user_j.getUserJSON(selected_user_j.getUsername());

        obj.put("selected_user", selected_user);

        request.setAttribute("json", obj);
        if (selected_user_j.getDirector().equals("I")) {
            if (tag.equals("go")) {
                path = "UpdateMemberProfileInactive.jsp";
            } else if (tag.equals("delete")) {
                path = "DeleteMember.jsp";
            } else if (tag.equals("view")) {
                path = "ViewMemberProfileInactive.jsp";
            }
        } else {
            if (tag.equals("go")) {
                path = "UpdateMemberProfile.jsp";
            } else if (tag.equals("delete")) {
                path = "DeleteMember.jsp";
            } else if (tag.equals("view")) {
                path = "ViewMemberProfile.jsp";
            }
        }

    }

}
