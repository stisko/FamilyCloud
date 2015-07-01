/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.ShoppingListDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ShoppingListItem;

/**
 *
 * @author GVra
 */
public class MySqlShoppingListDao implements ShoppingListDao {

    @Override
    public boolean insertShoppingItem(ShoppingListItem shl) {
        
        PreparedStatement pst=null;
            Connection conn = MySqlDaoFactory.createConnection();
        
        
        try {
            
            
            int validation;
            
            
            pst = conn.prepareStatement("INSERT INTO shopping_list(director_username, title,quantity,price,status,assigned_to,created_by) VALUES(?,?,?,?,?,?,?)");
            //pst.setInt(1, shl.getShopitemID());
            pst.setString(1, shl.getDirector_username());
            pst.setString(2, shl.getShoptitle());
            pst.setInt(3,shl.getQuantity());
            
              
                pst.setInt(4,shl.getQuantity() * shl.getPrice());
            
            
            
            pst.setString(5,shl.getShopstatus());
            pst.setString(6,shl.getAssigned_to());
            
            pst.setString(7,shl.getCreated_by());
            
            
                validation = pst.executeUpdate();
           
            if (validation == 1) {
                
                return true;
            } else {
                return false;
            }
            
            
           
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MySqlShoppingListDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
            
        }finally{
        
        
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySqlShoppingListDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
         
        
        
    }

    @Override
    public List<ShoppingListItem> getFamilyShoppingList(String username) {
        PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();
            
            List<ShoppingListItem> item_list= new ArrayList<ShoppingListItem>();
            
        try {
            
            
            
            
            pst = conn.prepareStatement("SELECT * FROM shopping_list WHERE director_username=?");
            
            pst.setString(1, username);
            
            ResultSet rs = pst.executeQuery();
            
            
            while(rs.next()){
                
                ShoppingListItem ss=new ShoppingListItem();
                
                
                ss.setAssigned_to(rs.getString("assigned_to"));
                ss.setDirector_username(rs.getString("director_username"));
               
                ss.setQuantity(rs.getInt("quantity"));
                 ss.setPrice(rs.getInt("price"));
                ss.setShopitemID(rs.getInt("shopitemid"));
                ss.setShopstatus(rs.getString("status"));
                ss.setShoptitle(rs.getString("title"));
                ss.setCreated_by(rs.getString("created_by"));
                
                item_list.add(ss);
                
                
                
                
            }
            
            
            
            
            return item_list;
        } catch (SQLException ex) {
            Logger.getLogger(MySqlShoppingListDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySqlShoppingListDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        }
        
        return item_list;
    }

    @Override
    public ShoppingListItem getshopItem(int item) {
        PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();
             
            ShoppingListItem sli=new ShoppingListItem();
        try {
            
            
            pst = conn.prepareStatement("SELECT * FROM shopping_list WHERE shopitemid=?");
            
            pst.setInt(1, item);
            
           
            ResultSet rs = pst.executeQuery();
            
            
            while(rs.next()){
            sli.setAssigned_to(rs.getString("assigned_to"));
            sli.setDirector_username(rs.getString("director_username"));
            
            sli.setQuantity(rs.getInt("quantity"));
            sli.setPrice(rs.getInt("price"));
            sli.setShopitemID(rs.getInt("shopitemid"));
            sli.setShopstatus(rs.getString("status"));
            sli.setShoptitle(rs.getString("title"));
            sli.setCreated_by(rs.getString("created_by"));;
            
            }
            
            
           
        } catch (SQLException ex) {
            Logger.getLogger(MySqlShoppingListDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySqlShoppingListDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
            
            
        return sli;
    }

    @Override
    public boolean updateItem(ShoppingListItem sli) {
        PreparedStatement pst=null;
            Connection conn = MySqlDaoFactory.createConnection();
            
            int validation;
            
         //   ShoppingListItem sli=new ShoppingListItem();
        try {
           
            
            
            pst = conn.prepareStatement("UPDATE shopping_list SET title=? ,director_username=?, assigned_to=? ,created_by=? , price=? , quantity=? , status=? WHERE shopitemid=?");
            
            pst.setString(1,sli.getShoptitle());
            pst.setString(2,sli.getDirector_username() );
            pst.setString(3,sli.getAssigned_to());
            pst.setString(4,sli.getCreated_by());
            pst.setInt(5,sli.getPrice());
            pst.setInt(6,sli.getQuantity());
            pst.setString(7,sli.getShopstatus());
            
            pst.setInt(8, sli.getShopitemID());
            
            
            
            
            
            
            validation = pst.executeUpdate();
            
            
            if (validation == 1) {
                
                return true;
            } else {
                return false;
            }
            
            
        } catch (SQLException ex) {
             return false;
            
        }finally{
        
        
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySqlShoppingListDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        }
       
    }

    @Override
    public boolean deleteItem(int item) {
        PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();
            
            
            int validation;
        
        try {
            
            
            pst = conn.prepareStatement("DELETE FROM shopping_list WHERE shopitemid=?");
            pst.setInt(1, item);
            
            
            validation = pst.executeUpdate();
            
            if (validation == 1) {
                
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            
            return false;
        }finally{
        
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySqlShoppingListDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        
        
        
    }

    @Override
    public List<ShoppingListItem> getSortedFamilyShoppingList(String username, String sortvalue, String asc_desc_tag) {
       
        
        
        
        PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();
            List<ShoppingListItem> item_list= new ArrayList<ShoppingListItem>();
        
        try {
           
           if( asc_desc_tag.equals("false")) {
               
               String qtemp="SELECT * FROM shopping_list WHERE director_username=? order by"+" "+sortvalue+" "+"DESC";
               
               pst = conn.prepareStatement(qtemp);
           
           }
           
           else{
               String qtemp="SELECT * FROM shopping_list WHERE director_username=? order by"+" "+sortvalue+" "+"ASC";
           
           pst = conn.prepareStatement(qtemp);
           
           }
            
            
            pst.setString(1, username);
           // pst.setString(2, sortvalue);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                ShoppingListItem item= new ShoppingListItem();
                
                item.setAssigned_to(rs.getString("assigned_to"));
            item.setDirector_username(rs.getString("director_username"));
            
            item.setQuantity(rs.getInt("quantity"));
            item.setPrice(rs.getInt("price"));
            item.setShopitemID(rs.getInt("shopitemid"));
            item.setShopstatus(rs.getString("status"));
            item.setShoptitle(rs.getString("title"));
            item.setCreated_by(rs.getString("created_by"));
                
                
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
