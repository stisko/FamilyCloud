/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import model.ToDoListItem;
import dao.ToDoListItemDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelBO.ToDoListItemBO;
import org.apache.tomcat.dbcp.pool2.PooledObjectState;
/**
 *
 * @author costi_000
 */
public class MySqlToDoListItemDao implements ToDoListItemDao{

    @Override
    public boolean insertItem(ToDoListItem item) {
        PreparedStatement pst=null;
        Connection conn = MySqlDaoFactory.createConnection();
        
        int validation;
        
        
        try {
            pst = conn.prepareStatement("INSERT INTO todo_list(itemid,director_username, title, assigned_to,created_by, due_date, status) VALUES(?,?,?,?,?,?,?)");
            pst.setInt(1, item.getItemID());
            pst.setString(2, item.getDirector_username());
            pst.setString(3, item.getTitle());
            pst.setString(4, item.getAssignedTo());
            pst.setString(5, item.getCreatedBy());
            
            
                pst.setDate(6, new java.sql.Date(item.getDueDate().getTime()));
            
            
            //pst.setDate(7, new java.sql.Date(item.getCompletedDate().getTime()));
            pst.setString(7, item.getStatus());
            
            
            validation = pst.executeUpdate();
            
            if (validation == 1) {
                
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public boolean deleteItem(int itemID) {
        PreparedStatement pst=null;
        Connection conn = MySqlDaoFactory.createConnection();
        
        int validation;
        
        
        try {
            pst = conn.prepareStatement("DELETE FROM todo_list WHERE itemid=?");
            pst.setInt(1, itemID);
            
            validation = pst.executeUpdate();
            
            if (validation == 1) {
                
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public boolean updateItem(ToDoListItem item) {
        PreparedStatement pst=null;
        Connection conn = MySqlDaoFactory.createConnection();
        
        int validation;
        
        
        try {
            pst = conn.prepareStatement("UPDATE todo_list SET title=? , assigned_to=? ,created_by=? , due_date=? , completed_date=? , status=? WHERE itemid=?");
            
            pst.setString(1, item.getTitle());
            pst.setString(2, item.getAssignedTo());
            pst.setString(3, item.getCreatedBy());
            pst.setDate(4, new java.sql.Date(item.getDueDate().getTime()));
            if(item.getCompletedDate()!=null){
                pst.setDate(5, new java.sql.Date(item.getCompletedDate().getTime()));
            }else{
                pst.setNull(5, java.sql.Types.DATE);
            }
            pst.setString(6, item.getStatus());
            pst.setInt(7, item.getItemID());
            
            validation = pst.executeUpdate();
            
            if (validation == 1) {
                
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public ToDoListItem getItem(int itemID) {
        PreparedStatement pst = null;
        Connection conn = MySqlDaoFactory.createConnection();
        
        ToDoListItem item= new ToDoListItem();
        try {
            pst = conn.prepareStatement("SELECT * FROM todo_list WHERE itemid = ?");
            pst.setInt(1, itemID);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                item.setItemID(rs.getInt("itemid"));
                item.setDirector_username(rs.getString("director_username"));
                item.setTitle(rs.getString("title"));
                item.setAssignedTo(rs.getString("assigned_to"));
                item.setCreatedBy(rs.getString("created_by"));
                item.setDueDate(new Date(rs.getDate("due_date").getTime()));
                //item.setCompletedDate(new Date(rs.getDate("completed_date").getTime()));
                item.setStatus(rs.getString("status"));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return item;
    }

    @Override
    public List<ToDoListItem> getFamilyToDoList(String username) {
        PreparedStatement pst = null;
        Connection conn = MySqlDaoFactory.createConnection();
        List<ToDoListItem> item_list= new ArrayList<ToDoListItem>();
        
        try {
            pst = conn.prepareStatement("SELECT * FROM todo_list WHERE director_username=?");
            pst.setString(1, username);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                ToDoListItem item= new ToDoListItem();
                item.setTitle(rs.getString("title"));
                item.setStatus(rs.getString("status"));
                item.setDirector_username(rs.getString("director_username"));
                item.setAssignedTo(rs.getString("assigned_to"));
                item.setItemID(rs.getInt("itemid"));
                item.setCreatedBy(rs.getString("created_by"));
                item.setDueDate(new Date(rs.getDate("due_date").getTime()));
                if(rs.getDate("completed_date")==null){
                    
                }else{
                item.setCompletedDate(new Date(rs.getDate("completed_date").getTime()));
                }
                
                
                item_list.add(item);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return item_list;
    }

    @Override
    public List<ToDoListItem> getSortedFamilyToDoList(String username, String sortvalue,String asc_desc_tag) {
         PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();
            List<ToDoListItem> item_list= new ArrayList<ToDoListItem>();
        
        try {
           
           if( asc_desc_tag.equals("false")) {
               
               String qtemp="SELECT * FROM todo_list WHERE director_username=? order by"+" "+sortvalue+" "+"DESC";
               
               pst = conn.prepareStatement(qtemp);
           
           }
           
           else{
               String qtemp="SELECT * FROM todo_list WHERE director_username=? order by"+" "+sortvalue+" "+"ASC";
           
           pst = conn.prepareStatement(qtemp);
           
           }
            
            
            pst.setString(1, username);
           // pst.setString(2, sortvalue);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                ToDoListItem item= new ToDoListItem();
                item.setTitle(rs.getString("title"));
                item.setStatus(rs.getString("status"));
                item.setDirector_username(rs.getString("director_username"));
                item.setAssignedTo(rs.getString("assigned_to"));
                item.setItemID(rs.getInt("itemid"));
                item.setCreatedBy(rs.getString("created_by"));
                item.setDueDate(new Date(rs.getDate("due_date").getTime()));
                if(rs.getDate("completed_date")==null){
                    
                }else{
                    item.setCompletedDate(new Date(rs.getDate("completed_date").getTime()));
                }
                
                
                item_list.add(item);
                
                
             
                
                
                
                
                
                
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
         return item_list;

    
}

    
    
}
