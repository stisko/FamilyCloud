/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.MessageDao;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Message;
import model.User;
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
public class AddMessageEventHandler extends EventHandlerBase {

    String path;

    @Override
    protected String getURL() {
        return path;
    }

    @Override
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
            MessageDao myMessageDAO = mySqlFactory.getMessageDao();
            byte[] data = null;
            boolean success;

            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            Message msg = new Message();
            User cur_user = (User) mySession.getAttribute("curr_user");

            List<String> list = new ArrayList<String>();
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                JSONObject obj = new JSONObject();
                if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    msg.setMessage(fieldValue);

                    // ... (do your job here)
                } else {
                    // Process form file field (input type="file").
                    String fieldName = item.getFieldName();
                    String fileName = FilenameUtils.getName(item.getName());
                    InputStream fileContent = item.getInputStream();
                    byte[] bytes = IOUtils.toByteArray(fileContent);

                    msg.setFile(bytes);

                    // ... (do your job here)
                }
            }
            
            
            msg.setSender(cur_user.getUsername());
            msg.setReceiver(request.getParameter("username"));
            
            success= myMessageDAO.addMessage(msg);
            if(success){
                path=null;
            }else{
                path=null;
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(AddMessageEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
