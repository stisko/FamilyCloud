/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoFactory;
import dao.MediaObjectDao;
import dao.UserDao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MediaObject;
import modelBO.MediaObjectBO;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.util.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author costi_000
 */
public class UploadHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UploadHandler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadHandler at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        boolean success;
        byte[] data = null;
        //case that no session found, start new
        if (session == null) {
            session = request.getSession();
        } 
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

// Configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

// Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                // Parse the request
                List<FileItem> items = upload.parseRequest(request);

                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();

                    data = item.get();
                    System.out.println();
                }

            } catch (FileUploadException ex) {
                Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        MediaObjectDao mediadao = mySqlFactory.getMediaObjectDao();

        MediaObjectBO mdobjBO = new MediaObjectBO();

        MediaObject mobj = new MediaObject();

        String usname = request.getParameter("username");

        mdobjBO.setContent(data);
        mdobjBO.setUsername(usname);

        mobj.setContent(mdobjBO.getContent());
        mobj.setUsername(mdobjBO.getUsername());

        success = mediadao.insertUserMedia(mobj);
        if (success) {
            System.out.println("mpike");
            session.setAttribute("tag", 1);
            RequestDispatcher rd = request.getRequestDispatcher("controller_servl?event=MYFAMILY");
            
//            ServletContext context = getServletContext();
//            RequestDispatcher rd = context.getRequestDispatcher("/controller_servl?event=MYFAMILY#editfamilydirector");
//
            rd.forward(request, response);
            //response.sendRedirect("HomePage.jsp");
            //response.sendRedirect("controller_servl?event=MYFAMILY");
        } else {
            System.out.println("Den mpike");
        }

        //response.setContentType(usname);
//        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//       if(isMultipart){
//                  ServletFileUpload upload = new ServletFileUpload();
//           try{
//               FileItemIterator iter = upload.getItemIterator(request);
//               FileItemStream item = null;
//               String name = "";
//               InputStream stream = null;
//               while (iter.hasNext()){
//                                     item = iter.next();
//                                     name = item.getFieldName();
//                                     stream = item.openStream();
//                  if(item.isFormField()){out.write("Form field " + name + ": " 
//                                           + Streams.asString(stream) + "<br/>");}
//                  else {
//                      name = item.getName();
//                      System.out.println("name==" + name);
//                      if(name != null && !"".equals(name)){
//                         String fileName = new File(item.getName()).getName();
//                         out.write("Client file: " + item.getName() + " <br/>with file name "
//                                                    + fileName + " was uploaded.<br/>");
//                         File file = new File(getServletContext().getRealPath("/" + fileName));
//                         FileOutputStream fos = new FileOutputStream(file);
//                         long fileSize = Streams.copy(stream, fos, true);
//                         System.out.println("Filesize= "+fileSize);
//                         out.write("Size was " + fileSize + " bytes <br/>");
//                         out.write("File Path is " + file.getPath() + "<br/>");
//                      }
//                  }
//               }
//           } catch(FileUploadException fue) {out.write("fue!!!!!!!!!");}
//       } 
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
