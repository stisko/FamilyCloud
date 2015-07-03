package dao.impl;

import dao.UserDao;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.dbcp.pool2.PooledObjectState;
import java.lang.String;
import javax.imageio.ImageIO;

public class MySqlUserDao implements UserDao {

    @Override
    public User getUser(String username) {
        PreparedStatement pst = null;
        Connection conn = MySqlDaoFactory.createConnection();

        User user = new User();

        try {
            pst = conn.prepareStatement("SELECT * FROM USER WHERE username = ?");
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setBirthdate(new Date(rs.getDate("birthdate").getTime()));
                user.setEmail(rs.getString("email"));
                user.setTown(rs.getString("town"));
                user.setDirector(rs.getString("director"));
//                user.setPicture(rs.get);
                Blob blob = rs.getBlob("picture");
                byte[] test;

                if (blob != null) {
                    test = blob.getBytes(1, (int) blob.length());
                    String test2 = new String(test);
                } else {
                    test = null;
                }
                user.setPicture(test);
                //user.setPicture(blob.getBytes(1,(int) blob.length()));
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

        return user;
    }

    @Override
    public boolean insertUser(User user) {
        int validation = 0;
        PreparedStatement pst = null;
        Connection conn = MySqlDaoFactory.createConnection();

        try {
            pst = conn.prepareStatement("INSERT INTO user(username,password,firstname,lastname,birthdate,email,town,director,picture) VALUES(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getFirstName());
            pst.setString(4, user.getLastName());
            pst.setDate(5, new java.sql.Date(user.getBirthdate().getTime()));
            pst.setString(6, user.getEmail());
            pst.setString(7, user.getTown());
            pst.setString(8, user.getDirector());
            byte[] picture= extractBytes();
            pst.setBinaryStream(9, new ByteArrayInputStream(picture), picture.length);

            validation = pst.executeUpdate();

            if (validation == 1) {

                pst = conn.prepareStatement("INSERT INTO directs_users(director_username, familymember_username) VALUES(?,?)");
                if (user.getDirector().equals("Y")) {
                    pst.setString(1, user.getUsername());
                } else {
                    // pst.setString(1, );
                }
                pst.setString(2, user.getUsername());

                validation = pst.executeUpdate();
                if (validation == 1) {
                    return true;
                } else {
                    return false;
                }
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
    public boolean insertUser(User user, String director_username) {
        int validation = 0;
        PreparedStatement pst = null;
        Connection conn = MySqlDaoFactory.createConnection();

        try {
            pst = conn.prepareStatement("INSERT INTO user(username,password,firstname,lastname,birthdate,email,town,director,picture) VALUES(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getFirstName());
            pst.setString(4, user.getLastName());
            pst.setDate(5, new java.sql.Date(user.getBirthdate().getTime()));
            pst.setString(6, user.getEmail());
            pst.setString(7, user.getTown());
            pst.setString(8, user.getDirector());
            byte[] picture= extractBytes();
            pst.setBinaryStream(9, new ByteArrayInputStream(picture), picture.length);

            validation = pst.executeUpdate();

            if (validation == 1) {

                pst = conn.prepareStatement("INSERT INTO directs_users(director_username, familymember_username) VALUES(?,?)");
                if (user.getDirector().equals("Y")) {
                    pst.setString(1, user.getUsername());
                } else {
                    pst.setString(1, director_username);
                }
                pst.setString(2, user.getUsername());

                validation = pst.executeUpdate();
                if (validation == 1) {
                    return true;
                } else {
                    return false;
                }
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
    public boolean deleteUser(String username) {
        int validation = 0;
        PreparedStatement pst = null;
        Connection conn = MySqlDaoFactory.createConnection();
        try {

            pst = conn.prepareStatement("DELETE FROM user WHERE username=?");
            pst.setString(1, username);

            validation = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (validation == 1) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean updateUser(User user, String username) {
        int validation = 0;
        PreparedStatement pst = null;
        Connection conn = MySqlDaoFactory.createConnection();
        try {

            if (user.getPicture().length != 0) {

                pst = conn.prepareStatement("UPDATE user SET firstname=?, lastname=?, email=?, birthdate=?, town=?,picture=? WHERE username=?");

                pst.setString(1, user.getFirstName());
                pst.setString(2, user.getLastName());
                pst.setString(3, user.getEmail());
                pst.setDate(4, new java.sql.Date(user.getBirthdate().getTime()));
                pst.setString(5, user.getTown());

                pst.setBinaryStream(6, new ByteArrayInputStream(user.getPicture()), user.getPicture().length);
                pst.setString(7, username);
            } else {

                pst = conn.prepareStatement("UPDATE user SET firstname=?, lastname=?, email=?, birthdate=?, town=? WHERE username=?");

                pst.setString(1, user.getFirstName());
                pst.setString(2, user.getLastName());
                pst.setString(3, user.getEmail());
                pst.setDate(4, new java.sql.Date(user.getBirthdate().getTime()));
                pst.setString(5, user.getTown());

             //   pst.setBinaryStream(6, new ByteArrayInputStream(user.getPicture()), user.getPicture().length);
                pst.setString(6, username);

            }
//pst = conn.prepareStatement("UPDATE user SET firstname=?, lastname=?, email=?, birthdate=?, town=?,picture=? WHERE username=?");

//            if(user.getPicture().length!=0){
//               
//            }else{
////                byte[] picture= extractBytes();
////                pst.setBinaryStream(6, new ByteArrayInputStream(picture), picture.length);
//            }
            validation = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (validation == 1) {
            return true;
        } else {
            return false;
        }

    }

    public boolean changePassword(User user) {
        int validation = 0;
        PreparedStatement pst = null;
        Connection conn = MySqlDaoFactory.createConnection();
        try {

            pst = conn.prepareStatement("UPDATE user SET password=? WHERE username=?");
            pst.setString(1, user.getPassword());
            pst.setString(2, user.getUsername());

            validation = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (validation == 1) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean isDuplicateMail(String email) {
        PreparedStatement pst = null;
        Connection conn = MySqlDaoFactory.createConnection();

        User user = new User();

        try {
            pst = conn.prepareStatement("SELECT * FROM USER WHERE email = ?");
            pst.setString(1, email);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                user.setEmail(rs.getString("email"));
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
        if (user.getEmail() == null) {
            return false;
        } else {
            return true;
        }

    }

    public boolean isDuplicateUsername(String username) {
        PreparedStatement pst = null;
        Connection conn = MySqlDaoFactory.createConnection();

        User user = new User();

        try {
            pst = conn.prepareStatement("SELECT * FROM USER WHERE username = ?");
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                user.setUsername(rs.getString("username"));
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
        if (user.getUsername() == null) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public User getFamilyDirector(String username) {
        PreparedStatement pst = null;
        Connection conn = MySqlDaoFactory.createConnection();

        User user = new User();

        try {
            pst = conn.prepareStatement("SELECT * FROM USER WHERE username= (SELECT director_username FROM directs_users WHERE familymember_username = ?)");
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                user.setUsername(rs.getString("username"));
            }

            user = getUser(user.getUsername());

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public List<User> getFamilyMembers(String username) {
        PreparedStatement pst = null;
        Connection conn = MySqlDaoFactory.createConnection();
        List<String> fmembers_username_list = new ArrayList<String>();
        List<User> fmembers_list = new ArrayList<User>();

        User director = getFamilyDirector(username);
        try {
            pst = conn.prepareStatement("SELECT familymember_username FROM directs_users WHERE (director_username=? and familymember_username!=?)");
            pst.setString(1, director.getUsername());
            pst.setString(2, director.getUsername());

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                fmembers_username_list.add(rs.getString("familymember_username"));
            }

            for (int i = 0; i < fmembers_username_list.size(); i++) {
                fmembers_list.add(getUser(fmembers_username_list.get(i)));
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

        return fmembers_list;
    }

    @Override
    public List<User> getFamilyMembersWithDirector(String username) {
        PreparedStatement pst = null;
        Connection conn = MySqlDaoFactory.createConnection();
        List<String> fmembers_username_list = new ArrayList<String>();
        List<User> fmembers_list = new ArrayList<User>();

        User director = getFamilyDirector(username);
        try {
            pst = conn.prepareStatement("SELECT familymember_username FROM directs_users WHERE (director_username=?)");
            pst.setString(1, director.getUsername());

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                fmembers_username_list.add(rs.getString("familymember_username"));
            }

            for (int i = 0; i < fmembers_username_list.size(); i++) {
                fmembers_list.add(getUser(fmembers_username_list.get(i)));
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

        return fmembers_list;
    }

    @Override
    public List<User> getActiveFamilyMembersExceptMe(String username) {
        PreparedStatement pst = null;
        Connection conn = MySqlDaoFactory.createConnection();
        List<String> fmembers_username_list = new ArrayList<String>();
        List<User> fmembers_list = new ArrayList<User>();

        User director = getFamilyDirector(username);
        try {
            pst = conn.prepareStatement("SELECT familymember_username FROM directs_users \n"
                    + "INNER JOIN user us1 on us1.username= directs_users.familymember_username and us1.director!='I'\n"
                    + "WHERE (director_username=? and familymember_username!=?) ");
            pst.setString(1, director.getUsername());
            pst.setString(2, username);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                fmembers_username_list.add(rs.getString("familymember_username"));
            }

            for (int i = 0; i < fmembers_username_list.size(); i++) {
                fmembers_list.add(getUser(fmembers_username_list.get(i)));
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

        return fmembers_list;
    }

    @Override
    public List<String> getforgottendetails(String email) {
        PreparedStatement pst = null;
        Connection conn = MySqlDaoFactory.createConnection();
        List<String> forg_list = new ArrayList<String>();

        try {

            pst = conn.prepareStatement("SELECT * FROM user WHERE (email=?)");

            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                forg_list.add(rs.getString("username"));
                forg_list.add(rs.getString("password"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySqlUserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                conn.close();
            } catch (SQLException ex) {
                return null;
            }

        }
        return forg_list;
    }

    public byte[] extractBytes() {
        try {
            // open image
            File imgPath = new File("C:/Users/costi_000/Documents/NetBeansProjects/FamilyCloud_start/web/img/default.png");
            BufferedImage bufferedImage = ImageIO.read(imgPath);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            return baos.toByteArray();
            // get DataBufferBytes from Raster
//            WritableRaster raster = bufferedImage .getRaster();
//            DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
//            
//            return ( data.getData() );
        } catch (IOException ex) {
            Logger.getLogger(MySqlUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
