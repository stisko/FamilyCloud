/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.UserDao;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import modelBO.UserBO;
import org.omg.CORBA.portable.ApplicationException;

/**
 *
 * @author costi_000
 */
public class getImageEventHandler extends EventHandlerBase{
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws IOException{
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao myUserDAO = mySqlFactory.getUserDao();
        UserBO myUserBO = new UserBO();
        
        String username=request.getParameter("username");
        User user = myUserDAO.getUser(username);
        response.setContentType("image/jpeg");
        
        response.getOutputStream().write(user.getPicture());
        //response.getOutputStream().write(scale(user.getPicture(), 500, 500));
        
        path=null;
        
    }
    
    public byte[] scale(byte[] fileData, int width, int height) {
    	ByteArrayInputStream in = new ByteArrayInputStream(fileData);
    	try {
    		BufferedImage img = ImageIO.read(in);
    		if(height == 0) {
    			height = (width * img.getHeight())/ img.getWidth(); 
    		}
    		if(width == 0) {
    			width = (height * img.getWidth())/ img.getHeight();
    		}
    		Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    		BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    		imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);

    		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    		ImageIO.write(imageBuff, "jpg", buffer);

    		return buffer.toByteArray();
    	} catch (IOException e) {
    		
    	}
        return null;
    }
    
}
