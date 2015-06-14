package controllers;

import models.*;
import play.mvc.Controller;

import java.util.*;
public class Application extends Controller {

    public static void signIn(){
        render();
    }

    public static void test() {
        render();
    }

    public static void index() {
        User user = User.find("byEmail", Security.connected()).first();
        render(user);
    }

    public static void explore() {
        User user = User.find("byEmail", Security.connected()).first();
        List<Findora> findoras = Findora.findAll();
        render(user, findoras);
    }

    public static void authenticate(String username, String password) {
        boolean auth;
        auth = (ControllerUser.connect(username, password));
        render();
    }

    public static void register(String firstname, String lastname, String email, String password) {
        User newUser = new User();
        newUser.setFirstname(firstname);
        newUser.setLastname(lastname);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.save();

        session.put("email",email);
        Application.index();
    }

    public static void search(String name){
        User user = User.find("byEmail", Security.connected()).first();
        Findora f = Findora.find("byName", name).first();

        if(f!=null){
            Set<TravelFindora> tfs = f.getTravels();
            List <Travel> ts = new ArrayList<Travel>();
            for(TravelFindora t: tfs){
                ts.add(t.getTravel());
            }
            Set<Integer> userIds = new HashSet<Integer>();
            List<Commentaire> com = new ArrayList<Commentaire>();

            for(Travel t: ts){
                for(TravelUser tu: t.getTravellers())
                    userIds.add(tu.getTraveller().getUserId());
                com.addAll(t.getCommentaires());

            }
            int nbUsers = userIds.size();
            Collections.sort(com, new Comparator() {
                public int compare(Object A, Object B) {
                    return ((Commentaire)A).getDateCreation()
                            .compareTo(((Commentaire)B).getDateCreation());
                }
            });
            
            
            List<Content> con = Content.find("findora like ?  order by dateCreation desc", f).fetch();
            render(f, nbUsers, con, user);
        }
        else render(user);
    }

    public static void searchVal(String query){
        search(query);
    }
}
