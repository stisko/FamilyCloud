
package dao;

import java.util.List;
import model.User;
    
    
    
    public interface UserDao {

    public boolean insertUser(User user);

    public boolean insertUser(User user, String director_username);
        
    public boolean deleteUser(String username);

    public boolean updateUser(User user, String username);

    public User getUser(String username);
    
    public boolean isDuplicateMail(String email);
    public boolean isDuplicateUsername(String username);
    
    public User getFamilyDirector(String username);
    
    public List<User> getFamilyMembers(String username);
    
    public List<User> getActiveFamilyMembersExceptMe(String username);
    
    public List<User> getFamilyMembersWithDirector(String username);
    
    
}

