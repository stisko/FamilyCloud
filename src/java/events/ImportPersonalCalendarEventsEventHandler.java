/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import dao.DaoFactory;
import dao.FamCalEventDao;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FamCalEvent;
import model.User;
import org.joda.time.DateTime;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author costi_000
 */
public class ImportPersonalCalendarEventsEventHandler extends EventHandlerBase{
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    @Override
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = null;
        try {
            DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
            FamCalEventDao myFamCalEventDao= mySqlFactory.getFamCalEventDao();
            UserDao myUserDAO= mySqlFactory.getUserDao();
            
            FamCalEvent ev= new FamCalEvent();
            User cur_user = (User) mySession.getAttribute("curr_user");
            User director = myUserDAO.getFamilyDirector(cur_user.getUsername());
            
            List<FamCalEvent> events_list;
          
            
                events_list=myFamCalEventDao.getFamCalEventsByUser(cur_user.getUsername());  //ola ta events gia kathe xristi
            
         //       events_list= myFamCalEventDao.getFamCalEvents(director.getUsername());//ola ta events tis oikogeneias
            
            
            
            
            JSONArray obj = new JSONArray();
            obj= toJSONEventsList(events_list);
            String ret= obj.toJSONString();// obj.toJSONString();
            
            
            out = response.getWriter();

//            obj.put("title", "testarw event");
//            obj.put("start", "2015-06-19");
//            obj.put("description", "desc");
//            String ret= obj.toJSONString();
out.print(ret);
//out.print("["+ret+"]");

        } catch (IOException ex) {
            Logger.getLogger(getFamilyCalendarEventsEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }


    }
    
    public JSONArray toJSONEventsList(List<FamCalEvent> events) {
        FamCalEvent temp;
        JSONObject obj = new JSONObject();
        JSONObject eventJSON;
        JSONArray jsonar = new JSONArray();
        for (int i = 0; i < events.size(); i++) {
            eventJSON = new JSONObject();
            temp = events.get(i);
            eventJSON.put("id", temp.getIdFamCalEvent());
            eventJSON.put("title", temp.getTitle());
            //eventJSON.put("color", "red");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
            eventJSON.put("start", df.format(temp.getStart_date()));//"2015-06-19");//
            eventJSON.put("end", df.format(temp.getEnd_date()));
            eventJSON.put("description", "Description= " + temp.getDescription() + " Location= " + temp.getLocation());
            jsonar.add(eventJSON);

            Date tmp_Date = temp.getStartRepeatDate();
            Date start_date = temp.getStart_date();
            Date end_date = temp.getEnd_date();
            if (temp.getRepeatTime() != null) {
                while (tmp_Date.compareTo(temp.getEndRepeatDate()) < 0) {
                    eventJSON = new JSONObject();
                    eventJSON.put("id", temp.getIdFamCalEvent());
                    eventJSON.put("title", temp.getTitle());
                    df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");

                    eventJSON.put("start", df.format(calculateDate(start_date, temp.getRepeatTime(), temp.getRepeat_every())));
                    eventJSON.put("end", df.format(calculateDate(end_date, temp.getRepeatTime(), temp.getRepeat_every())));
                    eventJSON.put("description", "Description= " + temp.getDescription() + " Location= " + temp.getLocation());
                    jsonar.add(eventJSON);

                    DateTime datet = new DateTime(tmp_Date);
                    tmp_Date = calculateDate(tmp_Date, temp.getRepeatTime(), temp.getRepeat_every());
                    start_date = calculateDate(start_date, temp.getRepeatTime(), temp.getRepeat_every());
                    end_date = calculateDate(end_date, temp.getRepeatTime(), temp.getRepeat_every());
                }
            }
        }

        return jsonar;
    }

    public Date calculateDate(Date date, String repeatDate, int repeat_time) {
        Date datef = new Date();
        DateTime dateTime = new DateTime(date);
        if (repeatDate.equals("Weekly")) {
            dateTime = dateTime.plusWeeks(repeat_time);
        } else if (repeatDate.equals("Daily")) {
            dateTime = dateTime.plusDays(repeat_time);
        } else if (repeatDate.equals("Monthly")) {
            dateTime = dateTime.plusMonths(repeat_time);
        }
        datef = dateTime.toDate();
        return datef;
    }
    
    
}
