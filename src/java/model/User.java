
package model;

import java.util.Arrays;
import java.util.Date;
import modelBO.UserBO;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONObject;


public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String email;
    private String town;
    private String director;
    private byte[] picture;
    
    public User(){
        
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public UserBO toUserBO() {
        UserBO userBO = new UserBO();

        userBO.setUsername(username);
        userBO.setPassword(password);
        userBO.setFirstName(firstName);
        userBO.setLastName(lastName);
        userBO.setBirthdate(birthdate);
        userBO.setEmail(email);
        userBO.setTown(town);
        userBO.setDirector(director);
        userBO.setPicture(picture);
        return userBO;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the birthdate
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * @param town the town to set
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * @return the director
     */
    public String getDirector() {
        return director;
    }

    /**
     * @param director the director to set
     */
    public void setDirector(String director) {
        this.director = director;
    }
    
    
    public JSONObject getUserJSON(String username){
        JSONObject obj= new JSONObject();
        obj.put("username", username);
        obj.put("firstName", firstName);
        obj.put("lastName", lastName);
        obj.put("email", email);
        obj.put("birthdate", birthdate);
        obj.put("town",town);
        obj.put("director",director);
        String url = "data:image/jpg,base64;" + Base64.encodeBase64String(picture);
        obj.put("picture", url);
        
        
        return obj;
}

    /**
     * @return the picture
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
    
    
}
