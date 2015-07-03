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
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
        boolean ck=false;
        if (email.isEmpty()) {
            inactive = true;
            email = cur_user.getUsername()+"_inactivemember" + generate_random_number()+"@ex.com";
            
            ck=true;
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
        if (success_insert && ck==false){
            
            try {
                invitation_user(myUser.getEmail(),myUser.getUsername(),myUser.getPassword());
                
                
                path="controller_servl?event=MYFAMILY&mtag=insert";
            } catch (MessagingException ex) {
                Logger.getLogger(InsertInactiveUserEventHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            path="controller_servl?event=MYFAMILY&mtag=insert";
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
    
    
    
    
    public void invitation_user(String us_mail,String usname,String pass) throws MessagingException{
    
    
    
        try {
            String to = us_mail;//change accordingly
            
            // Sender's email ID needs to be mentioned
            String from = "tucfamcloud@gmail.com";//change accordingly
            final String username = "tucfamcloud@gmail.com";//change accordingly
            final String password = "tucfamcloud21";//change accordingly
            
            // Assuming you are sending email through relay.jangosmtp.net
            String host = "smtp.gmail.com";
            
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            
            
            
            // Get the Session object.
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            
            
            
            Message message = new MimeMessage(session);
            
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            
            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            
            // Set Subject: header field
            message.setSubject("FamilyCloud Application 2015 ");
            
            // Now set the actual message
            message.setContent("Welcome to Family Cloud Use the below username and password to login<br></br>"
                    +"<b>Username:</b>"+" "+usname+"<br></br>"+"<b>Password:</b>"+" "+pass,"text/html;charset=utf-8");
            
            // Send message
            Transport.send(message);
            
            System.out.println("Sent message successfully....");
        } catch (AddressException ex) {
            Logger.getLogger(InsertInactiveUserEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    
    
    }

}
