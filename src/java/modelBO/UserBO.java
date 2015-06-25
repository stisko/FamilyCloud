
package modelBO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.User;


public class UserBO {
    

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String email;
    private String town;
    private String director;
    private byte[] picture;

   
    public UserBO() {
        
        username = null;
        password = null;
        firstName = null;
        lastName = null;
        birthdate = null;
        email = null;
        town = null;
        director = null;
        picture=null;
    }

    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public UserBO toUserBO(User user) {

        UserBO userBO = new UserBO();

        userBO.setUsername(user.getUsername());
        userBO.setPassword(user.getPassword());
        userBO.setFirstName(user.getFirstName());
        userBO.setLastName(user.getLastName());
        userBO.setBirthdate(user.getBirthdate());
        userBO.setEmail(user.getEmail());
        userBO.setTown(user.getTown());
        userBO.setDirector(director);
        userBO.setPicture(user.getPicture());
        
        
        return userBO;
    }
    
    public List<UserBO> family_toUserBo(List<User> family){
        UserBO userBO = new UserBO();
        List<UserBO> family_list= new ArrayList<UserBO>();
        
        for(int i=0;i<family.size();i++){
            family_list.add(toUserBO(family.get(i)));
        }
        
        return family_list;
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
