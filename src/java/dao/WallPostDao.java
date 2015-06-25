/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.WallPost;

/**
 *
 * @author costi_000
 */
public interface WallPostDao {
    
    
    public boolean insertPost(WallPost post);

    
        
    public boolean deletePost(int idWallPost);

    public boolean updatePost(WallPost post, int idWallPost);

    public WallPost getPost(int idWallPost);
    
    public List<WallPost> getFamilyPosts(String username);
    
}
