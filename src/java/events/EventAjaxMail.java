/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import org.json.simple.JSONObject;

/**
 *
 * @author costi_000
 */

public class EventAjaxMail extends EventHandlerBase {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
            /* TODO output your page here. You may use following sample code. */
            
            //out.println("<h1>lathos23 </h1>"+ request.getParameter("registeremail"));
        
        if(request.getParameter("registeremail")!=null){
            checkDuplicateEmail(mySession, request, response);
        }else if(request.getParameter("registerusername")!=null){
            checkDuplicateUsername(mySession, request, response);
        }else if(request.getParameter("changePassword")!=null){
            checkPassword(mySession, request, response);
        }else if(request.getParameter("Addregisteremail")!=null){
        
            checkAddDuplicateEmail(mySession,request,response);
        }
      
      
        //request.setAttribute("status", "true");
        
     
    }
    
    
    public void checkAddDuplicateEmail(HttpSession mySession, HttpServletRequest request, HttpServletResponse response){
    
    
        PrintWriter out= null;
        try {
            JSONObject obj= new JSONObject();
            out = response.getWriter();
            DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
            UserDao myUserDAO = mySqlFactory.getUserDao();
            String email=request.getParameter("Addregisteremail");
            boolean invalid_mail=myUserDAO.isDuplicateMail(email);
            if(invalid_mail){
                obj.put("message", "The <b>"+email+"</b> is invalid. Please write another");
                obj.put("disabled", "btn btn-primary disabled");
                 obj.put("flag","true");
                //out.println("The <b>"+email+"</b> is invalid. Please write another");
                out.println(obj);
            }else{
                 obj.put("flag","false");
                obj.put("message", "The <b>"+email+"</b> is valid.");
                obj.put("disabled","btn btn-primary");
                out.println(obj);
                //out.println("The <b>"+email+"</b> is valid.");
            }
        } catch (IOException ex) {
            Logger.getLogger(EventAjaxMail.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    
    
    }
    
    public void checkDuplicateEmail(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws IOException{
        JSONObject obj= new JSONObject();
        PrintWriter out = response.getWriter();
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao myUserDAO = mySqlFactory.getUserDao();

        String email= request.getParameter("registeremail");
        boolean invalid_mail = myUserDAO.isDuplicateMail(email);
        
        if(invalid_mail){
            obj.put("message", "The <b>"+email+"</b> exists. Please write another");
            obj.put("disabled", "btn btn-primary disabled");
             obj.put("flag","true");
            //out.println("The <b>"+email+"</b> is invalid. Please write another");
            out.println(obj);
        }else{
             obj.put("flag","false");
            obj.put("message", "The <b>"+email+"</b> doesn't exist.");
            obj.put("disabled","btn btn-primary");
            out.println(obj);
            //out.println("The <b>"+email+"</b> is valid.");
        }
        out.close();
        
        
    }
    
    public void checkDuplicateUsername(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws IOException{
        JSONObject obj= new JSONObject();
        PrintWriter out = response.getWriter();
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao myUserDAO = mySqlFactory.getUserDao();

        String username= request.getParameter("registerusername");
        boolean invalid_mail = myUserDAO.isDuplicateUsername(username);
        if(invalid_mail){
            obj.put("message", "The <b>"+username+"</b> is invalid. Please write another");
            obj.put("disabled", "btn btn-primary disabled");
            obj.put("flag","true");
            out.println(obj);
        }else{
            obj.put("flag","false");
            obj.put("message", "The <b>"+username+"</b> is valid.");
            obj.put("disabled","btn btn-primary");
            out.println(obj);
        }
    }

    public void checkPassword(HttpSession mySession, HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = null;
        try {
            DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
            UserDao myUserDAO = mySqlFactory.getUserDao();
            out = response.getWriter();
            User cur_user = (User) mySession.getAttribute("curr_user");
            if(cur_user.getPassword().equals(request.getParameter("changePassword"))){
                out.println("1");
            }else{
                out.println("0");
            }
        } catch (IOException ex) {
            Logger.getLogger(EventAjaxMail.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
    
    @Override
    protected String getURL() {
        return null;
    }

}
