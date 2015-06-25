/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.UserDao;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import modelBO.UserBO;

/**
 *
 * @author costi_000
 */
public class InsertInactiveUserEventHandler extends EventHandlerBase {

    String path;

    @Override
    protected String getURL() {
        return path;
    }

    @Override
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao myUserDAO = mySqlFactory.getUserDao();
        boolean inactive = false;
        boolean success_insert=false;
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String birthdate = request.getParameter("birthdate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate_d = null;

        try {
            birthdate_d = sdf.parse(birthdate);
        } catch (ParseException ex) {
            Logger.getLogger(UpdateProfileEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        User cur_user = (User) mySession.getAttribute("curr_user");

        UserBO userbo = new UserBO();
        
        userbo.setFirstName(firstName);
        userbo.setLastName(lastName);
        userbo.setBirthdate(birthdate_d);
        
        User myUser= new User();
        if (email.isEmpty()) {
            inactive = true;
            email = cur_user.getEmail()+"_inactivemember" + generate_random_number();
            
            userbo.setUsername(email);
            userbo.setPassword(email);
            userbo.setEmail(email);
            userbo.setDirector("I");            
        } else {
            inactive = false;
            
            userbo.setUsername(firstName.substring(0, 1)+lastName);
            userbo.setPassword("123");
            userbo.setEmail(email);
            userbo.setDirector("N");
            
        }
        
        
        myUser.setFirstName(userbo.getFirstName());
            myUser.setLastName(userbo.getLastName());
            myUser.setUsername(userbo.getUsername());
            myUser.setPassword(userbo.getPassword());
            myUser.setBirthdate(userbo.getBirthdate());
            myUser.setEmail(userbo.getEmail());
            myUser.setTown(userbo.getTown());
            myUser.setDirector(userbo.getDirector());
        
        success_insert= myUserDAO.insertUser(myUser, cur_user.getUsername());
        if (success_insert){
            path="controller_servl?event=MYFAMILY";
        }else{
            
        }

    }

    public String generate_random_number() {
        Random rand = new Random();
        int min = 0;
        int max = 100;
    // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return Integer.toString(randomNum);
    }

}
