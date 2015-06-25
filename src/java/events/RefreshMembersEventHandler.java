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
import org.json.simple.JSONObject;

/**
 *
 * @author costi_000
 */
public class RefreshMembersEventHandler extends EventHandlerBase {
    String path;

    @Override
    protected String getURL() {
        return path;
    }

    @Override
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao myUserDAO = mySqlFactory.getUserDao();
        JSONObject obj = new JSONObject();
        List<User> members_list = new ArrayList<User>();

        User cur_user = (User) mySession.getAttribute("curr_user");
        obj.put("cur_user", cur_user);
        members_list=myUserDAO.getActiveFamilyMembersExceptMe(cur_user.getUsername());

        request.setAttribute("json", obj);
        request.setAttribute("members", members_list);

        path="ChatMembers.jsp";
    }

}