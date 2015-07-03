/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.UserDao;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import modelBO.UserBO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;

/**
 *
 * @author costi_000
 */
public class UpdateProfileEventHandler extends EventHandlerBase{
    private String path;
    
    
    @Override
    protected String getURL() {
        return path;
    }
    
    @Override
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws IOException{
       
        try {
            
            UserBO myuser= new UserBO();
            
            String firstName =request.getParameter("firstName");
            String lastName =request.getParameter("lastName");
            String email =request.getParameter("email");
            String birthdate =request.getParameter("birthdate");
            String town =request.getParameter("town");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthdate_d = null;
            
            try {
                birthdate_d = sdf.parse(birthdate);
            } catch (ParseException ex) {
                Logger.getLogger(UpdateProfileEventHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            User cur_user = (User) mySession.getAttribute("curr_user");
            
            
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                JSONObject obj = new JSONObject();
                if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    

                    // ... (do your job here)
                } else {
                    // Process form file field (input type="file").
                    String fieldName = item.getFieldName();
                    String fileName = FilenameUtils.getName(item.getName());
                    InputStream fileContent = item.getInputStream();
                    byte[] bytes = IOUtils.toByteArray(fileContent);

                    myuser.setPicture(bytes);
                    
                    
                    
                    // ... (do your job here)
                }
            }
            
            
            
            myuser.setFirstName(firstName);
            myuser.setLastName(lastName);
            myuser.setEmail(email);
            myuser.setTown(town);
            myuser.setBirthdate(birthdate_d);
            //String tag= request.getParameter("tag");
            if(updateUser(myuser, cur_user.getUsername())== true){
//            JSONObject obj = new JSONObject();
//            mySession.setAttribute("curr_user", cur_user);
                
                //path="MyFamily.jsp";
                
                
                path="controller_servl?event=MYFAMILY&mtag=update";
                
                
            }else{
                
                
            }
            
            
            
        } catch (FileUploadException ex) {                    
            Logger.getLogger(UpdateProfileEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
               
    }
    
    public boolean updateUser(UserBO userbo, String username){
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao userdao = mySqlFactory.getUserDao();
        
        
        User user = new User();
        user.setEmail(userbo.getEmail());
        user.setFirstName(userbo.getFirstName());
        user.setLastName(userbo.getLastName());
        user.setTown(userbo.getTown());
        user.setBirthdate(userbo.getBirthdate());
        user.setPicture(userbo.getPicture());
        
        return userdao.updateUser(user, username);
    }
}
