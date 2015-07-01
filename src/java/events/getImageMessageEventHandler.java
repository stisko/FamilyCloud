/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import dao.DaoFactory;
import dao.MessageDao;
import dao.UserDao;
import dao.WallPostDao;
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
import model.Message;
import model.User;
import model.WallPost;
import modelBO.UserBO;
import modelBO.WallPostBO;

/**
 *
 * @author costi_000
 */
public class getImageMessageEventHandler extends EventHandlerBase{
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws IOException{
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        MessageDao myMessageDAO = mySqlFactory.getMessageDao();

        String messageid=request.getParameter("messageid");
        int messageID= Integer.parseInt(messageid);

        Message post= myMessageDAO.getMessage(messageID);

        response.setContentType("image/jpeg");
        //response.setHeader("Content-Length", String.valueOf(post.getFile().length));
        response.setContentLength(post.getFile().length);
        
        response.getOutputStream().write(post.getFile());
        //response.getOutputStream().write(scale(post.getFile(), 500, 500));

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
