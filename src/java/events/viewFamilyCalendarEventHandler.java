
package events;

import dao.DaoFactory;
import dao.FamCalEventDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FamCalEvent;
import model.User;
import modelBO.FamCalEventBO;

/**
 *
 * @author costi_000
 */
public class viewFamilyCalendarEventHandler extends EventHandlerBase{
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        FamCalEventDao myEventDao= mySqlFactory.getFamCalEventDao();
        
        FamCalEvent event= new FamCalEvent();
        FamCalEventBO eventBO= new FamCalEventBO();
        String id= request.getParameter("id");
        int intId= Integer.parseInt(id);
        
        
        event= myEventDao.getFamCalEvent(intId);
        eventBO=event.toFamCalEventBO();
        User cur_user = (User) mySession.getAttribute("curr_user");
        request.setAttribute("cur_user", cur_user);
        request.setAttribute("event", eventBO);
        
        path="ViewFamCalEvent.jsp";
        
        
    }
    
}
