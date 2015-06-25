/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.ShoppingListDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author GVra
 */
public class DeleteShoppingListEventHandler extends EventHandlerBase {

    String path;

    @Override
    protected String getURL() {

        return path;
    }

    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ShoppingListDao myItemDAO = mySqlFactory.getShoppingListDao();

        boolean success;

        String it = request.getParameter("itemid");

        int tempit = Integer.parseInt(it);

        success = myItemDAO.deleteItem(tempit);

        if (success) {
            
            path = "controller_servl?event=SHOPP";

        }

    }

   // path="controller_servl?event=SHOPP";
}
