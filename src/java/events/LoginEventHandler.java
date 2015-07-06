
package events;

import dao.DaoFactory;
import dao.UserDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import modelBO.UserBO;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;


public class LoginEventHandler extends EventHandlerBase {
    
    private String path;
    private boolean success_login;
    
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao myUserDAO = mySqlFactory.getUserDao();
        
        String username= request.getParameter("username_n");
        String password= request.getParameter("password_n");
        
        UserBO user = new UserBO();
        user.setUsername(username);
        user.setPassword(password);
        
        success_login= loadUserBasedOnUsernameAndPassword(user);
        
        if(success_login){
            
            JSONObject obj = new JSONObject();
            User cur_user = myUserDAO.getUser(username);
            mySession.setAttribute("curr_user", cur_user);
            obj.put("cur_user", cur_user);
            request.setAttribute("json", obj);
            path="controller_servl?event=MYFAMILY";
            mySession.setAttribute("tag", 1);
            //path="HomePage.jsp";
            
        }else{
            
            path="StartPage.jsp";
            
            request.setAttribute("alert_message", "You are not logged in!");
            request.setAttribute("alert_class", "alert alert-danger");
        }
        
    }
 
    
    
    
    @Override
    protected String getURL() {
        return path;
    }

    public UserBO getUser(String email) {
        try {
            DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
            UserDao userDAO = mySqlFactory.getUserDao();

            UserBO userBO = new UserBO();

            User user = userDAO.getUser(email);

            userBO = userBO.toUserBO(user);

            return userBO;
        } catch (Exception e) {
            return null;
        }
    }
    
    
    public boolean loadUserBasedOnUsernameAndPassword(UserBO myUserBO) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao myUserDAO = mySqlFactory.getUserDao();

        User myUser = myUserDAO.getUser(myUserBO.getUsername());
//        if (myUser.getPassword() != null && myUser.getUsername()!=null) {
        if (isfound(myUser)) {
            if (myUser.getUsername().equals(myUserBO.getUsername()) && myUser.getPassword().equals(myUserBO.getPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\nMPIKEEEEEEEEEEEEEEEEEEEEEEEE\n\n\n\n\n\n\n\n\n\n\n\n");
            return false;
        }
    }
    
    public boolean isfound(User myUser){
        if (myUser.getPassword() != null && myUser.getUsername()!=null) {
            return true;
        }else{
            return false;
        }
        
    }

    /**
     * @return the success_login
     */
    public boolean isSuccess_login() {
        return success_login;
    }
    
}
