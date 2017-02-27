import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import nu.te4.entities.Posts;
import nu.te4.entities.Users;
import nu.te4.sessionbeans.PostsFacade;
@Named
@SessionScoped
public class PostBean implements Serializable{
    
    //variabler för värdena som ska in i databasen.
    private String titel, meddelande, namn, email;
    //sparar dagens datum och tid
    String datum = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getMeddelande() {
        return meddelande;
    }

    public void setMeddelande(String meddelande) {
        this.meddelande = meddelande;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @EJB
    PostsFacade postsFacade;
    
    //Lista som retunerar alla inlägg från databasen.
    public List<Posts> getPosts(){
        
        return postsFacade.findAll();
        
    }
    
    public String savePost(){
        Posts posts = new Posts(null,titel, meddelande, datum, namn, email);
        postsFacade.create(posts);
        return "index";
    }
   
}
