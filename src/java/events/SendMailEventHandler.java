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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author GVra
 */
public class SendMailEventHandler extends EventHandlerBase {

    String path;

    @Override
    protected String getURL() {
        return path;
    }

    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {

        try {
            DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
            UserDao myUser = mySqlFactory.getUserDao();

            String us_mail = request.getParameter("mailret");

            List<String> forgLi = new ArrayList<String>();

            forgLi = myUser.getforgottendetails(us_mail);

            if (forgLi.size() == 0) {

                request.setAttribute("alert_message", "Wrong Email");
                request.setAttribute("alert_class", "alert alert-danger");

            } else {
                

                String to = us_mail;//change accordingly

                // Sender's email ID needs to be mentioned
                String from = "tucfamcloud@gmail.com";//change accordingly
                final String username = "tucfamcloud@gmail.com";//change accordingly
                final String password = "password";//change accordingly

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
                message.setContent("Hello, FamilyCloud Username and Password Reminder <br></br>"
                        + "<b>Username:</b>" + " " + forgLi.get(0) + "<br></br>" + "<b>Password:</b>" + " " + forgLi.get(1), "text/html;charset=utf-8");

                // Send message
                Transport.send(message);

                System.out.println("Sent message successfully....");
                
                
                
                request.setAttribute("alert_message", "Email Has Been Sent");
                request.setAttribute("alert_class", "alert alert-success");
                    
               
            }

                // Recipient's email ID needs to be mentioned.
            path = "StartPage.jsp";
        } catch (MessagingException ex) {
            Logger.getLogger(SendMailEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
