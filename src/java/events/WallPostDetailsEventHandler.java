/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.WallPostDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.WallPost;
import org.json.simple.JSONObject;

/**
 *
 * @author costi_000
 */
public class WallPostDetailsEventHandler extends EventHandlerBase {

    String path;

    @Override
    protected String getURL() {
        return path;
    }

    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        WallPostDao myPostDAO = mySqlFactory.getWallPostDao();

        JSONObject selected_item = new JSONObject();
        JSONObject obj = new JSONObject();

        int itemID;
        String test = request.getParameter("id");

        itemID = Integer.parseInt(test);
        String tag = request.getParameter("tag");
        WallPost wallPost = myPostDAO.getPost(itemID);

        request.setAttribute("post", wallPost);

        path = "DeleteWallPost.jsp";

    }
}
