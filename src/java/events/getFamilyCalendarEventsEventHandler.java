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
public class getFamilyCalendarEventsEventHandler extends EventHandlerBase {

    String path;

    @Override
    protected String getURL() {
        return path;
    }

    @Override
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
            FamCalEventDao myFamCalEventDao = mySqlFactory.getFamCalEventDao();
            UserDao myUserDAO = mySqlFactory.getUserDao();

            FamCalEvent ev = new FamCalEvent();
            User cur_user = (User) mySession.getAttribute("curr_user");
            User director = myUserDAO.getFamilyDirector(cur_user.getUsername());

            List<FamCalEvent> events_list;
            String viewbyUsername = request.getParameter("username");
            String category= request.getParameter("category");
            if (viewbyUsername != null) {
                events_list = myFamCalEventDao.getFamCalEventsByUser(viewbyUsername);  //ola ta events gia kathe xristi
            } else if(category!=null){
                events_list= myFamCalEventDao.getFamCalEvents(category, director.getUsername());
            }else{
                events_list = myFamCalEventDao.getFamCalEvents(director.getUsername());//ola ta events tis oikogeneias
            }
            boolean adult = isAdult(cur_user);
            JSONArray obj = new JSONArray();
            obj = toJSONEventsList(events_list, adult);
            String ret = obj.toJSONString();// obj.toJSONString();

            out = response.getWriter();

            out.print(ret);

        } catch (IOException ex) {
            Logger.getLogger(getFamilyCalendarEventsEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    public JSONArray toJSONEventsList(List<FamCalEvent> events, boolean adult) {
        FamCalEvent temp;
        JSONObject obj = new JSONObject();
        JSONObject eventJSON;
        JSONArray jsonar = new JSONArray();
        for (int i = 0; i < events.size(); i++) {

            eventJSON = new JSONObject();
            temp = events.get(i);
            if (((temp.getVisibility().equals("Adults") )&& adult==true) || temp.getVisibility().equals("All")) {
                eventJSON.put("id", temp.getIdFamCalEvent());
                eventJSON.put("title", temp.getTitle());
                eventJSON.put("color", temp.getCategory());
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
                        eventJSON.put("color", temp.getCategory());
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

    private boolean isAdult(User cur_user) {
        DateTime birthdate = new DateTime(cur_user.getBirthdate());
        DateTime curr_Date = new DateTime();
        if (birthdate.plusYears(18).isAfter(curr_Date)) {
            return false;
        } else {
            return true;
        }
    }

}
