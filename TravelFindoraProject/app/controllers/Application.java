package controllers;

import models.*;
import play.mvc.Controller;

import java.util.*;
public class Application extends Controller {

    public static void signIn(){
        render();
    }

    public static void index() {
        List<Findora> findoras = Findora.findAll();
        render(findoras);
    }

    public static void login() {
        render();
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
        System.out.println(name);
        Findora f = Findora.find("byName", name).first();
        System.out.println(f);

        if(f!=null){
            Set<TravelFindora> tfs = f.getTravels();
            List <Travel> ts = new ArrayList<Travel>();
            for(TravelFindora t: tfs){
                ts.add(t.getTravel());
            }
            Set<Integer> userIds = new HashSet<Integer>();
            //   Set<Integer> likersIds = new HashSet<Integer>();
            List<Commentaire> com = new ArrayList<Commentaire>();

            for(Travel t: ts){
                for(TravelUser tu: t.getTravellers())
                    userIds.add(tu.getTraveller().getUserId());
                com.addAll(t.getCommentaires());

                //   	for(TravelLike tl: t.getLikers())
                //   		likersIds.add(tl.getLikerTravel().getUserId());
            }
            int nbUsers = userIds.size();
            //  int likes = likersIds.size();

            Collections.sort(com, new Comparator() {
                public int compare(Object A, Object B) {
                    return ((Commentaire)A).getDateCreation()
                            .compareTo(((Commentaire)B).getDateCreation());
                }
            });

            render(f, nbUsers, com);
        }
        else render();
    }

    public static void searchVal(String query){
        search(query);
    }
}
