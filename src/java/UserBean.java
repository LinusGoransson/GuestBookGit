
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import nu.te4.entities.Posts;
import nu.te4.entities.Users;
import nu.te4.sessionbeans.UsersFacade;
import org.mindrot.jbcrypt.BCrypt;

@Named
@SessionScoped
public class UserBean implements Serializable{
    private String username, password;
    
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
    @EJB
    UsersFacade usersFacade;
    public String register(){
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        Users users = new Users(null,username,hashedPassword);
        usersFacade.create(users);
        return "index";
    }
    
    public List<Users> getUsers(){
           return usersFacade.findWithName(username);   
    }
    
   
}
