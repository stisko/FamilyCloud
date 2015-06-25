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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import modelBO.UserBO;

/**
 *
 * @author costi_000
 */

public class SignUpEventHandler extends EventHandlerBase {
private String path;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param mySession
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    @Override
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        boolean success_signup = false;
        
        String firstname= request.getParameter("firstname_n");
        String lastname= request.getParameter("lastname_n");
        String username= request.getParameter("username_n");
        String password= request.getParameter("password_n");
        String birthdate= request.getParameter("birthdate_n");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate_d = null;
        try {
            birthdate_d = sdf.parse(birthdate);
        } catch (ParseException ex) {
            Logger.getLogger(SignUpEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        String email = request.getParameter("email_n");
        String town = request.getParameter("town_n");
        
        
        UserBO userbo= new UserBO();
        userbo.setFirstName(firstname);
        userbo.setLastName(lastname);
        userbo.setUsername(username);
        userbo.setPassword(password);
        userbo.setBirthdate(birthdate_d);
        userbo.setEmail(email);
        userbo.setTown(town);
        userbo.setDirector("Y");
        
        
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao myUserDAO = mySqlFactory.getUserDao();
        
        User myUser= new User();
        myUser.setFirstName(userbo.getFirstName());
        myUser.setLastName(userbo.getLastName());
        myUser.setUsername(userbo.getUsername());
        myUser.setPassword(userbo.getPassword());
        myUser.setBirthdate(userbo.getBirthdate());
        myUser.setEmail(userbo.getEmail());
        myUser.setTown(userbo.getTown());
        myUser.setDirector(userbo.getDirector());
        
        success_signup= myUserDAO.insertUser(myUser);
        if(success_signup){
            path= "/HomePage.jsp";
        }else{
            path= "/StartPage.jsp";
        }
        
        mySession.setAttribute("curr_user",userbo);
        
    }

    @Override
    protected String getURL() {
        return path;
    }
    


   

}
