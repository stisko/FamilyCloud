/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.WallPostDao;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.WallPost;

/**
 *
 * @author costi_000
 */
public class MySqlWallPostDao implements WallPostDao {

    @Override
    public boolean insertPost(WallPost post) {

        try {
            int validation = 0;
            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();

            pst = conn.prepareStatement("INSERT INTO wallpost(director_username,created_by, message, creation_time, file) VALUES(?,?,?,?,?)");
            pst.setString(1, post.getDirector_username());
            pst.setString(2, post.getCreated_by());
            pst.setString(3, post.getMessage());
            //pst.setString(4, user.getLastName());
            //pst.set
            java.util.Date dt = new java.util.Date();

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentTime = sdf.format(dt);
            pst.setString(4, currentTime);

            //pst.setDate(4, new java.sql.Date(post.getCreation_time().getTime()));
            if(post.getFile().length!=0){
            pst.setBinaryStream(5, new ByteArrayInputStream(post.getFile()), post.getFile().length);
            }else{
                pst.setNull(5, java.sql.Types.BLOB);
            }
            validation = pst.executeUpdate();
            conn.close();

            if (validation == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySqlWallPostDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    @Override
    public boolean deletePost(int idWallPost) {
        PreparedStatement pst=null;
        Connection conn = MySqlDaoFactory.createConnection();
        
        int validation;
        
        
        try {
            pst = conn.prepareStatement("DELETE FROM wallpost WHERE idWallPost=?");
            pst.setInt(1, idWallPost);
            
            validation = pst.executeUpdate();
            
            if (validation == 1){
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
    public boolean updatePost(WallPost post, int idWallPost) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WallPost getPost(int idWallPost) {
        int validation = 0;
        PreparedStatement pst = null;
        WallPost post = new WallPost();
        try {

            Connection conn = MySqlDaoFactory.createConnection();

            pst = conn.prepareStatement("SELECT * FROM wallpost WHERE idWallPost=?");
            pst.setInt(1, idWallPost);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                post.setIdWallPost(rs.getInt("idWallPost"));
                post.setCreated_by(rs.getString("created_by"));
                post.setDirector_username(rs.getString("director_username"));
                Timestamp timestamp = rs.getTimestamp("creation_time");
                post.setCreation_time(timestamp);

                //post.setCreation_time(new Date(rs.getDate("creation_time").getTime()));
                post.setMessage(rs.getString("message"));
                Blob blob = rs.getBlob("file");
                byte[] test;
                if (blob != null) {
                    test = blob.getBytes(1, (int) blob.length());
                    String test2 = new String(test);
                } else {
                    test = null;
                }
                post.setFile(test);
                

            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlWallPostDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return post;

    }

    @Override
    public List<WallPost> getFamilyPosts(String username) {
        List<WallPost> familyPosts = new ArrayList<WallPost>();

        try {
            int validation = 0;
            PreparedStatement pst = null;
            Connection conn = MySqlDaoFactory.createConnection();

            pst = conn.prepareStatement("SELECT * FROM wallpost WHERE director_username=? ORDER BY creation_time DESC");
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                WallPost post = new WallPost();
                post.setIdWallPost(rs.getInt("idWallPost"));
                post.setCreated_by(rs.getString("created_by"));
                post.setDirector_username(rs.getString("director_username"));
                Timestamp timestamp = rs.getTimestamp("creation_time");
                post.setCreation_time(timestamp);

                //post.setCreation_time(new Date(rs.getDate("creation_time").getTime()));
                post.setMessage(rs.getString("message"));
                Blob blob = rs.getBlob("file");
                byte[] test;
                if (blob != null) {
                    test = blob.getBytes(1, (int) blob.length());
                    String test2 = new String(test);
                } else {
                    test = null;
                }
                post.setFile(test);
                familyPosts.add(post);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySqlWallPostDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return familyPosts;
    }

}
