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

/**
 *
 * @author costi_000
 */
public class getMealsPlanEventHandler extends EventHandlerBase {

    String path;

    @Override
    protected String getURL() {
        return path;
    }

    @Override
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        
        
        
        
        JSONObject jsme = new JSONObject();

        if (request.getParameter("mtag") != null) {

            String temp = request.getParameter("mtag");

            if (temp.equals("insert")) {

                jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "<span class=\"glyphicon glyphicon-ok-circle\"></span> Meals Plan Event Has Been Succefully Added!");

            } else if (temp.equals("update")) {

                jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "<span class=\"glyphicon glyphicon-ok-circle\"></span> Meals Plan Event Has Been Succefully Updated!");

            } else if (temp.equals("delete")) {

                jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "<span class=\"glyphicon glyphicon-ok-circle\"></span> Meals Plan Event Has Been Succefully Deleted!");

            }

            request.setAttribute("noti_message", jsme);

        }
        
        
        
        path="MealsPlan.jsp";
        

    }

}
