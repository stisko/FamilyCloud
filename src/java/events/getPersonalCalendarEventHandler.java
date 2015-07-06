/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;


public class getPersonalCalendarEventHandler extends EventHandlerBase{

    String path;
    
    @Override
    protected String getURL() {
        
        return path;
    }
    
    
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
    
    JSONObject jsme = new JSONObject();

        if (request.getParameter("mtag") != null) {

            String temp = request.getParameter("mtag");

            if (temp.equals("insert")) {

                jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "<span class=\"glyphicon glyphicon-ok-circle\"></span> Personal Calendar Event Has Been Succefully Added!");

            } else if (temp.equals("update")) {

                jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "<span class=\"glyphicon glyphicon-ok-circle\"></span> Personal Calendar Event Has Been Succefully Updated!");

            } else if (temp.equals("delete")) {

                jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "<span class=\"glyphicon glyphicon-ok-circle\"></span> Personal Calendar Event Has Been Succefully Deleted!");

            }else if(temp.equals("import")){
            
                 jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "<span class=\"glyphicon glyphicon-ok-circle\"></span> Family Calendar Event Has Been Succefully Imported!");
            
            }

            request.setAttribute("noti_message", jsme);

        }
    
    
    path="PersonalCalendar.jsp";
    
    
    
    
    }
    
    
    
    
    
    
}
