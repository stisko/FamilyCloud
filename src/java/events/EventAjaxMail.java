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
        }
      
      
        //request.setAttribute("status", "true");
        
     
    }
    
    public void checkDuplicateEmail(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws IOException{
        JSONObject obj= new JSONObject();
        PrintWriter out = response.getWriter();
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao myUserDAO = mySqlFactory.getUserDao();

        String email= request.getParameter("registeremail");
        boolean invalid_mail = myUserDAO.isDuplicateMail(email);
        
        if(invalid_mail){
            obj.put("message", "The <b>"+email+"</b> is invalid. Please write another");
            obj.put("disabled", "btn btn-primary disabled");
            //out.println("The <b>"+email+"</b> is invalid. Please write another");
            out.println(obj);
        }else{
            obj.put("message", "The <b>"+email+"</b> is valid.");
            obj.put("disabled","btn btn-primary");
            out.println(obj);
            //out.println("The <b>"+email+"</b> is valid.");
        }
        
        
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
            out.println(obj);
        }else{
            obj.put("message", "The <b>"+username+"</b> is valid.");
            obj.put("disabled","btn btn-primary");
            out.println(obj);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  
    
    @Override
    protected String getURL() {
        return null;
    }

}
