/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.UserDao;
import dao.WallPostDao;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import model.WallPost;
import modelBO.WallPostBO;
//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
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
public class insertWallPostEventHandler extends EventHandlerBase {

    String path;

    @Override
    protected String getURL() {
        return path;
    }

    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        WallPostDao myPostWallDAO = mySqlFactory.getWallPostDao();
        UserDao myUserDAO = mySqlFactory.getUserDao();
        byte[] data = null;
        boolean success;
        //boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        //  if (isMultipart) {
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

// Configure a repository (to ensure a secure temp location is used)
        //ServletContext servletContext = this.getServletConfig().getServletContext();
        //File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        //factory.setRepository(repository);
// Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {

            WallPost post = new WallPost();
            User cur_user = (User) mySession.getAttribute("curr_user");
            User director_user = myUserDAO.getFamilyDirector(cur_user.getUsername());
            //directorusername
            post.setCreated_by(cur_user.getUsername());
            post.setDirector_username(director_user.getUsername());
            Date completedDate = new Date();

            post.setCreation_time(completedDate);
            List<String> list = new ArrayList<String>();
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                JSONObject obj = new JSONObject();
                if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    post.setMessage(fieldValue);

                    // ... (do your job here)
                } else {
                    // Process form file field (input type="file").
                    String fieldName = item.getFieldName();
                    String fileName = FilenameUtils.getName(item.getName());
                    InputStream fileContent = item.getInputStream();
                    byte[] bytes = IOUtils.toByteArray(fileContent);

                    post.setFile(bytes);

                   

                    // ... (do your job here)
                }
            }

            success = myPostWallDAO.insertPost(post);

            if (success) {
                path = "controller_servl?event=WALLPOST";
            } else {

            }

        } catch (FileUploadException e) {
            throw new ServletException("Cannot parse multipart request.", e);
        }

        System.out.println("test");

    }

}
