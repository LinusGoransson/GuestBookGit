import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import nu.te4.entities.Users;
import nu.te4.sessionbeans.UsersFacade;
import org.mindrot.jbcrypt.BCrypt;

@Named
@SessionScoped
public class UserBean implements Serializable{
    private String username, password;
    private String sessionUser;
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
    
    public static String name;
    public static String isLoggedin;
    public String login(){
        System.out.println("Password: "+password);
        System.out.println("Username: "+username);
        List<Users> users = usersFacade.findWithName(username);
        Users user = users.get(0);
        System.out.println(user.getPassword());
        System.out.println(user.getUserId());
        System.out.println(user.getUsername());
        if(BCrypt.checkpw(password, user.getPassword())){
            name = username;
            isLoggedin = "asd";
            System.out.println("Inloggad");
            System.out.println("sessionUser"+ name);
            return "inner/index.xhtml?faces-redirect=true";
        }else{
            System.out.println("Fungerar inte");
             return "login";
        }
       
    }
    public String logout(){
        name = fbNameStatic;
        isLoggedin=null;
        return "login.xhtml?faces-redirect=true";
    }
    public static String fbNameStatic;
    public String getfbname(){
	   String fbName = FacesContext.getCurrentInstance().
		getExternalContext().getRequestParameterMap().get("hiddenField");
           System.err.println("FbName: "+fbName);
           if(fbName.isEmpty()){
               return "login";
           }else{
               fbNameStatic = fbName;
               isLoggedin = "asd";
               return "inner/index.xhtml?faces-redirect=true";
           }
           
	}
   
}
