
package controller;
import events.EventHandlerBase;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Controller extends HttpServlet {

     protected HashMap events= new HashMap();
     
     public void init() throws ServletException{
        
      // get the event values and save them into events
      ResourceBundle bundle = ResourceBundle.getBundle("Event");
      Enumeration e = bundle.getKeys();
        
        while(e.hasMoreElements()) {
           String key = (String) e.nextElement();
           String value = bundle.getString(key);
           try {
                EventHandlerBase event =
                (EventHandlerBase) Class.forName(value).newInstance();
                events.put(key, event);
                System.out.println(this + "init event"+key+ "Handler: "+ event.getClass().getName());
                
                } catch(Exception exc) {
                    System.out.println("NO HANDLER FOUND!");
                    exc.printStackTrace();
            }
        }
        
        
    }
    
     @Override
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
     
     
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {      
       
        
        //get sessions if the server sent one to the browser
        HttpSession session = request.getSession(false);
        
        //case that no session found, start new
        if (session == null) {
            session = request.getSession();
        }

        String event = validateEvent(request);

        EventHandlerBase handler = getEventHandler(event);

        try {
            handler.process(session, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            //handler = getEventHandler("UNKNOWN_EVENT");
        }

        
        handler.forward(request, response);
    }
    
    protected String validateEvent(HttpServletRequest request) {
        String event = request.getParameter("event");
        System.out.println("\n\nevent= "+event);
        
        if (event == null || !events.containsKey(event)) {
        //event = Events.UNKNOWN.toString();
            System.out.println("UNKNOWN_EVENT EGINE");
            event="UNKNOWN_EVENT";
            
        }
        
        return event;
     }
    
    protected EventHandlerBase getEventHandler(String e) {
            EventHandlerBase h;
            try {
            h = (EventHandlerBase) events.get(e);
            } catch(Exception exc) {
            h = (EventHandlerBase)events.get("UNKNOWN_EVENT");
            }
            //System.out.println("to h sto getEventHandler: "+ h.getClass().getName());
           
            return h;
    }
    
    
    
  }

   
   
